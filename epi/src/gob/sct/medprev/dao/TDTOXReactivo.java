package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.msc.*;

/**
 * <p>
 * Title: Data Acces Object de TOXReactivo DAO
 * </p>
 * <p>
 * Description: DAO de la entidad TOXReactivo
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */

public class TDTOXReactivo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private JXLSBean jxlsBean; // Agregado por Rafael Alcocer Caldera
								// 22/Sep/2006

	public TDTOXReactivo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXReactivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXReactivo vTOXReactivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveLaboratorio,"
					+ // 1
					"iCveReactivo,"
					+ // 2
					"iCveTpoReact,"
					+ // 3
					"iAnio,"
					+ // 4
					"iCodigo,"
					+ // 5
					"cDscBreve,"
					+ // 6
					"cDscReactivo,"
					+ // 7
					"TOXReactivo.iCveMarcaSust,"
					+ // 8
					"cPreparadoDe,"
					+ // 9
					"dVolumen,"
					+ // 10
					"iViales,"
					+ // 11
					"dtPreparacion,"
					+ // 12
					"iCveUsuPrepara,"
					+ // 13
					"dtCaducidad,"
					+ // 14
					"dtAutoriza,"
					+ // 15
					"iCveUsuAutoriza,"
					+ // 16
					"lAgotado,"
					+ // 17
					"dtAgotado,"
					+ // 18
					"lBaja,"
					+ // 19
					"dtBaja,"
					+ // 20
					"iCveBaja,"
					+ // 21
					"cObservacion, "
					+ // 22
					"dCorteNeg, "
					+ // 23
					"dCortePost, "
					+ // 24
					"lCuantCual, "
					+ // 25
					"lCual, "
					+ // 26
					"iCveSustancia,"
					+ // 27
					"cNumLote,"
					+ // 28
					"cNumCatalogo,"
					+ // 29
					"dConcentracion,"
					+ // 30
					"dtCaducAmp,"
					+ // 31
					"cProveedor,"
					+ // 32
					"iCveEquipoE1,"
					+ // 33
					"dConcentExper1,"
					+ // 34
					"dtValoracion1,"
					+ // 35
					"iCveEquipoE2,"
					+ // 36
					"dConcentExper2,"
					+ // 37
					"dtValoracion2,"
					+ // 38
					"dConcentTeor,"
					+ // 39
					"TOXMarcaSust.cDscMarcaSust"
					+ // 40
					" from TOXReactivo "
					+ "inner join TOXMarcaSust on TOXMarcaSust.iCveMarcaSust = TOXReactivo.iCveMarcaSust "
					+ cFiltro + cOrden;
			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				vTOXReactivo = new TVTOXReactivo();
				vTOXReactivo.setICveLaboratorio(rset.getInt(1));
				vTOXReactivo.setICveReactivo(rset.getInt(2));
				vTOXReactivo.setICveTpoReact(rset.getInt(3));
				vTOXReactivo.setIAnio(rset.getInt(4));
				vTOXReactivo.setICodigo(rset.getString(5));
				vTOXReactivo.setCDscBreve(rset.getString(6));
				vTOXReactivo.setCDscReactivo(rset.getString(7));
				vTOXReactivo.setICveMarcaSust(rset.getInt(8));
				vTOXReactivo.setCPreparadoDe(rset.getString(9));
				vTOXReactivo.setDVolumen(rset.getFloat(10));
				vTOXReactivo.setIViales(rset.getInt(11));
				vTOXReactivo.setDtPreparacion(rset.getDate(12));
				vTOXReactivo.setICveUsuPrepara(rset.getInt(13));
				vTOXReactivo.setDtCaducidad(rset.getDate(14));
				vTOXReactivo.setDtAutoriza(rset.getDate(15));
				vTOXReactivo.setICveUsuAutoriza(rset.getInt(16));
				vTOXReactivo.setLAgotado(rset.getInt(17));
				vTOXReactivo.setDtAgotado(rset.getDate(18));
				vTOXReactivo.setLBaja(rset.getInt(19));
				vTOXReactivo.setDtBaja(rset.getDate(20));
				vTOXReactivo.setICveBaja(rset.getInt(21));
				vTOXReactivo.setCObservacion(rset.getString(22));
				vTOXReactivo.setDCorteNeg(rset.getFloat(23));
				vTOXReactivo.setDCortePost(rset.getFloat(24));
				vTOXReactivo.setLCuantCual(rset.getInt(25));
				vTOXReactivo.setLCual(rset.getInt(26));
				vTOXReactivo.setICveSustancia(rset.getInt(27));
				vTOXReactivo.setCNumLote(rset.getString(28));
				vTOXReactivo.setCNumCatalogo(rset.getString(29));
				vTOXReactivo.setDConcentracion(rset.getDouble(30));
				vTOXReactivo.setDtCaducAmp(rset.getDate(31));
				vTOXReactivo.setCProveedor(rset.getString(32));
				vTOXReactivo.setICveEquipoE1(rset.getInt(33));
				vTOXReactivo.setDConcentExper1(rset.getDouble(34));
				vTOXReactivo.setDtValoracion1(rset.getDate(35));
				vTOXReactivo.setICveEquipoE2(rset.getInt(36));
				vTOXReactivo.setDConcentExper2(rset.getDouble(37));
				vTOXReactivo.setDtValoracion2(rset.getDate(38));
				vTOXReactivo.setDConcentTeor(rset.getDouble(39));
				vTOXReactivo.setCDscMarcaSust(rset.getString(40));

				vTOXReactivo.setCComboBaja(vTOXReactivo.getICodigo() + " - "
						+ vTOXReactivo.getCDscBreve());

				vcTOXReactivo.addElement(vTOXReactivo);
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
			return vcTOXReactivo;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector findByWhere(String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXReactivo = new Vector();
		String cPreparacion = "";
		TFechas dtFecha = new TFechas();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXReactivo vTOXReactivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "R.iCveLaboratorio,"
					+ // 1
					"R.iCveReactivo,"
					+ // 2
					"R.iCveTpoReact,"
					+ // 3
					"R.iAnio,"
					+ // 4
					"R.iCodigo,"
					+ // 5
					"R.cDscBreve,"
					+ // 6
					"R.cDscReactivo,"
					+ // 7
					"R.iCveMarcaSust,"
					+ // 8
					"R.cPreparadoDe,"
					+ // 9
					"R.dVolumen,"
					+ // 10
					"R.iViales,"
					+ // 11
					"R.dtPreparacion,"
					+ // 12
					"R.iCveUsuPrepara,"
					+ // 13
					"R.dtCaducidad,"
					+ // 14
					"R.dtAutoriza,"
					+ // 15
					"R.iCveUsuAutoriza,"
					+ // 16
					"R.lAgotado,"
					+ // 17
					"R.dtAgotado,"
					+ // 18
					"R.lBaja,"
					+ // 19
					"R.dtBaja,"
					+ // 20
					"R.iCveBaja,"
					+ // 21
					"R.cObservacion,"
					+ // 22
					"R.dCorteNeg,"
					+ // 23
					"R.dCortePost,"
					+ // 24
					"R.lCuantCual, "
					+ // 25
					"T.cDscTpoReact, "
					+ // 26
					"M.cDscMarcaSust, "
					+ // 27
					"a.cNombre||' '||a.cApPaterno||' '||a.cApMaterno as cNomCompletoPrepara, "
					+ // 28
					"b.cNombre||' '||b.cApPaterno||' '||b.cApMaterno as cNomCompletoAutoriza, "
					+ // 29
					"R.lCual, "
					+ // 30
					"iCveSustancia,"
					+ // 31
					"cNumLote,"
					+ // 32
					"cNumCatalogo,"
					+ // 33
					"dConcentracion,"
					+ // 34
					"dtCaducAmp,"
					+ // 35
					"cProveedor,"
					+ // 36
					"iCveEquipoE1,"
					+ // 37
					"dConcentExper1,"
					+ // 38
					"dtValoracion1,"
					+ // 39
					"iCveEquipoE2,"
					+ // 40
					"dConcentExper2,"
					+ // 41
					"dtValoracion2,"
					+ // 42
					"dConcentTeor "
					+ // 43
					" from "
					+ "TOXReactivo R "
					+ "left join SEGUsuario a on (R.iCveUsuPrepara = a.iCveUsuario) "
					+ "left join SEGUsuario b on (R.iCveUsuAutoriza = b.iCveUsuario) "
					+ ",TOXTpoReact T, TOXMarcaSust M  "
					+ "where R.iCveTpoReact = T.iCveTpoReact  "
					+ "and R.iCveMarcaSust = M.iCveMarcaSust " + cFiltro
					+ cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			jxlsBean = new JXLSBean("SOLUCION PATRON"); // Agregado por Rafael
														// Alcocer Caldera
														// 22/Sep/2006

			while (rset.next()) {
				vTOXReactivo = new TVTOXReactivo();
				vTOXReactivo.setICveLaboratorio(rset.getInt(1));
				vTOXReactivo.setICveReactivo(rset.getInt(2));
				vTOXReactivo.setICveTpoReact(rset.getInt(3));
				vTOXReactivo.setIAnio(rset.getInt(4));
				vTOXReactivo.setICodigo(rset.getString(5));
				vTOXReactivo.setCDscBreve(rset.getString(6));
				vTOXReactivo.setCDscReactivo(rset.getString(7));
				vTOXReactivo.setICveMarcaSust(rset.getInt(8));
				vTOXReactivo.setCPreparadoDe(rset.getString(9));
				vTOXReactivo.setDVolumen(rset.getFloat(10));
				vTOXReactivo.setIViales(rset.getInt(11));
				vTOXReactivo.setDtPreparacion(rset.getDate(12));
				vTOXReactivo.setICveUsuPrepara(rset.getInt(13));
				vTOXReactivo.setDtCaducidad(rset.getDate(14));
				vTOXReactivo.setDtAutoriza(rset.getDate(15));
				vTOXReactivo.setICveUsuAutoriza(rset.getInt(16));
				vTOXReactivo.setLAgotado(rset.getInt(17));
				vTOXReactivo.setDtAgotado(rset.getDate(18));
				vTOXReactivo.setLBaja(rset.getInt(19));
				vTOXReactivo.setDtBaja(rset.getDate(20));
				vTOXReactivo.setICveBaja(rset.getInt(21));
				vTOXReactivo.setCObservacion(rset.getString(22));
				cPreparacion = "";// dtFecha.getFechaDDMMYYYY(vTOXReactivo.getDtPreparacion(),"/");
				vTOXReactivo.setCPreparacion(cPreparacion);
				vTOXReactivo.setDCorteNeg(rset.getFloat(23));
				vTOXReactivo.setDCortePost(rset.getFloat(24));
				vTOXReactivo.setLCuantCual(rset.getInt(25));
				vTOXReactivo.setCDscTpoReact(rset.getString(26));
				vTOXReactivo.setCDscMarcaSust(rset.getString(27));
				vTOXReactivo.setCNomCompletoPrepara(rset.getString(28));
				vTOXReactivo.setCNomCompletoAutoriza(rset.getString(29));
				vTOXReactivo.setLCual(rset.getInt(30));
				vTOXReactivo.setICveSustancia(rset.getInt(31));
				vTOXReactivo.setCNumLote(rset.getString(32));
				vTOXReactivo.setCNumCatalogo(rset.getString(33));
				vTOXReactivo.setDConcentracion(rset.getDouble(34));
				vTOXReactivo.setDtCaducAmp(rset.getDate(35));
				vTOXReactivo.setCProveedor(rset.getString(36));
				vTOXReactivo.setICveEquipoE1(rset.getInt(37));
				vTOXReactivo.setDConcentExper1(rset.getDouble(38));
				vTOXReactivo.setDtValoracion1(rset.getDate(39));
				vTOXReactivo.setICveEquipoE2(rset.getInt(40));
				vTOXReactivo.setDConcentExper2(rset.getDouble(41));
				vTOXReactivo.setDtValoracion2(rset.getDate(42));
				vTOXReactivo.setDConcentTeor(rset.getDouble(43));

				TDEQMEquipo dEQMEquipo = new TDEQMEquipo();
				Vector vcEQMEquipo1 = dEQMEquipo
						.FindByAll(" where iCveEquipo = "
								+ vTOXReactivo.getICveEquipoE1());

				for (int i = 0; i < vcEQMEquipo1.size(); i++) {
					TVEQMEquipo tvEQMEquipo = (TVEQMEquipo) vcEQMEquipo1.get(i);
					vTOXReactivo.setCCveEquipo1(tvEQMEquipo.getCCveEquipo());
					vTOXReactivo.setCDscEquipo1(tvEQMEquipo.getCDscEquipo());
					vTOXReactivo.setCNumSerie1(tvEQMEquipo.getCNumSerie());
					vTOXReactivo.setCModelo1(tvEQMEquipo.getCModelo());
				}

				Vector vcEQMEquipo2 = dEQMEquipo
						.FindByAll(" where iCveEquipo = "
								+ vTOXReactivo.getICveEquipoE2());

				for (int i = 0; i < vcEQMEquipo2.size(); i++) {
					TVEQMEquipo tvEQMEquipo = (TVEQMEquipo) vcEQMEquipo2.get(i);
					vTOXReactivo.setCCveEquipo2(tvEQMEquipo.getCCveEquipo());
					vTOXReactivo.setCDscEquipo2(tvEQMEquipo.getCDscEquipo());
					vTOXReactivo.setCNumSerie2(tvEQMEquipo.getCNumSerie());
					vTOXReactivo.setCModelo2(tvEQMEquipo.getCModelo());
				}

				TDTOXSustancia dToxSustancia = new TDTOXSustancia();
				Vector vcToxSustancia = dToxSustancia.FindByAll(
						" where iCveSustancia = "
								+ vTOXReactivo.getICveSustancia(),
						" order by cDscSustancia ");

				for (int i = 0; i < vcToxSustancia.size(); i++) {
					TVTOXSustancia tvToxSustancia = (TVTOXSustancia) vcToxSustancia
							.get(i);
					vTOXReactivo.setCDscSustancia(tvToxSustancia
							.getcDscSustancia());
				}

				vcTOXReactivo.addElement(vTOXReactivo);

				jxlsBean.addBean(vTOXReactivo); // Agregado por Rafael Alcocer
												// Caldera 22/Sep/2006
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
			return vcTOXReactivo;
		}
	}

	/* FindByReactivos - Metodo Abreviado Para Llenar Combos, Clave, Descripcion */

	public Vector FindByReactivos(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcReactivos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXReactivo vReactivo = new TVTOXReactivo();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "Select " + "R.iCveReactivo, " + "R.iCveLaboratorio, "
					+ "R.lAgotado, " + "R.lBaja, " + "R.lCuantCual, "
					+ "R.iCveTpoReact, " + "R.cDscBreve, " + "R.lCual, "
					+ "R.iCodigo " + "from TOXReactivo R "
					+ "where  R.lBaja = 0 " + "and  R.lAgotado = 0 " + cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vReactivo = new TVTOXReactivo();
				vReactivo.setICveReactivo(rset.getInt(1));
				vReactivo.setICveLaboratorio(rset.getInt(2));
				vReactivo.setLAgotado(rset.getInt(3));
				vReactivo.setLBaja(rset.getInt(4));
				vReactivo.setLCuantCual(rset.getInt(5));
				vReactivo.setICveTpoReact(rset.getInt(6));
				vReactivo.setCDscBreve(rset.getString(7));
				vReactivo.setLCual(rset.getInt(8));
				vReactivo.setICodigo(rset.getString(9));
				vcReactivos.addElement(vReactivo);
			}
		} catch (Exception ex) {
			warn("FindByReactivos", ex);
			throw new DAOException("FindByReactivos");
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
				warn("FindByReactivos.close", ex2);
			}
			return vcReactivos;
		}
	}

	// Busca Reactivo sin importar el agotamieto solo para despliegue
	public Vector FindByReactivosNV(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcReactivos = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXReactivo vReactivo = new TVTOXReactivo();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "Select " + "R.iCveReactivo, " + "R.iCveLaboratorio, "
					+ "R.lAgotado, " + "R.lBaja, " + "R.lCuantCual, "
					+ "R.iCveTpoReact, " + "R.cDscBreve, " + "R.lCual, "
					+ "R.iCodigo " + "from TOXReactivo R " + cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vReactivo = new TVTOXReactivo();
				vReactivo.setICveReactivo(rset.getInt(1));
				vReactivo.setICveLaboratorio(rset.getInt(2));
				vReactivo.setLAgotado(rset.getInt(3));
				vReactivo.setLBaja(rset.getInt(4));
				vReactivo.setLCuantCual(rset.getInt(5));
				vReactivo.setICveTpoReact(rset.getInt(6));
				vReactivo.setCDscBreve(rset.getString(7));
				vReactivo.setLCual(rset.getInt(8));
				vReactivo.setICodigo(rset.getString(9));
				vcReactivos.addElement(vReactivo);
			}
		} catch (Exception ex) {
			warn("FindByReactivosNV", ex);
			throw new DAOException("FindByReactivosNV");
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
				warn("FindByReactivosNV.close", ex2);
			}
			return vcReactivos;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iClave = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXReactivo vTOXReactivo = (TVTOXReactivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			cSQL = "select max(iCveReactivo) from TOXReactivo where iCveLaboratorio = "
					+ vTOXReactivo.getICveLaboratorio();
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				iClave = rset.getInt(1) + 1;
			}

			if (rset != null) {
				rset.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			vTOXReactivo.setICveReactivo(iClave);

			cSQL = "insert into TOXReactivo(" + "iCveLaboratorio," + // 1
					"iCveReactivo," + // 2
					"iCveTpoReact," + // 3
					"iAnio," + // 4
					"iCodigo," + // 5
					"cDscBreve," + // 6
					"cDscReactivo," + // 7
					"iCveMarcaSust," + // 8
					"cPreparadoDe," + // 9
					"dVolumen," + // 10
					"iViales," + // 11
					"dtPreparacion," + // 12
					"iCveUsuPrepara," + // 13
					"dtCaducidad," + // 14
					"dtAutoriza," + // 15
					"iCveUsuAutoriza," + // 16
					"lAgotado," + // 17
					"dtAgotado," + // 18
					"lBaja," + // 19
					"dtBaja," + // 20
					"iCveBaja," + // 21
					"cObservacion," + // 22
					"dCorteNeg," + // 23
					"dCortePost," + // 24
					"lCuantCual, " + // 25
					"lCual, " + // 26
					// nuevos campos a ingresar a la BD
					"dConcentTeor," + // 27
					"iCveSustancia," + // 28
					"cNumLote," + // 29
					"cNumCatalogo," + // 30
					"dConcentracion," + // 31
					"dtCaducAmp," + // 32
					"cProveedor," + // 33
					"iCveEquipoE1," + // 34
					"dConcentExper1," + // 35
					"dtValoracion1," + // 36
					"iCveEquipoE2," + // 37
					"dConcentExper2," + // 38
					"dtValoracion2" + // 39

					")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vTOXReactivo.getICveLaboratorio());
			pstmt.setInt(2, vTOXReactivo.getICveReactivo());
			pstmt.setInt(3, vTOXReactivo.getICveTpoReact());
			pstmt.setInt(4, vTOXReactivo.getIAnio());
			pstmt.setString(5, vTOXReactivo.getICodigo());
			pstmt.setString(6, vTOXReactivo.getCDscBreve());
			pstmt.setString(7, vTOXReactivo.getCDscReactivo());
			pstmt.setInt(8, vTOXReactivo.getICveMarcaSust());
			pstmt.setString(9, vTOXReactivo.getCPreparadoDe());
			pstmt.setFloat(10, vTOXReactivo.getDVolumen());
			pstmt.setInt(11, vTOXReactivo.getIViales());
			pstmt.setDate(12, vTOXReactivo.getDtPreparacion());
			pstmt.setInt(13, vTOXReactivo.getICveUsuPrepara());
			pstmt.setDate(14, vTOXReactivo.getDtCaducidad());
			pstmt.setDate(15, vTOXReactivo.getDtAutoriza());
			pstmt.setInt(16, vTOXReactivo.getICveUsuAutoriza());
			pstmt.setInt(17, vTOXReactivo.getLAgotado());
			pstmt.setDate(18, vTOXReactivo.getDtAgotado());
			pstmt.setInt(19, vTOXReactivo.getLBaja());
			pstmt.setDate(20, vTOXReactivo.getDtBaja());
			pstmt.setInt(21, vTOXReactivo.getICveBaja());
			pstmt.setString(22, vTOXReactivo.getCObservacion());
			pstmt.setFloat(23, vTOXReactivo.getDCorteNeg());
			pstmt.setFloat(24, vTOXReactivo.getDCortePost());
			pstmt.setInt(25, vTOXReactivo.getLCuantCual());
			pstmt.setInt(26, vTOXReactivo.getLCual());
			pstmt.setDouble(27, vTOXReactivo.getDConcentTeor());
			pstmt.setInt(28, vTOXReactivo.getICveSustancia());
			pstmt.setString(29, vTOXReactivo.getCNumLote());
			pstmt.setString(30, vTOXReactivo.getCNumCatalogo());
			pstmt.setDouble(31, vTOXReactivo.getDConcentracion());
			pstmt.setDate(32, vTOXReactivo.getDtCaducAmp());
			pstmt.setString(33, vTOXReactivo.getCProveedor());
			// pstmt.setString(34,vTOXReactivo.getCObservaAmp());
			pstmt.setInt(34, vTOXReactivo.getICveEquipoE1());
			pstmt.setDouble(35, vTOXReactivo.getDConcentExper1());
			pstmt.setDate(36, vTOXReactivo.getDtValoracion1());
			pstmt.setInt(37, vTOXReactivo.getICveEquipoE2());
			pstmt.setDouble(38, vTOXReactivo.getDConcentExper2());
			pstmt.setDate(39, vTOXReactivo.getDtValoracion2());
			pstmt.executeUpdate();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return "" + iClave;
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
			TVTOXReactivo vTOXReactivo = (TVTOXReactivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXReactivo" + " set iCveTpoReact= ?, " + // 1
					"iAnio= ?, " + // 2
					"iCodigo= ?, " + // 3
					"cDscBreve= ?, " + // 4
					"cDscReactivo= ?, " + // 5
					"iCveMarcaSust= ?, " + // 6
					"cPreparadoDe= ?, " + // 7
					"dVolumen= ?, " + // 8
					"iViales= ?, " + // 9
					"dtPreparacion= ?, " + // 10
					"iCveUsuPrepara= ?, " + // 11
					"dtCaducidad= ?, " + // 12
					"dtAutoriza= ?, " + // 13
					"iCveUsuAutoriza= ?, " + // 14
					"lAgotado= ?, " + // 15
					"dtAgotado= ?, " + // 16
					"lBaja= ?, " + // 17
					"dtBaja= ?, " + // 18
					"iCveBaja= ?, " + // 19
					"cObservacion= ?, " + // 20
					"dCorteNeg = ?, " + // 21
					"dCortePost = ?, " + // 22
					"lCuantCual = ?, " + // 23
					"lCual = ?, " + // 24
					"dConcentTeor = ?, " + // 25
					"iCveSustancia = ?, " + // 26
					"cNumLote = ?, " + // 27
					"cNumCatalogo = ?, " + // 28
					"dConcentracion = ?, " + // 29
					"dtCaducAmp = ?, " + // 30
					"cProveedor = ?, " + // 31
					"iCveEquipoE1 = ?, " + // 32
					"dConcentExper1 = ?, " + // 33
					"dtValoracion1 = ?, " + // 34
					"iCveEquipoE2 = ?, " + // 35
					"dConcentExper2 = ?, " + // 36
					"dtValoracion2 = ? " + // 37

					"where iCveLaboratorio = ? " + // 38
					" and iCveReactivo = ?"; // 39
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXReactivo.getICveTpoReact());
			pstmt.setInt(2, vTOXReactivo.getIAnio());
			pstmt.setString(3, vTOXReactivo.getICodigo());
			pstmt.setString(4, vTOXReactivo.getCDscBreve());
			pstmt.setString(5, vTOXReactivo.getCDscReactivo());
			pstmt.setInt(6, vTOXReactivo.getICveMarcaSust());
			pstmt.setString(7, vTOXReactivo.getCPreparadoDe());
			pstmt.setFloat(8, vTOXReactivo.getDVolumen());
			pstmt.setInt(9, vTOXReactivo.getIViales());
			pstmt.setDate(10, vTOXReactivo.getDtPreparacion());
			pstmt.setInt(11, vTOXReactivo.getICveUsuPrepara());
			pstmt.setDate(12, vTOXReactivo.getDtCaducidad());
			pstmt.setDate(13, vTOXReactivo.getDtAutoriza());
			pstmt.setInt(14, vTOXReactivo.getICveUsuAutoriza());
			pstmt.setInt(15, vTOXReactivo.getLAgotado());
			pstmt.setDate(16, vTOXReactivo.getDtAgotado());
			pstmt.setInt(17, vTOXReactivo.getLBaja());
			pstmt.setDate(18, vTOXReactivo.getDtBaja());
			pstmt.setInt(19, vTOXReactivo.getICveBaja());
			pstmt.setString(20, vTOXReactivo.getCObservacion());
			pstmt.setFloat(21, vTOXReactivo.getDCorteNeg());
			pstmt.setFloat(22, vTOXReactivo.getDCortePost());
			pstmt.setInt(23, vTOXReactivo.getLCuantCual());
			pstmt.setInt(24, vTOXReactivo.getLCual());

			pstmt.setDouble(25, vTOXReactivo.getDConcentTeor());
			pstmt.setInt(26, vTOXReactivo.getICveSustancia());
			pstmt.setString(27, vTOXReactivo.getCNumLote());
			pstmt.setString(28, vTOXReactivo.getCNumCatalogo());
			pstmt.setDouble(29, vTOXReactivo.getDConcentracion());
			pstmt.setDate(30, vTOXReactivo.getDtCaducAmp());
			pstmt.setString(31, vTOXReactivo.getCProveedor());
			pstmt.setInt(32, vTOXReactivo.getICveEquipoE1());
			pstmt.setDouble(33, vTOXReactivo.getDConcentExper1());
			pstmt.setDate(34, vTOXReactivo.getDtValoracion1());
			pstmt.setInt(35, vTOXReactivo.getICveEquipoE2());
			pstmt.setDouble(36, vTOXReactivo.getDConcentExper2());
			pstmt.setDate(37, vTOXReactivo.getDtValoracion2());

			// where...

			int lab = vTOXReactivo.getICveLaboratorio();
			int react = vTOXReactivo.getICveReactivo();

			pstmt.setInt(38, lab);
			pstmt.setInt(39, react);

			pstmt.executeUpdate();
			cClave = "" + vTOXReactivo.getICveReactivo();
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			TVTOXReactivo vTOXReactivo = (TVTOXReactivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXReactivo" + " where iCveLaboratorio = ?"
					+ " and iCveReactivo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXReactivo.getICveLaboratorio());
			pstmt.setInt(2, vTOXReactivo.getICveReactivo());
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
				warn("delete.closeTOXReactivo", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All TpoReact
	 */
	public Vector FindByAllTpoReact(String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXReactivo = new Vector();
		String cPreparacion = "";
		TFechas dtFecha = new TFechas();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXReactivo vTOXReactivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "R.iCveLaboratorio," + "R.iCveReactivo,"
					+ "R.iCveTpoReact," + "R.iAnio," + "R.iCodigo,"
					+ "R.cDscBreve," + "R.cDscReactivo," + "R.iCveMarcaSust,"
					+ "R.cPreparadoDe," + "R.dVolumen," + "R.iViales,"
					+ "R.dtPreparacion," + "R.iCveUsuPrepara,"
					+ "R.dtCaducidad," + "R.dtAutoriza," + "R.iCveUsuAutoriza,"
					+ "R.lAgotado," + "R.dtAgotado," + "R.lBaja," + "R.dtBaja,"
					+ "R.iCveBaja," + "R.cObservacion," + "T.cDscTpoReact "
					+ " from TOXReactivo R, TOXTpoReact T"
					+ " where R.iCveTpoReact = T.iCveTpoReact " + cFiltro
					+ cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vTOXReactivo = new TVTOXReactivo();
				vTOXReactivo.setICveLaboratorio(rset.getInt(1));
				vTOXReactivo.setICveReactivo(rset.getInt(2));
				vTOXReactivo.setICveTpoReact(rset.getInt(3));
				vTOXReactivo.setIAnio(rset.getInt(4));
				vTOXReactivo.setICodigo(rset.getString(5));
				vTOXReactivo.setCDscBreve(rset.getString(6));
				vTOXReactivo.setCDscReactivo(rset.getString(7));
				vTOXReactivo.setICveMarcaSust(rset.getInt(8));
				vTOXReactivo.setCPreparadoDe(rset.getString(9));
				vTOXReactivo.setDVolumen(rset.getFloat(10));
				vTOXReactivo.setIViales(rset.getInt(11));
				vTOXReactivo.setDtPreparacion(rset.getDate(12));
				vTOXReactivo.setICveUsuPrepara(rset.getInt(13));
				vTOXReactivo.setDtCaducidad(rset.getDate(14));
				vTOXReactivo.setDtAutoriza(rset.getDate(15));
				vTOXReactivo.setICveUsuAutoriza(rset.getInt(16));
				vTOXReactivo.setLAgotado(rset.getInt(17));
				vTOXReactivo.setDtAgotado(rset.getDate(18));
				vTOXReactivo.setLBaja(rset.getInt(19));
				vTOXReactivo.setDtBaja(rset.getDate(20));
				vTOXReactivo.setICveBaja(rset.getInt(21));
				vTOXReactivo.setCObservacion(rset.getString(22));
				cPreparacion = dtFecha.getFechaDDMMYYYY(
						vTOXReactivo.getDtPreparacion(), "/");
				vTOXReactivo.setCPreparacion(cPreparacion);
				vTOXReactivo.setCDscTpoReact(rset.getString(23));
				vcTOXReactivo.addElement(vTOXReactivo);
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
			return vcTOXReactivo;
		}
	}

	/**
	 * Agregada por Rafael Alcocer Caldera 22/Ago/2006
	 * 
	 * @param cFiltro
	 *            String
	 * @param cOrden
	 *            String
	 * @throws DAOException
	 * @return ResultSet
	 */
	public ResultSet getResultSet(String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select R.iCveLaboratorio, "
					+ "        R.iCveReactivo, "
					+ "        R.iCveTpoReact, "
					+ "        R.iAnio, "
					+ "        R.iCodigo, "
					+ "        R.cDscBreve, "
					+ "        R.cDscReactivo, "
					+ "        R.iCveMarcaSust, "
					+ "        R.cPreparadoDe, "
					+ "        R.dVolumen, "
					+ "        R.iViales, "
					+ "        R.dtPreparacion, "
					+ "        R.iCveUsuPrepara, "
					+ "        R.dtCaducidad, "
					+ "        R.dtAutoriza, "
					+ "        R.iCveUsuAutoriza, "
					+ "        R.lAgotado, "
					+ "        R.dtAgotado, "
					+ "        R.lBaja, "
					+ "        R.dtBaja,"
					+ "        R.iCveBaja,"
					+ "        R.cObservacion,"
					+ "        R.lCuantCual, "
					+ "        R.dCorteNeg, "
					+ "        R.dCortePost, "
					+ "        R.lCual, "
					+ "        R.dConcentTeor, "
					+ "        R.iCveSustancia, "
					+ "        R.cNumLote, "
					+ "        R.cNumCatalogo, "
					+ "        R.dConcentracion, "
					+ "        R.cProveedor, "
					+ "        R.iCveEquipoE1, "
					+ "        R.dConcentExper1, "
					+ "        R.dtValoracion1, "
					+ "        R.iCveEquipoE2, "
					+ "        R.dConcentExper2, "
					+ "        R.dtValoracion2, "
					+ "        R.dtCaducaMP, "
					+ "        T.cDscTpoReact, "
					+ "        M.cDscMarcaSust, "
					+ "        a.cNombre||' '||a.cApPaterno||' '||a.cApMaterno as cNomCompletoPrepara, "
					+ "        b.cNombre||' '||b.cApPaterno||' '||b.cApMaterno as cNomCompletoAutoriza, "
					+ "        EQ.iCveEquipo, "
					+ "        EQ.lCuantCual, "
					+ "        EQ.iCarruseles, "
					+ "        EQ.iPosiciones, "
					+ "        EQMEquipo.cDscEquipo, "
					+ "        EQMEquipo.cNumSerie, "
					+ "        EQMEquipo.cModelo, "
					+ "        EQMEquipo.cCveEquipo "
					+ " from TOXReactivo R"
					+ " left join TOXEquipo EQ on EQ.iCveEquipo = R.iCveEquipoE1 "
					+ " left join EQMEquipo on EQMEquipo.iCveEquipo = EQ.iCveEquipo "
					+ " left join SEGUsuario a on (R.iCveUsuPrepara = a.iCveUsuario) "
					+ " left join SEGUsuario b on (R.iCveUsuAutoriza = b.iCveUsuario) "
					+ " ,    TOXTpoReact T, " + "      TOXMarcaSust M  "
					+ cFiltro + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
		} catch (Exception ex) {
			warn("getResultSet", ex);
			throw new DAOException("getResultSet");
		}

		return rset;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 22/Sep/2006
	 * 
	 * @return JXLSBean
	 */
	public JXLSBean getJXLSBean() {
		return jxlsBean;
	}
}
