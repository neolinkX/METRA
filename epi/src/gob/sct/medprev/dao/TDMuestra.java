package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de Muestra DAO
 * </p>
 * <p>
 * Description: DAO para las muestras
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a & Marco A. Gonz�lez Paz
 * @version 1.0
 */

public class TDMuestra extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMuestra() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra vMuestra;
			vMuestra = (TVMuestra) (Obj);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMuestra," + "iCvePbaRapida,"
					+ "iCveUniMed," + "iCveEnvio," + "iCveProceso,"
					+ "iCveMotivo," + "dtRecoleccion," + "iCveTipoRecep,"
					+ "lIntegraLote," + "iCveSituacion," + "lTemperaC,"
					+ "lAlteracion," + "lBajoObserva," + "iCveEmpresa"
					+ " from TOXMuestra ";

			if (vMuestra.getCFiltrar() != null
					&& vMuestra.getCFiltrar().compareToIgnoreCase("") != 0) {
				cSQL = cSQL + " where " + vMuestra.getCFiltrar().toString();
			}
			if (vMuestra.getCOrdenar() != null
					&& vMuestra.getCOrdenar().compareToIgnoreCase("") != 0) {
				cSQL = cSQL + vMuestra.getCOrdenar();
			} else {
				cSQL = cSQL + " order by iAnio, iCveMuestra ";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMuestra = new TVMuestra();
				vMuestra.setIAnio(rset.getInt(1));
				vMuestra.setICveMuestra(rset.getInt(2));
				vMuestra.setICvePbaRapida(rset.getInt(3));
				vMuestra.setICveUniMed(rset.getInt(4));
				vMuestra.setICveEnvio(rset.getInt(5));
				vMuestra.setICveProceso(rset.getInt(6));
				vMuestra.setICveMotivo(rset.getInt(7));
				vMuestra.setDtRecoleccion(rset.getDate(8));
				vMuestra.setICveTipoRecep(rset.getInt(9));
				vMuestra.setLIntegraLote(rset.getInt(10));
				vMuestra.setICveSituacion(rset.getInt(11));
				vMuestra.setLTemperaC(rset.getInt(12));
				vMuestra.setLAlteracion(rset.getInt(13));
				vMuestra.setLBajoObserva(rset.getInt(14));
				vMuestra.setICveEmpresa(rset.getInt(15));
				vcMuestra.addElement(vMuestra);
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
			return vcMuestra;
		}
	}

	/**
	 * Metodo FindByDsc
	 */
	public Vector FindByDsc(Object obj, String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra vMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveMuestra," + "iCvePbaRapida,"
					+ "TOXMuestra.iCveUniMed," + "iCveEnvio,"
					+ "TOXMuestra.iCveProceso," + "TOXMuestra.iCveMotivo,"
					+ "dtRecoleccion," + "iCveTipoRecep," + "iCveMotRecep,"
					+ "lIntegraLote," + "iCveSituacion," + "lTemperaC,"
					+ "lAlteracion," + "lBajoObserva,"
					+ "TOXMuestra.iCveEmpresa," + "GRLUniMed.cDscUniMed,"
					+ "GRLProceso.cDscProceso," + "GRLMotivo.cDscMotivo,"
					+ "SEGUsuario.cNombre," + "SEGUsuario.cApPaterno,"
					+ "SEGUsuario.cApMaterno," + "GRLEmpresas.cDscEmpresa,"
					+ "TOXMuestra.iCvePersonal," + "dtCaptura,"
					+ "cObsMuestra," + "TOXMuestra.iCveMdoTrans, "
					+ "GRLMdoTrans.cDscMdoTrans," + "PERDatos.cNombre,"
					+ "PERDatos.cApPaterno," + "PERDatos.cApMaterno, "
					+ "GRLModulo.cDscModulo, " + "TOXMuestra.iEdad "
					+ " from TOXMuestra ";
			cSQL = cSQL + " inner join GRLUnimed ON "
					+ " GrlUniMed.iCveUniMed = TOXMuestra.iCveUniMed ";

			cSQL = cSQL + " inner join GRLModulo ON "
					+ " GRLModulo.iCveUniMed = TOXMuestra.iCveUniMed "
					+ " and GRLModulo.iCveModulo = TOXMuestra.iCveModulo ";

			cSQL = cSQL + " inner join GRLProceso ON "
					+ " GRLProceso.iCveProceso = TOXMuestra.iCveProceso ";
			cSQL = cSQL + " inner join GRLMotivo ON " +
			// " GRLMotivo.iCveProceso = ToxPbaRapida.iCveProceso " +
					" GRLMotivo.iCveMotivo = TOXMuestra.iCveMotivo ";
			cSQL = cSQL + " inner join SEGUsuario ON "
					+ " SEGUsuario.iCveUsuario = TOXMuestra.iCveUsuRecolec ";
			cSQL = cSQL + " inner join GRLEmpresas ON " +
			// " GRLMotivo.iCveProceso = ToxPbaRapida.iCveProceso " +
					" GRLEmpresas.iCveEmpresa = TOXMuestra.iCveEmpresa ";
			cSQL = cSQL + " inner join GRLMdoTrans ON "
					+ " GRLMdoTrans.iCveMdoTrans = TOXMuestra.iCveMdoTrans ";

			cSQL = cSQL + " inner join PERDatos ON "
					+ " PERDatos.iCvePersonal = TOXMuestra.iCvePersonal ";

			if (cWhere.compareTo("true") == 0) {
				cSQL = cSQL + " where TOXMuestra.iAnio = ? "
						+ " and TOXMuestra.iCveMuestra = ? ";
			} else {
				cSQL = cSQL + " where TOXMuestra.iAnio = ? "
						+ " and TOXMuestra.iCveUniMed = ? ";

				if (cWhere != null && cWhere.compareTo("") != 0) {
					cSQL = cSQL + " and " + cWhere + " ";

				}
			}

			cSQL = cSQL + "order by iAnio, iCveMuestra";
			// cSQL = cSQL + "order by iAnio, TOXMuestra.iOrden";
			pstmt = conn.prepareStatement(cSQL);

			if (cWhere.compareTo("true") == 0) {
				TVMuestra vMuestraF = (TVMuestra) obj;
				pstmt.setInt(1, vMuestraF.getIAnio());
				pstmt.setInt(2, vMuestraF.getICveMuestra());
			} else {
				TVMuestra vMuestraF = (TVMuestra) obj;
				pstmt.setInt(1, vMuestraF.getIAnio());
				pstmt.setInt(2, vMuestraF.getICveUniMed());
			}

			String cFecha = "";
			TFechas dtFecha = new TFechas();

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMuestra = new TVMuestra();
				vMuestra.setIAnio(rset.getInt(1));
				vMuestra.setICveMuestra(rset.getInt(2));
				vMuestra.setICvePbaRapida(rset.getInt(3));
				vMuestra.setICveUniMed(rset.getInt(4));
				vMuestra.setICveEnvio(rset.getInt(5));
				vMuestra.setICveProceso(rset.getInt(6));
				vMuestra.setICveMotivo(rset.getInt(7));
				vMuestra.setDtRecoleccion(rset.getDate(8));
				vMuestra.setICveTipoRecep(rset.getInt(9));
				vMuestra.setICveMotRecep(rset.getInt(10));
				vMuestra.setLIntegraLote(rset.getInt(11));
				vMuestra.setICveSituacion(rset.getInt(12));
				vMuestra.setLTemperaC(rset.getInt(13));
				vMuestra.setLAlteracion(rset.getInt(14));
				vMuestra.setLBajoObserva(rset.getInt(15));
				vMuestra.setICveEmpresa(rset.getInt(16));
				vMuestra.setCDscUM(rset.getString(17));
				vMuestra.setCDscProceso(rset.getString(18));
				vMuestra.setCDscMotivo(rset.getString(19));
				vMuestra.setCDscUsuRecolec(rset.getString(20) + " "
						+ rset.getString(21) + " " + rset.getString(22));
				vMuestra.setCDscEmpresa(rset.getString(23));
				vMuestra.setICvePersonal(rset.getInt(24));
				vMuestra.setDtCaptura(rset.getDate(25));
				vMuestra.setCObsMuestra(rset.getString(26));
				vMuestra.setICveMdoTrans(rset.getInt(27));
				vMuestra.setCDscMdoTrans(rset.getString(28));
				vMuestra.setCNombreCompleto(rset.getString(29) + " "
						+ rset.getString(30) + " " + rset.getString(31));
				vMuestra.setCDscModulo(rset.getString(32));
				vMuestra.setIEdad(rset.getInt(33));

				if (vMuestra.getDtRecoleccion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vMuestra.getDtRecoleccion(), "/");
					vMuestra.setCDtRecoleccion(cFecha);
				} else {
					vMuestra.setCDtRecoleccion("");

				}
				if (vMuestra.getDtCaptura() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(vMuestra.getDtCaptura(),
							"/");
					vMuestra.setCDtCaptura(cFecha);
				} else {
					vMuestra.setCDtCaptura("");

				}

				vcMuestra.addElement(vMuestra);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("FindByDsc", ex);
			throw new DAOException("FindByDsc");
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
				warn("FindByDsc.close", ex2);
			}
			return vcMuestra;
		}
	}

	public Vector FindByAll2(Object Obj) throws DAOException {
		// Solo para saber que muestras tienen toxmuestra.lintegralote = 0
		// ************************//
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVMuestra vMuestra;
			vMuestra = (TVMuestra) (Obj);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			/*
			 * cSQL =
			 * "  select toxenvio.icveunimed, toxmuestra.icvemuestra, toxmuestra.icveenvio, toxmuestra.dtrecoleccion, "
			 * +
			 * "  toxmuestra.icvepbarapida, grlproceso.cdscproceso, grlmotivo.cdscmotivo "
			 * + "  from toxenvio " + "  inner join toxmuestra on " +
			 * "  (toxmuestra.ianio = toxenvio.ianio and " +
			 * "  toxmuestra.icveunimed = toxenvio.icveunimed and " +
			 * "  toxmuestra.icveenvio = toxenvio.icveenvio and " +
			 * "  toxmuestra.lintegralote = 0 and " +
			 * "  toxmuestra.iCveTipoRecep != 0) " +
			 * "  left join grlproceso on grlproceso.icveproceso =  toxmuestra.icveproceso "
			 * +
			 * "  left join grlmotivo on grlmotivo.icvemotivo = toxmuestra.icvemotivo "
			 * + "  where toxenvio.ianio = " + vMuestra.getIAnio() +
			 * "  and toxenvio.icveunimed = " + vMuestra.getICveUniMed() + " ";
			 */
			cSQL.append("select toxenvio.icveunimed, ")
					.append("       toxmuestra.icvemuestra, toxmuestra.icveenvio, toxmuestra.dtrecoleccion,  ")
					.append("       toxmuestra.icvepbarapida, grlproceso.cdscproceso, grlmotivo.cdscmotivo,  ")
					.append("       U.cDscUniMed ")
					.append("  from toxenvio  ")
					.append("       inner join GRLUniMed U on U.iCveUniMed = toxEnvio.icveUniMed ")
					.append("       inner join toxmuestra on  ")
					.append("         (toxmuestra.ianio         =  toxenvio.ianio      and  ")
					.append("          toxmuestra.icveunimed    =  toxenvio.icveunimed and  ")
					.append("          toxmuestra.icveenvio     =  toxenvio.icveenvio   and  ")
					.append("          toxmuestra.lintegralote  =  0 and  ")
					.append("          toxmuestra.iCveTipoRecep >  0 and ")
					.append("          toxmuestra.iCveSituacion != ")
					.append(VParametros.getPropEspecifica("TOXRechazo"))
					.append(" )")
					.append("       join grlproceso on grlproceso.icveproceso =  toxmuestra.icveproceso  ")
					.append("       join grlmotivo on grlmotivo.icvemotivo = toxmuestra.icvemotivo  ")
					.append("  where toxenvio.ianio =  ")
					.append(vMuestra.getIAnio())
					.append("    and toxenvio.iCveLaboratorio = ")
					.append(vMuestra.getICveUniMed());
			if (vMuestra.getICveEnvio() > 0) {
				cSQL.append(" and toxenvio.iCveUniMed = ").append(
						vMuestra.getICveEnvio());

			}
			if (vMuestra.getCFiltrar() != null
					&& vMuestra.getCFiltrar().compareToIgnoreCase("") != 0) {
				cSQL.append(" and ").append(vMuestra.getCFiltrar().toString());
			}
			if (vMuestra.getCOrdenar() != null
					&& vMuestra.getCOrdenar().compareToIgnoreCase("") != 0) {
				cSQL.append(" order by TOXEnvio.iAnio, TOXEnvio.iCveUniMed, TOXEnvio.iCveEnvio, TOXMuestra.iOrden");

				// System.out.println("ordenamiento= " +
				// vMuestra.getCOrdenar());
			} else {
				cSQL.append(" order by TOXEnvio.iAnio, TOXEnvio.iCveUniMed, TOXEnvio.iCveEnvio, TOXMuestra.iOrden");
			}
			// System.out.print(cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMuestra = new TVMuestra();
				vMuestra.setICveUniMed(rset.getInt(1));
				vMuestra.setICveMuestra(rset.getInt(2));
				vMuestra.setICveEnvio(rset.getInt(3));
				vMuestra.setDtRecoleccion(rset.getDate(4));
				vMuestra.setICvePbaRapida(rset.getInt(5));
				vMuestra.setCDscProceso(rset.getString(6));
				vMuestra.setCDscMotivo(rset.getString(7));
				vMuestra.setCDscUM(rset.getString(8));
				vcMuestra.addElement(vMuestra);
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
			return vcMuestra;
		}
	}

	/**
	 * Metodo Find By All 3 Tablas: TOXEnvio, TOXMuestra
	 */
	public Vector FindByAll3(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		Vector vcMuestra = new Vector();
		TFechas dtFecha = new TFechas();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVMuestra vMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());
			TDTOXCuantAnalisis dTOXCuantAnalisis = new TDTOXCuantAnalisis();

			cSQL.append(
					" select TOXMuestra.iCveMuestra, TOXMuestra.iCveProceso, ")
					// 1,2
					.append("        GRLProceso.cDscProceso, ")
					// 3
					.append("        TOXMuestra.iCveMotivo, ")
					// 4
					.append("        GRLMotivo.cDscMotivo, TOXMuestra.dtRecoleccion, ")
					// 5,6
					.append("        TOXMuestra.iCveSituacion, TOXSituacion.cDscSituacion, ")
					// 7, 8
					.append("        TOXMuestra.iCveEnvio, ")
					// 9
					.append("        M.cDscMotRecep, ")
					// 10
					.append("        A.lAutorizado, A.lResultado, A.iSustPost, A.iSustConf, ")
					// 11, 12, 13, 14
					.append("        A.lPresuntoPost, A.iCveLaboratorio, TOXMuestra.iAnio, ")
					// 15, 16, 17
					.append("        A.iCveAnalisis ")
					// 18
					.append(" from TOXMuestra  ")
					.append(" left join GRLProceso       on TOXMuestra.iCveProceso = GRLProceso.iCveProceso  ")
					.append(" left join GRLMotivo        on TOXMuestra.iCveMotivo = GRLMotivo.iCveMotivo  ")
					.append(" left join TOXSituacion     on TOXMuestra.iCveSituacion = TOXSituacion.iCveSituacion ")
					.append(" left join TOXMotivoRecep M on M.iCveMotRecep = TOXMuestra.iCveMotRecep ")
					.append(" left join TOXAnalisis    A on A.iAnio       = TOXMuestra.iAnio ")
					.append("                           and A.iCveMuestra = TOXMuestra.iCveMuestra ")
					.append(cFiltro);

			// System.out.println(cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMuestra = new TVMuestra();
				vMuestra.setICveMuestra(rset.getInt(1));
				vMuestra.setICveProceso(rset.getInt(2));
				vMuestra.setCDscProceso(rset.getString(3));
				vMuestra.setICveMotivo(rset.getInt(4));
				vMuestra.setCDscMotivo(rset.getString(5));
				vMuestra.setdtRecoleccion(rset.getDate(6));
				vMuestra.setICveSituacion(rset.getInt(7));
				vMuestra.setCDscSituacion(rset.getString(8));
				vMuestra.setICveEnvio(rset.getInt(9));
				vMuestra.setIAnio(rset.getInt(17));
				if (vMuestra.getdtRecoleccion() == null) {
					vMuestra.setdtRecoleccion(defaultFecha);
				}
				vMuestra.setICveSituacion(rset.getInt(7));
				vMuestra.setCDscSituacion(rset.getString(8));
				vMuestra.setCObsMuestra(rset.getString(10));
				// System.out.println("rset.getInt(16) = "+ rset.getInt(16));
				// System.out.println("rset.getInt(11) = "+ rset.getInt(11));
				// System.out.println("rset.getInt(15) = "+ rset.getInt(15));
				// System.out.println("rset.getInt(13) = "+ rset.getInt(13));
				// System.out.println("rset.getInt(14) = "+ rset.getInt(14));
				// System.out.println("rset.getInt(12) = "+ rset.getInt(12));
				if (rset.getInt(16) > 0) {
					if (rset.getInt(11) == 0) // No est� autorizado el
												// resultado
						vMuestra.setCResultado("ANALIZANDO");
					else { // Est� autorizado el resultado
						if (rset.getInt(15) == 0)
							vMuestra.setCResultado("NEGATIVO");
						else {
							/*
							 * if (rset.getInt(13) == rset.getInt(14)) Se Anulo
							 * la siguiente validacion ya que no cumple con las
							 * reglas del negocio if (rset.getInt(13) ==
							 * rset.getInt(14))
							 * vMuestra.setCResultado(rset.getInt(12) == 1 ?
							 * "POSITIVO" : "NEGATIVO"); else
							 * vMuestra.setCResultado("EN PROCESO");
							 */
							// //////////////////////////////////////////////////////////////////////////////////////

							int contador = 0;
							int autorizado = 0;
							int correcto = 0;
							int resultado2 = 0;
							int positivo = 0;
							int ICVEANALISIS = rset.getInt(18), IANIO = rset
									.getInt(17);

							String sustancias = "select toxc.icvesustancia, toxc.icveanalisis from toxcuantanalisis as toxc, toxanalisis tox "
									+ " where toxc.icveanalisis = tox.icveanalisis and "
									+ " tox.icveanalisis = "
									+ ICVEANALISIS
									+ " and toxc.ianio = "
									+ IANIO
									+ " group by toxc.icvesustancia, toxc.icveanalisis ";

							pstmt2 = conn.prepareStatement(sustancias);
							rset2 = pstmt2.executeQuery();
							Vector Sustancias = new Vector();
							int icveanalisis = 0;
							Vector lotemax = new Vector();
							while (rset2.next()) {
								Sustancias.addElement(rset2.getInt(1));
								icveanalisis = rset2.getInt(2);
							}
							/*
							 * System.out.println(sustancias); //
							 * System.out.println(Sustancias.size()); //
							 * System.out.println("esperamos 5 seg...");
							 * Thread.sleep(1000);
							 */
							String maxlotexanal = "";
							String resultado = "";
							String update = "";
							int countS = 0;
							int countL = 0;
							if (Sustancias.size() > 0) {
								for (int j = 0; j < Sustancias.size(); j++) {
									maxlotexanal = "select max(icvelotecuantita) "
											+ " from toxcuantanalisis where icveanalisis = "
											+ ICVEANALISIS
											+ " and ianio = "
											+ IANIO
											+ " and icvesustancia = "
											+ Sustancias.elementAt(j)
													.toString();

									// System.out.print(maxlotexanal);
									pstmt2 = conn
											.prepareStatement(maxlotexanal);
									rset2 = pstmt2.executeQuery();
									while (rset2.next()) {
										lotemax.addElement(rset2.getInt(1));
									}
								}
								/*
								 * System.out.println(lotemax.size()); //
								 * System.out.println("esperamos 5 seg...");
								 * Thread.sleep(1000);
								 */
								for (int k = 0; k < Sustancias.size(); k++) {
									resultado = "select icvesustancia, lresultado, lcorrecto, lautorizado "
											+ " from toxcuantanalisis where icveanalisis = "
											+ ICVEANALISIS
											+ " and ianio = "
											+ IANIO
											+ " and icvesustancia = "
											+ Sustancias.elementAt(k)
											+ " and icvelotecuantita = "
											+ lotemax.elementAt(k);
									// System.out.println(resultado);
									pstmt2 = conn.prepareStatement(resultado);
									rset2 = pstmt2.executeQuery();
									while (rset2.next()) {
										contador++;
										if (rset2.getInt(3) == 1
												&& rset2.getInt(4) == 1) {
											autorizado++;
											correcto++;
											if (rset2.getInt(2) == 1) {
												positivo++;
											}
											String Sentencia = "update toxexamresult set lpositivo = "
													+ rset2.getInt(2)
													+ ", lautorizado = "
													+ rset2.getInt(4)
													+ "  where icvesustancia = "
													+ Sustancias.elementAt(k)
													+ " and icveanalisis = "
													+ ICVEANALISIS
													+ " and ianio = " + IANIO;
											dTOXCuantAnalisis.update(Sentencia);
										}
									}
								}
								/*
								 * System.out.println(lotemax.size()); //
								 * System.out.println("esperamos 5 seg...");
								 * Thread.sleep(1000);
								 */
							}

							/*
							 * System.out.println("contador = "+contador); //
							 * System.out.println("autorizado = "+autorizado);
							 * // System.out.println("positivo = "+positivo);
							 */

							// ///////////////////////////////////////////////////////////////////////////

							/*
							 * String Query =
							 * "SELECT LAUTORIZADO, LRESULTADO, LCORRECTO FROM TOXCUANTANALISIS WHERE ICVEANALISIS = "
							 * +
							 * rset.getInt(18)+" AND IANIO = "+rset.getInt(17)+" "
							 * ; //System.out.println(Query);
							 * 
							 * pstmt2 = conn.prepareStatement(Query.toString());
							 * rset2 = pstmt2.executeQuery(); while
							 * (rset2.next()){ contador++; autorizado =
							 * autorizado + rset2.getInt(1); resultado =
							 * resultado + rset2.getInt(2); correcto = correcto
							 * + rset2.getInt(3); if(rset2.getInt(1) == 1 &&
							 * rset2.getInt(2) == 1 && rset2.getInt(3) == 1){
							 * positivo = positivo + 1;
							 * 
							 * } }
							 * 
							 * // System.out.println("contador = " +contador+
							 * "     autorizado = "
							 * +autorizado+"  positivo = "+positivo);
							 */
							/*
							 * if(contador == autorizado && autorizado ==
							 * correcto){ vMuestra.setCResultado(rset.getInt(12)
							 * == 1 ? "POSITIVO" : "NEGATIVO"); } else{
							 * if(positivo > 0){
							 * vMuestra.setCResultado("POSITIVO"); } else{
							 * vMuestra.setCResultado("EN PROCESO"); } }
							 */
							if (positivo == 0 && contador == correcto) {
								vMuestra.setCResultado("NEGATIVO");
							}
							if (positivo == 0 && contador > correcto) {
								vMuestra.setCResultado("EN PROCESO");
							}
							if (positivo > 0) {
								vMuestra.setCResultado("POSITIVO");
							}

						}
					}
				} else
					vMuestra.setCResultado("&nbsp;");

				vcMuestra.addElement(vMuestra);
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
				if (rset2 != null) {
					rset2.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMuestra;
		}
	}

	/**
	 * Metodo Find By All 4 Tablas: TOXAnalisis, TOXMuestra
	 */
	public Vector FindByAll4(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMuestra = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra vMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select TOXMuestra.iAnio, iCveUniMed, iCvePbaRapida, "
					+ "TOXMuestra.iCveProceso, TOXMuestra.iCveMotivo, "
					+ "TOXMuestra.iCveMuestra, dtRecoleccion, TOXMuestra.iCveSituacion, "
					+ "TOXAnalisis.iCveAnalisis, GRLProceso.cDscProceso, "
					+ "GRLMotivo.cDscMotivo, TOXSituacion.cDscSituacion, lResultado "
					+ "from TOXMuestra "
					+ "left join TOXAnalisis on TOXMuestra.iCveMuestra = TOXAnalisis.iCveMuestra "
					+ "left join GRLProceso on TOXMuestra.iCveProceso = GRLProceso.iCveProceso "
					+ "left join GRLMotivo on TOXMuestra.iCveMotivo = GRLMotivo.iCveMotivo "
					+ "left join TOXSituacion on TOXMuestra.iCveSituacion = TOXSituacion.iCveSituacion "
					+ cFiltro;
			// System.out.println("Busqueda = " + cSQL);
			TFechas dtFecha = new TFechas();
			String cFecha = "";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMuestra = new TVMuestra();
				vMuestra.setIAnio(rset.getInt(1));
				vMuestra.setICveUniMed(rset.getInt(2));
				vMuestra.setICvePbaRapida(rset.getInt(3));
				vMuestra.setICveProceso(rset.getInt(4));
				vMuestra.setICveMotivo(rset.getInt(5));
				vMuestra.setICveMuestra(rset.getInt(6));
				vMuestra.setDtRecoleccion(rset.getDate(7));
				vMuestra.setICveSituacion(rset.getInt(8));
				vMuestra.setICveAnalisis(rset.getInt(9));
				vMuestra.setCDscProceso(rset.getString(10));
				vMuestra.setCDscMotivo(rset.getString(11));
				vMuestra.setCDscSituacion(rset.getString(12));
				vMuestra.setLResultado(rset.getInt(13));

				if (vMuestra.getDtRecoleccion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vMuestra.getDtRecoleccion(), "/");
					vMuestra.setCDtRecoleccion(cFecha);
				} else {
					vMuestra.setCDtRecoleccion("");
				}

				vcMuestra.addElement(vMuestra);
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
			return vcMuestra;
		}
	}

	/**
	 * Metodo FindMuestraNE
	 */
	public Vector FindMuestraNE(int iAnio, int iUniMed, int iEnvio,
			String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra vMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select  "
					+ "iAnio, "
					+ "iCveMuestra, "
					+ "iCveEnvio, "
					+ "iCveUniMed, "
					+ "dtRecoleccion, "
					+ "GRLProceso.cDscProceso, "
					+ "GRLMotivo.cDscMotivo "
					+ "from TOXMuestra "
					+ "inner join GRLProceso ON GRLProceso.iCveProceso = TOXMuestra.iCveProceso "
					+ "inner join GRLMotivo ON GRLMotivo.iCveMotivo = TOXMuestra.iCveMotivo "
					+ "where ((TOXMuestra.iCveEnvio is null or TOXMuestra.iCveEnvio = 0) "
					+ "AND TOXMuestra.iAnio = ? "
					+ "AND TOXMuestra.iCveUniMed = ? "
					+ ")or ("
					+ "TOXMuestra.iCveEnvio = (select iCveEnvio from TOXEnvio WHERE TOXEnvio.dtEnvio is null "
					+ "AND TOXEnvio.iAnio = ? " + "AND TOXEnvio.iCveUniMed = ?"
					+ "AND TOXEnvio.iCveEnvio = ? ) "
					+ "AND TOXMuestra.iAnio = ? "
					+ "AND TOXMuestra.iCveUniMed = ?) " + cOrden;
		    System.out.println("FindMuestraNE cSQL = " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iUniMed);
			pstmt.setInt(3, iAnio);
			pstmt.setInt(4, iUniMed);
			pstmt.setInt(5, iEnvio);
			pstmt.setInt(6, iAnio);
			pstmt.setInt(7, iUniMed);

			rset = pstmt.executeQuery();

			if (rset != null) {
				while (rset.next()) {
					vMuestra = new TVMuestra();
					vMuestra.setIAnio(rset.getInt(1));
					vMuestra.setICveMuestra(rset.getInt(2));
					vMuestra.setICveEnvio(rset.getInt(3));
					vMuestra.setICveUniMed(rset.getInt(4));
					vMuestra.setdtRecoleccion(rset.getDate(5));
					vMuestra.setCDscProceso(rset.getString(6));
					vMuestra.setCDscMotivo(rset.getString(7));
					vcMuestra.addElement(vMuestra);
				}
			}
		} catch (Exception ex) {
			warn("FindMuestraNE", ex);
			throw new DAOException("FindMuestraNE");
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
				warn("FindMuestraNE.close", ex2);
			}
			return vcMuestra;
		}
	}
	
	/**
	 * Metodo FindMuestraHielera
	 */
	public String FindMuestraHielera(int iAnio, int iUniMed, int iEnvio,
			int iMuestra) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Hielera = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra vMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT ICVEHIELERA FROM TOXENVIOMUESTRA " +
					"WHERE IANIO = ? AND " +
					"ICVEUNIMED = ? AND " +
					"ICVEENVIO = ? AND " +
					"ICVEMUESTRA = ? ";
			//System.out.println("FindMuestraHielera cSQL = " + cSQL);
			//System.out.println(iAnio);
			//System.out.println(iUniMed);
			//System.out.println(iEnvio);
			//System.out.println(iMuestra);
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iUniMed);
			pstmt.setInt(3, iEnvio);
			pstmt.setInt(4, iMuestra);

			rset = pstmt.executeQuery();

			if (rset != null) {
				while (rset.next()) {
					Hielera = rset.getString(1);
				}
			}
		} catch (Exception ex) {
			warn("FindMuestraNE", ex);
			throw new DAOException("FindMuestraNE");
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
				warn("FindMuestraNE.close", ex2);
			}
			return Hielera;
		}
	}	
	
	/**
	 * Metodo FindMuestraHielera
	 */
	public String FindNumHieleras(int iAnio, int iUniMed, int iEnvio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Hielera = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra vMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT COUNT(DISTINCT ICVEHIELERA) FROM TOXENVIOMUESTRA " +
					"WHERE IANIO = ? AND " +
					"ICVEUNIMED = ? AND " +
					"ICVEENVIO = ? ";
			//System.out.println("FindMuestraHielera cSQL = " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iUniMed);
			pstmt.setInt(3, iEnvio);

			rset = pstmt.executeQuery();

			if (rset != null) {
				while (rset.next()) {
					Hielera = rset.getString(1);
				}
			}
		} catch (Exception ex) {
			warn("FindMuestraNE", ex);
			throw new DAOException("FindMuestraNE");
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
				warn("FindMuestraNE.close", ex2);
			}
			return Hielera;
		}
	}	

	/**
	 * Metodo FindMuestraHielera
	 */
	public String FindMuestraMensajeria(int iAnio, int iUniMed, int iEnvio,
			int iMuestra) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String Mensajeria = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMuestra vMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT M.CDSCMENSAJERIA " +
					"FROM TOXENVIOMUESTRA AS E, TOXMENSAJERIA AS M " +
					"WHERE E.IANIO = ? AND " +
					"E.ICVEUNIMED = ? AND " +
					"E.ICVEENVIO = ? AND " +
					"E.ICVEMUESTRA = ? AND " +
					"E.ICVEMENSAJERIA = M.ICVEMENSAJERIA";
			//System.out.println("FindMuestraMensajeria cSQL = " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iUniMed);
			pstmt.setInt(3, iEnvio);
			pstmt.setInt(4, iMuestra);

			rset = pstmt.executeQuery();

			if (rset != null) {
				while (rset.next()) {
					Mensajeria = rset.getString(1);
				}
			}
		} catch (Exception ex) {
			warn("FindMuestraNE", ex);
			throw new DAOException("FindMuestraNE");
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
				warn("FindMuestraNE.close", ex2);
			}
			return Mensajeria;
		}
	}	
	
	

	/**
	 * Metodo Insert
	 */
	/*
	 * public Object insert(Connection conGral, Object obj) throws DAOException
	 * { DbConnection dbConn = null; Connection conn = null; PreparedStatement
	 * pstmt = null; String cClave = ""; try { if (conGral != null) { conn =
	 * conGral; } else { dbConn = new DbConnection(dataSourceName); conn =
	 * dbConn.getConnection(); } String cSQL = ""; TVMuestra vMuestra =
	 * (TVMuestra) obj; conn.setAutoCommit(false);
	 * conn.setTransactionIsolation(2); cSQL = "insert into TOXMuestra(" +
	 * "iAnio," + "iCveMuestra," + "iCvePbaRapida," + "iCveUniMed," +
	 * "iCveEnvio," + "iCveProceso," + "iCveMotivo," + "dtRecoleccion," +
	 * "iCveTipoRecep," + "lIntegraLote," + "iCveSituacion," + "lTemperaC," +
	 * "lAlteracion," + "lBajoObserva," + "iCveEmpresa" +
	 * ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; pstmt =
	 * conn.prepareStatement(cSQL); // DEBE DE INGRESAR C�DIGO PARA CALCULAR
	 * EL CONSECUTIVO DE LA TABLA pstmt.setInt(1, vMuestra.getIAnio());
	 * pstmt.setString(2, vMuestra.getICveMuestra()); pstmt.setInt(3,
	 * vMuestra.getICvePbaRapida()); pstmt.setInt(4, vMuestra.getICveUniMed());
	 * pstmt.setInt(5, vMuestra.getICveEnvio()); pstmt.setInt(6,
	 * vMuestra.getICveProceso()); pstmt.setInt(7, vMuestra.getICveMotivo());
	 * pstmt.setDate(8, vMuestra.getDtRecoleccion()); pstmt.setInt(9,
	 * vMuestra.getICveTipoRecep()); pstmt.setInt(10,
	 * vMuestra.getLIntegraLote()); pstmt.setInt(11,
	 * vMuestra.getICveSituacion()); pstmt.setInt(12, vMuestra.getLTemperaC());
	 * pstmt.setInt(13, vMuestra.getLAlteracion()); pstmt.setInt(14,
	 * vMuestra.getLBajoObserva()); pstmt.setInt(15, vMuestra.getICveEmpresa());
	 * pstmt.executeUpdate(); cClave = "" + vMuestra.getIAnio(); if (conGral ==
	 * null) { conn.commit(); } } catch (Exception ex) { try { if (conGral ==
	 * null) { conn.rollback(); } } catch (Exception ex1) { warn("insert", ex1);
	 * } warn("insert", ex); throw new DAOException(""); } finally { try { if
	 * (pstmt != null) { pstmt.close(); } if (conGral == null) { conn.close(); }
	 * dbConn.closeConnection(); } catch (Exception ex2) { warn("insert.close",
	 * ex2); } return cClave; } }
	 */

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtEXPExamAplica = null;
		PreparedStatement pstmtFindExamen = null;
		ResultSet rset = null;
		ResultSet rsetExamen = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVMuestra vMuestra = (TVMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int j = 0;
			String cSQLFind;
			String cRes;

			cSQLFind = "select " + "iAnio,iCveMuestra " + "from TOXMuestra "
					+ "where iAnio = ? and iCveMuestra = ? ";
			pstmtFind = conn.prepareStatement(cSQLFind);
			pstmtFind.setInt(1, vMuestra.getIAnio());
			pstmtFind.setInt(2, vMuestra.getICveMuestra());

			rset = pstmtFind.executeQuery();
			while (rset.next()) {
				cRes = "" + rset.getInt(1);
				if (cRes.compareTo("0") != 0) {
					j = 1;
				}
			}

			if (j == 0) {
				cSQL = "insert into TOXMuestra(" + "iCveUniMed,"
						+ "iCveProceso," + "iCveMotivo," + "iCveMuestra,"
						+ "iAnio," + "iCvePersonal," + "iCveEmpresa,"
						+ "dtCaptura," + "dtRecoleccion," + "iCveUsuRecolec,"
						+ "iCveSituacion," + "lTemperaC," + "lAlteracion,"
						+ "lBajoObserva," + "cObsMuestra," + "iCvePbaRapida,"
						+ "iCveUsuCaptura," + "iCveMdoTrans, " + "iEdad, "
						+ "iCveModulo "
						+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vMuestra.getICveUniMed());
				pstmt.setInt(2, vMuestra.getICveProceso());
				pstmt.setInt(3, vMuestra.getICveMotivo());
				pstmt.setInt(4, vMuestra.getICveMuestra());
				pstmt.setInt(5, vMuestra.getIAnio());
				pstmt.setInt(6, vMuestra.getICvePersonal());
				pstmt.setInt(7, vMuestra.getICveEmpresa());
				pstmt.setDate(8, vMuestra.getDtCaptura());
				pstmt.setDate(9, vMuestra.getDtRecoleccion());
				pstmt.setInt(10, vMuestra.getICveUsuRecolec());
				pstmt.setInt(11, vMuestra.getICveSituacion());
				pstmt.setInt(12, vMuestra.getLTemperaC());
				pstmt.setInt(13, vMuestra.getLAlteracion());
				pstmt.setInt(14, vMuestra.getLBajoObserva());
				pstmt.setString(15, vMuestra.getCObsMuestra());
				pstmt.setInt(16, vMuestra.getICvePbaRapida());
				pstmt.setInt(17, vMuestra.getICveUsuCaptura());
				pstmt.setInt(18, vMuestra.getICveMdoTrans());
				pstmt.setInt(19, vMuestra.getIEdad());
				pstmt.setInt(20, vMuestra.getICveModulo());
				pstmt.executeUpdate();
			} else {
				cClave = "ERROR";
			}

			if (cClave.compareTo("null") == 0 && cClave.compareTo("") == 0) {
				cClave = "" + vMuestra.getICveMuestra();
			}

			// Nuevo Proceso 18/11/2004
			// Agrego MGonz�lez Inserci�n a EXPExamAplica
			int iMax = 0;

			cSQLFind = "select max(iNumExamen) " + "from EXPExamAplica "
					+ "where iCveExpediente = ?  ";
			pstmtFindExamen = conn.prepareStatement(cSQLFind);
			pstmtFindExamen.setInt(1, vMuestra.getICvePersonal());

			rsetExamen = pstmtFindExamen.executeQuery();
			while (rsetExamen.next()) {
				iMax = rsetExamen.getInt(1);
			}
			/*
			 * if (iMax == 0){ iMax = 1; }
			 */

			cSQL = "insert into EXPExamAplica("
					+ "iCveExpediente,iNumExamen,iCveUniMed,iCveProceso,iCvePersonal,"
					+ "iCveModulo,dtSolicitado,iFolioEs,dtAplicacion,dtConcluido,lIniciado,"
					+ "lDictaminado,lInterConsulta,lInterConcluye,lConcluido,dtDictamen,"
					+ "dtEntregaRes,dtArchivado,iCveNumEmpresa,iCveUsuSolicita,tInicio,tConcluido,cConstancia"
					+ ")values(?,?,?,?,?,?,{FN CURDATE()},?,{FN CURDATE()},{FN CURDATE()},? ,?,?,?,?,{FN CURDATE()},{FN CURDATE()},{FN CURDATE()},?,?,{FN CURTIME()},{FN CURTIME()},?)";

			pstmtEXPExamAplica = conn.prepareStatement(cSQL);
			pstmtEXPExamAplica.setInt(1, vMuestra.getICvePersonal());
			pstmtEXPExamAplica.setInt(2, iMax + 1);
			pstmtEXPExamAplica.setInt(3, vMuestra.getICveUniMed());
			pstmtEXPExamAplica.setInt(4, vMuestra.getICveProceso());
			pstmtEXPExamAplica.setInt(5, vMuestra.getICvePersonal());
			pstmtEXPExamAplica.setInt(6, vMuestra.getICveModulo());

			pstmtEXPExamAplica.setInt(7, 0);

			pstmtEXPExamAplica.setInt(8, 1); // lIniciado
			pstmtEXPExamAplica.setInt(9, 1); // lDictaminado
			pstmtEXPExamAplica.setInt(10, 0); // lInterconsulta
			pstmtEXPExamAplica.setInt(11, 0); // lInterconcluye
			pstmtEXPExamAplica.setInt(12, 1); // lConcluido
			pstmtEXPExamAplica.setInt(12, 1); // lConcluido

			pstmtEXPExamAplica.setInt(13, vMuestra.getICveEmpresa()); // Empresa
			pstmtEXPExamAplica.setInt(14, vMuestra.getICveUsuCaptura()); // Solicita
			pstmtEXPExamAplica.setString(15, "TDMuestra - insert"); // Identificador de metodo
			// System.out.println("----- >>>> EXEC NUEVO PROCESO EXPEXAMAPLICA PARA TOXX");
			pstmtEXPExamAplica.executeUpdate();

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
				if (rset != null) {
					rset.close();
				}
				if (pstmtFind != null) {
					pstmtFind.close();
				}
				if (rsetExamen != null) {
					rsetExamen.close();
				}

				if (pstmtFindExamen != null) {
					pstmtFindExamen.close();
				}

				if (pstmtEXPExamAplica != null) {
					pstmtEXPExamAplica.close();
				}

				if (pstmt != null) {
					pstmt.close();
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
	 * Metodo Insert
	 */
	public Object insertToxPbaRYMuestra(Connection conGral, Object obj, int iCvePbaRapida) 
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtEXPExamAplica = null;
		PreparedStatement pstmtFindExamen = null;
		ResultSet rset = null;
		ResultSet rsetExamen = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVMuestra vMuestra = (TVMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int j = 0;
			String cSQLFind;
			String cRes;

			cSQLFind = "select " + "iAnio,iCveMuestra " + "from TOXMuestra "
					+ "where iAnio = ? and iCveMuestra = ? ";
			pstmtFind = conn.prepareStatement(cSQLFind);
			pstmtFind.setInt(1, vMuestra.getIAnio());
			pstmtFind.setInt(2, vMuestra.getICveMuestra());

			rset = pstmtFind.executeQuery();
			while (rset.next()) {
				cRes = "" + rset.getInt(1);
				if (cRes.compareTo("0") != 0) {
					j = 1;
				}
			}

			if (j == 0) {
				cSQL = "insert into TOXMuestra(" + "iCveUniMed,"
						+ "iCveProceso," + "iCveMotivo," + "iCveMuestra,"
						+ "iAnio," + "iCvePersonal," + "iCveEmpresa,"
						+ "dtCaptura," + "dtRecoleccion," + "iCveUsuRecolec,"
						+ "iCveSituacion," + "lTemperaC," + "lAlteracion,"
						+ "lBajoObserva," + "cObsMuestra," + "iCvePbaRapida,"
						+ "iCveUsuCaptura," + "iCveMdoTrans, " + "iEdad, "
						+ "iCveModulo "
						+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vMuestra.getICveUniMed());
				pstmt.setInt(2, vMuestra.getICveProceso());
				pstmt.setInt(3, vMuestra.getICveMotivo());
				pstmt.setInt(4, vMuestra.getICveMuestra());
				pstmt.setInt(5, vMuestra.getIAnio());
				pstmt.setInt(6, vMuestra.getICvePersonal());
				pstmt.setInt(7, vMuestra.getICveEmpresa());
				pstmt.setDate(8, vMuestra.getDtCaptura());
				pstmt.setDate(9, vMuestra.getDtRecoleccion());
				pstmt.setInt(10, vMuestra.getICveUsuRecolec());
				pstmt.setInt(11, vMuestra.getICveSituacion());
				pstmt.setInt(12, vMuestra.getLTemperaC());
				pstmt.setInt(13, vMuestra.getLAlteracion());
				pstmt.setInt(14, vMuestra.getLBajoObserva());
				pstmt.setString(15, vMuestra.getCObsMuestra());
				pstmt.setInt(16, iCvePbaRapida);
				pstmt.setInt(17, vMuestra.getICveUsuCaptura());
				pstmt.setInt(18, vMuestra.getICveMdoTrans());
				pstmt.setInt(19, vMuestra.getIEdad());
				pstmt.setInt(20, vMuestra.getICveModulo());
				pstmt.executeUpdate();
			} else {
				cClave = "ERROR";
			}

			if (cClave.compareTo("null") == 0 && cClave.compareTo("") == 0) {
				cClave = "" + vMuestra.getICveMuestra();
			}

			// Nuevo Proceso 18/11/2004
			// Agrego MGonz�lez Inserci�n a EXPExamAplica
			int iMax = 0;

			cSQLFind = "select max(iNumExamen) " + "from EXPExamAplica "
					+ "where iCveExpediente = ?  ";
			pstmtFindExamen = conn.prepareStatement(cSQLFind);
			pstmtFindExamen.setInt(1, vMuestra.getICvePersonal());

			rsetExamen = pstmtFindExamen.executeQuery();
			while (rsetExamen.next()) {
				iMax = rsetExamen.getInt(1);
			}
			/*
			 * if (iMax == 0){ iMax = 1; }
			 */

			cSQL = "insert into EXPExamAplica("
					+ "iCveExpediente,iNumExamen,iCveUniMed,iCveProceso,iCvePersonal,"
					+ "iCveModulo,dtSolicitado,iFolioEs,dtAplicacion,dtConcluido,lIniciado,"
					+ "lDictaminado,lInterConsulta,lInterConcluye,lConcluido,dtDictamen,"
					+ "dtEntregaRes,dtArchivado,iCveNumEmpresa,iCveUsuSolicita,tInicio,tConcluido,cConstancia"
					+ ")values(?,?,?,?,?,?,{FN CURDATE()},?,{FN CURDATE()},{FN CURDATE()},? ,?,?,?,?,{FN CURDATE()},{FN CURDATE()},{FN CURDATE()},?,?,{FN CURTIME()},{FN CURTIME()},?)";

			pstmtEXPExamAplica = conn.prepareStatement(cSQL);
			pstmtEXPExamAplica.setInt(1, vMuestra.getICvePersonal());
			pstmtEXPExamAplica.setInt(2, iMax + 1);
			pstmtEXPExamAplica.setInt(3, vMuestra.getICveUniMed());
			pstmtEXPExamAplica.setInt(4, vMuestra.getICveProceso());
			pstmtEXPExamAplica.setInt(5, vMuestra.getICvePersonal());
			pstmtEXPExamAplica.setInt(6, vMuestra.getICveModulo());

			pstmtEXPExamAplica.setInt(7, 0);

			pstmtEXPExamAplica.setInt(8, 1); // lIniciado
			pstmtEXPExamAplica.setInt(9, 1); // lDictaminado
			pstmtEXPExamAplica.setInt(10, 0); // lInterconsulta
			pstmtEXPExamAplica.setInt(11, 0); // lInterconcluye
			pstmtEXPExamAplica.setInt(12, 1); // lConcluido
			pstmtEXPExamAplica.setInt(12, 1); // lConcluido

			pstmtEXPExamAplica.setInt(13, vMuestra.getICveEmpresa()); // Empresa
			pstmtEXPExamAplica.setInt(14, vMuestra.getICveUsuCaptura()); // Solicita
			pstmtEXPExamAplica.setString(15, "TDMuestra - insertToxPbaRYMuestra"); // Solicita
			// System.out.println("----- >>>> EXEC NUEVO PROCESO EXPEXAMAPLICA PARA TOXX");
			pstmtEXPExamAplica.executeUpdate();

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
				if (rset != null) {
					rset.close();
				}
				if (pstmtFind != null) {
					pstmtFind.close();
				}
				if (rsetExamen != null) {
					rsetExamen.close();
				}

				if (pstmtFindExamen != null) {
					pstmtFindExamen.close();
				}

				if (pstmtEXPExamAplica != null) {
					pstmtEXPExamAplica.close();
				}

				if (pstmt != null) {
					pstmt.close();
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
	 * Metodo update
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
			TVMuestra vMuestra = (TVMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXMuestra" + " set iCvePbaRapida= ?, "
					+ "iCveUniMed= ?, " + "iCveEnvio= ?, " + "iCveProceso= ?, "
					+ "iCveMotivo= ?, " + "dtRecoleccion= ?, "
					+ "iCveTipoRecep= ?, " + "lIntegraLote= ?, "
					+ "iCveSituacion= ?, " + "lTemperaC= ?, "
					+ "lAlteracion= ?, " + "lBajoObserva= ?, "
					+ "iCveEmpresa= ? " + "where iAnio = ? "
					+ " and iCveMuestra = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMuestra.getICvePbaRapida());
			pstmt.setInt(2, vMuestra.getICveUniMed());
			pstmt.setInt(3, vMuestra.getICveEnvio());
			pstmt.setInt(4, vMuestra.getICveProceso());
			pstmt.setInt(5, vMuestra.getICveMotivo());
			pstmt.setDate(6, vMuestra.getDtRecoleccion());
			pstmt.setInt(7, vMuestra.getICveTipoRecep());
			pstmt.setInt(8, vMuestra.getLIntegraLote());
			pstmt.setInt(9, vMuestra.getICveSituacion());
			pstmt.setInt(10, vMuestra.getLTemperaC());
			pstmt.setInt(11, vMuestra.getLAlteracion());
			pstmt.setInt(12, vMuestra.getLBajoObserva());
			pstmt.setInt(13, vMuestra.getICveEmpresa());
			pstmt.setInt(14, vMuestra.getIAnio());
			pstmt.setInt(15, vMuestra.getICveMuestra());
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

	public Object update2(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL = "";
			TVMuestra vMuestra = (TVMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXMuestra set lIntegraLote= ? where iAnio = ? and iCveMuestra = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMuestra.getLIntegraLote());
			pstmt.setInt(2, vMuestra.getIAnio());
			pstmt.setInt(3, vMuestra.getICveMuestra());
			pstmt.executeUpdate();
			cClave = "";
			conn.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
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
				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Delete
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
			TVMuestra vMuestra = (TVMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXMuestra" + " where iAnio = ?"
					+ " and iCveMuestra = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMuestra.getIAnio());
			pstmt.setInt(2, vMuestra.getICveMuestra());
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
				warn("delete.closeMuestra", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo InsertFromALM
	 */
	public boolean insertFromALM(int iAnio, int iUniMed, int iNumRegs)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		ResultSet rset = null;
		String cSQL = "";
		String cSQLFind = "";
		boolean lRetorno = true;
		int iMuestraIni = 0, iMuestraFin = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLFind = "select max(iCveMuestra) " + "from TOXMuestra "
					+ "where iAnio = " + iAnio;
			pstmtFind = conn.prepareStatement(cSQLFind);
			rset = pstmtFind.executeQuery();
			while (rset.next()) {
				iMuestraIni = rset.getInt(1);
			}
			iMuestraIni++;
			iMuestraFin = iMuestraIni + iNumRegs;
			cSQL = "insert into TOXMuestra(" + "iAnio," + "iCveMuestra,"
					+ "iCveUniMed," + "iCveProceso," + "iCveMotivo,"
					+ "lIntegraLote," + "lTemperaC," + "lAlteracion,"
					+ "lBajoObserva," + "iCveUsuCaptura," + "iCveUsuRecolec,"
					+ "iCvePersonal" + ")values(?,?,?,?,?,?,?,?,?,?,?,?)";
			for (int i = iMuestraIni; i < iMuestraFin; i++) {
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, iAnio);
				pstmt.setInt(2, i);
				pstmt.setInt(3, iUniMed);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, 0);
				pstmt.setInt(6, 0);
				pstmt.setInt(7, 0);
				pstmt.setInt(8, 0);
				pstmt.setInt(9, 0);
				pstmt.setInt(10, 0);
				pstmt.setInt(11, 0);
				pstmt.setInt(12, 0);
				pstmt.executeUpdate();
			}
			conn.commit();
		} catch (Exception ex) {
			lRetorno = false;
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("insertFromALM", ex1);
			}
			warn("insertFromALM", ex);
			throw new DAOException("");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmtFind != null) {
					pstmtFind.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}
				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insertFromALM.close", ex2);
			}
		}
		return lRetorno;
	}
}
