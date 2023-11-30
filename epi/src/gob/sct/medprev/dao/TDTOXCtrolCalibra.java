package gob.sct.medprev.dao;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.msc.*;

/**
 * <p>
 * Title: Data Acces Object de CtrolCalibra DAO
 * </p>
 * <p>
 * Description: DAO de TOXCtrolCalibra
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */

public class TDTOXCtrolCalibra extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private SimpleDateFormat dtFormatter = new SimpleDateFormat("dd/MM/yyyy");
	private List jxlsBeanList; // Agregado Rafael Alcocer Caldera 24/Ago/2006
	private pg0703060110JXLSBean2 bean2; // Agregado Rafael Alcocer Caldera
											// 05/Sep/2006

	public TDTOXCtrolCalibra() {
	}

	public Vector FindByAll(HashMap hmParam) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCtrolCalibra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXCtrolCalibra vCtrolCalibra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = "select "
					+ "a.iCveLaboratorio,a.iCveCtrolCalibra,a.iCveReactivo,a.iAnio,"
					+ // 0 - 3
					"a.cLote,a.cDscCtrolCalibra,a.cDscBreve,a.iCveSustancia,a.dVolumen,"
					+ // 4 - 8
					"a.dConcentracion,a.iCveEmpleoCalib,a.lCuantCual,a.iViales,"
					+ // 9 -12
					"a.dtPreparacion,a.dtCaducidad,a.dtAutoriza,a.iCveUsuAutoriza,"
					+ // 13 - 16
					"a.lAgotado,a.dtAgotado,a.lBaja,a.dtBaja,a.iAnioBaja,a.iCveBaja,"
					+ // 17 - 22
					"a.iCveMarcaSust,a.cObservacion,a.iCveUsuPrepara,b.cDscEmpleoCalib, a.lCual,"
					+ // 23 - 27
					"a.dConcentExper, a.iCveEquipo, a.dtValoracion, a.dVolUtilizadado "
					+ // 28 - 31
					"from TOXCtrolCalibra a join TOXEmpleoCalib b on("
					+ "a.iCveEmpleoCalib=b.iCveEmpleoCalib) "
					+ "where iCveLaboratorio=?";

			String cTmp = (String) hmParam.get("hdLCondicion");
			if (cTmp != null && cTmp.length() > 0) {
				cSQL += " and " + cTmp;
			}
			cTmp = (String) hmParam.get("iCveEmpleoCalib");
			if (cTmp != null && cTmp.length() > 0) {
				cSQL += " and a.iCveEmpleoCalib=?";
			}
			cTmp = (String) hmParam.get("iCveUsuPrepara");
			if (cTmp != null && cTmp.length() > 0) {
				cSQL += " and a.iCveUsuPrepara=?";
			}
			cTmp = (String) hmParam.get("dtCaducidad");
			if (cTmp != null && cTmp.length() > 0) {
				cSQL += " and a.dtCaducidad=?";
			}
			cTmp = (String) hmParam.get("dtPreparacion");
			if (cTmp != null && cTmp.length() > 0) {
				cSQL += " and a.dtPreparacion=?";
			}
			cTmp = (String) hmParam.get("rSituacion");
			if (cTmp != null && cTmp.length() > 0) {
				cSQL += " and 2*a.lBaja+a.lAgotado=?";
			}
			cTmp = (String) hmParam.get("hdLOrdenar");
			if (cTmp != null && cTmp.length() > 0) {
				cSQL += cTmp;

			}
			// System.out.println("CtrolCalibnra Query: " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;

			cTmp = (String) hmParam.get("iCveLaboratorio");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(cTmp));
			} else {
				pstmt.setInt(i++, 0);
			}
			cTmp = (String) hmParam.get("iCveEmpleoCalib");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(cTmp));
			}
			cTmp = (String) hmParam.get("iCveUsuPrepara");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(cTmp));
			}
			cTmp = (String) hmParam.get("dtCaducidad");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setDate(i++, getDate(cTmp));
			}
			cTmp = (String) hmParam.get("dtPreparacion");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setDate(i++, getDate(cTmp));
			}
			cTmp = (String) hmParam.get("rSituacion");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(cTmp));

			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCtrolCalibra = new TVTOXCtrolCalibra();
				vCtrolCalibra
						.setICveLaboratorio(rset.getInt("iCveLaboratorio"));
				vCtrolCalibra.setICveCtrolCalibra(rset
						.getInt("iCveCtrolCalibra"));
				vCtrolCalibra.setICveReactivo(rset.getInt("iCveReactivo"));
				vCtrolCalibra.setIAnio(rset.getInt("iAnio"));
				vCtrolCalibra.setCLote(rset.getString("cLote"));
				vCtrolCalibra.setCDscCtrolCalibra(rset
						.getString("cDscCtrolCalibra"));
				vCtrolCalibra.setCDscBreve(rset.getString("cDscBreve"));
				vCtrolCalibra.setICveSustancia(rset.getInt("iCveSustancia"));
				vCtrolCalibra.setDVolumen(rset.getFloat("dVolumen"));
				vCtrolCalibra
						.setDConcentracion(rset.getFloat("dConcentracion"));
				vCtrolCalibra
						.setICveEmpleoCalib(rset.getInt("iCveEmpleoCalib"));
				vCtrolCalibra.setLCuantCual(rset.getInt("lCuantCual"));
				vCtrolCalibra.setIViales(rset.getInt("iViales"));
				vCtrolCalibra.setDtPreparacion(rset.getDate("dtPreparacion"));
				vCtrolCalibra.setDtCaducidad(rset.getDate("dtCaducidad"));
				vCtrolCalibra.setDtAutoriza(rset.getDate("dtAutoriza"));
				vCtrolCalibra
						.setICveUsuAutoriza(rset.getInt("iCveUsuAutoriza"));
				vCtrolCalibra.setLAgotado(rset.getInt("lAgotado"));
				vCtrolCalibra.setDtAgotado(rset.getDate("dtAgotado"));
				vCtrolCalibra.setLBaja(rset.getInt("lBaja"));
				vCtrolCalibra.setDtBaja(rset.getDate("dtBaja"));
				vCtrolCalibra.setIAnioBaja(rset.getInt("iAnioBaja"));
				vCtrolCalibra.setICveBaja(rset.getInt("iCveBaja"));
				vCtrolCalibra.setICveMarcaSust(rset.getInt("iCveMarcaSust"));
				vCtrolCalibra.setCObservacion(rset.getString("cObservacion"));
				vCtrolCalibra.setICveUsuPrepara(rset.getInt("iCveUsuPrepara"));
				vCtrolCalibra.setCDscEmpleoCalib(rset
						.getString("cDscEmpleoCalib"));
				vCtrolCalibra.setLCual(rset.getInt("lCual"));
				vCtrolCalibra.setDConcentExper(rset.getFloat("dConcentExper"));
				vCtrolCalibra.setICveEquipo(rset.getInt("iCveEquipo"));
				vCtrolCalibra.setDtValoracion(rset.getDate("dtValoracion"));
				vCtrolCalibra
						.setDVolUtilizado(rset.getFloat("dVolUtilizadado"));

				vcCtrolCalibra.addElement(vCtrolCalibra);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return vcCtrolCalibra;
		}
	}

	public Vector FindByAll(String cWhere, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Vector vcCtrolCalibra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLJoin = "";
			TFechas dtFecha = new TFechas();
			String cFecha;

			TVTOXCtrolCalibra vCtrolCalibra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQLJoin = "Select "
					+ "C.iCveLaboratorio,"
					+ // 1
					"C.iCveCtrolCalibra,"
					+ // 2
					"C.iCveReactivo,"
					+ // 3
					"C.iAnio,"
					+ // 4
					"C.cLote,"
					+ // 5
					"C.cDscCtrolCalibra,"
					+ // 6
					"C.cDscBreve,"
					+ // 7
					"C.iCveSustancia,"
					+ // 8
					"C.dVolumen,"
					+ // 9
					"C.dConcentracion,"
					+ // 10
					"C.iCveEmpleoCalib,"
					+ // 11
					"C.lCuantCual,"
					+ // 12
					"C.iViales,"
					+ // 13
					"C.dtPreparacion,"
					+ // 14
					"C.dtCaducidad,"
					+ // 15
					"C.dtAutoriza,"
					+ // 16
					"C.iCveUsuAutoriza,"
					+ // 17
					"C.lAgotado,"
					+ // 18
					"C.dtAgotado,"
					+ // 19
					"C.lBaja,"
					+ // 20
					"C.dtBaja,"
					+ // 21
					"C.iAnioBaja,"
					+ // 22
					"C.iCveBaja,"
					+ // 23
					"C.iCveMarcaSust,"
					+ // 24
					"C.cObservacion,"
					+ // 25
					"C.iCveUsuPrepara,"
					+ // 26
					"L.cDscUniMed,"
					+ // 27
					"S.cDscSustancia,"
					+ // 28
					"E.cDscEmpleoCalib,"
					+ // 29
					"M.cDscMarcaSust, "
					+ // 30
					"R.cDscBreve, "
					+ // Se cambio tenia cDscReactivo y asi se sigue invocando
					"TR.cDscTpoReact,"
					+ // 32
					"TR.iCveTpoReact, "
					+ // 33
					"U.cnombre || ' ' || U.cappaterno || ' ' || U.capmaterno cDscUsuPrepara, "
					+ "U2.cnombre || ' ' || U2.cappaterno || ' ' || U2.capmaterno cDscUsuAutoriza, "
					+ "C.lCual, "
					+ // 36
					"EC.cDscEmpleoCalib, "
					+ // 37
					"C.dConcentExper,"
					+ // 38
					"C.iCveEquipo,"
					+ // 39
					"C.dtValoracion,"
					+ // 40
					"C.dVolUtilizadado, "
					+ // 41
					"EQ.iCveEquipo, "
					+ // 42
					"EQ.lCuantCual, "
					+ // 43
					"EQ.iCarruseles, "
					+ // 44
					"EQ.iPosiciones, "
					+ // 45
					"EQMEquipo.cDscEquipo, "
					+ // 46
					"EQMEquipo.cNumSerie, "
					+ // 47
					"EQMEquipo.cModelo, "
					+ // 48
					"EQMEquipo.cCveEquipo, "
					+ // 49
					"R.iCodigo "
					+ // 50
					"from TOXCtrolCalibra C "
					+ "inner join GRLUniMed L on ( C.iCveLaboratorio = L.iCveUniMed ) "
					+ "inner join TOXEmpleoCalib E on ( C.iCveEmpleoCalib = E.iCveEmpleoCalib ) "
					+ "inner join TOXMarcaSust M on ( C.iCveMarcaSust = M.iCveMarcaSust ) "
					+ "inner join TOXSustancia S on ( C.iCveSustancia = S.iCveSustancia ) "
					+ "inner join TOXReactivo R on (R.iCveLaboratorio = C.iCveLaboratorio and  C.iCveReactivo = R.iCveReactivo) "
					+ "inner join TOXTpoReact TR on ( R.iCveTpoReact = TR.iCveTpoReact ) "
					+ "inner join SEGUsuario U on ( C.iCveUsuPrepara = U.iCveUsuario ) "
					+ "inner join SEGUsuario U2 on ( C.iCveUsuAutoriza = U2.iCveUsuario ) "
					+ "inner join TOXEmpleoCalib EC on (EC.iCveEmpleoCalib = C.iCveEmpleoCalib) "
					+ "left join TOXEquipo EQ on EQ.iCveEquipo = C.iCveEquipo "
					+ "left join EQMEquipo on EQMEquipo.iCveEquipo = EQ.iCveEquipo "
					+ cWhere + cOrden;
			// System.out.println("Query CtrolCalibra: " + cSQLJoin);
			pstmt = conn.prepareStatement(cSQLJoin);

			// System.out.println("TOXCtrlCalibra Where : " + cWhere);
			rset = pstmt.executeQuery();

			jxlsBeanList = new ArrayList(); // Agregado Rafael Alcocer Caldera
											// 24/Ago/2006

			while (rset.next()) {
				vCtrolCalibra = new TVTOXCtrolCalibra();
				vCtrolCalibra.setICveLaboratorio(rset.getInt(1));
				vCtrolCalibra.setICveCtrolCalibra(rset.getInt(2));
				vCtrolCalibra.setICveReactivo(rset.getInt(3));
				vCtrolCalibra.setIAnio(rset.getInt(4));
				vCtrolCalibra.setCLote(rset.getString(5));
				vCtrolCalibra.setCDscCtrolCalibra(rset.getString(6));
				vCtrolCalibra.setCDscBreve(rset.getString(7));
				vCtrolCalibra.setICveSustancia(rset.getInt(8));
				vCtrolCalibra.setDVolumen(rset.getFloat(9));
				vCtrolCalibra.setDConcentracion(rset.getFloat(10));
				vCtrolCalibra.setICveEmpleoCalib(rset.getInt(11));
				vCtrolCalibra.setLCuantCual(rset.getInt(12));
				vCtrolCalibra.setIViales(rset.getInt(13));
				vCtrolCalibra.setDtPreparacion(rset.getDate(14));
				vCtrolCalibra.setDtCaducidad(rset.getDate(15));
				vCtrolCalibra.setDtAutoriza(rset.getDate(16));
				vCtrolCalibra.setICveUsuAutoriza(rset.getInt(17));
				vCtrolCalibra.setLAgotado(rset.getInt(18));
				vCtrolCalibra.setDtAgotado(rset.getDate(19));
				vCtrolCalibra.setLBaja(rset.getInt(20));
				vCtrolCalibra.setDtBaja(rset.getDate(21));
				vCtrolCalibra.setIAnioBaja(rset.getInt(22));
				vCtrolCalibra.setICveBaja(rset.getInt(23));
				vCtrolCalibra.setICveMarcaSust(rset.getInt(24));
				vCtrolCalibra.setCObservacion(rset.getString(25));
				vCtrolCalibra.setICveUsuPrepara(rset.getInt(26));

				if (rset.getString(27) != null) {
					vCtrolCalibra.setCDscUniMed(rset.getString(27));
				} else {
					vCtrolCalibra.setCDscUniMed("");
				}
				// if (rset.getString(28) != null) {
				vCtrolCalibra.setCDscSustancia(rset.getString(28));
				// }
				// else {
				// vCtrolCalibra.setCDscSustancia("");
				// }
				if (rset.getString(29) != null) {
					vCtrolCalibra.setCDscEmpleoCalib(rset.getString(29));
				} else {
					vCtrolCalibra.setCDscEmpleoCalib("");
				}
				if (rset.getString(30) != null) {
					vCtrolCalibra.setCDscMarcaSust(rset.getString(30));
				} else {
					vCtrolCalibra.setCDscMarcaSust("");
				}
				if (rset.getString(31) != null) {
					vCtrolCalibra.setCDscReactivo(rset.getString(31));
				} else {
					vCtrolCalibra.setCDscReactivo("");
				}
				if (rset.getString(32) != null) {
					vCtrolCalibra.setCDscTpoReact(rset.getString(32));
				} else {
					vCtrolCalibra.setCDscTpoReact("");

				}
				vCtrolCalibra.setICveTpoReact(rset.getInt(33));

				if (rset.getString(34) != null) {
					vCtrolCalibra.setCDscUsuPrepara(rset.getString(34));
				} else {
					vCtrolCalibra.setCDscUsuPrepara("");
				}
				if (rset.getString(35) != null) {
					vCtrolCalibra.setCDscUsuAutoriza(rset.getString(35));
				} else {
					vCtrolCalibra.setCDscUsuAutoriza("");
				}

				// ### NO SE ENCONTRABA EN EL QUERY...SE AGREGO ###
				// Agregado Rafael Alcocer Caldera 31/Ago/2006
				vCtrolCalibra.setLCual(rset.getInt(36));

				vCtrolCalibra.setCDscEmpleoCalib(rset.getString(37));

				// cDtPreparacion
				if (vCtrolCalibra.getDtPreparacion() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vCtrolCalibra.getDtPreparacion(), "/");
					vCtrolCalibra.setCDtPreparacion(cFecha);
				} else {
					vCtrolCalibra.setCDtPreparacion("");
					vCtrolCalibra.setDConcentExper(rset.getFloat(38));
					vCtrolCalibra.setICveEquipo(rset.getInt(39));
					vCtrolCalibra.setDtValoracion(rset.getDate(40));
					vCtrolCalibra.setDVolUtilizado(rset.getFloat(41));

					/* cDtCaducidad */
				}
				if (vCtrolCalibra.getDtCaducidad() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vCtrolCalibra.getDtCaducidad(), "/");
					vCtrolCalibra.setCDtCaducidad(cFecha);
				} else {
					vCtrolCalibra.setCDtCaducidad("");

					/* cDtAutoriza */
				}
				if (vCtrolCalibra.getDtAutoriza() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vCtrolCalibra.getDtAutoriza(), "/");
					vCtrolCalibra.setCDtAutoriza(cFecha);
				} else {
					vCtrolCalibra.setCDtAutoriza("");

					/* cDtAgotado */

				}
				if (vCtrolCalibra.getDtAgotado() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vCtrolCalibra.getDtAgotado(), "/");
					if (cFecha.compareTo("null") != 0
							&& cFecha.compareToIgnoreCase("") != 0) {
						vCtrolCalibra.setCDtAgotado(cFecha);
					}
				} else {
					vCtrolCalibra.setCDtAgotado("");

					/* cDtBaja */
				}
				if (vCtrolCalibra.getDtBaja() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vCtrolCalibra.getDtBaja(), "/");
					vCtrolCalibra.setCDtBaja(cFecha);
				} else {
					vCtrolCalibra.setCDtBaja("");

				}

				/**
				 * Agregado Rafael Alcocer Caldera 31/Ago/2006
				 * *******************************************
				 */
				vCtrolCalibra.setDConcentExper(rset.getFloat(38));

				if (rset.getDate(40) != null) {
					vCtrolCalibra.setDtValoracion(rset.getDate(40));
				}

				if (rset.getString(46) != null) {
					vCtrolCalibra.setCDscEquipo(rset.getString(46).trim());
				}

				if (rset.getString(47) != null) {
					vCtrolCalibra.setCNumSerie(rset.getString(47).trim());
				}

				if (rset.getString(48) != null) {
					vCtrolCalibra.setCModelo(rset.getString(48).trim());
				}

				if (rset.getString(49) != null) {
					vCtrolCalibra.setCCveEquipo(rset.getString(49).trim());
				}

				if (rset.getString(50) != null) {
					vCtrolCalibra.setICodigo(rset.getString(50).trim());
				}
				/**
				 * *******************************************
				 */

				vcCtrolCalibra.addElement(vCtrolCalibra);

				JXLSBean jxlsBean = new JXLSBean("");
				jxlsBean.addBean(vCtrolCalibra);
				jxlsBeanList.add(jxlsBean);
				/**
				 * ********************************************
				 */
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return vcCtrolCalibra;
		}
	}

	public Vector FindByAll2(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCtrolCalibra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCtrolCalibra vCtrolCalibra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select " + " iCveLaboratorio," + " iCveCtrolCalibra,"
					+ " iAnio," + " cLote," + " cDscCtrolCalibra,"
					+ " cDscBreve," + " iCveSustancia," + " dVolumen,"
					+ " dConcentracion," + " iCveEmpleoCalib," + " lCuantCual,"
					+ " iViales," + " dtPreparacion," + " dtCaducidad,"
					+ " dtAutoriza," + " iCveUsuAutoriza," + " lAgotado,"
					+ " dtAgotado," + " lBaja," + " dtBaja," + " iAnioBaja,"
					+ " iCveBaja," + " iCveMarcaSust," + " cObservacion"
					+ " from TOXCtrolCalibra " + cFiltro + cOrden;

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCtrolCalibra = new TVTOXCtrolCalibra();
				vCtrolCalibra.setICveLaboratorio(rset.getInt(1));
				vCtrolCalibra.setICveCtrolCalibra(rset.getInt(2));
				vCtrolCalibra.setIAnio(rset.getInt(3));
				vCtrolCalibra.setCLote(rset.getString(4));
				vCtrolCalibra.setCDscCtrolCalibra(rset.getString(5));
				vCtrolCalibra.setCDscBreve(rset.getString(6));
				vCtrolCalibra.setICveSustancia(rset.getInt(7));
				vCtrolCalibra.setDVolumen(rset.getFloat(8));
				vCtrolCalibra.setDConcentracion(rset.getFloat(9));
				vCtrolCalibra.setICveEmpleoCalib(rset.getInt(10));
				vCtrolCalibra.setLCuantCual(rset.getInt(11));
				vCtrolCalibra.setIViales(rset.getInt(12));
				vCtrolCalibra.setDtPreparacion(rset.getDate(13));
				vCtrolCalibra.setDtCaducidad(rset.getDate(14));
				vCtrolCalibra.setDtAutoriza(rset.getDate(15));
				vCtrolCalibra.setICveUsuAutoriza(rset.getInt(16));
				vCtrolCalibra.setLAgotado(rset.getInt(17));
				vCtrolCalibra.setDtAgotado(rset.getDate(18));
				vCtrolCalibra.setLBaja(rset.getInt(19));
				vCtrolCalibra.setDtBaja(rset.getDate(20));
				vCtrolCalibra.setIAnioBaja(rset.getInt(21));
				vCtrolCalibra.setICveBaja(rset.getInt(22));
				vCtrolCalibra.setICveMarcaSust(rset.getInt(23));
				vCtrolCalibra.setCObservacion(rset.getString(24));
				vCtrolCalibra.setCComboBaja(vCtrolCalibra.getCLote() + " - "
						+ vCtrolCalibra.getCDscBreve());
				vcCtrolCalibra.addElement(vCtrolCalibra);
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
			return vcCtrolCalibra;
		}
	}

	public Vector FindByCustomWhere(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCtrolCalibra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCtrolCalibra vCtrolCalibra = new TVTOXCtrolCalibra();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select " + "iCveLaboratorio," + "iCveCtrolCalibra,"
					+ "iAnio," + "cLote," + " cDscCtrolCalibra," + "cDscBreve,"
					+ "iCveSustancia," + "dVolumen," + "dConcentracion,"
					+ "iCveEmpleoCalib," + "lCuantCual," + "iViales,"
					+ "dtPreparacion," + "dtCaducidad," + "dtAutoriza,"
					+ "iCveUsuAutoriza," + "lAgotado," + "dtAgotado,"
					+ "lBaja," + "dtBaja," + "iAnioBaja," + "iCveBaja,"
					+ "iCveMarcaSust," + "cObservacion"
					+ " from TOXCtrolCalibra " + cCondicion;

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCtrolCalibra = new TVTOXCtrolCalibra();
				vCtrolCalibra.setICveLaboratorio(rset.getInt(1));
				vCtrolCalibra.setICveCtrolCalibra(rset.getInt(2));
				vCtrolCalibra.setIAnio(rset.getInt(3));
				vCtrolCalibra.setCLote(rset.getString(4));
				vCtrolCalibra.setCDscBreve(rset.getString(6));
				vCtrolCalibra.setICveSustancia(rset.getInt(7));
				vCtrolCalibra.setDVolumen(rset.getFloat(8));
				vCtrolCalibra.setDConcentracion(rset.getFloat(9));
				vCtrolCalibra.setICveEmpleoCalib(rset.getInt(10));
				vCtrolCalibra.setLCuantCual(rset.getInt(11));
				vCtrolCalibra.setIViales(rset.getInt(12));
				vCtrolCalibra.setDtPreparacion(rset.getDate(13));
				vCtrolCalibra.setDtCaducidad(rset.getDate(14));
				vCtrolCalibra.setDtAutoriza(rset.getDate(15));
				vCtrolCalibra.setICveUsuAutoriza(rset.getInt(16));
				vCtrolCalibra.setLAgotado(rset.getInt(17));
				vCtrolCalibra.setDtAgotado(rset.getDate(18));
				vCtrolCalibra.setLBaja(rset.getInt(19));
				vCtrolCalibra.setDtBaja(rset.getDate(20));
				vCtrolCalibra.setIAnioBaja(rset.getInt(21));
				vCtrolCalibra.setICveBaja(rset.getInt(22));
				vCtrolCalibra.setICveMarcaSust(rset.getInt(23));
				vCtrolCalibra.setCObservacion(rset.getString(24));
				vcCtrolCalibra.addElement(vCtrolCalibra);
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
			return vcCtrolCalibra;
		}
	}

	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iCve = 0;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXCtrolCalibra vCtrolCalibra = (TVTOXCtrolCalibra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "Select max(iCveCtrolCalibra) from TOXCtrolCalibra "
					+ " where iCveLaboratorio = "
					+ vCtrolCalibra.getICveLaboratorio();

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1) + 1;
			}

			if (rset != null) {
				rset.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			vCtrolCalibra.setICveCtrolCalibra(iCve);

			cSQL = "";
			cSQL = "insert into TOXCtrolCalibra(" + "iCveLaboratorio,"
					+ "iCveCtrolCalibra," + "iCveReactivo," + "iAnio,"
					+ "cLote," + "cDscCtrolCalibra," + "cDscBreve,"
					+ "iCveSustancia," + "dVolumen," + "dConcentracion,"
					+ "iCveEmpleoCalib," + "lCuantCual," + "iViales,"
					+ "dtPreparacion," + "dtCaducidad," + "dtAutoriza,"
					+ "iCveUsuAutoriza," + "lAgotado," + "dtAgotado,"
					+ "lBaja," + "dtBaja," + "iAnioBaja," + "iCveBaja,"
					+ "iCveMarcaSust," + "cObservacion," + "iCveUsuPrepara,"
					+ "lCual," + "dConcentExper, " + "iCveEquipo, "
					+ "dtValoracion, " + "dVolUtilizadado" +

					")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vCtrolCalibra.getICveLaboratorio());
			pstmt.setInt(2, vCtrolCalibra.getICveCtrolCalibra());
			pstmt.setInt(3, vCtrolCalibra.getICveReactivo());
			pstmt.setInt(4, vCtrolCalibra.getIAnio());
			pstmt.setString(5, vCtrolCalibra.getCLote());
			pstmt.setString(6, vCtrolCalibra.getCDscCtrolCalibra());
			pstmt.setString(7, vCtrolCalibra.getCDscBreve());
			pstmt.setInt(8, vCtrolCalibra.getICveSustancia());
			pstmt.setFloat(9, vCtrolCalibra.getDVolumen());
			pstmt.setFloat(10, vCtrolCalibra.getDConcentracion());
			pstmt.setInt(11, vCtrolCalibra.getICveEmpleoCalib());
			pstmt.setInt(12, vCtrolCalibra.getLCuantCual());
			pstmt.setInt(13, vCtrolCalibra.getIViales());
			pstmt.setDate(14, vCtrolCalibra.getDtPreparacion());
			pstmt.setDate(15, vCtrolCalibra.getDtCaducidad());
			pstmt.setDate(16, vCtrolCalibra.getDtAutoriza());
			pstmt.setInt(17, vCtrolCalibra.getICveUsuAutoriza());
			pstmt.setInt(18, vCtrolCalibra.getLAgotado());
			pstmt.setDate(19, vCtrolCalibra.getDtAgotado());
			pstmt.setInt(20, vCtrolCalibra.getLBaja());
			pstmt.setDate(21, vCtrolCalibra.getDtBaja());
			pstmt.setInt(22, vCtrolCalibra.getIAnioBaja());
			pstmt.setInt(23, vCtrolCalibra.getICveBaja());
			pstmt.setInt(24, vCtrolCalibra.getICveMarcaSust());
			pstmt.setString(25, vCtrolCalibra.getCObservacion());
			pstmt.setInt(26, vCtrolCalibra.getICveUsuPrepara());
			pstmt.setInt(27, vCtrolCalibra.getLCual());
			pstmt.setFloat(28, vCtrolCalibra.getDConcentExper());
			pstmt.setInt(29, vCtrolCalibra.getICveEquipo());
			pstmt.setDate(30, vCtrolCalibra.getDtValoracion());
			pstmt.setFloat(31, vCtrolCalibra.getDVolUtilizado());

			pstmt.executeUpdate();

			cClave = "" + vCtrolCalibra.getICveCtrolCalibra();
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
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
		}
	}

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
			TVTOXCtrolCalibra vCtrolCalibra = (TVTOXCtrolCalibra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXCtrolCalibra" + " set iCveReactivo= ?, "
					+ "iAnio= ?, " + "cLote= ?, " + "cDscCtrolCalibra= ?, "
					+ "cDscBreve= ?, " + "iCveSustancia= ?, " + "dVolumen= ?, "
					+ "dConcentracion= ?, " + "iCveEmpleoCalib= ?, "
					+ "lCuantCual= ?, " + "iViales= ?, " + "dtPreparacion= ?, "
					+ "dtCaducidad= ?, " + "dtAutoriza= ?, "
					+ "iCveUsuAutoriza= ?, " + "lAgotado= ?, "
					+ "dtAgotado= ?, " + "lBaja= ?, " + "dtBaja= ?, "
					+ "iAnioBaja= ?, " + "iCveBaja= ?, " + "iCveMarcaSust= ?, "
					+ "cObservacion= ?, " + "iCveUsuPrepara= ?, "
					+ "lCual = ?, " + "dConcentExper = ?, "
					+ "iCveEquipo = ?, " + "dtValoracion = ?, "
					+ "dVolUtilizadado = ? " + "where iCveLaboratorio = ? "
					+ " and iCveCtrolCalibra = ?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCtrolCalibra.getICveReactivo());
			pstmt.setInt(2, vCtrolCalibra.getIAnio());
			pstmt.setString(3, vCtrolCalibra.getCLote());
			pstmt.setString(4, vCtrolCalibra.getCDscCtrolCalibra());
			pstmt.setString(5, vCtrolCalibra.getCDscBreve());
			pstmt.setInt(6, vCtrolCalibra.getICveSustancia());
			pstmt.setFloat(7, vCtrolCalibra.getDVolumen());
			pstmt.setFloat(8, vCtrolCalibra.getDConcentracion());
			pstmt.setInt(9, vCtrolCalibra.getICveEmpleoCalib());
			pstmt.setInt(10, vCtrolCalibra.getLCuantCual());
			pstmt.setInt(11, vCtrolCalibra.getIViales());
			pstmt.setDate(12, vCtrolCalibra.getDtPreparacion());
			pstmt.setDate(13, vCtrolCalibra.getDtCaducidad());
			pstmt.setDate(14, vCtrolCalibra.getDtAutoriza());
			pstmt.setInt(15, vCtrolCalibra.getICveUsuAutoriza());
			pstmt.setInt(16, vCtrolCalibra.getLAgotado());
			pstmt.setDate(17, vCtrolCalibra.getDtAgotado());
			pstmt.setInt(18, vCtrolCalibra.getLBaja());
			pstmt.setDate(19, vCtrolCalibra.getDtBaja());
			pstmt.setInt(20, vCtrolCalibra.getIAnioBaja());
			pstmt.setInt(21, vCtrolCalibra.getICveBaja());
			pstmt.setInt(22, vCtrolCalibra.getICveMarcaSust());
			pstmt.setString(23, vCtrolCalibra.getCObservacion());
			pstmt.setInt(24, vCtrolCalibra.getICveUsuPrepara());
			pstmt.setInt(25, vCtrolCalibra.getLCual());

			pstmt.setFloat(26, vCtrolCalibra.getDConcentExper());
			pstmt.setInt(27, vCtrolCalibra.getICveEquipo());
			pstmt.setDate(28, vCtrolCalibra.getDtValoracion());
			pstmt.setFloat(29, vCtrolCalibra.getDVolUtilizado());

			pstmt.setInt(30, vCtrolCalibra.getICveLaboratorio());
			pstmt.setInt(31, vCtrolCalibra.getICveCtrolCalibra());

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
			TVTOXCtrolCalibra vCtrolCalibra = (TVTOXCtrolCalibra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXCtrolCalibra" + " where iCveLaboratorio = ?"
					+ " and iCveCtrolCalibra = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCtrolCalibra.getICveLaboratorio());
			pstmt.setInt(2, vCtrolCalibra.getICveCtrolCalibra());
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
				warn("delete.closeCtrolCalibra", ex2);
			}
			return cClave;
		}
	}

	private java.sql.Date getDate(String cDate) {
		java.sql.Date dtDate = null;
		try {
			dtDate = new java.sql.Date(dtFormatter.parse(cDate).getTime());
		} catch (ParseException e) {
		}
		return dtDate;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 11/Ago/2006 Sirve para generar el
	 * reporte: Registro y Comportamiento de Controles para An�lisis
	 * Confirmatorio
	 * 
	 * @param cFiltro
	 *            String
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean generaReporteXLS(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JXLSBean jxlsBean = new JXLSBean("");
		bean2 = new pg0703060110JXLSBean2();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " select CC.iCveCtrolCalibra, "
					+ "        CC.cLote, "
					+ "        CC.dConcentracion, "
					+ "        CC.dtValoracion, "
					+ "        CC.iCveEquipo, "
					+ "        CC.dConcentExper, "
					+ "        CC.dtAutoriza, "
					+ "        CC.iCveSustancia, "
					+ "        CC.dtValoracion, "
					+ "        A.iAnio, "
					+ "        A.iCveLaboratorio, "
					+ "        A.iCveLoteCualita, "
					+ "        A.iCveAnalisis, "
					+ "        ER.dResultado, "
					+ "        ER.iCveExamCualita, "
					+ "        E.cCveEquipo, "
					+ "        E.cDscEquipo, "
					+ "        E.cModelo , "
					+ "        E.cNumSerie "
					+ " from   TOXCtrolCalibra CC "
					+ " inner join TOXAnalisis A on A.iCveLaboratorio = CC.iCveLaboratorio "
					+ "                         and A.iCveCtrolCalibra = CC.iCveCtrolCalibra "
					+ "                         and A.lControl = 1 "
					+ " inner join TOXExamResult ER on ER.iAnio = A.iAnio "
					+ "                            and ER.iCveLaboratorio = A.iCveLaboratorio "
					+ "                            and ER.iCveLoteCualita = A.iCveLoteCualita "
					+ "                            and ER.iCveAnalisis    = A.iCveAnalisis "
					+ "                            and ER.iCveSustancia   = CC.iCveSustancia "
					+ " inner join TOXExamenCualita EC on EC.iAnio = A.iAnio "
					+ "                               and EC.iCveLaboratorio = A.iCveLaboratorio "
					+ "                               and EC.iCveLoteCualita = A.iCveLoteCualita "
					+ " inner join EQMEquipo E on E.iCveEquipo = EC.iCveEquipo "
					+ cFiltro;
			// " where CC.iCveEmpleoCalib = 4 " +
			// " and CC.lCual = 1 " +
			// " and CC.iCveCtrolCalibra = 5 " +

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			int iCount = 0;

			while (rset.next()) {

				pg0703060110XLSBean bean = new pg0703060110XLSBean();

				String cLote = "";
				Double dConcentracion = new Double(0);
				Double dConcentExper = new Double(0);
				String cDscEquipo = "";
				String cCveEquipo = "";
				String cModelo = "";
				String cNumSerie = "";
				java.util.Date dtAutoriza = null;

				/*
				 * Se requiere iCveLoteCualita y no cLote if
				 * (rset.getString("cLote") != null) { cLote =
				 * rset.getString("cLote"); }
				 */

				if (rset.getString("iCveLoteCualita") != null) {
					cLote = rset.getString("iCveLoteCualita");
				}

				if (new Double(rset.getDouble("dConcentracion")) != null) {
					dConcentracion = new Double(
							rset.getDouble("dConcentracion"));
				}

				if (new Double(rset.getDouble("dConcentExper")) != null) {
					dConcentExper = new Double(rset.getDouble("dConcentExper"));
				}

				if (rset.getString("cCveEquipo") != null) {
					cCveEquipo = rset.getString("cCveEquipo");
				}

				if (rset.getString("cDscEquipo") != null) {
					cDscEquipo = rset.getString("cDscEquipo");
				}

				if (rset.getString("cModelo") != null) {
					cModelo = rset.getString("cModelo");
				}

				if (rset.getString("cNumSerie") != null) {
					cNumSerie = rset.getString("cNumSerie");
				}

				if (rset.getDate("dtAutoriza") != null) {
					dtAutoriza = rset.getDate("dtAutoriza");
				}

				// Obtengo el corte de la sustancia perteneciente a An�lisis
				// Presuntivo
				TDTOXCorteXSust dToxCorteXSust = new TDTOXCorteXSust();
				Vector vcToxCorteXSust = dToxCorteXSust
						.FindByAll(" where toxcortexsust.lCuantCual = 0 "
								+ " and toxcortexsust.iCveSustancia = "
								+ rset.getInt("iCveSustancia"));

				for (int i = 0; i < vcToxCorteXSust.size(); i++) {
					TVTOXCorteXSust tvToxCorteXSust = (TVTOXCorteXSust) vcToxCorteXSust
							.get(i);
					bean.setDcorte(tvToxCorteXSust.getdCorte());
				}

				bean.setIcount(++iCount);
				bean.setIanio(new Integer(rset.getInt("iAnio")));
				bean.setDtpreparacion(new java.util.Date(rset.getDate(
						"dtValoracion").getTime()));
				bean.setClote(cLote);
				bean.setDresultado(new Double(rset.getDouble("dResultado")));

				bean2.setDconcentracion(dConcentracion);
				bean2.setDconcentexper(dConcentExper);
				bean2.setCcveequipo(cCveEquipo);
				bean2.setCdscequipo(cDscEquipo);
				bean2.setCmodelo(cModelo);
				bean2.setCnumserie(cNumSerie);
				bean2.setDtautoriza(dtAutoriza);
				bean2.setClote(cLote);

				jxlsBean.addBean(bean);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// warn("generaReporteXLS", ex);
			// throw new DAOException("generaReporteXLS");
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
				warn("generaReporteXLS.close", ex2);
			}

			return jxlsBean;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 05/Sep/2006
	 * 
	 * @return pg0703060100XLSBean2
	 */
	public pg0703060110JXLSBean2 getBean2() {
		return bean2;
	}

	/**
	 * Agregado Rafael Alcocer Caldera 24/Ago/2006
	 * 
	 * @return JXLSBean
	 */
	public List getJXLSBeanList() {
		return jxlsBeanList;
	}
}
