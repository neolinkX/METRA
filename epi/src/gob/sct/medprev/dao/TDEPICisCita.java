package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de EPICisCita DAO
 * </p>
 * <p>
 * Description: DAO Tabla EPICisCita
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco A. Gonz�lez Paz
 * @version 1.0
 */
 
public class TDEPICisCita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");

	public TDEPICisCita() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcEPICisCita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVEPICisCita vEPICisCita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+ "EPICisCita.cCalle,"
					+ "EPICisCita.cNumExt,"
					+ "EPICisCita.cNumInt,"
					+ "EPICisCita.cColonia,"
					+ "EPICisCita.iCP,"
					+ "EPICisCita.cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "EPICisCita.cTel,"
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "EPICisCita.iCveModulo, "
					+ "GRLModulo.cDscModulo, "
					+
					// Licencias
					"EPICisCita.cLicencia,"
					+ "EPICisCita.cLicenciaInt,"
					+ "GRLCategoria.cDscBreve "
					+ "from EPICisCita "
					+ " join GRLUnimed ON EPICisCita.iCveUniMed = GRLUnimed.iCveUniMed "
					+ " join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans"
					+ " join GRLPuesto ON EPICisCita.iCvePuesto = GRLPuesto.iCvePuesto "
					+ " and  EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  "
					+ " join GRLMotivo ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo "
					+ " and  GRLMotivo.iCveProceso = 1 "
					+ " join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion "
					+ " join GRLModulo ON EPICisCita.iCveModulo = GRLModulo.iCveModulo "
					+ " and  EPICisCita.iCveUniMed = GRLModulo.iCveUniMed "
					+ " inner join GRLCategoria ON GRLCategoria.iCveMdoTrans = EPICisCita.iCveMdoTrans "
					+ " and GRLCategoria.iCveCategoria = EPICisCita.iCveCategoria ";

			cSQLPEM = "select "
					+ "GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "PGRL.cNombre,"
					+ "EGRL.cNombre,"
					+ "MGRL.cNombre "
					+ "from EPICisCita "
					+ " join GRLPais ON EPICisCita.iCvePaisNac = GRLPais.iCvePais "
					+ " join GRLEntidadFed ON EPICisCita.iCvePaisNac = GRLEntidadFed.iCvePais"
					+ " and  EPICisCita.iCveEstadoNac = GRLEntidadFed.iCveEntidadFed "
					+ " join GRLPais PGRL ON EPICisCita.iCvePais = PGRL.iCvePais "
					+ " join GRLEntidadFed EGRL ON EPICisCita.iCvePais = EGRL.iCvePais "
					+ " and  EPICisCita.iCveEstado = EGRL.iCveEntidadFed "
					+ " join GRLMunicipio MGRL ON EPICisCita.iCvePais = MGRL.iCvePais "
					+ " and  EPICisCita.iCveEstado = MGRL.iCveEntidadFed "
					+ " and  EPICisCita.iCveMunicipio = MGRL.iCveMunicipio ";

			if (cCondicion != null) {
				cSQL = cSQL + cCondicion + " ";
				cSQLPEM = cSQLPEM + cCondicion + " and iCveCita = ?";
			}
			cSQL = cSQL + "order by iCveCita";
			pstmt = conn.prepareStatement(cSQL);
			pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();
				vEPICisCita.setICveUniMed(rset.getInt(1));
				vEPICisCita.setDtFecha(rset.getDate(2));
				vEPICisCita.setICveCita(rset.getInt(3));
				vEPICisCita.setCHora(rset.getString(4));
				vEPICisCita.setCApPaterno(rset.getString(5));
				vEPICisCita.setCApMaterno(rset.getString(6));
				vEPICisCita.setCNombre(rset.getString(7));
				vEPICisCita.setCGenero(rset.getString(8));
				vEPICisCita.setDtNacimiento(rset.getDate(9));
				vEPICisCita.setCRFC(rset.getString(10));
				vEPICisCita.setCCURP(rset.getString(11));
				vEPICisCita.setICvePaisNac(rset.getInt(12));
				vEPICisCita.setICveEstadoNac(rset.getInt(13));
				vEPICisCita.setCExpediente(rset.getString(14));
				vEPICisCita.setICvePersonal(rset.getInt(15));
				vEPICisCita.setCCalle(rset.getString(16));
				vEPICisCita.setCNumExt(rset.getString(17));
				vEPICisCita.setCNumInt(rset.getString(18));
				vEPICisCita.setCColonia(rset.getString(19));
				vEPICisCita.setICP(rset.getInt(20));
				vEPICisCita.setCCiudad(rset.getString(21));
				vEPICisCita.setICvePais(rset.getInt(22));
				vEPICisCita.setICveEstado(rset.getInt(23));
				vEPICisCita.setICveMunicipio(rset.getInt(24));
				vEPICisCita.setCTelefono(rset.getString(25));
				vEPICisCita.setLCambioDir(rset.getInt(26));
				vEPICisCita.setICveMdoTrans(rset.getInt(27));
				vEPICisCita.setICvePuesto(rset.getInt(28));
				vEPICisCita.setICveCategoria(rset.getInt(29));
				vEPICisCita.setICveMotivo(rset.getInt(30));
				vEPICisCita.setCObservacion(rset.getString(31));
				vEPICisCita.setICveSituacion(rset.getInt(32));
				vEPICisCita.setICveUsuCita(rset.getInt(33));
				vEPICisCita.setLRealizoExam(rset.getInt(34));
				vEPICisCita.setICveUsuMCIS(rset.getInt(35));
				vEPICisCita.setLProdNoConf(rset.getInt(36));
				// Descripciones
				vEPICisCita.setCDscUniMed(rset.getString(37));

				vEPICisCita.setCDscMdoTransporte(rset.getString(38));
				vEPICisCita.setCDscPuesto(rset.getString(39));
				vEPICisCita.setCDscMotivo(rset.getString(40));
				vEPICisCita.setCDscSituacion(rset.getString(41));

				vEPICisCita.setICveModulo(rset.getInt(42));
				vEPICisCita.setCDscModulo(rset.getString(43));
				// Licencias
				vEPICisCita.setCLicencia(rset.getString(44));
				vEPICisCita.setCLiceniaInt(rset.getString(45));

				vEPICisCita.setCDscCategoria(rset.getString(46));

				if (vEPICisCita.getDtFecha() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(vEPICisCita.getDtFecha(),
							"/");
					vEPICisCita.setCDscDtFecha(cFecha);
				} else {
					vEPICisCita.setCDscDtFecha("");
				}

				if (vEPICisCita.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vEPICisCita.getDtNacimiento(), "/");
					vEPICisCita.setCDscDtFechaNac(cFecha);
				} else {
					vEPICisCita.setCDscDtFechaNac("");
				}

				// Ejecuci�n Query con Joins a Pa�s, Estado, Municipio
				pstmtPEM.setInt(1, vEPICisCita.getICveCita());
				rsetPEM = pstmtPEM.executeQuery();
				while (rsetPEM.next()) {
					vEPICisCita.setCDscPaisNac(rsetPEM.getString(1));
					vEPICisCita.setCDscEstadoNac(rsetPEM.getString(2));
					vEPICisCita.setCDscPais(rsetPEM.getString(3));
					vEPICisCita.setCDscEstado(rsetPEM.getString(4));
					vEPICisCita.setCDscMunicipio(rsetPEM.getString(5));
				}
				rsetPEM.close();
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
	 * Metodo Find By All
	 */
	public Vector FindByAll2(String cCondicion, String cCondicion2)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		PreparedStatement pstmtAdnl = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		ResultSet rsetAdnl = null;
		Vector vcEPICisCita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cSQLAdnl = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVEPICisCita vEPICisCita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+ "EPICisCita.cCalle,"
					+ "EPICisCita.cNumExt,"
					+ "EPICisCita.cNumInt,"
					+ "EPICisCita.cColonia,"
					+ "EPICisCita.iCP,"
					+ "EPICisCita.cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "EPICisCita.cTel,"
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "EPICisCita.iCveModulo, "
					+ "GRLModulo.cDscModulo, "
					+
					// Licencias
					"EPICisCita.cLicencia,"
					+ "EPICisCita.cLicenciaInt,"
					+ "GRLCategoria.cDscBreve, "
					+ "EPICisCita.cSexo_DGIS, "
					+ "EPICisCita.iCveLocalidad "
					+ "from EPICisCita "
					+ " join GRLUnimed ON EPICisCita.iCveUniMed = GRLUnimed.iCveUniMed "
					+ " join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans"
					+ " join GRLPuesto ON EPICisCita.iCvePuesto = GRLPuesto.iCvePuesto "
					+ " and  EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  "
					+ " join GRLMotivo ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo "
					+ " and  GRLMotivo.iCveProceso = 1 "
					+ " join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion "
					+ " join GRLModulo ON EPICisCita.iCveModulo = GRLModulo.iCveModulo "
					+ " and  EPICisCita.iCveUniMed = GRLModulo.iCveUniMed "
					+ " inner join GRLCategoria ON GRLCategoria.iCveMdoTrans = EPICisCita.iCveMdoTrans "
					+ " and GRLCategoria.iCveCategoria = EPICisCita.iCveCategoria ";

			cSQLPEM = "select "
					+ "GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "PGRL.cNombre,"
					+ "EGRL.cNombre,"
					+ "MGRL.cNombre "
					+ "from EPICisCita "
					+ " join GRLPais ON EPICisCita.iCvePaisNac = GRLPais.iCvePais "
					+ " join GRLEntidadFed ON EPICisCita.iCvePaisNac = GRLEntidadFed.iCvePais"
					+ " and  EPICisCita.iCveEstadoNac = GRLEntidadFed.iCveEntidadFed "
					+ " join GRLPais PGRL ON EPICisCita.iCvePais = PGRL.iCvePais "
					+ " join GRLEntidadFed EGRL ON EPICisCita.iCvePais = EGRL.iCvePais "
					+ " and  EPICisCita.iCveEstado = EGRL.iCveEntidadFed "
					+ " join GRLMunicipio MGRL ON EPICisCita.iCvePais = MGRL.iCvePais "
					+ " and  EPICisCita.iCveEstado = MGRL.iCveEntidadFed "
					+ " and  EPICisCita.iCveMunicipio = MGRL.iCveMunicipio ";

			cSQLAdnl = "SELECT E.cTel2, " + "E.iCveVivienda, "
					+ "V.cConcepto, " + "E.iCveDiscapacidad, "
					+ "D.cDcsDiscapacidad, " + "E.iCveGpoEtnico,"
					+ "T.cGpoEtnico," + "E.iCveReligion," + "R.cCodDsc,"
					+ "E.iCveNivelSec," + "N.cNivelSec," + "E.iCveParPol,"
					+ "P.cDscParPol," + "E.iCveTpoSangre," + "S.cTpoSangre,"
					+ "E.iCveECivil," + "C.cECivil " + "FROM "
					+ "EPICISCITAADNL AS E," + "GRLVIVIENDA AS V,"
					+ "GRLDISCAPACIDAD AS D," + "GRLGPOETNICO AS T,"
					+ "GRLRELIGION AS R," + "GRLNIVELSEC AS N,"
					+ "GRLPARPOL AS P," + "GRLTPOSANGRE AS S,"
					+ "GRLECIVIL AS C " + "WHERE "
					+ "E.ICVEVIVIENDA = V.ICVEVIVIENDA AND "
					+ "E.ICVEDISCAPACIDAD = D.ICVEDISCAPACIDAD AND "
					+ "E.ICVEGPOETNICO = T.ICVEGPOETNICO AND "
					+ "E.ICVERELIGION = R.ICVERELIGION AND "
					+ "E.ICVENIVELSEC = N.ICVENIVELSEC AND "
					+ "E.ICVEPARPOL = P.ICVEPARPOL  AND "
					+ "E.ICVETPOSANGRE = S.ICVETPOSANGRE  AND "
					+ "E.ICVEECIVIL = C.ICVEECIVIL " + " AND  " + cCondicion2
					+ " AND E.ICVECITA = ? " + " order by E.iCveCita";

			if (cCondicion != null) {
				cSQL = cSQL + cCondicion + " ";
				cSQLPEM = cSQLPEM + cCondicion + " and iCveCita = ? ";
			}

			cSQL = cSQL + "order by iCveCita";

			pstmt = conn.prepareStatement(cSQL);
			pstmtPEM = conn.prepareStatement(cSQLPEM);
			pstmtAdnl = conn.prepareStatement(cSQLAdnl);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();
				vEPICisCita.setICveUniMed(rset.getInt(1));
				vEPICisCita.setDtFecha(rset.getDate(2));
				vEPICisCita.setICveCita(rset.getInt(3));
				vEPICisCita.setCHora(rset.getString(4));
				vEPICisCita.setCApPaterno(rset.getString(5));
				vEPICisCita.setCApMaterno(rset.getString(6));
				vEPICisCita.setCNombre(rset.getString(7));
				vEPICisCita.setCGenero(rset.getString(8));
				vEPICisCita.setDtNacimiento(rset.getDate(9));
				vEPICisCita.setCRFC(rset.getString(10));
				vEPICisCita.setCCURP(rset.getString(11));
				vEPICisCita.setICvePaisNac(rset.getInt(12));
				vEPICisCita.setICveEstadoNac(rset.getInt(13));
				vEPICisCita.setCExpediente(rset.getString(14));
				vEPICisCita.setICvePersonal(rset.getInt(15));
				vEPICisCita.setCCalle(rset.getString(16));
				vEPICisCita.setCNumExt(rset.getString(17));
				vEPICisCita.setCNumInt(rset.getString(18));
				vEPICisCita.setCColonia(rset.getString(19));
				vEPICisCita.setICP(rset.getInt(20));
				vEPICisCita.setCCiudad(rset.getString(21));
				vEPICisCita.setICvePais(rset.getInt(22));
				vEPICisCita.setICveEstado(rset.getInt(23));
				vEPICisCita.setICveMunicipio(rset.getInt(24));
				vEPICisCita.setCTelefono(rset.getString(25));
				vEPICisCita.setLCambioDir(rset.getInt(26));
				vEPICisCita.setICveMdoTrans(rset.getInt(27));
				vEPICisCita.setICvePuesto(rset.getInt(28));
				vEPICisCita.setICveCategoria(rset.getInt(29));
				vEPICisCita.setICveMotivo(rset.getInt(30));
				vEPICisCita.setCObservacion(rset.getString(31));
				vEPICisCita.setICveSituacion(rset.getInt(32));
				vEPICisCita.setICveUsuCita(rset.getInt(33));
				vEPICisCita.setLRealizoExam(rset.getInt(34));
				vEPICisCita.setICveUsuMCIS(rset.getInt(35));
				vEPICisCita.setLProdNoConf(rset.getInt(36));
				// Descripciones
				vEPICisCita.setCDscUniMed(rset.getString(37));

				vEPICisCita.setCDscMdoTransporte(rset.getString(38));
				vEPICisCita.setCDscPuesto(rset.getString(39));
				vEPICisCita.setCDscMotivo(rset.getString(40));
				vEPICisCita.setCDscSituacion(rset.getString(41));

				vEPICisCita.setICveModulo(rset.getInt(42));
				vEPICisCita.setCDscModulo(rset.getString(43));
				// Licencias
				vEPICisCita.setCLicencia(rset.getString(44));
				vEPICisCita.setCLiceniaInt(rset.getString(45));

				vEPICisCita.setCDscCategoria(rset.getString(46));
				
				vEPICisCita.setCSexo_DGIS(rset.getString(47));
				vEPICisCita.setICveLocalidad(rset.getInt(48));

				if (vEPICisCita.getDtFecha() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(vEPICisCita.getDtFecha(),
							"/");
					vEPICisCita.setCDscDtFecha(cFecha);
				} else {
					vEPICisCita.setCDscDtFecha("");
				}

				if (vEPICisCita.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vEPICisCita.getDtNacimiento(), "/");
					vEPICisCita.setCDscDtFechaNac(cFecha);
					cFecha = dtFecha.getFechaYYYYMMMDDNom024(
							vEPICisCita.getDtNacimiento());
					vEPICisCita.setCDscDtFechaNacNom024(cFecha);
				} else {
					vEPICisCita.setCDscDtFechaNac("");
					vEPICisCita.setCDscDtFechaNacNom024("");
				}

				// Ejecuci�n Query con Joins a Pa�s, Estado, Municipio
				pstmtPEM.setInt(1, vEPICisCita.getICveCita());
				rsetPEM = pstmtPEM.executeQuery();
				while (rsetPEM.next()) {
					vEPICisCita.setCDscPaisNac(rsetPEM.getString(1));
					vEPICisCita.setCDscEstadoNac(rsetPEM.getString(2));
					vEPICisCita.setCDscPais(rsetPEM.getString(3));
					vEPICisCita.setCDscEstado(rsetPEM.getString(4));
					vEPICisCita.setCDscMunicipio(rsetPEM.getString(5));
				}
				rsetPEM.close();
				// vcEPICisCita.addElement(vEPICisCita);

				// Ejecuci�n Query con Joins a Pa�s, Estado, Municipio
				pstmtAdnl.setInt(1, vEPICisCita.getICveCita());
				rsetAdnl = pstmtAdnl.executeQuery();
				while (rsetAdnl.next()) {
					vEPICisCita.setCTelefono2(rsetAdnl.getString(1));
					vEPICisCita.setICveVivienda(rsetAdnl.getInt(2));
					vEPICisCita.setCConcepto(rsetAdnl.getString(3));
					vEPICisCita.setICveDiscapacidad(rsetAdnl.getInt(4));
					vEPICisCita.setCDcsDiscapacidad(rsetAdnl.getString(5));
					vEPICisCita.setICveGpoEtnico(rsetAdnl.getInt(6));
					vEPICisCita.setCGpoEtnico(rsetAdnl.getString(7));
					vEPICisCita.setICveReligion(rsetAdnl.getInt(8));
					vEPICisCita.setCCodDsc(rsetAdnl.getString(9));
					// Falta Sanguineo }
					vEPICisCita.setICveNivelSEC(rsetAdnl.getInt(10));
					vEPICisCita.setCNivelSec(rsetAdnl.getString(11));
					vEPICisCita.setICveParPOL(rsetAdnl.getInt(12));
					vEPICisCita.setCDscParPol(rsetAdnl.getString(13));
					vEPICisCita.setICveTpoSangre(rsetAdnl.getInt(14));
					vEPICisCita.setCTpoSangre(rsetAdnl.getString(15));
					vEPICisCita.setICveECivil(rsetAdnl.getInt(16));
					vEPICisCita.setCECivil(rsetAdnl.getString(17));
				}
				rsetAdnl.close();
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
	 * Metodo GenExpediente
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Object GenExpediente(String cCondicion, int iUsuario)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtPEM = null;
		ResultSet rset = null;
		ResultSet rsetPEM = null;
		Vector vcEPICisCita = new Vector();
		TVPERDatos vPERDatos = new TVPERDatos();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+ "EPICisCita.cCalle,"
					+ "EPICisCita.cNumExt,"
					+ "EPICisCita.cNumInt,"
					+ "EPICisCita.cColonia,"
					+ "EPICisCita.iCP,"
					+ "EPICisCita.cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "EPICisCita.cTel,"
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "EPICisCita.iCveModulo, "
					+ "GRLModulo.cDscModulo "
					+ "from EPICisCita "
					+ " join GRLUnimed ON EPICisCita.iCveUniMed = GRLUnimed.iCveUniMed "
					+ " join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans"
					+ " join GRLPuesto ON EPICisCita.iCvePuesto = GRLPuesto.iCvePuesto "
					+ " and  EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  "
					+ " join GRLMotivo ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo "
					+ " and  GRLMotivo.iCveProceso = 1 "
					+ " join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion "
					+ " join GRLModulo ON EPICisCita.iCveModulo = GRLModulo.iCveModulo "
					+ " and  EPICisCita.iCveUniMed = GRLModulo.iCveUniMed ";

			cSQLPEM = "select "
					+ "GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "PGRL.cNombre,"
					+ "EGRL.cNombre,"
					+ "MGRL.cNombre "
					+ "from EPICisCita "
					+ " join GRLPais ON EPICisCita.iCvePaisNac = GRLPais.iCvePais "
					+ " join GRLEntidadFed ON EPICisCita.iCvePaisNac = GRLEntidadFed.iCvePais"
					+ " and  EPICisCita.iCveEstadoNac = GRLEntidadFed.iCveEntidadFed "
					+ " join GRLPais PGRL ON EPICisCita.iCvePais = PGRL.iCvePais "
					+ " join GRLEntidadFed EGRL ON EPICisCita.iCvePais = EGRL.iCvePais "
					+ " and  EPICisCita.iCveEstado = EGRL.iCveEntidadFed "
					+ " join GRLMunicipio MGRL ON EPICisCita.iCvePais = MGRL.iCvePais "
					+ " and  EPICisCita.iCveEstado = MGRL.iCveEntidadFed "
					+ " and  EPICisCita.iCveMunicipio = MGRL.iCveMunicipio ";

			if (cCondicion != null) {
				cSQL = cSQL + cCondicion + " ";
				cSQLPEM = cSQLPEM + cCondicion + " and iCveCita = ?";
			}
			cSQL = cSQL + "order by iCveCita";

			pstmt = conn.prepareStatement(cSQL);
			pstmtPEM = conn.prepareStatement(cSQLPEM);
			rset = pstmt.executeQuery();
			while (rset.next()) {

				vPERDatos.setICveExpediente(11); // null
				vPERDatos.setCSexo(rset.getString(8));
				vPERDatos.setCNombre(rset.getString(7));
				vPERDatos.setCApPaterno(rset.getString(5));
				vPERDatos.setCApMaterno(rset.getString(6));
				vPERDatos.setCRFC(rset.getString(10));
				vPERDatos.setCHomoclave(""); // null
				vPERDatos.setCCURP(rset.getString(11));
				vPERDatos.setDtNacimiento(rset.getDate(9));
				vPERDatos.setICvePaisNac(rset.getInt(12));
				vPERDatos.setICveEstadoNac(rset.getInt(13));

				vPERDatos.setCExpediente(rset.getString(14));
				vPERDatos.setCSenasPersonal(""); // null
				vPERDatos.setCCorreoElec(""); // null
				vPERDatos.setLDonadorOrg(0); // null
				vPERDatos.setCPersonaAviso(""); // null
				vPERDatos.setCTelAviso(""); // null
				vPERDatos.setCDirecAviso(""); // null
				vPERDatos.setCCorreoAviso(""); // null
				vPERDatos.setICveDireccion(1); // null
				vPERDatos.setICveFoto(0); // null
				vPERDatos.setLNoHuellas(0); // null
				vPERDatos.setICveNumEmpresa(0); // null
				vPERDatos.setICveUsuRegistra(iUsuario); // Toamdo de la sesi�n

			}
		} catch (Exception ex) {
			warn("GenExpediente", ex);
			throw new DAOException("GenExpediente");
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
					dbConn.closeConnection();
				}

			} catch (Exception ex2) {
				warn("GenExpediente.close", ex2);
			}
			return vPERDatos;
		}
	}

	/**
	 * Metodo GenDireccion
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Object GenDireccion(Connection conGral, String cCondicion)
			throws DAOException {
		// DbConnection dbConn = null;
		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEPICisCita = new Vector();
		TVPERDireccion vPERDireccion = new TVPERDireccion();
		try {

			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}

			String cSQL = "";
			String cSQLPEM = "";
			String cCita = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+

					"EPICisCita.cCalle,"
					+ "EPICisCita.cNumExt,"
					+ "EPICisCita.cNumInt,"
					+ "EPICisCita.cColonia,"
					+ "EPICisCita.iCP,"
					+ "EPICisCita.cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "EPICisCita.cTel,"
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "EPICisCita.iCveModulo, "
					+ "GRLModulo.cDscModulo "
					+ "from EPICisCita "
					+ " join GRLUnimed ON EPICisCita.iCveUniMed = GRLUnimed.iCveUniMed "
					+ " join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans"
					+ " join GRLPuesto ON EPICisCita.iCvePuesto = GRLPuesto.iCvePuesto "
					+ " and  EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  "
					+ " join GRLMotivo ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo "
					+ " and  GRLMotivo.iCveProceso = 1 "
					+ " join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion "
					+ " join GRLModulo ON EPICisCita.iCveModulo = GRLModulo.iCveModulo "
					+ " andEPICisCita.iCveUniMed = GRLModulo.iCveUniMed ";

			cSQLPEM = "select "
					+ "GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "PGRL.cNombre,"
					+ "EGRL.cNombre,"
					+ "MGRL.cNombre "
					+ "from EPICisCita "
					+ " join GRLPais ON EPICisCita.iCvePaisNac = GRLPais.iCvePais "
					+ " join GRLEntidadFed ON EPICisCita.iCvePaisNac = GRLEntidadFed.iCvePais"
					+ " and  EPICisCita.iCveEstadoNac = GRLEntidadFed.iCveEntidadFed "
					+ " join GRLPais PGRL ON EPICisCita.iCvePais = PGRL.iCvePais "
					+ " join GRLEntidadFed EGRL ON EPICisCita.iCvePais = EGRL.iCvePais "
					+ " and  EPICisCita.iCveEstado = EGRL.iCveEntidadFed "
					+ " join GRLMunicipio MGRL ON EPICisCita.iCvePais = MGRL.iCvePais "
					+ " andEPICisCita.iCveEstado = MGRL.iCveEntidadFed"
					+ " andEPICisCita.iCveMunicipio = MGRL.iCveMunicipio ";

			if (cCondicion != null) {
				cSQL = cSQL + cCondicion + " ";
			}
			cSQL = cSQL + "order by iCveCita";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {

				vPERDireccion.setICvePersonal(rset.getInt(15));
				vPERDireccion.setCCalle(rset.getString(16));
				vPERDireccion.setCNumExt(rset.getString(17));
				vPERDireccion.setCNumInt(rset.getString(18));
				vPERDireccion.setCColonia(rset.getString(19));

				vPERDireccion.setICP(rset.getInt(20));
				vPERDireccion.setCCiudad(rset.getString(21));
				vPERDireccion.setICvePais(rset.getInt(22));
				vPERDireccion.setICveEstado(rset.getInt(23));
				vPERDireccion.setICveMunicipio(rset.getInt(24));
				vPERDireccion.setCTel(rset.getString(25));

			}

			/*
			 * if (conGral == null) { conn.commit(); }
			 */

		} catch (Exception ex) {
			warn("GenDireccion", ex); /*
									 * if (conGral == null) { conn.commit(); }
									 */
			throw new DAOException("GenDireccion");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}

				/*
				 * if (conn != null) { conn.close(); dbConn.closeConnection(); }
				 */

			} catch (Exception ex2) {
				warn("GenDireccion.close", ex2);
			}
			return vPERDireccion;
		}
	}

	/**
	 * Metodo Find By Cita
	 * 
	 * @author: Marco A. Gonz�lez Paz
	 * @param: cCveCita- Clave de la Cita.
	 */
	public Vector FindByCita(String cCveCita) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEPICisCita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVEPICisCita vEPICisCita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+ "cCalle,"
					+ "cNumExt,"
					+ "cNumInt,"
					+ "cColonia,"
					+ "iCP,"
					+ "cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "cTel,"
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLPais.cNombre,"
					+ "GRLEntidadFed.cNombre,"
					+ "PGRL.cNombre,"
					+ "EGRL.cNombre,"
					+ "MGRL.cNombre,"
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "GRLCategoria.cDscBreve "
					+ " from EPICisCita "
					+ "inner join GRLUnimed ON GRLUnimed.iCveUniMed = EPICisCita.iCveUniMed "
					+ "inner join GRLPais ON GRLPais.iCvePais = EPICisCita.iCvePaisNac "
					+ "inner join GRLEntidadFed ON GRLEntidadFed.iCvePais = EPICisCita.iCvePaisNac and GRLEntidadFed.iCveEntidadFed = EPICisCita.iCveEstadoNac "
					+ "inner join GRLPais PGRL ON PGRL.iCvePais = EPICisCita.iCvePais "
					+ "inner join GRLEntidadFed EGRL ON EGRL.iCvePais = EPICisCita.iCvePais and EGRL.iCveEntidadFed = EPICisCita.iCveEstado "
					+ "inner join GRLMunicipio MGRL ON MGRL.iCvePais = EPICisCita.iCvePais and MGRL.iCveEntidadFed = EPICisCita.iCveEstado and MGRL.iCveMunicipio = EPICisCita.iCveMunicipio "
					+ "inner join GRLMdoTrans ON GRLMdoTrans.iCveMdoTrans= EPICisCita.iCveMdoTrans "
					+ "inner join GRLPuesto ON GRLPuesto.iCvePuesto= EPICisCita.iCvePuesto "
					+ "inner join GRLMotivo ON GRLMotivo.iCveMotivo= EPICisCita.iCveMotivo "
					+ "inner join EPISituacion ON EPISituacion.iCveSituacion = EPICisCita.iCveSituacion "
					+ " inner join GRLCategoria ON GRLCategoria.iCveMdoTrans = EPICisCita.iCveMdoTrans "
					+ " and GRLCategoria.iCveCategoria = EPICisCita.iCveCategoria "
					+ "where EPICisCita.iCveCita = " + cCveCita + " "
					+ "order by iCveUniMed";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();
				vEPICisCita.setICveUniMed(rset.getInt(1));
				vEPICisCita.setDtFecha(rset.getDate(2));
				vEPICisCita.setICveCita(rset.getInt(3));
				vEPICisCita.setCHora(rset.getString(4));
				vEPICisCita.setCApPaterno(rset.getString(5));
				vEPICisCita.setCApMaterno(rset.getString(6));
				vEPICisCita.setCNombre(rset.getString(7));
				vEPICisCita.setCGenero(rset.getString(8));
				vEPICisCita.setDtNacimiento(rset.getDate(9));
				vEPICisCita.setCRFC(rset.getString(10));
				vEPICisCita.setCCURP(rset.getString(11));
				vEPICisCita.setICvePaisNac(rset.getInt(12));
				vEPICisCita.setICveEstadoNac(rset.getInt(13));
				vEPICisCita.setCExpediente(rset.getString(14));
				vEPICisCita.setICvePersonal(rset.getInt(15));
				vEPICisCita.setCCalle(rset.getString(16));
				vEPICisCita.setCNumExt(rset.getString(17));
				vEPICisCita.setCNumInt(rset.getString(18));
				vEPICisCita.setCColonia(rset.getString(19));
				vEPICisCita.setICP(rset.getInt(20));
				vEPICisCita.setCCiudad(rset.getString(21));
				vEPICisCita.setICvePais(rset.getInt(22));
				vEPICisCita.setICveEstado(rset.getInt(23));
				vEPICisCita.setICveMunicipio(rset.getInt(24));
				vEPICisCita.setCTelefono(rset.getString(25));
				vEPICisCita.setLCambioDir(rset.getInt(26));
				vEPICisCita.setICveMdoTrans(rset.getInt(27));
				vEPICisCita.setICvePuesto(rset.getInt(28));
				vEPICisCita.setICveCategoria(rset.getInt(29));
				vEPICisCita.setICveMotivo(rset.getInt(30));
				vEPICisCita.setCObservacion(rset.getString(31));
				vEPICisCita.setICveSituacion(rset.getInt(32));
				vEPICisCita.setICveUsuCita(rset.getInt(33));
				vEPICisCita.setLRealizoExam(rset.getInt(34));
				vEPICisCita.setICveUsuMCIS(rset.getInt(35));
				vEPICisCita.setLProdNoConf(rset.getInt(36));
				// Descripciones
				vEPICisCita.setCDscUniMed(rset.getString(37));
				vEPICisCita.setCDscPaisNac(rset.getString(38));
				vEPICisCita.setCDscEstadoNac(rset.getString(39));
				vEPICisCita.setCDscPais(rset.getString(40));
				vEPICisCita.setCDscEstado(rset.getString(41));
				vEPICisCita.setCDscMunicipio(rset.getString(42));
				vEPICisCita.setCDscMdoTransporte(rset.getString(43));
				vEPICisCita.setCDscPuesto(rset.getString(44));
				vEPICisCita.setCDscMotivo(rset.getString(45));
				vEPICisCita.setCDscSituacion(rset.getString(46));
				vEPICisCita.setCDscCategoria(rset.getString(47));

				if (vEPICisCita.getDtFecha() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(vEPICisCita.getDtFecha(),
							"/");
					vEPICisCita.setCDscDtFecha(cFecha);
				} else {
					vEPICisCita.setCDscDtFecha("");
				}

				if (vEPICisCita.getDtNacimiento() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vEPICisCita.getDtNacimiento(), "/");
					vEPICisCita.setCDscDtFechaNac(cFecha);
				} else {
					vEPICisCita.setCDscDtFechaNac("");
				}
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
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtValida = null;
		ResultSet rset = null;
		ResultSet rsetValida = null;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";

			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int iConsecutivo = 0;
			int iExistencia = 0;
			String cSQLFind;
			String cRes;
			String cSQLValida = "";

			cSQLValida = "select iCvePersonal from EPICisCita "
					+ "where iCvePersonal = ? and iCveUniMed = ? and dtFecha = ?"; // and
																					// iCveModulo
																					// =
																					// ?";

			pstmtValida = conn.prepareStatement(cSQLValida);
			pstmtValida.setInt(1, vEPICisCita.getICvePersonal());
			pstmtValida.setInt(2, vEPICisCita.getICveUniMed());
			pstmtValida.setDate(3, vEPICisCita.getDtFecha());
			// pstmtValida.setInt(4, vEPICisCita.getICveModulo());
			rsetValida = pstmtValida.executeQuery();

			while (rsetValida.next()) {
				iExistencia = rsetValida.getInt(1);
			}

			// Validamos que la persona que realiza la cita no sea tercero
			int esTercero = 0;// No es tercero
			String cSQLValidaTerceros = "SELECT LPRIVADA FROM GRLUNIMED WHERE ICVEUNIMED =  "
					+ vEPICisCita.getICveUniMed();
			PreparedStatement pstmtValidaTerceros = conn
					.prepareStatement(cSQLValidaTerceros);
			ResultSet rsetValidaTerceros = pstmtValidaTerceros.executeQuery();
			while (rsetValidaTerceros.next()) {
				esTercero = rsetValidaTerceros.getInt(1);
			}
			// System.out.println("esTercero="+esTercero);
			if (vEPICisCita.getICveMotivo() == 5 && esTercero == 1) {// Si es
																		// REVALORACION
																		// y es
																		// TERCERO
				cSQLValidaTerceros = "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, A.ICVEEXPEDIENTE, A.INUMEXAMEN, B.ICVEUNIMED  "
						+ "FROM EXPEXAMCAT A "
						+ "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
						+ "AND A.INUMEXAMEN = B.INUMEXAMEN "
						+ " JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED "
						+ "WHERE A.ICVEEXPEDIENTE = "
						+ vEPICisCita.getICvePersonal()
						+ " AND B.ICVEPROCESO = 1 "
						+ " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN";
				// System.out.println(cSQLValidaTerceros);
				PreparedStatement pstmtValidaTerceros2 = conn
						.prepareStatement(cSQLValidaTerceros);
				ResultSet rsetValidaTerceros2 = pstmtValidaTerceros2
						.executeQuery();
				int ldictamen = 1;// APTO
				int ldictaminado = 1;// DICTAMINADO
				int icveunimedEsTercero = 0;// ES INTERNA DE LA SCT
				while (rsetValidaTerceros2.next()) {// Se quedara con los
													// valores del ultimo examen
													// por el ciclo
					ldictamen = rsetValidaTerceros2.getInt(1);
					ldictaminado = rsetValidaTerceros2.getInt(2);
					icveunimedEsTercero = rsetValidaTerceros2.getInt(3);
					// System.out.println(rsetValidaTerceros2.getInt(5));
				}
				if (ldictamen == 0 && ldictaminado == 1
						&& icveunimedEsTercero == 0) {// Si el ultimo examen fue
														// DICTAMINADO y resulto
														// NO APTO y fue por
														// DICTAMINADO POR LA
														// SCT
					// if (ldictamen == 0 && ldictaminado == 1) {//Si el ultimo
					// examen fue DICTAMINADO y resulto NO APTO y fue por
					// DICTAMINADO POR LA SCT
					cClave = "ERROR2";
					return cClave;
				}
			}
			// FIN DE Validamos que la persona que realiza la cita no sea
			// tercero

			if (esTercero == 1) {// Si es TERCERO
				System.out.println("entrando2");
				cSQLValidaTerceros = "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, B.ICVEUSUDICTAMEN "
						+ "FROM EXPEXAMCAT A "
						+ "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
						+ "AND A.INUMEXAMEN = B.INUMEXAMEN "
						+ " JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED "
						+ "WHERE A.ICVEEXPEDIENTE = "
						+ vEPICisCita.getICvePersonal()
						+ " AND B.ICVEPROCESO = 1 "
						+ " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN";
				// System.out.println(cSQLValidaTerceros);
				PreparedStatement pstmtValidaTerceros3 = conn
						.prepareStatement(cSQLValidaTerceros);
				ResultSet rsetValidaTerceros3 = pstmtValidaTerceros3
						.executeQuery();
				int ldictamen = 1;// APTO
				int ldictaminado = 1;// DICTAMINADO
				int icveunimedEsTercero = 0;// ES INTERNA DE LA SCT
				int usudictamen = 0;// Usurio que dictamino
				while (rsetValidaTerceros3.next()) {// Se quedara con los
													// valores del ultimo examen
													// por el ciclo
					ldictamen = rsetValidaTerceros3.getInt(1);
					ldictaminado = rsetValidaTerceros3.getInt(2);
					icveunimedEsTercero = rsetValidaTerceros3.getInt(3);
					usudictamen = rsetValidaTerceros3.getInt(4);
					// System.out.println(rsetValidaTerceros2.getInt(5));
				}
				System.out.println(ldictamen + "," + ldictaminado + ","
						+ icveunimedEsTercero + "," + usudictamen);
				if (ldictamen == 0 && ldictaminado == 1
						&& icveunimedEsTercero == 0 && usudictamen == 12065) {
					// Todos aquellos expedientes cerrados como NO APTOS por
					// art?ulo 12 de todas las unidades m?icas de la DGPMPT no
					// podr? ser revalorados por terceros particulares,
					// ?nicamente podr? revalorarse en alguna unidad m?ica de
					// la DGPMPT.
					// Mientras que los cerrados por articulo 12 en los terceros
					// particulares, si podr? ser revalorados en otro o el
					// mismo tercero particular o en alguna unidad m?ica de la
					// DGPMPT.
					cClave = "ERROR4";
					return cClave;
				}
			}

			if (iExistencia == 0) {

				cSQLFind = "select max(iCveCita) "
						+ "from EPICisCita "
						+ "where iCveUniMed = ? and dtFecha = ? and iCveModulo = ?";
				pstmtFind = conn.prepareStatement(cSQLFind);
				pstmtFind.setInt(1, vEPICisCita.getICveUniMed());
				pstmtFind.setDate(2, vEPICisCita.getDtFecha());
				pstmtFind.setInt(3, vEPICisCita.getICveModulo());

				rset = pstmtFind.executeQuery();
				while (rset.next()) {
					iConsecutivo = rset.getInt(1);
				}
				vEPICisCita.setICveCita(iConsecutivo + 1);

				cSQL = "insert into EPICisCita(" + "iCveUniMed,"
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
						+
						// Licencias
						"cLicencia,"
						+ "cLicenciaInt,"
						+ "iCveModulo"
						+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEPICisCita.getICveUniMed());
				pstmt.setDate(2, vEPICisCita.getDtFecha());
				pstmt.setInt(3, vEPICisCita.getICveCita());

				GregorianCalendar g = new GregorianCalendar(0, 0, 0,
						vEPICisCita.getIHora(), vEPICisCita.getIMinutos());
				Time t = new Time(g.getTimeInMillis());
				pstmt.setString(4, "" + t);

				pstmt.setString(5, vEPICisCita.getCApPaterno());
				pstmt.setString(6, vEPICisCita.getCApMaterno());
				pstmt.setString(7, vEPICisCita.getCNombre());
				pstmt.setString(8, vEPICisCita.getCGenero());
				pstmt.setDate(9, vEPICisCita.getDtNacimiento());
				pstmt.setString(10, vEPICisCita.getCRFC());
				pstmt.setString(11, vEPICisCita.getCCURP());
				pstmt.setInt(12, vEPICisCita.getICvePaisNac());
				pstmt.setInt(13, vEPICisCita.getICveEstadoNac());
				pstmt.setString(14, vEPICisCita.getCExpediente());
				pstmt.setInt(15, vEPICisCita.getICvePersonal());
				pstmt.setString(16, vEPICisCita.getCCalle());
				pstmt.setString(17, vEPICisCita.getCNumExt());
				pstmt.setString(18, vEPICisCita.getCNumInt());
				pstmt.setString(19, vEPICisCita.getCColonia());
				pstmt.setInt(20, vEPICisCita.getICP());
				pstmt.setString(21, vEPICisCita.getCCiudad());
				pstmt.setInt(22, vEPICisCita.getICvePais());
				pstmt.setInt(23, vEPICisCita.getICveEstado());
				pstmt.setInt(24, vEPICisCita.getICveMunicipio());
				pstmt.setString(25, vEPICisCita.getCTelefono());
				pstmt.setInt(26, vEPICisCita.getLCambioDir());
				pstmt.setInt(27, vEPICisCita.getICveMdoTrans());
				pstmt.setInt(28, vEPICisCita.getICvePuesto());

				// Validar si es de Autotrasporte
				// Si lo es se aplicara la categoria 7
				if (vEPICisCita.getICveMdoTrans() == 2) {
					int catAutotrans = 7;
					pstmt.setInt(29, catAutotrans);
				} else {
					pstmt.setInt(29, vEPICisCita.getICveCategoria());
				}

				pstmt.setInt(30, vEPICisCita.getICveMotivo());
				pstmt.setString(31, vEPICisCita.getCObservacion());
				pstmt.setInt(32, vEPICisCita.getICveSituacion());
				pstmt.setInt(33, vEPICisCita.getICveUsuCita());
				pstmt.setInt(34, vEPICisCita.getLRealizoExam());
				pstmt.setInt(35, vEPICisCita.getICveUsuMCIS());
				pstmt.setInt(36, vEPICisCita.getLProdNoConf());
				// Licencias
				pstmt.setString(37, vEPICisCita.getCLicencia());
				pstmt.setString(38, vEPICisCita.getCLicenciaInt());
				pstmt.setInt(39, vEPICisCita.getICveModulo());
				pstmt.executeUpdate();
				cClave = "" + vEPICisCita.getICveCita();
			} else {
				cClave = "ERROR";
			}

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

				if (pstmtFind != null) {
					pstmtFind.close();
				}

				if (pstmtValida != null) {
					pstmtValida.close();
				}

				if (rset != null) {
					rset.close();
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
	 * Metodo Insert para el proceso EPI NOM-024
	 */
	public Object insertCA(Connection conGral, Object obj) throws DAOException {
		// System.out.println("Generando cita");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtAdnl = null;
		PreparedStatement pstmtFind = null;
		PreparedStatement pstmtFind2 = null;
		PreparedStatement pstmtValida = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		ResultSet rsetValida = null;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String cSQL2 = "";

			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			int iConsecutivo = 0;
			int iExistencia = 0;
			String cSQLFind;
			String cSQLFind2;
			String cRes;
			String cSQLValida = "";

			cSQLValida = "select iCvePersonal from EPICisCita "
					+ "where iCvePersonal = ? and iCveUniMed = ? and dtFecha = ?"; // and
																					// iCveModulo
																					// =
																					// ?";

			pstmtValida = conn.prepareStatement(cSQLValida);
			pstmtValida.setInt(1, vEPICisCita.getICvePersonal());
			pstmtValida.setInt(2, vEPICisCita.getICveUniMed());
			pstmtValida.setDate(3, vEPICisCita.getDtFecha());
			// pstmtValida.setInt(4, vEPICisCita.getICveModulo());
			rsetValida = pstmtValida.executeQuery();

			while (rsetValida.next()) {
				iExistencia = rsetValida.getInt(1);
			}

			// Validamos que la persona que realiza la cita no sea tercero
			int esTercero = 0;// No es tercero
			String cSQLValidaTerceros = "SELECT LPRIVADA FROM GRLUNIMED WHERE ICVEUNIMED =  "
					+ vEPICisCita.getICveUniMed();
			PreparedStatement pstmtValidaTerceros = conn
					.prepareStatement(cSQLValidaTerceros);
			ResultSet rsetValidaTerceros = pstmtValidaTerceros.executeQuery();
			while (rsetValidaTerceros.next()) {
				esTercero = rsetValidaTerceros.getInt(1);
			}
			if (vEPICisCita.getICveMotivo() == 5 && esTercero == 1) {// Si es
																		// REVALORACION
																		// y es
																		// TERCERO
				cSQLValidaTerceros = "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, A.ICVEEXPEDIENTE, A.INUMEXAMEN, B.ICVEUNIMED  "
						+ "FROM EXPEXAMCAT A "
						+ "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
						+ "AND A.INUMEXAMEN = B.INUMEXAMEN "
						+ " JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED "
						+ "WHERE A.ICVEEXPEDIENTE = "
						+ vEPICisCita.getICvePersonal()
						+ " AND B.ICVEPROCESO = 1 "
						+ " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN";
				// System.out.println(cSQLValidaTerceros);
				PreparedStatement pstmtValidaTerceros2 = conn
						.prepareStatement(cSQLValidaTerceros);
				ResultSet rsetValidaTerceros2 = pstmtValidaTerceros2
						.executeQuery();
				int ldictamen = 1;// APTO
				int ldictaminado = 1;// DICTAMINADO
				int icveunimedEsTercero = 0;// ES INTERNA DE LA SCT
				while (rsetValidaTerceros2.next()) {// Se quedara con los
													// valores del ultimo examen
													// por el ciclo
					ldictamen = rsetValidaTerceros2.getInt(1);
					ldictaminado = rsetValidaTerceros2.getInt(2);
					icveunimedEsTercero = rsetValidaTerceros2.getInt(3);
					// System.out.println(rsetValidaTerceros2.getInt(5));
				}
				if (ldictamen == 0 && ldictaminado == 1
						&& icveunimedEsTercero == 0) {// Si el ultimo examen fue
														// DICTAMINADO y resulto
														// NO APTO y fue por
														// DICTAMINADO POR LA
														// SCT
					// if (ldictamen == 0 && ldictaminado == 1) {//Si el ultimo
					// examen fue DICTAMINADO y resulto NO APTO y fue por
					// DICTAMINADO POR LA SCT
					cClave = "ERROR2";
					return cClave;
				}
			}
			// FIN DE Validamos que la persona que realiza la cita no sea
			// tercero

			if (esTercero == 1) {// Si es TERCERO
				cSQLValidaTerceros = "SELECT A.LDICTAMEN, B.LDICTAMINADO, C.LPRIVADA, B.ICVEUSUDICTAMEN, A.ICVEEXPEDIENTE, A.INUMEXAMEN, B.ICVEUNIMED  "
						+ "FROM EXPEXAMCAT A "
						+ "JOIN EXPEXAMAPLICA B on A.ICVEEXPEDIENTE = B.ICVEEXPEDIENTE "
						+ "AND A.INUMEXAMEN = B.INUMEXAMEN "
						+ " JOIN GRLUNIMED C ON B.ICVEUNIMED = C.ICVEUNIMED "
						+ "WHERE A.ICVEEXPEDIENTE = "
						+ vEPICisCita.getICvePersonal()
						+ " AND B.ICVEPROCESO = 1 "
						+ " AND B.LDICTAMINADO = 1 ORDER BY INUMEXAMEN";
				// System.out.println(cSQLValidaTerceros);
				PreparedStatement pstmtValidaTerceros3 = conn
						.prepareStatement(cSQLValidaTerceros);
				ResultSet rsetValidaTerceros3 = pstmtValidaTerceros3
						.executeQuery();
				int ldictamen = 1;// APTO
				int ldictaminado = 1;// DICTAMINADO
				int icveunimedEsTercero = 0;// ES INTERNA DE LA SCT
				int usudictamen = 0;// Usurio que dictamino
				while (rsetValidaTerceros3.next()) {// Se quedara con los
													// valores del ultimo examen
													// por el ciclo
					ldictamen = rsetValidaTerceros3.getInt(1);
					ldictaminado = rsetValidaTerceros3.getInt(2);
					icveunimedEsTercero = rsetValidaTerceros3.getInt(3);
					usudictamen = rsetValidaTerceros3.getInt(4);
					// System.out.println(rsetValidaTerceros2.getInt(5));
				}
				// System.out.println(ldictamen
				// +","+ldictaminado+","+icveunimedEsTercero+","+usudictamen);
				if (ldictamen == 0 && ldictaminado == 1
						&& icveunimedEsTercero == 0 && usudictamen == 12065) {
					// Todos aquellos expedientes cerrados como NO APTOS por
					// art?ulo 12 de todas las unidades m?icas de la DGPMPT no
					// podr? ser revalorados por terceros particulares,
					// ?nicamente podr? revalorarse en alguna unidad m?ica de
					// la DGPMPT.
					// Mientras que los cerrados por articulo 12 en los terceros
					// particulares, si podr? ser revalorados en otro o el
					// mismo tercero particular o en alguna unidad m?ica de la
					// DGPMPT.
					cClave = "ERROR4";
					return cClave;
				}
			}

			if (iExistencia == 0) {

				cSQLFind = "select max(iCveCita) "
						+ "from EPICisCita "
						+ "where iCveUniMed = ? and dtFecha = ? and iCveModulo = ?";
				pstmtFind = conn.prepareStatement(cSQLFind);
				pstmtFind.setInt(1, vEPICisCita.getICveUniMed());
				pstmtFind.setDate(2, vEPICisCita.getDtFecha());
				pstmtFind.setInt(3, vEPICisCita.getICveModulo());

				rset = pstmtFind.executeQuery();
				while (rset.next()) {
					iConsecutivo = rset.getInt(1);
				}

				cSQLFind2 = "select max(iCveCita) "
						+ "from EPICisCitaAdnl "
						+ "where iCveUniMed = ? and dtFecha = ? and iCveModulo = ?";
				pstmtFind2 = conn.prepareStatement(cSQLFind2);
				pstmtFind2.setInt(1, vEPICisCita.getICveUniMed());
				pstmtFind2.setDate(2, vEPICisCita.getDtFecha());
				pstmtFind2.setInt(3, vEPICisCita.getICveModulo());
				rset2 = pstmtFind2.executeQuery();
				while (rset2.next()) {
					if (rset2.getInt(1) > iConsecutivo) {
						iConsecutivo = rset2.getInt(1);
					}
				}

				vEPICisCita.setICveCita(iConsecutivo + 1);

				cSQL = "insert into EPICisCita(" + "iCveUniMed,"
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
						+
						// Licencias
						"cLicencia,"
						+ "cLicenciaInt,"
						+ "iCveModulo,"
						+ "cSexo_DGIS,"
						+ "iCveLocalidad"
						+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vEPICisCita.getICveUniMed());
				pstmt.setDate(2, vEPICisCita.getDtFecha());
				pstmt.setInt(3, vEPICisCita.getICveCita());

				GregorianCalendar g = new GregorianCalendar(0, 0, 0,
						vEPICisCita.getIHora(), vEPICisCita.getIMinutos());
				Time t = new Time(g.getTimeInMillis());
				pstmt.setString(4, "" + t);

				pstmt.setString(5, vEPICisCita.getCApPaterno());
				pstmt.setString(6, vEPICisCita.getCApMaterno());
				pstmt.setString(7, vEPICisCita.getCNombre());
				pstmt.setString(8, vEPICisCita.getCGenero());
				pstmt.setDate(9, vEPICisCita.getDtNacimiento());
				pstmt.setString(10, vEPICisCita.getCRFC());
				pstmt.setString(11, vEPICisCita.getCCURP());
				pstmt.setInt(12, vEPICisCita.getICvePaisNac());
				pstmt.setInt(13, vEPICisCita.getICveEstadoNac());
				pstmt.setString(14, vEPICisCita.getCExpediente());
				pstmt.setInt(15, vEPICisCita.getICvePersonal());
				pstmt.setString(16, vEPICisCita.getCCalle());
				pstmt.setString(17, vEPICisCita.getCNumExt());
				pstmt.setString(18, vEPICisCita.getCNumInt());
				pstmt.setString(19, vEPICisCita.getCColonia());
				pstmt.setInt(20, vEPICisCita.getICP());
				pstmt.setString(21, vEPICisCita.getCCiudad());
				pstmt.setInt(22, vEPICisCita.getICvePais());
				pstmt.setInt(23, vEPICisCita.getICveEstado());
				pstmt.setInt(24, vEPICisCita.getICveMunicipio());
				pstmt.setString(25, vEPICisCita.getCTelefono());
				pstmt.setInt(26, vEPICisCita.getLCambioDir());
				pstmt.setInt(27, vEPICisCita.getICveMdoTrans());
				pstmt.setInt(28, vEPICisCita.getICvePuesto());

				// Validar si es de Autotrasporte
				// Si lo es se aplicara la categoria 7
				if (vEPICisCita.getICveMdoTrans() == 2) {
					int catAutotrans = 7;
					pstmt.setInt(29, catAutotrans);
				} else {
					pstmt.setInt(29, vEPICisCita.getICveCategoria());
				}

				pstmt.setInt(30, vEPICisCita.getICveMotivo());
				pstmt.setString(31, vEPICisCita.getCObservacion());
				pstmt.setInt(32, vEPICisCita.getICveSituacion());
				pstmt.setInt(33, vEPICisCita.getICveUsuCita());
				pstmt.setInt(34, vEPICisCita.getLRealizoExam());
				pstmt.setInt(35, vEPICisCita.getICveUsuMCIS());
				pstmt.setInt(36, vEPICisCita.getLProdNoConf());
				// Licencias
				pstmt.setString(37, vEPICisCita.getCLicencia());
				pstmt.setString(38, vEPICisCita.getCLicenciaInt());
				pstmt.setInt(39, vEPICisCita.getICveModulo());
				pstmt.setString(40, vEPICisCita.getCSexo_DGIS());
				pstmt.setInt(41, vEPICisCita.getICveLocalidad());
				pstmt.executeUpdate();
				cClave = "" + vEPICisCita.getICveCita();
				/*
				 * cSQL2 = "insert into EPICisCitaAdnl(" + "iCveUniMed," +
				 * "iCveModulo," + "dtFecha," + "iCveCita," + "iCvePersonal," +
				 * "iCveVivienda," + "iCveDiscapacidad," + "iCveGpoEtnico," +
				 * "iCveReligion," + "iCveNivelSec," + "iCveParPol," +
				 * "iCveECivil," + "cTel2," + "iCveTpoSangre," + "tGNRCita" +
				 * ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				 * 
				 * //Calculando Timestamp para el campo TINIEXA java.util.Date
				 * utilDate = new java.util.Date(); //fecha actual long
				 * lnMilisegundos = utilDate.getTime(); java.sql.Timestamp
				 * sqlTimestamp = new java.sql.Timestamp(lnMilisegundos);
				 * 
				 * pstmtAdnl = conn.prepareStatement(cSQL2); pstmtAdnl.setInt(1,
				 * vEPICisCita.getICveUniMed()); pstmtAdnl.setInt(2,
				 * vEPICisCita.getICveModulo()); pstmtAdnl.setDate(3,
				 * vEPICisCita.getDtFecha()); pstmtAdnl.setInt(4,
				 * vEPICisCita.getICveCita()); pstmtAdnl.setInt(5,
				 * vEPICisCita.getICvePersonal()); pstmtAdnl.setInt(6,
				 * vEPICisCita.getICveVivienda()); pstmtAdnl.setInt(7,
				 * vEPICisCita.getICveDiscapacidad()); pstmtAdnl.setInt(8,
				 * vEPICisCita.getICveGpoEtnico()); pstmtAdnl.setInt(9,
				 * vEPICisCita.getICveReligion()); pstmtAdnl.setInt(10,
				 * vEPICisCita.getICveNivelSEC()); pstmtAdnl.setInt(11,
				 * vEPICisCita.getICveParPOL()); pstmtAdnl.setInt(12,
				 * vEPICisCita.getICveECivil()); pstmtAdnl.setString(13,
				 * vEPICisCita.getCTelefono2()); pstmtAdnl.setInt(14,
				 * vEPICisCita.getICveTpoSangre()); pstmtAdnl.setTimestamp(15,
				 * sqlTimestamp); pstmtAdnl.executeUpdate();
				 */

			} else {
				cClave = "ERROR";
			}

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
				if (pstmtAdnl != null) {
					pstmtAdnl.close();
				}

				if (pstmtFind != null) {
					pstmtFind.close();
				}

				if (pstmtValida != null) {
					pstmtValida.close();
				}

				if (rset != null) {
					rset.close();
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
			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EPICisCita " + "set cHora = ? "
					+ "where iCveUniMed = ? " + "and dtFecha = ? "
					+ "and iCveCita = ? " + "and iCveModulo = ?";
			pstmt = conn.prepareStatement(cSQL);

			GregorianCalendar g = new GregorianCalendar(0, 0, 0,
					vEPICisCita.getIHora(), vEPICisCita.getIMinutos());
			Time t = new Time(g.getTimeInMillis());
			pstmt.setString(1, "" + t);
			pstmt.setInt(2, vEPICisCita.getICveUniMedA());
			pstmt.setDate(3, vEPICisCita.getDtFechaA());
			pstmt.setInt(4, vEPICisCita.getICveCita());
			pstmt.setInt(5, vEPICisCita.getICveModuloA());
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

	/**
	 * Metodo updateNombre
	 */
	public Object updateNombre(Connection conGral, Object obj)
			throws DAOException {
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
			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EPICisCita " + "set cApPaterno = ?, "
					+ "cApMaterno = ?, " + "cNombre = ?, "
					+ "dtNacimiento = ?, " + "cRFC = ?, " + "cCURP = ? "
					+ "where iCveUniMed = ? " + "and dtFecha = ? "
					+ "and iCveCita = ? " + "and iCveModulo = ?";
			pstmt = conn.prepareStatement(cSQL);

			GregorianCalendar g = new GregorianCalendar(0, 0, 0,
					vEPICisCita.getIHora(), vEPICisCita.getIMinutos());
			Time t = new Time(g.getTimeInMillis());
			pstmt.setString(1, vEPICisCita.getCApPaterno());
			pstmt.setString(2, vEPICisCita.getCApMaterno());
			pstmt.setString(3, vEPICisCita.getCNombre());
			pstmt.setDate(4, vEPICisCita.getDtNacimiento());
			pstmt.setString(5, vEPICisCita.getCRFC());
			pstmt.setString(6, vEPICisCita.getCCURP());

			pstmt.setInt(7, vEPICisCita.getICveUniMedA());
			pstmt.setDate(8, vEPICisCita.getDtFechaA());
			pstmt.setInt(9, vEPICisCita.getICveCita());
			pstmt.setInt(10, vEPICisCita.getICveModuloA());
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
				warn("updateNombre", ex1);
			}
			warn("updateNombre", ex);
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
				warn("updateNombre.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo AltaPersonal
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Object AltaPersonal(String cWhere, int iUsuario, String cActualiza)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtCveExpediente = null;
		PreparedStatement pstmtActualiza = null;
		PreparedStatement pstmtActualiza2 = null;
		PreparedStatement pstmtDir = null;
		PreparedStatement pstmtMax = null;
		PreparedStatement pstmtInsDir = null;
		PreparedStatement pstmtInsDatos = null;

		ResultSet rset = null;
		ResultSet rsetCvePersona = null;
		ResultSet rsetCveExpediente = null;
		ResultSet rsetDir = null;
		ResultSet rsetMax = null;

		int unidad = 0;
		int modulo = 0;

		String cClave = "";
		StringBuffer cSQL = new StringBuffer();
		String cSQLMax = "";
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			TVPERDatos vPERDatos = new TVPERDatos();
			TDEPICisCita dEPICisCita = new TDEPICisCita();
			TVEPICisCita vEPICisCita;
			TDPERDatos dPERDatos = new TDPERDatos();
			TDPERDireccion dPERDireccion = new TDPERDireccion();
			TVPERDireccion vPERDireccion = new TVPERDireccion();

			/***************************************** GenExpediente *****/
			cSQL.append(" select  ")
					.append("         EPICisCita.iCveUniMed, ")
					.append("         dtFecha, ")
					.append("         iCveCita, ")
					.append("         cHora, ")
					.append("         cApPaterno, ")
					.append("         cApMaterno, ")
					.append("         EPICisCita.cNombre, ")
					.append("         cGenero, ")
					.append("         dtNacimiento, ")
					.append("         cRFC, ")
					.append("         cCURP, ")
					.append("         EPICisCita.iCvePaisNac, ")
					.append("         EPICisCita.iCveEstadoNac, ")
					.append("         cExpediente, ")
					// 14
					.append("         iCvePersonal, ")
					.append("         EPICisCita.cCalle, ")
					.append("         EPICisCita.cNumExt, ")
					.append("         EPICisCita.cNumInt, ")
					.append("         EPICisCita.cColonia, ")
					.append("         EPICisCita.iCP, ")
					.append("         EPICisCita.cCiudad, ")
					.append("         EPICisCita.iCvePais, ")
					.append("         EPICisCita.iCveEstado, ")
					.append("         EPICisCita.iCveMunicipio, ")
					.append("         EPICisCita.cTel, ")
					.append("         lCambioDir, ")
					.append("         EPICisCita.iCveMdoTrans, ")
					.append("         EPICisCita.iCvePuesto, ")
					.append("         EPICisCita.iCveCategoria, ")
					.append("         EPICisCita.iCveMotivo, ")
					.append("         cObservacion, ")
					.append("         EPICisCita.iCveSituacion, ")
					.append("         iCveUsuCita, lRealizoExam, iCveUsuMCIS, ")
					// 33,34, 35
					.append("         lProdNoConf,  GRLUnimed.cDscUniMed, GRLMdoTrans.cDscMdoTrans,")
					// 36-38
					.append("         GRLPuesto.cDscPuesto,  GRLMotivo.cDscMotivo, ")
					.append("         EPISituacion.cDscSituacion,  ")
					.append("         EPICisCita.iCveModulo,  GRLModulo.cDscModulo,  ")
					// Licencias
					.append("         EPICisCita.cLicencia, EPICisCita.cLicenciaInt  ")
					// 44, 45
					.append(" from EPICisCita ")
					.append(" inner join GRLUnimed   ON EPICisCita.iCveUniMed   = GRLUnimed.iCveUniMed  ")
					.append(" inner join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans ")
					.append(" left join GRLPuesto   ON EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  ")
					.append("                       and EPICisCita.iCvePuesto   = GRLPuesto.iCvePuesto  ")
					.append(" inner join GRLMotivo   ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo  ")
					.append(" inner join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion  ")
					.append(" inner join GRLModulo    ON EPICisCita.iCveUniMed = GRLModulo.iCveUniMed  ")
					.append("                        and EPICisCita.iCveModulo = GRLModulo.iCveModulo  ");
			if (cWhere != null) {
				cSQL.append(cWhere);
			}
			cSQL.append(" order by iCveCita ");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {

				vPERDatos.setICveExpediente(11); // null
				vPERDatos.setCSexo(rset.getString(8));
				vPERDatos.setCNombre(rset.getString(7));
				vPERDatos.setCApPaterno(rset.getString(5));
				vPERDatos.setCApMaterno(rset.getString(6));
				vPERDatos.setCRFC(rset.getString(10));
				vPERDatos.setCHomoclave(""); // null

				vPERDatos.setCCURP(rset.getString(11));

				vPERDatos.setDtNacimiento(rset.getDate(9));
				if (vPERDatos.getDtNacimiento() != null) {
					// //System.out.println("Fecha " +
					// vPERDatos.getDtNacimiento().toString());
				} else {
					// //System.out.println("Fecha " +
					// vPERDatos.getDtNacimiento().toString());
				}
				vPERDatos.setICvePaisNac(rset.getInt(12));
				vPERDatos.setICveEstadoNac(rset.getInt(13));

				vPERDatos.setCExpediente(rset.getString(14));
				vPERDatos.setCSenasPersonal(""); // null
				vPERDatos.setCCorreoElec(""); // null
				vPERDatos.setLDonadorOrg(0); // null
				vPERDatos.setCPersonaAviso(""); // null
				vPERDatos.setCTelAviso(""); // null
				vPERDatos.setCDirecAviso(""); // null
				vPERDatos.setCCorreoAviso(""); // null
				vPERDatos.setICveDireccion(1); // null
				vPERDatos.setICveFoto(0); // null
				vPERDatos.setLNoHuellas(0); // null
				vPERDatos.setICveNumEmpresa(0); // null
				vPERDatos.setICveUsuRegistra(iUsuario); // Toamdo de la sesi�n
				// validar transfronterizo
				unidad = rset.getInt(1);
				modulo = rset.getInt(42);
				// Licencias
				vPERDatos.setCLicencia(rset.getString(44));
				vPERDatos.setCLicenciaInt(rset.getString(45));

			}

			/* *******************Insert Perdatos********************** */
			String cSQLCvePersonal = "";
			String cSQLCveExpediente = "";
			int iPersonal = 0;
			int iExpediente = 0;

			cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
			pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			rsetCvePersona = pstmtCvePersonal.executeQuery();
			while (rsetCvePersona.next()) {
				iPersonal = rsetCvePersona.getInt(1);
			}
			vPERDatos.setICvePersonal(iPersonal + 1);

			vPERDatos.setICveExpediente(vPERDatos.getICvePersonal());
			cSQL = new StringBuffer();
			String cSQL1;
			cSQL1 = "insert into PERDatos(" + "iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "cNombre,"
					+ "cApPaterno,"
					+

					"cApMaterno,"
					+ "cRFC,"
					+ "cHomoclave,"
					+ "cCURP,"
					+ "dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+

					"lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "iCveNumEmpresa, "
					+
					// Licencias
					"cLicencia, "
					+ "cLicenciaInt, "
					+ "iCveUsuRegistra, "
					+ "tsgenerado "
					+

					")values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,?,?,(SELECT current timestamp FROM sysibm.sysdummy1))";

			pstmtInsDatos = conn.prepareStatement(cSQL1);

			pstmtInsDatos.setInt(1, vPERDatos.getICvePersonal());
			pstmtInsDatos.setInt(2, vPERDatos.getICveExpediente());
			pstmtInsDatos.setString(3, vPERDatos.getCSexo());
			pstmtInsDatos.setString(4, vPERDatos.getCNombre());
			pstmtInsDatos.setString(5, vPERDatos.getCApPaterno());

			pstmtInsDatos.setString(6, vPERDatos.getCApMaterno());
			pstmtInsDatos.setString(7, vPERDatos.getCRFC());
			pstmtInsDatos.setString(8, vPERDatos.getCHomoclave());

			pstmtInsDatos.setString(9, vPERDatos.getCCURP());
			pstmtInsDatos.setDate(10, vPERDatos.getDtNacimiento());

			pstmtInsDatos.setInt(11, vPERDatos.getICvePaisNac());
			pstmtInsDatos.setInt(12, vPERDatos.getICveEstadoNac());
			pstmtInsDatos.setString(13, vPERDatos.getCExpediente());
			pstmtInsDatos.setString(14, vPERDatos.getCSenasPersonal());
			pstmtInsDatos.setString(15, vPERDatos.getCCorreoElec());

			pstmtInsDatos.setInt(16, vPERDatos.getLDonadorOrg());
			pstmtInsDatos.setString(17, vPERDatos.getCPersonaAviso());
			pstmtInsDatos.setString(18, vPERDatos.getCDirecAviso());
			pstmtInsDatos.setString(19, vPERDatos.getCTelAviso());
			pstmtInsDatos.setString(20, vPERDatos.getCCorreoAviso());

			pstmtInsDatos.setInt(21, vPERDatos.getICveDireccion());
			pstmtInsDatos.setInt(22, vPERDatos.getICveFoto());
			pstmtInsDatos.setInt(23, vPERDatos.getLNoHuellas());
			pstmtInsDatos.setInt(24, vPERDatos.getICveNumEmpresa());
			// Licencias
			pstmtInsDatos.setString(25, vPERDatos.getCLicencia());
			pstmtInsDatos.setString(26, vPERDatos.getCLicenciaInt());

			pstmtInsDatos.setInt(27, vPERDatos.getICveUsuRegistra());
			pstmtInsDatos.executeUpdate();
			cClave = "" + vPERDatos.getICvePersonal();

			/* ************* Actualiza Clave*************** */

			cSQL1 = "update EPICisCita " + "set iCvePersonal = " + cClave + " "
					+ cActualiza;

			pstmtActualiza = conn.prepareStatement(cSQL1);

			pstmtActualiza.executeUpdate();

			// ##########################################################
			// Agregar bandera Linternacional
			if (modulo == 14 && unidad == 1) {
				cSQL1 = "update PERDATOS "
						+ "set lInternacional = 1 where icveexpediente = "
						+ vPERDatos.getICvePersonal();

				// System.out.println(cSQL1);
				pstmtActualiza = conn.prepareStatement(cSQL1);

				pstmtActualiza.executeUpdate();
			}

			/* ***************Genera Direccion **************** */
			cSQL1 = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+

					"EPICisCita.cCalle,"
					+ "EPICisCita.cNumExt,"
					+ "EPICisCita.cNumInt,"
					+ "EPICisCita.cColonia,"
					+ "EPICisCita.iCP,"
					+ "EPICisCita.cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "EPICisCita.cTel,"
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "EPICisCita.iCveModulo, "
					+ "GRLModulo.cDscModulo "
					+ "from EPICisCita "
					+ " inner join GRLUnimed ON EPICisCita.iCveUniMed = GRLUnimed.iCveUniMed "
					+ " inner join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans"
					+ " left join GRLPuesto ON EPICisCita.iCvePuesto = GRLPuesto.iCvePuesto "
					+ " and  EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  "
					+ " inner join GRLMotivo ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo "
					+ " and  GRLMotivo.iCveProceso = 1 "
					+ " inner join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion "
					+ " inner join GRLModulo ON EPICisCita.iCveModulo = GRLModulo.iCveModulo "
					+ " and EPICisCita.iCveUniMed = GRLModulo.iCveUniMed ";

			if (cWhere != null) {
				cSQL1 = cSQL1 + cWhere + " ";
			}
			cSQL1 = cSQL1 + "order by iCveCita";

			pstmtDir = conn.prepareStatement(cSQL1);
			rsetDir = pstmtDir.executeQuery();
			while (rsetDir.next()) {

				vPERDireccion.setICvePersonal(rsetDir.getInt(15));
				vPERDireccion.setCCalle(rsetDir.getString(16));
				vPERDireccion.setCNumExt(rsetDir.getString(17));
				vPERDireccion.setCNumInt(rsetDir.getString(18));
				vPERDireccion.setCColonia(rsetDir.getString(19));

				vPERDireccion.setICP(rsetDir.getInt(20));
				vPERDireccion.setCCiudad(rsetDir.getString(21));
				vPERDireccion.setICvePais(rsetDir.getInt(22));
				vPERDireccion.setICveEstado(rsetDir.getInt(23));
				vPERDireccion.setICveMunicipio(rsetDir.getInt(24));
				vPERDireccion.setCTel(rsetDir.getString(25));

			}

			/* ***************Inserta Direccion **************** */
			int iConsecutivo = 0;
			cSQLMax = "select max(iCveDireccion) from PERDireccion where iCvePersonal = ?";
			pstmtMax = conn.prepareStatement(cSQLMax);
			pstmtMax.setInt(1, vPERDireccion.getICvePersonal());
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iConsecutivo = rsetMax.getInt(1);
			}
			vPERDireccion.setICveDireccion(iConsecutivo + 1);

			cSQL1 = "insert into PERDireccion(" + "iCvePersonal,"
					+ "iCveDireccion," + "cCalle," + "cNumExt," + "cNumInt,"
					+ "cColonia," + "iCP," + "cCiudad," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cTel"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmtInsDir = conn.prepareStatement(cSQL1);

			pstmtInsDir.setInt(1, vPERDireccion.getICvePersonal());
			pstmtInsDir.setInt(2, vPERDireccion.getICveDireccion());
			pstmtInsDir.setString(3, vPERDireccion.getCCalle());
			pstmtInsDir.setString(4, vPERDireccion.getCNumExt());
			pstmtInsDir.setString(5, vPERDireccion.getCNumInt());
			pstmtInsDir.setString(6, vPERDireccion.getCColonia());
			pstmtInsDir.setInt(7, vPERDireccion.getICP());
			pstmtInsDir.setString(8, vPERDireccion.getCCiudad());
			pstmtInsDir.setInt(9, vPERDireccion.getICvePais());
			pstmtInsDir.setInt(10, vPERDireccion.getICveEstado());
			pstmtInsDir.setInt(11, vPERDireccion.getICveMunicipio());
			pstmtInsDir.setString(12, vPERDireccion.getCTel());
			pstmtInsDir.executeUpdate();

			/* ***************Respaldo Proceso Anterior **************** */

			/*
			 * vPERDatos = (TVPERDatos) dEPICisCita.GenExpediente(cWhere,
			 * iUsuario); cClave = (String) dPERDatos.insert(conn, vPERDatos);
			 * vEPICisCita = new TVEPICisCita();
			 * dEPICisCita.updateCvePersonal(conn, "set iCvePersonal = " +
			 * cClave + " " + cActualiza); vPERDireccion = (TVPERDireccion)
			 * dEPICisCita.GenDireccion(conn, cWhere);
			 * dPERDireccion.insert(conn, vPERDireccion);
			 */
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("Alta Personal.rollback", ex1);
			}
			warn("Alta Personal", ex);
			throw new DAOException("");
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}

				if (pstmtCveExpediente != null) {
					pstmtCveExpediente.close();
				}

				if (pstmtActualiza != null) {
					pstmtActualiza.close();
				}

				if (pstmtDir != null) {
					pstmtDir.close();
				}

				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (pstmtInsDir != null) {
					pstmtInsDir.close();
				}

				if (pstmtInsDatos != null) {
					pstmtInsDatos.close();
				}

				if (rset != null) {
					rset.close();
				}

				if (rsetCvePersona != null) {
					rsetCvePersona.close();
				}

				if (rsetCveExpediente != null) {
					rsetCveExpediente.close();
				}

				if (rsetDir != null) {
					rsetDir.close();
				}

				if (rsetMax != null) {
					rsetMax.close();
				}

				// if (conGral == null) {
				conn.close();
				// }
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Alta Personal.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo AltaPersonal NOM-024
	 * 
	 * @author AG SA.
	 */
	public Object AltaPersonal3(String cWhere, int iUsuario, String cActualiza)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtCveExpediente = null;
		PreparedStatement pstmtActualiza = null;
		PreparedStatement pstmtActualiza2 = null;
		PreparedStatement pstmtDir = null;
		PreparedStatement pstmtAd = null;
		PreparedStatement pstmtMax = null;
		PreparedStatement pstmtInsDir = null;
		PreparedStatement pstmtInsAd = null;
		PreparedStatement pstmtInsDatos = null;

		ResultSet rset = null;
		ResultSet rsetCvePersona = null;
		ResultSet rsetCveExpediente = null;
		ResultSet rsetDir = null;
		ResultSet rsetAd = null;
		ResultSet rsetMax = null;

		int unidad = 0;
		int modulo = 0;

		String cClave = "";
		StringBuffer cSQL = new StringBuffer();
		String cSQLMax = "";
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			TVPERDatos vPERDatos = new TVPERDatos();
			TDEPICisCita dEPICisCita = new TDEPICisCita();
			TVEPICisCita vEPICisCita;
			TDPERDatos dPERDatos = new TDPERDatos();
			TDPERDireccion dPERDireccion = new TDPERDireccion();
			TVPERDireccion vPERDireccion = new TVPERDireccion();
			TVEPICisCita vEPICisCita2 = new TVEPICisCita();

			/***************************************** GenExpediente *****/
			cSQL.append(" select  ")
					.append("         EPICisCita.iCveUniMed, ")
					.append("         dtFecha, ")
					.append("         iCveCita, ")
					.append("         cHora, ")
					.append("         cApPaterno, ")
					.append("         cApMaterno, ")
					.append("         EPICisCita.cNombre, ")
					.append("         cGenero, ")
					.append("         dtNacimiento, ")
					.append("         cRFC, ")
					.append("         cCURP, ")
					.append("         EPICisCita.iCvePaisNac, ")
					.append("         EPICisCita.iCveEstadoNac, ")
					.append("         cExpediente, ")
					// 14
					.append("         iCvePersonal, ")
					.append("         EPICisCita.cCalle, ")
					.append("         EPICisCita.cNumExt, ")
					.append("         EPICisCita.cNumInt, ")
					.append("         EPICisCita.cColonia, ")
					.append("         EPICisCita.iCP, ")
					.append("         EPICisCita.cCiudad, ")
					.append("         EPICisCita.iCvePais, ")
					.append("         EPICisCita.iCveEstado, ")
					.append("         EPICisCita.iCveMunicipio, ")
					.append("         EPICisCita.cTel, ")
					.append("         lCambioDir, ")
					.append("         EPICisCita.iCveMdoTrans, ")
					.append("         EPICisCita.iCvePuesto, ")
					.append("         EPICisCita.iCveCategoria, ")
					.append("         EPICisCita.iCveMotivo, ")
					.append("         cObservacion, ")
					.append("         EPICisCita.iCveSituacion, ")
					.append("         iCveUsuCita, lRealizoExam, iCveUsuMCIS, ")
					// 33,34, 35
					.append("         lProdNoConf,  GRLUnimed.cDscUniMed, GRLMdoTrans.cDscMdoTrans,")
					// 36-38
					.append("         GRLPuesto.cDscPuesto,  GRLMotivo.cDscMotivo, ")
					.append("         EPISituacion.cDscSituacion,  ")
					.append("         EPICisCita.iCveModulo,  GRLModulo.cDscModulo,  ")
					// Licencias
					.append("         EPICisCita.cLicencia, EPICisCita.cLicenciaInt, EPICisCita.cSexo_DGIS, ")
					// 44, 45, 46
					.append("         EPICisCita.iCveLocalidad ")
					// 47
					.append(" from EPICisCita ")
					.append(" inner join GRLUnimed   ON EPICisCita.iCveUniMed   = GRLUnimed.iCveUniMed  ")
					.append(" inner join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans ")
					.append(" left join GRLPuesto   ON EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  ")
					.append("                       and EPICisCita.iCvePuesto   = GRLPuesto.iCvePuesto  ")
					.append(" inner join GRLMotivo   ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo  ")
					.append(" inner join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion  ")
					.append(" inner join GRLModulo    ON EPICisCita.iCveUniMed = GRLModulo.iCveUniMed  ")
					.append("                        and EPICisCita.iCveModulo = GRLModulo.iCveModulo  ");
			if (cWhere != null) {
				cSQL.append(cWhere);
			}
			cSQL.append(" order by iCveCita ");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {

				vPERDatos.setICveExpediente(11); // null
				vPERDatos.setCSexo(rset.getString(8));
				vPERDatos.setCNombre(rset.getString(7));
				vPERDatos.setCApPaterno(rset.getString(5));
				vPERDatos.setCApMaterno(rset.getString(6));
				vPERDatos.setCRFC(rset.getString(10));
				vPERDatos.setCHomoclave(""); // null

				vPERDatos.setCCURP(rset.getString(11));

				vPERDatos.setDtNacimiento(rset.getDate(9));
				if (vPERDatos.getDtNacimiento() != null) {
					// //System.out.println("Fecha " +
					// vPERDatos.getDtNacimiento().toString());
				} else {
					// //System.out.println("Fecha " +
					// vPERDatos.getDtNacimiento().toString());
				}
				vPERDatos.setICvePaisNac(rset.getInt(12));
				vPERDatos.setICveEstadoNac(rset.getInt(13));

				vPERDatos.setCExpediente(rset.getString(14));
				vPERDatos.setCSenasPersonal(""); // null
				vPERDatos.setCCorreoElec(""); // null
				vPERDatos.setLDonadorOrg(0); // null
				vPERDatos.setCPersonaAviso(""); // null
				vPERDatos.setCTelAviso(""); // null
				vPERDatos.setCDirecAviso(""); // null
				vPERDatos.setCCorreoAviso(""); // null
				vPERDatos.setICveDireccion(1); // null
				vPERDatos.setICveFoto(0); // null
				vPERDatos.setLNoHuellas(0); // null
				vPERDatos.setICveNumEmpresa(0); // null
				vPERDatos.setICveUsuRegistra(iUsuario); // Toamdo de la sesi�n
				// validar transfronterizo
				unidad = rset.getInt(1);
				modulo = rset.getInt(42);
				// Licencias
				vPERDatos.setCLicencia(rset.getString(44));
				vPERDatos.setCLicenciaInt(rset.getString(45));
				vPERDatos.setCSexo_DGIS(rset.getString(46));
				vPERDatos.setICveLocalidad(rset.getInt(47));

			}

			/* *******************Insert Perdatos********************** */
			String cSQLCvePersonal = "";
			String cSQLCveExpediente = "";
			int iPersonal = 0;
			int iExpediente = 0;

			cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
			pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			rsetCvePersona = pstmtCvePersonal.executeQuery();
			while (rsetCvePersona.next()) {
				iPersonal = rsetCvePersona.getInt(1);
			}
			vPERDatos.setICvePersonal(iPersonal + 1);

			vPERDatos.setICveExpediente(vPERDatos.getICvePersonal());
			cSQL = new StringBuffer();
			String cSQL1;
			cSQL1 = "insert into PERDatos(" + "iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "cNombre,"
					+ "cApPaterno,"
					+

					"cApMaterno,"
					+ "cRFC,"
					+ "cHomoclave,"
					+ "cCURP,"
					+ "dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+

					"lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "iCveNumEmpresa, "
					+
					// Licencias
					"cLicencia, "
					+ "cLicenciaInt, "
					+ "iCveUsuRegistra, "
					+ "tsgenerado, "
					
					+ "cSexo_DGIS "
					+
					
					")values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,?,?,(SELECT current timestamp FROM sysibm.sysdummy1),?)";

			pstmtInsDatos = conn.prepareStatement(cSQL1);

			pstmtInsDatos.setInt(1, vPERDatos.getICvePersonal());
			pstmtInsDatos.setInt(2, vPERDatos.getICveExpediente());
			pstmtInsDatos.setString(3, vPERDatos.getCSexo());
			pstmtInsDatos.setString(4, vPERDatos.getCNombre());
			pstmtInsDatos.setString(5, vPERDatos.getCApPaterno());

			pstmtInsDatos.setString(6, vPERDatos.getCApMaterno());
			pstmtInsDatos.setString(7, vPERDatos.getCRFC());
			pstmtInsDatos.setString(8, vPERDatos.getCHomoclave());

			pstmtInsDatos.setString(9, vPERDatos.getCCURP());
			pstmtInsDatos.setDate(10, vPERDatos.getDtNacimiento());

			pstmtInsDatos.setInt(11, vPERDatos.getICvePaisNac());
			pstmtInsDatos.setInt(12, vPERDatos.getICveEstadoNac());
			pstmtInsDatos.setString(13, vPERDatos.getCExpediente());
			pstmtInsDatos.setString(14, vPERDatos.getCSenasPersonal());
			pstmtInsDatos.setString(15, vPERDatos.getCCorreoElec());

			pstmtInsDatos.setInt(16, vPERDatos.getLDonadorOrg());
			pstmtInsDatos.setString(17, vPERDatos.getCPersonaAviso());
			pstmtInsDatos.setString(18, vPERDatos.getCDirecAviso());
			pstmtInsDatos.setString(19, vPERDatos.getCTelAviso());
			pstmtInsDatos.setString(20, vPERDatos.getCCorreoAviso());

			pstmtInsDatos.setInt(21, vPERDatos.getICveDireccion());
			pstmtInsDatos.setInt(22, vPERDatos.getICveFoto());
			pstmtInsDatos.setInt(23, vPERDatos.getLNoHuellas());
			pstmtInsDatos.setInt(24, vPERDatos.getICveNumEmpresa());
			// Licencias
			pstmtInsDatos.setString(25, vPERDatos.getCLicencia());
			pstmtInsDatos.setString(26, vPERDatos.getCLicenciaInt());

			pstmtInsDatos.setInt(27, vPERDatos.getICveUsuRegistra());
			pstmtInsDatos.setString(28, vPERDatos.getCSexo_DGIS());
			pstmtInsDatos.executeUpdate();
			cClave = "" + vPERDatos.getICvePersonal();

			/* ************* Actualiza Clave*************** */

			cSQL1 = "update EPICisCita " + "set iCvePersonal = " + cClave + " "
					+ cActualiza;

			pstmtActualiza = conn.prepareStatement(cSQL1);

			// /Actualiza
			pstmtActualiza.executeUpdate();

			cSQL1 = "update EPICisCitaAdnl " + "set iCvePersonal = " + cClave
					+ " " + cActualiza;

			pstmtActualiza = conn.prepareStatement(cSQL1);

			pstmtActualiza.executeUpdate();

			// ##########################################################
			// Agregar bandera Linternacional
			if (modulo == 14 && unidad == 1) {
				cSQL1 = "update PERDATOS "
						+ "set lInternacional = 1 where icveexpediente = "
						+ vPERDatos.getICvePersonal();

				// System.out.println(cSQL1);
				pstmtActualiza = conn.prepareStatement(cSQL1);

				pstmtActualiza.executeUpdate();
			}

			/* ***************Genera Direccion **************** */
			cSQL1 = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+

					"EPICisCita.cCalle,"
					+ "EPICisCita.cNumExt,"
					+ "EPICisCita.cNumInt,"
					+ "EPICisCita.cColonia,"
					+ "EPICisCita.iCP,"
					+ "EPICisCita.cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "EPICisCita.cTel,"
					+ "EPICisCita.iCveLocalidad,"					
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "EPICisCita.iCveModulo, "
					+ "GRLModulo.cDscModulo "
					
					+ "from EPICisCita "
					+ " inner join GRLUnimed ON EPICisCita.iCveUniMed = GRLUnimed.iCveUniMed "
					+ " inner join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans"
					+ " left join GRLPuesto ON EPICisCita.iCvePuesto = GRLPuesto.iCvePuesto "
					+ " and  EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  "
					+ " inner join GRLMotivo ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo "
					+ " and  GRLMotivo.iCveProceso = 1 "
					+ " inner join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion "
					+ " inner join GRLModulo ON EPICisCita.iCveModulo = GRLModulo.iCveModulo "
					+ " and EPICisCita.iCveUniMed = GRLModulo.iCveUniMed ";

			if (cWhere != null) {
				cSQL1 = cSQL1 + cWhere + " ";
			}
			cSQL1 = cSQL1 + "order by iCveCita";

			pstmtDir = conn.prepareStatement(cSQL1);
			rsetDir = pstmtDir.executeQuery();
			while (rsetDir.next()) {

				vPERDireccion.setICvePersonal(rsetDir.getInt(15));
				vPERDireccion.setCCalle(rsetDir.getString(16));
				vPERDireccion.setCNumExt(rsetDir.getString(17));
				vPERDireccion.setCNumInt(rsetDir.getString(18));
				vPERDireccion.setCColonia(rsetDir.getString(19));

				vPERDireccion.setICP(rsetDir.getInt(20));
				vPERDireccion.setCCiudad(rsetDir.getString(21));
				vPERDireccion.setICvePais(rsetDir.getInt(22));
				vPERDireccion.setICveEstado(rsetDir.getInt(23));
				vPERDireccion.setICveMunicipio(rsetDir.getInt(24));
				vPERDireccion.setCTel(rsetDir.getString(25));
				vPERDireccion.setICveLocalidad(rsetDir.getInt(26));

			}

			/* ***************Inserta Direccion **************** */
			int iConsecutivo = 0;
			cSQLMax = "select max(iCveDireccion) from PERDireccion where iCvePersonal = ?";
			pstmtMax = conn.prepareStatement(cSQLMax);
			pstmtMax.setInt(1, vPERDireccion.getICvePersonal());
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iConsecutivo = rsetMax.getInt(1);
			}
			vPERDireccion.setICveDireccion(iConsecutivo + 1);

			cSQL1 = "insert into PERDireccion(" + "iCvePersonal,"
					+ "iCveDireccion," + "cCalle," + "cNumExt," + "cNumInt,"
					+ "cColonia," + "iCP," + "cCiudad," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cTel,"+ "iCveLocalidad"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmtInsDir = conn.prepareStatement(cSQL1);

			pstmtInsDir.setInt(1, vPERDireccion.getICvePersonal());
			pstmtInsDir.setInt(2, vPERDireccion.getICveDireccion());
			pstmtInsDir.setString(3, vPERDireccion.getCCalle());
			pstmtInsDir.setString(4, vPERDireccion.getCNumExt());
			pstmtInsDir.setString(5, vPERDireccion.getCNumInt());
			pstmtInsDir.setString(6, vPERDireccion.getCColonia());
			pstmtInsDir.setInt(7, vPERDireccion.getICP());
			pstmtInsDir.setString(8, vPERDireccion.getCCiudad());
			pstmtInsDir.setInt(9, vPERDireccion.getICvePais());
			pstmtInsDir.setInt(10, vPERDireccion.getICveEstado());
			pstmtInsDir.setInt(11, vPERDireccion.getICveMunicipio());
			pstmtInsDir.setString(12, vPERDireccion.getCTel());
			pstmtInsDir.setInt(13, vPERDireccion.getICveLocalidad());
			pstmtInsDir.executeUpdate();

			/* ***************Genera Adicionales **************** */
			cSQL1 = " SELECT E.cTel2, "
					+ "                E.iCveVivienda,"
					+ "                V.cConcepto,                 "
					+ "                E.iCveDiscapacidad, "
					+ "                D.cDcsDiscapacidad, "
					+ "                E.iCveGpoEtnico,"
					+ "                T.cGpoEtnico,"
					+ "                E.iCveReligion,"
					+ "                R.cCodDsc,"
					+ "                E.iCveNivelSec,"
					+ "                N.cNivelSec,"
					+ "                E.iCveParPol,"
					+ "                P.cDscParPol,"
					+ "                E.iCveTpoSangre,"
					+ "                S.cTpoSangre,"
					+ "                E.iCveECivil,"
					+ "                C.cECivil "
					+ "    FROM "
					+ "                EPICISCITAADNL AS E,"
					+ "		 EPICISCITA AS EPICISCITA,"
					+ "                GRLVIVIENDA AS V,"
					+ "                GRLDISCAPACIDAD AS D,"
					+ "                GRLGPOETNICO AS T,"
					+ "                GRLRELIGION AS R,"
					+ "                GRLNIVELSEC AS N,"
					+ "                GRLPARPOL AS P,"
					+ "                GRLTPOSANGRE AS S,"
					+ "                GRLECIVIL AS C "
					+ cWhere
					+ "	and	 E.iCveUniMed = EPICisCita.iCveUniMed AND"
					+ "		 E.dtFecha = EPICisCita.dtFecha AND		"
					+ "                EPICisCita.iCveCita = EPICisCita.iCveCita AND"
					+ "		 E.iCveModulo = EPICisCita.iCveModulo AND"
					+ "                E.ICVEVIVIENDA = V.ICVEVIVIENDA AND "
					+ "                E.ICVEDISCAPACIDAD = D.ICVEDISCAPACIDAD AND "
					+ "                E.ICVEGPOETNICO = T.ICVEGPOETNICO AND  "
					+ "                E.ICVERELIGION = R.ICVERELIGION AND "
					+ "                E.ICVENIVELSEC = N.ICVENIVELSEC AND "
					+ "                E.ICVEPARPOL = P.ICVEPARPOL  AND "
					+ "                E.ICVETPOSANGRE = S.ICVETPOSANGRE  AND "
					+ "                E.ICVEECIVIL = C.ICVEECIVIL     "
					+ "                order by EPICisCita.iCveCita";

			// System.out.println(cSQL1);

			pstmtAd = conn.prepareStatement(cSQL1);
			rsetAd = pstmtAd.executeQuery();
			while (rsetAd.next()) {
				vEPICisCita2.setCTelefono2(rsetAd.getString(1));
				vEPICisCita2.setICveVivienda(rsetAd.getInt(2));
				vEPICisCita2.setCConcepto(rsetAd.getString(3));
				vEPICisCita2.setICveDiscapacidad(rsetAd.getInt(4));
				vEPICisCita2.setCDcsDiscapacidad(rsetAd.getString(5));
				vEPICisCita2.setICveGpoEtnico(rsetAd.getInt(6));
				vEPICisCita2.setCGpoEtnico(rsetAd.getString(7));
				vEPICisCita2.setICveReligion(rsetAd.getInt(8));
				vEPICisCita2.setCCodDsc(rsetAd.getString(9));
				vEPICisCita2.setICveNivelSEC(rsetAd.getInt(10));
				vEPICisCita2.setCNivelSec(rsetAd.getString(11));
				vEPICisCita2.setICveParPOL(rsetAd.getInt(12));
				vEPICisCita2.setCDscParPol(rsetAd.getString(13));
				vEPICisCita2.setICveTpoSangre(rsetAd.getInt(14));
				vEPICisCita2.setCTpoSangre(rsetAd.getString(15));
				vEPICisCita2.setICveECivil(rsetAd.getInt(16));
				vEPICisCita2.setCECivil(rsetAd.getString(17));
			}

			/*
			 *  ***************Inserta Direccion **************** cSQLMax =
			 * "select max(iCveDireccion) from PERDireccion where iCvePersonal = ?"
			 * ; pstmtMax = conn.prepareStatement(cSQLMax); pstmtMax.setInt(1,
			 * vPERDireccion.getICvePersonal()); rsetMax =
			 * pstmtMax.executeQuery(); while (rsetMax.next()) { iConsecutivo =
			 * rsetMax.getInt(1); } vPERDireccion.setICveDireccion(iConsecutivo
			 * + 1);
			 */
/*
			cSQL1 = "insert into PERADICIONAL(" + "iCvePersonal,"
					+ "iCveVivienda," + "iCveDiscapacidad," + "iCveGpoEtnico,"
					+ "iCveReligion," + "iCveNivelSec," + "iCveParPol,"
					+ "iCveECivil," + "cTel2," + "iCveTpoSangre"
					+ ")values(?,?,?,?,?,?,?,?,?,?)";

			pstmtInsAd = conn.prepareStatement(cSQL1);

			// System.out.println(vPERDireccion.getICvePersonal());
			// System.out.println(vEPICisCita2.getICveVivienda());
			// System.out.println( vEPICisCita2.getICveDiscapacidad());
			// System.out.println( vEPICisCita2.getICveGpoEtnico());
			// System.out.println( vEPICisCita2.getICveReligion());
			// System.out.println( vEPICisCita2.getICveNivelSEC());
			// System.out.println( vEPICisCita2.getICveParPOL());
			// System.out.println( vEPICisCita2.getICveECivil());
			// System.out.println( vEPICisCita2.getCTelefono2());
			// System.out.println( vEPICisCita2.getICveTpoSangre());

			pstmtInsAd.setInt(1, vPERDireccion.getICvePersonal());
			pstmtInsAd.setInt(2, vEPICisCita2.getICveVivienda());
			pstmtInsAd.setInt(3, vEPICisCita2.getICveDiscapacidad());
			pstmtInsAd.setInt(4, vEPICisCita2.getICveGpoEtnico());
			pstmtInsAd.setInt(5, vEPICisCita2.getICveReligion());
			pstmtInsAd.setInt(6, vEPICisCita2.getICveNivelSEC());
			pstmtInsAd.setInt(7, vEPICisCita2.getICveParPOL());
			pstmtInsAd.setInt(8, vEPICisCita2.getICveECivil());
			pstmtInsAd.setString(9, vEPICisCita2.getCTelefono2());
			pstmtInsAd.setInt(10, vEPICisCita2.getICveTpoSangre());
			pstmtInsAd.executeUpdate();*/

			/* ***************Respaldo Proceso Anterior **************** */

			/*
			 * vPERDatos = (TVPERDatos) dEPICisCita.GenExpediente(cWhere,
			 * iUsuario); cClave = (String) dPERDatos.insert(conn, vPERDatos);
			 * vEPICisCita = new TVEPICisCita();
			 * dEPICisCita.updateCvePersonal(conn, "set iCvePersonal = " +
			 * cClave + " " + cActualiza); vPERDireccion = (TVPERDireccion)
			 * dEPICisCita.GenDireccion(conn, cWhere);
			 * dPERDireccion.insert(conn, vPERDireccion);
			 */
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("Alta Personal.rollback", ex1);
			}
			warn("Alta Personal", ex);
			throw new DAOException("");
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}

				if (pstmtCveExpediente != null) {
					pstmtCveExpediente.close();
				}

				if (pstmtActualiza != null) {
					pstmtActualiza.close();
				}

				if (pstmtDir != null) {
					pstmtDir.close();
				}

				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (pstmtInsDir != null) {
					pstmtInsDir.close();
				}

				if (pstmtInsDatos != null) {
					pstmtInsDatos.close();
				}

				if (rset != null) {
					rset.close();
				}

				if (rsetCvePersona != null) {
					rsetCvePersona.close();
				}

				if (rsetCveExpediente != null) {
					rsetCveExpediente.close();
				}

				if (rsetDir != null) {
					rsetDir.close();
				}

				if (rsetMax != null) {
					rsetMax.close();
				}

				// if (conGral == null) {
				conn.close();
				// }
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Alta Personal.close", ex2);
			}
			return cClave;
		}
	}
	
	
	
	

	/**
	 * Metodo AltaPersonal NOM-024
	 * 
	 * @author AG SA.
	 */
	@SuppressWarnings("finally")
	public int AltaPersonalBootstrap(TVPERDatos vPERDatos, TVPERDireccion vPERDireccion)
			throws DAOException {
		int nuevoExpediente = 0;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtCveExpediente = null;
		PreparedStatement pstmtActualiza = null;
		PreparedStatement pstmtDir = null;
		PreparedStatement pstmtMax = null;
		PreparedStatement pstmtInsDir = null;
		PreparedStatement pstmtInsDatos = null;

		ResultSet rset = null;
		ResultSet rsetCvePersona = null;
		ResultSet rsetCveExpediente = null;
		ResultSet rsetDir = null;
		ResultSet rsetMax = null;

		String cClave = "";
		String cSQLMax = "";
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			/***************************************** GenExpediente *****/
			/* *******************Insert Perdatos********************** */
			String cSQLCvePersonal = "";
			int iPersonal = 0;
			cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
			pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			rsetCvePersona = pstmtCvePersonal.executeQuery();
			while (rsetCvePersona.next()) {
				iPersonal = rsetCvePersona.getInt(1);
			}
			vPERDatos.setICvePersonal(iPersonal + 1);

			
			System.out.println(vPERDatos.getICvePersonal());
			
			vPERDatos.setICveExpediente(vPERDatos.getICvePersonal());
			String cSQL1;
			cSQL1 = "insert into PERDatos(" + "iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "cNombre,"
					+ "cApPaterno,"
					+

					"cApMaterno,"
					+ "cRFC,"
					+ "cHomoclave,"
					+ "cCURP,"
					+ "dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+

					"lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "iCveNumEmpresa, "
					+
					// Licencias
					"cLicencia, "
					+ "cLicenciaInt, "
					+ "iCveUsuRegistra, "
					+ "tsgenerado, "
					
					+ "cSexo_DGIS "
					+
					
					")values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?,?,?,(SELECT current timestamp FROM sysibm.sysdummy1),?)";

			pstmtInsDatos = conn.prepareStatement(cSQL1);
			System.out.println("persona Agregada1");
			pstmtInsDatos.setInt(1, vPERDatos.getICvePersonal());
			pstmtInsDatos.setInt(2, vPERDatos.getICveExpediente());
			pstmtInsDatos.setString(3, vPERDatos.getCSexo());
			pstmtInsDatos.setString(4, vPERDatos.getCNombre());
			pstmtInsDatos.setString(5, vPERDatos.getCApPaterno());
			System.out.println("persona Agregada2");
			pstmtInsDatos.setString(6, vPERDatos.getCApMaterno());
			pstmtInsDatos.setString(7, vPERDatos.getCRFC());
			pstmtInsDatos.setString(8, vPERDatos.getCHomoclave());
			System.out.println("persona Agregada3");
			pstmtInsDatos.setString(9, vPERDatos.getCCURP());
			pstmtInsDatos.setDate(10, vPERDatos.getDtNacimiento());
			System.out.println("persona Agregada4");
			pstmtInsDatos.setInt(11, vPERDatos.getICvePaisNac());
			pstmtInsDatos.setInt(12, vPERDatos.getICveEstadoNac());
			pstmtInsDatos.setString(13, vPERDatos.getCExpediente());
			pstmtInsDatos.setString(14, vPERDatos.getCSenasPersonal());
			pstmtInsDatos.setString(15, vPERDatos.getCCorreoElec());
			System.out.println("persona Agregada5");
			pstmtInsDatos.setInt(16, vPERDatos.getLDonadorOrg());
			pstmtInsDatos.setString(17, vPERDatos.getCPersonaAviso());
			pstmtInsDatos.setString(18, vPERDatos.getCDirecAviso());
			pstmtInsDatos.setString(19, vPERDatos.getCTelAviso());
			pstmtInsDatos.setString(20, vPERDatos.getCCorreoAviso());
			System.out.println("persona Agregada5");
			pstmtInsDatos.setInt(21, vPERDatos.getICveDireccion());
			pstmtInsDatos.setInt(22, vPERDatos.getICveFoto());
			pstmtInsDatos.setInt(23, vPERDatos.getLNoHuellas());
			pstmtInsDatos.setInt(24, vPERDatos.getICveNumEmpresa());
			// Licencias
			pstmtInsDatos.setString(25, vPERDatos.getCLicencia());
			pstmtInsDatos.setString(26, vPERDatos.getCLicenciaInt());
			System.out.println("persona Agregada6");
			pstmtInsDatos.setInt(27, vPERDatos.getICveUsuRegistra());
			pstmtInsDatos.setString(28, vPERDatos.getCSexo_DGIS());
			System.out.println("persona Agregada7");
			pstmtInsDatos.executeUpdate();
			cClave = "" + vPERDatos.getICvePersonal();

			
			/* ***************Inserta Direccion **************** */
			vPERDireccion.setICvePersonal(vPERDatos.getICvePersonal());
			int iConsecutivo = 0;
			cSQLMax = "select max(iCveDireccion) from PERDireccion where iCvePersonal = ?";
			pstmtMax = conn.prepareStatement(cSQLMax);
			pstmtMax.setInt(1, vPERDireccion.getICvePersonal());
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iConsecutivo = rsetMax.getInt(1);
			}
			
			System.out.println("direccion");
			
			vPERDireccion.setICveDireccion(iConsecutivo + 1);

			cSQL1 = "insert into PERDireccion(" + "iCvePersonal,"
					+ "iCveDireccion," + "cCalle," + "cNumExt," + "cNumInt,"
					+ "cColonia," + "iCP," + "cCiudad," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cTel,"+ "iCveLocalidad"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmtInsDir = conn.prepareStatement(cSQL1);

			pstmtInsDir.setInt(1, vPERDireccion.getICvePersonal());
			
			System.out.println("direccion2");
			
			pstmtInsDir.setInt(2, vPERDireccion.getICveDireccion());
			pstmtInsDir.setString(3, vPERDireccion.getCCalle());
			pstmtInsDir.setString(4, vPERDireccion.getCNumExt());
			pstmtInsDir.setString(5, vPERDireccion.getCNumInt());
			pstmtInsDir.setString(6, vPERDireccion.getCColonia());
			pstmtInsDir.setInt(7, vPERDireccion.getICP());
			pstmtInsDir.setString(8, vPERDireccion.getCCiudad());
			pstmtInsDir.setInt(9, vPERDireccion.getICvePais());
			pstmtInsDir.setInt(10, vPERDireccion.getICveEstado());
			pstmtInsDir.setInt(11, vPERDireccion.getICveMunicipio());
			pstmtInsDir.setString(12, vPERDireccion.getCTel());
			pstmtInsDir.setInt(13, vPERDireccion.getICveLocalidad());
			System.out.println("direccion3");
			
			pstmtInsDir.executeUpdate();
			System.out.println("direccion4");
			
			conn.commit();
			System.out.println("direccion5");
			
			/////////////Se Genero todo Correctamemte //////////////
			nuevoExpediente = vPERDireccion.getICvePersonal();
			
			
		} catch (Exception ex) {
			try {
				conn.rollback();
				nuevoExpediente = 0;
			} catch (Exception ex1) {
				warn("Alta PersonalBot.rollback", ex1);
			}
			warn("Alta Personal Bot", ex);
			throw new DAOException("");
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}

				if (pstmtCveExpediente != null) {
					pstmtCveExpediente.close();
				}

				if (pstmtActualiza != null) {
					pstmtActualiza.close();
				}

				if (pstmtDir != null) {
					pstmtDir.close();
				}

				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (pstmtInsDir != null) {
					pstmtInsDir.close();
				}

				if (pstmtInsDatos != null) {
					pstmtInsDatos.close();
				}

				if (rset != null) {
					rset.close();
				}

				if (rsetCvePersona != null) {
					rsetCvePersona.close();
				}

				if (rsetCveExpediente != null) {
					rsetCveExpediente.close();
				}

				if (rsetDir != null) {
					rsetDir.close();
				}

				if (rsetMax != null) {
					rsetMax.close();
				}

				// if (conGral == null) {
				conn.close();
				// }
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Alta Personal.close", ex2);
			}
			return nuevoExpediente;
		}
	}
	
	

	/**
	 * Metodo AltaPersonal
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Object AltaPersonal2(String cWhere, int iUsuario, int iCveJUniMed,
			String cActualiza) throws DAOException {
		// System.out.println("Dando de alta personal");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtCveExpediente = null;
		PreparedStatement pstmtActualiza = null;
		PreparedStatement pstmtDir = null;
		PreparedStatement pstmtMax = null;
		PreparedStatement pstmtInsDir = null;
		PreparedStatement pstmtInsDatos = null;

		ResultSet rset = null;
		ResultSet rsetCvePersona = null;
		ResultSet rsetCveExpediente = null;
		ResultSet rsetDir = null;
		ResultSet rsetMax = null;

		String cClave = "";
		StringBuffer cSQL = new StringBuffer();
		String cSQLMax = "";
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			TVPERDatos vPERDatos = new TVPERDatos();
			TDEPICisCita dEPICisCita = new TDEPICisCita();
			TVEPICisCita vEPICisCita;
			TDPERDatos dPERDatos = new TDPERDatos();
			TDPERDireccion dPERDireccion = new TDPERDireccion();
			TVPERDireccion vPERDireccion = new TVPERDireccion();

			/***************************************** GenExpediente *****/
			cSQL.append(" select  ")
					.append("         EPICisCita.iCveUniMed, ")
					.append("         dtFecha, ")
					.append("         iCveCita, ")
					.append("         cHora, ")
					.append("         cApPaterno, ")
					.append("         cApMaterno, ")
					.append("         EPICisCita.cNombre, ")
					.append("         cGenero, ")
					.append("         dtNacimiento, ")
					.append("         cRFC, ")
					.append("         cCURP, ")
					.append("         EPICisCita.iCvePaisNac, ")
					.append("         EPICisCita.iCveEstadoNac, ")
					.append("         cExpediente, ")
					.append("         iCvePersonal, ")
					.append("         EPICisCita.cCalle, ")
					.append("         EPICisCita.cNumExt, ")
					.append("         EPICisCita.cNumInt, ")
					.append("         EPICisCita.cColonia, ")
					.append("         EPICisCita.iCP, ")
					.append("         EPICisCita.cCiudad, ")
					.append("         EPICisCita.iCvePais, ")
					.append("         EPICisCita.iCveEstado, ")
					.append("         EPICisCita.iCveMunicipio, ")
					.append("         EPICisCita.cTel, ")
					.append("         lCambioDir, ")
					.append("         EPICisCita.iCveMdoTrans, ")
					.append("         EPICisCita.iCvePuesto, ")
					.append("         EPICisCita.iCveCategoria, ")
					.append("         EPICisCita.iCveMotivo, ")
					.append("         cObservacion, ")
					.append("         EPICisCita.iCveSituacion, ")
					.append("         iCveUsuCita, lRealizoExam, iCveUsuMCIS, ")
					.append("         lProdNoConf,  GRLUnimed.cDscUniMed, GRLMdoTrans.cDscMdoTrans,")
					.append("         GRLPuesto.cDscPuesto,  GRLMotivo.cDscMotivo, ")
					.append("         EPISituacion.cDscSituacion,  ")
					.append("         EPICisCita.iCveModulo,  GRLModulo.cDscModulo  ")
					.append(" from EPICisCita ")
					.append(" inner join GRLUnimed   ON EPICisCita.iCveUniMed   = GRLUnimed.iCveUniMed  ")
					.append(" inner join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans ")
					.append(" left join GRLPuesto   ON EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  ")
					.append("                       and EPICisCita.iCvePuesto   = GRLPuesto.iCvePuesto  ")
					.append(" inner join GRLMotivo   ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo  ")
					.append(" inner join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion  ")
					.append(" inner join GRLModulo    ON EPICisCita.iCveUniMed = GRLModulo.iCveUniMed  ")
					.append("                        and EPICisCita.iCveModulo = GRLModulo.iCveModulo  ");
			if (cWhere != null) {
				cSQL.append(cWhere);
			}
			cSQL.append(" order by iCveCita ");

			// System.out.print(cSQL.toString());

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {

				vPERDatos.setICveExpediente(11); // null
				vPERDatos.setCSexo(rset.getString(8));
				vPERDatos.setCNombre(rset.getString(7));
				vPERDatos.setCApPaterno(rset.getString(5));
				vPERDatos.setCApMaterno(rset.getString(6));
				vPERDatos.setCRFC(rset.getString(10));
				vPERDatos.setCHomoclave(""); // null

				vPERDatos.setCCURP(rset.getString(11));

				vPERDatos.setDtNacimiento(rset.getDate(9));
				/*
				 * if (vPERDatos.getDtNacimiento() != null) {
				 * //System.out.println("Fecha " +
				 * vPERDatos.getDtNacimiento().toString()); } else {
				 * //System.out.println("Fecha " +
				 * vPERDatos.getDtNacimiento().toString()); }*
				 */
				vPERDatos.setICvePaisNac(rset.getInt(12));
				vPERDatos.setICveEstadoNac(rset.getInt(13));

				vPERDatos.setCExpediente(rset.getString(14));
				vPERDatos.setCSenasPersonal(""); // null
				vPERDatos.setCCorreoElec(""); // null
				vPERDatos.setLDonadorOrg(0); // null
				vPERDatos.setCPersonaAviso(""); // null
				vPERDatos.setCTelAviso(""); // null
				vPERDatos.setCDirecAviso(""); // null
				vPERDatos.setCCorreoAviso(""); // null
				vPERDatos.setICveDireccion(1); // null
				vPERDatos.setICveFoto(0); // null
				vPERDatos.setLNoHuellas(0); // null
				vPERDatos.setICveNumEmpresa(0); // null
				vPERDatos.setICveUsuRegistra(iUsuario); // Toamdo de la sesi�n
				vPERDatos.setICveJUniMed(iCveJUniMed); // Obtenido en la
														// validacion al acceso
														// del JUM

			}

			/* *******************Insert Perdatos********************** */
			// System.out.println("Insertando datos");

			String cSQLCvePersonal = "";
			String cSQLCveExpediente = "";
			int iPersonal = 0;
			int iExpediente = 0;

			cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
			pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			rsetCvePersona = pstmtCvePersonal.executeQuery();
			while (rsetCvePersona.next()) {
				iPersonal = rsetCvePersona.getInt(1);
			}
			vPERDatos.setICvePersonal(iPersonal + 1);
			// System.out.println(iPersonal);
			// System.out.println(vPERDatos.getICveJUniMed());

			vPERDatos.setICveExpediente(vPERDatos.getICvePersonal());
			cSQL = new StringBuffer();
			String cSQL1;
			cSQL1 = "insert into PERDatos(" + "iCvePersonal,"
					+ "iCveExpediente,"
					+ "cSexo,"
					+ "cNombre,"
					+ "cApPaterno,"
					+

					"cApMaterno,"
					+ "cRFC,"
					+ "cHomoclave,"
					+ "cCURP,"
					+ "dtNacimiento,"
					+

					"iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "cExpediente,"
					+ "cSenasPersonal,"
					+ "cCorreoElec,"
					+

					"lDonadorOrg,"
					+ "cPersonaAviso,"
					+ "cDirecAviso,"
					+ "cTelAviso,"
					+ "cCorreoAviso,"
					+

					"iCveDireccion,"
					+ "iCveFoto,"
					+ "lNoHuellas,"
					+ "iCveNumEmpresa, "
					+ "iCveUsuRegistra, "
					+

					// Se agrego el siguiente campo donde solo Jefe Unidad
					// Medica puede ingresar usuarios
					// AG SA 25 de mayo 2010
					"iCveJUniMed,"
					+ "tsgenerado "
					+

					")values(?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,(SELECT current timestamp FROM sysibm.sysdummy1))";

			// System.out.println("direccion = "+cSQL1);

			pstmtInsDatos = conn.prepareStatement(cSQL1);

			pstmtInsDatos.setInt(1, vPERDatos.getICvePersonal());
			pstmtInsDatos.setInt(2, vPERDatos.getICveExpediente());
			pstmtInsDatos.setString(3, vPERDatos.getCSexo());
			pstmtInsDatos.setString(4, vPERDatos.getCNombre());
			pstmtInsDatos.setString(5, vPERDatos.getCApPaterno());

			pstmtInsDatos.setString(6, vPERDatos.getCApMaterno());
			pstmtInsDatos.setString(7, vPERDatos.getCRFC());
			pstmtInsDatos.setString(8, vPERDatos.getCHomoclave());

			pstmtInsDatos.setString(9, vPERDatos.getCCURP());
			pstmtInsDatos.setDate(10, vPERDatos.getDtNacimiento());

			pstmtInsDatos.setInt(11, vPERDatos.getICvePaisNac());
			pstmtInsDatos.setInt(12, vPERDatos.getICveEstadoNac());
			pstmtInsDatos.setString(13, vPERDatos.getCExpediente());
			pstmtInsDatos.setString(14, vPERDatos.getCSenasPersonal());
			pstmtInsDatos.setString(15, vPERDatos.getCCorreoElec());

			pstmtInsDatos.setInt(16, vPERDatos.getLDonadorOrg());
			pstmtInsDatos.setString(17, vPERDatos.getCPersonaAviso());
			pstmtInsDatos.setString(18, vPERDatos.getCDirecAviso());
			pstmtInsDatos.setString(19, vPERDatos.getCTelAviso());
			pstmtInsDatos.setString(20, vPERDatos.getCCorreoAviso());

			pstmtInsDatos.setInt(21, vPERDatos.getICveDireccion());
			pstmtInsDatos.setInt(22, vPERDatos.getICveFoto());
			pstmtInsDatos.setInt(23, vPERDatos.getLNoHuellas());
			pstmtInsDatos.setInt(24, vPERDatos.getICveNumEmpresa());
			pstmtInsDatos.setInt(25, vPERDatos.getICveUsuRegistra());

			pstmtInsDatos.setInt(26, vPERDatos.getICveJUniMed());

			pstmtInsDatos.executeUpdate();

			cClave = "" + vPERDatos.getICvePersonal();

			// System.out.println("Nuevo expediente = " + cClave);
			// System.out.println(cSQL1.toString());

			/* ************* Actualiza Clave*************** */

			cSQL1 = "update EPICisCita " + "set iCvePersonal = " + cClave + " "
					+ cActualiza;

			pstmtActualiza = conn.prepareStatement(cSQL1);

			pstmtActualiza.executeUpdate();

			/* ***************Genera Direccion **************** */
			cSQL1 = "select "
					+ "EPICisCita.iCveUniMed,"
					+ "dtFecha,"
					+ "iCveCita,"
					+ "cHora,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "EPICisCita.cNombre,"
					+ "cGenero,"
					+ "dtNacimiento,"
					+ "cRFC,"
					+ "cCURP,"
					+ "EPICisCita.iCvePaisNac,"
					+ "EPICisCita.iCveEstadoNac,"
					+ "cExpediente,"
					+ "iCvePersonal,"
					+

					"EPICisCita.cCalle,"
					+ "EPICisCita.cNumExt,"
					+ "EPICisCita.cNumInt,"
					+ "EPICisCita.cColonia,"
					+ "EPICisCita.iCP,"
					+ "EPICisCita.cCiudad,"
					+ "EPICisCita.iCvePais,"
					+ "EPICisCita.iCveEstado,"
					+ "EPICisCita.iCveMunicipio,"
					+ "EPICisCita.cTel,"
					+ "lCambioDir,"
					+ "EPICisCita.iCveMdoTrans,"
					+ "EPICisCita.iCvePuesto,"
					+ "EPICisCita.iCveCategoria,"
					+ "EPICisCita.iCveMotivo,"
					+ "cObservacion,"
					+ "EPICisCita.iCveSituacion,"
					+ "iCveUsuCita,"
					+ "lRealizoExam,"
					+ "iCveUsuMCIS,"
					+ "lProdNoConf, "
					+ "GRLUnimed.cDscUniMed, "
					+ "GRLMdoTrans.cDscMdoTrans, "
					+ "GRLPuesto.cDscPuesto, "
					+ "GRLMotivo.cDscMotivo, "
					+ "EPISituacion.cDscSituacion, "
					+ "EPICisCita.iCveModulo, "
					+ "GRLModulo.cDscModulo "
					+ "from EPICisCita "
					+ " inner join GRLUnimed ON EPICisCita.iCveUniMed = GRLUnimed.iCveUniMed "
					+ " inner join GRLMdoTrans ON EPICisCita.iCveMdoTrans = GRLMdoTrans.iCveMdoTrans"
					+ " left join GRLPuesto ON EPICisCita.iCvePuesto = GRLPuesto.iCvePuesto "
					+ " and  EPICisCita.iCveMdoTrans = GRLPuesto.iCveMdoTrans  "
					+ " inner join GRLMotivo ON EPICisCita.iCveMotivo = GRLMotivo.iCveMotivo "
					+ " and  GRLMotivo.iCveProceso = 1 "
					+ " inner join EPISituacion ON EPICisCita.iCveSituacion = EPISituacion.iCveSituacion "
					+ " inner join GRLModulo ON EPICisCita.iCveModulo = GRLModulo.iCveModulo "
					+ " and EPICisCita.iCveUniMed = GRLModulo.iCveUniMed ";

			// System.out.println("direccion = "+cSQL1);

			if (cWhere != null) {
				cSQL1 = cSQL1 + cWhere + " ";
			}
			cSQL1 = cSQL1 + "order by iCveCita";

			pstmtDir = conn.prepareStatement(cSQL1);
			rsetDir = pstmtDir.executeQuery();
			while (rsetDir.next()) {

				vPERDireccion.setICvePersonal(rsetDir.getInt(15));
				vPERDireccion.setCCalle(rsetDir.getString(16));
				vPERDireccion.setCNumExt(rsetDir.getString(17));
				vPERDireccion.setCNumInt(rsetDir.getString(18));
				vPERDireccion.setCColonia(rsetDir.getString(19));

				vPERDireccion.setICP(rsetDir.getInt(20));
				vPERDireccion.setCCiudad(rsetDir.getString(21));
				vPERDireccion.setICvePais(rsetDir.getInt(22));
				vPERDireccion.setICveEstado(rsetDir.getInt(23));
				vPERDireccion.setICveMunicipio(rsetDir.getInt(24));
				vPERDireccion.setCTel(rsetDir.getString(25));

			}

			/* ***************Inserta Direccion **************** */
			int iConsecutivo = 0;
			cSQLMax = "select max(iCveDireccion) from PERDireccion where iCvePersonal = ?";
			pstmtMax = conn.prepareStatement(cSQLMax);
			pstmtMax.setInt(1, vPERDireccion.getICvePersonal());
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iConsecutivo = rsetMax.getInt(1);
			}
			vPERDireccion.setICveDireccion(iConsecutivo + 1);

			cSQL1 = "insert into PERDireccion(" + "iCvePersonal,"
					+ "iCveDireccion," + "cCalle," + "cNumExt," + "cNumInt,"
					+ "cColonia," + "iCP," + "cCiudad," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cTel"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

			// System.out.println("direccion = "+cSQL1);

			pstmtInsDir = conn.prepareStatement(cSQL1);

			pstmtInsDir.setInt(1, vPERDireccion.getICvePersonal());
			pstmtInsDir.setInt(2, vPERDireccion.getICveDireccion());
			pstmtInsDir.setString(3, vPERDireccion.getCCalle());
			pstmtInsDir.setString(4, vPERDireccion.getCNumExt());
			pstmtInsDir.setString(5, vPERDireccion.getCNumInt());
			pstmtInsDir.setString(6, vPERDireccion.getCColonia());
			pstmtInsDir.setInt(7, vPERDireccion.getICP());
			pstmtInsDir.setString(8, vPERDireccion.getCCiudad());
			pstmtInsDir.setInt(9, vPERDireccion.getICvePais());
			pstmtInsDir.setInt(10, vPERDireccion.getICveEstado());
			pstmtInsDir.setInt(11, vPERDireccion.getICveMunicipio());
			pstmtInsDir.setString(12, vPERDireccion.getCTel());
			pstmtInsDir.executeUpdate();

			/* ***************Respaldo Proceso Anterior **************** */

			/*
			 * vPERDatos = (TVPERDatos) dEPICisCita.GenExpediente(cWhere,
			 * iUsuario); cClave = (String) dPERDatos.insert(conn, vPERDatos);
			 * vEPICisCita = new TVEPICisCita();
			 * dEPICisCita.updateCvePersonal(conn, "set iCvePersonal = " +
			 * cClave + " " + cActualiza); vPERDireccion = (TVPERDireccion)
			 * dEPICisCita.GenDireccion(conn, cWhere);
			 * dPERDireccion.insert(conn, vPERDireccion);
			 */
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("Alta Personal.rollback", ex1);
			}
			warn("Alta Personal", ex);
			throw new DAOException("");
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}

				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}

				if (pstmtCveExpediente != null) {
					pstmtCveExpediente.close();
				}

				if (pstmtActualiza != null) {
					pstmtActualiza.close();
				}

				if (pstmtDir != null) {
					pstmtDir.close();
				}

				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (pstmtInsDir != null) {
					pstmtInsDir.close();
				}

				if (pstmtInsDatos != null) {
					pstmtInsDatos.close();
				}

				if (rset != null) {
					rset.close();
				}

				if (rsetCvePersona != null) {
					rsetCvePersona.close();
				}

				if (rsetCveExpediente != null) {
					rsetCveExpediente.close();
				}

				if (rsetDir != null) {
					rsetDir.close();
				}

				if (rsetMax != null) {
					rsetMax.close();
				}

				// if (conGral == null) {
				conn.close();
				// }
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("Alta Personal.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo updateCvePersonal
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Object updateCvePersonal(Connection conGral, String cCondicion)
			throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EPICisCita " + cCondicion;

			pstmt = conn.prepareStatement(cSQL);

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
				warn("updateCvePersonal", ex1);
			}
			warn("updateCvePersonal", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
					dbConn.closeConnection();
				}

			} catch (Exception ex2) {
				warn("updateCvePersonal.close", ex2);
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
			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from EPICisCita" + " where iCveUniMed = ?"
					+ " and dtFecha = ? " + " and iCveCita = ? "
					+ " and iCveModulo = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEPICisCita.getICveUniMedA());
			pstmt.setDate(2, vEPICisCita.getDtFechaA());
			pstmt.setInt(3, vEPICisCita.getICveCita());
			pstmt.setInt(4, vEPICisCita.getICveModulo());
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
				warn("delete.closeEPICisCita", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo FindConsultaCitas
	 */
	public Vector FindConsultaCitas(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEPICisCita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVEPICisCita vEPICisCita;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "a.iCveUniMed,a.iCveModulo,a.dtFecha,a.iCveCita,a.cRFC,a.cApPaterno,"
					+ "a.cApMaterno,a.cNombre,a.cHora,b.cDscMotivo,c.cDscSituacion, a.cExpediente, a.iCvePersonal, a.iCvePaisNac "
					+ "from EPICisCita a join GRLMotivo b on (a.iCveMotivo=b.iCveMotivo) "
					+ "join EPISituacion c on (a.iCveSituacion=c.iCveSituacion)";

			String cWhereAnd = " where";
			String iCveUniMed = (String) hmFiltros.get("iCveUniMed");
			String iCveModulo = (String) hmFiltros.get("iCveModulo");
			String dtFecha = (String) hmFiltros.get("dtFecha");
			String cHora = (String) hmFiltros.get("cHora");
			if (iCveUniMed != null && iCveUniMed.length() > 0) {
				cSQL += " where a.iCveUniMed=?";
				cWhereAnd = " and";
			} else
				return vcEPICisCita;
			if (iCveModulo != null && iCveModulo.length() > 0) {
				cSQL += cWhereAnd + " a.iCveModulo=?";
				cWhereAnd = " and";
			} else
				return vcEPICisCita;

			if (dtFecha != null && dtFecha.length() > 0) {
				cSQL += cWhereAnd + " a.dtFecha=?";
				cWhereAnd = " and";
			} else
				return vcEPICisCita;

			if (cHora != null && cHora.length() > 0) {
				cSQL += cWhereAnd + " a.cHora=?";
			}

			cSQL += " order by a.cApPaterno";
			pstmt = conn.prepareStatement(cSQL);

			int i = 1;
			if (iCveUniMed != null && iCveUniMed.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(iCveUniMed));
			}
			if (iCveModulo != null && iCveModulo.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(iCveModulo));
			}
			if (dtFecha != null && dtFecha.length() > 0) {
				pstmt.setDate(i++, new java.sql.Date(sdfDate.parse(dtFecha)
						.getTime()));
			}
			if (cHora != null && cHora.length() > 0) {
				pstmt.setTime(i++, new java.sql.Time(sdfTime.parse(cHora)
						.getTime()));

			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();
				vEPICisCita.setICveUniMed(rset.getInt("iCveUniMed"));
				vEPICisCita.setICveModulo(rset.getInt("iCveModulo"));
				vEPICisCita.setDtFecha(rset.getDate("dtFecha"));
				vEPICisCita.setICveCita(rset.getInt("iCveCita"));
				vEPICisCita.setCRFC(rset.getString("cRFC"));
				vEPICisCita.setCApPaterno(rset.getString("cApPaterno"));
				vEPICisCita.setCApMaterno(rset.getString("cApMaterno"));
				vEPICisCita.setCNombre(rset.getString("cNombre"));
				vEPICisCita.setCHora(rset.getString("cHora"));
				vEPICisCita.setCDscMotivo(rset.getString("cDscMotivo"));
				vEPICisCita.setCDscSituacion(rset.getString("cDscSituacion"));
				vEPICisCita.setCExpediente(rset.getString("cExpediente"));
				vEPICisCita.setICvePersonal(rset.getInt("iCvePersonal"));
				vEPICisCita.setICvePaisNac(rset.getInt("iCvePaisNac"));
				vcEPICisCita.addElement(vEPICisCita);
			}
		} catch (Exception ex) {
			warn("FindConsultaCitas", ex);
			throw new DAOException("FindConsultaCitas");
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
				warn("FindConsultaCitas.close", ex2);
			}
			return vcEPICisCita;
		}
	}

	/**
	 * Metodo getHorasDeCitas
	 */
	public Vector getHorasDeCitas(HashMap hmFiltros) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCombo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select distinct cHora,cHora from EPICisCita";

			String cWhereAnd = " where";
			String iCveUniMed = (String) hmFiltros.get("iCveUniMed");
			String iCveModulo = (String) hmFiltros.get("iCveModulo");
			String dtFecha = (String) hmFiltros.get("dtFecha");

			if (iCveUniMed != null && iCveUniMed.length() > 0) {
				cSQL += " where iCveUniMed=?";
				cWhereAnd = " and";
			}
			if (iCveModulo != null && iCveModulo.length() > 0) {
				cSQL += cWhereAnd + " iCveModulo=?";
				cWhereAnd = " and";
			}
			if (dtFecha != null && dtFecha.length() > 0) {
				cSQL += cWhereAnd + " dtFecha=?";
			}

			cSQL += " order by cHora";
			pstmt = conn.prepareStatement(cSQL);

			int i = 1;
			if (iCveUniMed != null && iCveUniMed.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(iCveUniMed));
			}
			if (iCveModulo != null && iCveModulo.length() > 0) {
				pstmt.setInt(i++, Integer.parseInt(iCveModulo));
			}
			if (dtFecha != null && dtFecha.length() > 0) {
				pstmt.setDate(i++, new java.sql.Date(sdfDate.parse(dtFecha)
						.getTime()));

			}
			rset = pstmt.executeQuery();
			TVDinamico vDinamico;
			while (rset.next()) {
				vDinamico = new TVDinamico();
				vDinamico.put("cIndice", rset.getString("cHora"));
				vDinamico.put("cDescripcion", rset.getString("cHora"));
				vcCombo.add(vDinamico);
			}
		} catch (Exception ex) {
			warn("getHorasDeCitas", ex);
			throw new DAOException("getHorasDeCitas");
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
				warn("getHorasDeCitas.close", ex2);
			}
			return vcCombo;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcEPICisCita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVEPICisCita vEPICisCita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			StringBuffer cSQL = new StringBuffer();
			cSQL.append("SELECT ")
					.append(" a.iCveUniMed, a.iCveModulo, a.dtFecha, a.iCveCita, a.cHora, ")
					.append(" a.cApPaterno, a.cApMaterno, a.cNombre, ")
					.append(" a.cGenero,    a.dtNacimiento, a.cRFC, a.cCURP, ")
					.append(" a.iCvePaisNac, a.iCveEstadoNac, ")
					.append(" a.cExpediente, a.iCvePersonal, ")
					.append(" a.cCalle,      a.cNumExt,    a.cNumInt, a.cColonia, a.iCP, a.cCiudad, ")
					.append(" a.iCvePais,    a.iCveEstado, a.iCveMunicipio, a.cTel, ")
					.append(" a.lCambioDir,  a.iCveMdoTrans, ")
					.append(" a.iCvePuesto,  a.iCveCategoria, a.iCveMotivo, a.cObservacion, ")
					.append(" a.iCveSituacion, a.iCveUsuCita, a.lRealizoExam, a.iCveUsuMCIS, ")
					.append(" a.lProdNoConf, ")
					.append(" b.cDscUniMed, ")
					.append(" c.cNombre as cDscPaisNac, ")
					.append(" d.cNombre as cDscEstadoNac, ")
					.append(" e.cNombre as cDscPais, f.cNombre as cDscEstado, g.cNombre as cDscMunicipio, ")
					.append(" h.cDscMdoTrans, i.cDscPuesto, j.cDscMotivo, ")
					.append(" k.cDscSituacion, l.cDscModulo, m.cDscBreve,  ")
					.append(" a.cLicencia, a.cLicenciaInt  ")
					.append(" from  EPICisCita a ")
					.append(" inner join GRLUnimed b on b.iCveUniMed = a.iCveUniMed  ")
					.append(" inner join GRLPais c on c.iCvePais = a.iCvePaisNac  ")
					.append(" inner join GRLEntidadFed d on d.iCvePais       = a.iCvePaisNac ")
					.append("                           and d.iCveEntidadFed = a.iCveEstadoNac  ")
					.append(" inner join GRLPais e on e.iCvePais = a.iCvePais  ")
					.append(" inner join GRLEntidadFed f on f.iCvePais       = a.iCvePais ")
					.append("                           and f.iCveEntidadFed = a.iCveEstado  ")
					.append(" inner join GRLMunicipio g on g.iCvePais       = a.iCvePais ")
					.append("                          and g.iCveEntidadFed = a.iCveEstado ")
					.append("                          and g.iCveMunicipio  = a.iCveMunicipio  ")
					.append(" inner join GRLMdoTrans h on h.iCveMdoTrans = a.iCveMdoTrans  ")
					.append(" left join GRLPuesto i on i.iCveMdoTrans = a.iCveMdoTrans ")
					.append("                      and i.iCvePuesto   = a.iCvePuesto  ")
					.append(" inner join GRLMotivo j on j.iCveMotivo = a.iCveMotivo  ")
					.append(" inner join EPISituacion k on k.iCveSituacion = a.iCveSituacion  ")
					.append(" inner join GRLModulo l ON l.iCveUniMed = a.iCveUniMed  ")
					.append("                       and l.iCveModulo = a.iCveModulo  ")
					.append(" inner join GRLCategoria m ON m.iCveMdoTrans  = a.iCveMdoTrans  ")
					.append("                          and m.iCveCategoria = a.iCveCategoria  ")
					.append(" where a.iCveUniMed = ? ")
					.append("   and a.dtFecha    = ? ")
					.append("   and a.iCveCita   = ? ")
					.append("   and a.iCveModulo = ? ");
			pstmt = conn.prepareStatement(cSQL.toString());

			String cTmp = (String) hmFiltro.get("iCveUniMed");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(1, Integer.parseInt(cTmp));
			}
			cTmp = (String) hmFiltro.get("dtFecha");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setDate(2, new java.sql.Date(sdfDate.parse(cTmp)
						.getTime()));
			}
			cTmp = (String) hmFiltro.get("iCveCita");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(3, Integer.parseInt(cTmp));
			}
			cTmp = (String) hmFiltro.get("iCveModulo");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(4, Integer.parseInt(cTmp));
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();
				vEPICisCita.setICveUniMed(rset.getInt("iCveUniMed"));
				vEPICisCita.setICveModulo(rset.getInt("iCveModulo"));
				vEPICisCita.setDtFecha(rset.getDate("dtFecha"));
				vEPICisCita.setICveCita(rset.getInt("iCveCita"));
				vEPICisCita.setCHora(rset.getString("cHora"));
				vEPICisCita.setCApPaterno(rset.getString("cApPaterno"));
				vEPICisCita.setCApMaterno(rset.getString("cApMaterno"));
				vEPICisCita.setCNombre(rset.getString("cNombre"));
				vEPICisCita.setCGenero(rset.getString("cGenero"));
				vEPICisCita.setDtNacimiento(rset.getDate("dtNacimiento"));
				vEPICisCita.setCRFC(rset.getString("cRFC"));
				vEPICisCita.setCCURP(rset.getString("cCURP"));
				vEPICisCita.setICvePaisNac(rset.getInt("iCvePaisNac"));
				vEPICisCita.setICveEstadoNac(rset.getInt("iCveEstadoNac"));
				vEPICisCita.setCExpediente(rset.getString("cExpediente"));
				vEPICisCita.setICvePersonal(rset.getInt("iCvePersonal"));
				vEPICisCita.setCCalle(rset.getString("cCalle"));
				vEPICisCita.setCNumExt(rset.getString("cNumExt"));
				vEPICisCita.setCNumInt(rset.getString("cNumInt"));
				vEPICisCita.setCColonia(rset.getString("cColonia"));
				vEPICisCita.setICP(rset.getInt("iCP"));
				vEPICisCita.setCCiudad(rset.getString("cCiudad"));
				vEPICisCita.setICvePais(rset.getInt("iCvePais"));
				vEPICisCita.setICveEstado(rset.getInt("iCveEstado"));
				vEPICisCita.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vEPICisCita.setCTelefono(rset.getString("cTel"));
				vEPICisCita.setLCambioDir(rset.getInt("lCambioDir"));
				vEPICisCita.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vEPICisCita.setICvePuesto(rset.getInt("iCvePuesto"));
				vEPICisCita.setICveCategoria(rset.getInt("iCveCategoria"));
				vEPICisCita.setICveMotivo(rset.getInt("iCveMotivo"));
				vEPICisCita.setCObservacion(rset.getString("cObservacion"));
				vEPICisCita.setICveSituacion(rset.getInt("iCveSituacion"));
				vEPICisCita.setICveUsuCita(rset.getInt("iCveUsuCita"));
				vEPICisCita.setLRealizoExam(rset.getInt("lRealizoExam"));
				vEPICisCita.setICveUsuMCIS(rset.getInt("iCveUsuMCIS"));
				vEPICisCita.setLProdNoConf(rset.getInt("lProdNoConf"));
				vEPICisCita.setCDscUniMed(rset.getString("cDscUniMed"));
				vEPICisCita.setCDscPaisNac(rset.getString("cDscPaisNac"));
				vEPICisCita.setCDscEstadoNac(rset.getString("cDscEstadoNac"));
				vEPICisCita.setCDscPais(rset.getString("cDscPais"));
				vEPICisCita.setCDscEstado(rset.getString("cDscEstado"));
				vEPICisCita.setCDscMunicipio(rset.getString("cDscMunicipio"));
				vEPICisCita
						.setCDscMdoTransporte(rset.getString("cDscMdoTrans"));
				vEPICisCita.setCDscPuesto(rset.getString("cDscPuesto"));
				vEPICisCita.setCDscMotivo(rset.getString("cDscMotivo"));
				vEPICisCita.setCDscSituacion(rset.getString("cDscSituacion"));
				vEPICisCita.setCDscModulo(rset.getString("cDscModulo"));
				vEPICisCita.setCDscCategoria(rset.getString("cDscBreve"));
				vEPICisCita.setCLicencia(rset.getString("cLicencia"));
				vEPICisCita.setCLiceniaInt(rset.getString("cLicenciaInt"));

				if (vEPICisCita.getDtFecha() != null) {
					vEPICisCita.setCDscDtFecha(dtFecha.getFechaDDMMYYYY(
							vEPICisCita.getDtFecha(), "/"));
				} else {
					vEPICisCita.setCDscDtFecha("");

				}
				if (vEPICisCita.getDtNacimiento() != null) {
					vEPICisCita.setCDscDtFechaNac(dtFecha.getFechaDDMMYYYY(
							vEPICisCita.getDtNacimiento(), "/"));
				} else {
					vEPICisCita.setCDscDtFechaNac("");

				}

				vcEPICisCita.addElement(vEPICisCita);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			ex.printStackTrace();
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
				ex2.printStackTrace();
			}
			return vcEPICisCita;
		}
	}

	/**
	 * Metodo Find By All epi NOM-024
	 */
	public Vector FindByAll2(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		Vector vcEPICisCita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cFecha = "";
			TFechas dtFecha = new TFechas();
			TVEPICisCita vEPICisCita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			StringBuffer cSQL = new StringBuffer();
			cSQL.append("SELECT ")
					.append(" a.iCveUniMed, a.iCveModulo, a.dtFecha, a.iCveCita, a.cHora, ")
					.append(" a.cApPaterno, a.cApMaterno, a.cNombre, ")
					.append(" a.cGenero,    a.dtNacimiento, a.cRFC, a.cCURP, ")
					.append(" a.iCvePaisNac, a.iCveEstadoNac, ")
					.append(" a.cExpediente, a.iCvePersonal, ")
					.append(" a.cCalle,      a.cNumExt,    a.cNumInt, a.cColonia, a.iCP, a.cCiudad, ")
					.append(" a.iCvePais,    a.iCveEstado, a.iCveMunicipio, a.cTel, ")
					.append(" a.lCambioDir,  a.iCveMdoTrans, ")
					.append(" a.iCvePuesto,  a.iCveCategoria, a.iCveMotivo, a.cObservacion, ")
					.append(" a.iCveSituacion, a.iCveUsuCita, a.lRealizoExam, a.iCveUsuMCIS, ")
					.append(" a.lProdNoConf, ")
					.append(" b.cDscUniMed, ")
					.append(" c.cNombre as cDscPaisNac, ")
					.append(" d.cNombre as cDscEstadoNac, ")
					.append(" e.cNombre as cDscPais, f.cNombre as cDscEstado, g.cNombre as cDscMunicipio, ")
					.append(" h.cDscMdoTrans, i.cDscPuesto, j.cDscMotivo, ")
					.append(" k.cDscSituacion, l.cDscModulo, m.cDscBreve,  ")
					.append(" a.cLicencia, a.cLicenciaInt, a.cSexo_DGIS,  ")
					.append(" a.iCveLocalidad  ")
					.append(" from  EPICisCita a ")
					.append(" inner join GRLUnimed b on b.iCveUniMed = a.iCveUniMed  ")
					.append(" inner join GRLPais c on c.iCvePais = a.iCvePaisNac  ")
					.append(" inner join GRLEntidadFed d on d.iCvePais       = a.iCvePaisNac ")
					.append("                           and d.iCveEntidadFed = a.iCveEstadoNac  ")
					.append(" inner join GRLPais e on e.iCvePais = a.iCvePais  ")
					.append(" inner join GRLEntidadFed f on f.iCvePais       = a.iCvePais ")
					.append("                           and f.iCveEntidadFed = a.iCveEstado  ")
					.append(" inner join GRLMunicipio g on g.iCvePais       = a.iCvePais ")
					.append("                          and g.iCveEntidadFed = a.iCveEstado ")
					.append("                          and g.iCveMunicipio  = a.iCveMunicipio  ")
					.append(" inner join GRLMdoTrans h on h.iCveMdoTrans = a.iCveMdoTrans  ")
					.append(" left join GRLPuesto i on i.iCveMdoTrans = a.iCveMdoTrans ")
					.append("                      and i.iCvePuesto   = a.iCvePuesto  ")
					.append(" inner join GRLMotivo j on j.iCveMotivo = a.iCveMotivo  ")
					.append(" inner join EPISituacion k on k.iCveSituacion = a.iCveSituacion  ")
					.append(" inner join GRLModulo l ON l.iCveUniMed = a.iCveUniMed  ")
					.append("                       and l.iCveModulo = a.iCveModulo  ")
					.append(" inner join GRLCategoria m ON m.iCveMdoTrans  = a.iCveMdoTrans  ")
					.append("                          and m.iCveCategoria = a.iCveCategoria  ")
					.append(" where a.iCveUniMed = ? ")
					.append("   and a.dtFecha    = ? ")
					.append("   and a.iCveCita   = ? ")
					.append("   and a.iCveModulo = ? ");
			pstmt = conn.prepareStatement(cSQL.toString());

			String cTmp = (String) hmFiltro.get("iCveUniMed");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(1, Integer.parseInt(cTmp));
			}
			cTmp = (String) hmFiltro.get("dtFecha");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setDate(2, new java.sql.Date(sdfDate.parse(cTmp)
						.getTime()));
			}
			cTmp = (String) hmFiltro.get("iCveCita");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(3, Integer.parseInt(cTmp));
			}
			cTmp = (String) hmFiltro.get("iCveModulo");
			if (cTmp != null && cTmp.length() > 0) {
				pstmt.setInt(4, Integer.parseInt(cTmp));
			}

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vEPICisCita = new TVEPICisCita();
				vEPICisCita.setICveUniMed(rset.getInt("iCveUniMed"));
				vEPICisCita.setICveModulo(rset.getInt("iCveModulo"));
				vEPICisCita.setDtFecha(rset.getDate("dtFecha"));
				vEPICisCita.setICveCita(rset.getInt("iCveCita"));
				vEPICisCita.setCHora(rset.getString("cHora"));
				vEPICisCita.setCApPaterno(rset.getString("cApPaterno"));
				vEPICisCita.setCApMaterno(rset.getString("cApMaterno"));
				vEPICisCita.setCNombre(rset.getString("cNombre"));
				vEPICisCita.setCGenero(rset.getString("cGenero"));
				vEPICisCita.setDtNacimiento(rset.getDate("dtNacimiento"));
				vEPICisCita.setCRFC(rset.getString("cRFC"));
				vEPICisCita.setCCURP(rset.getString("cCURP"));
				vEPICisCita.setICvePaisNac(rset.getInt("iCvePaisNac"));
				vEPICisCita.setICveEstadoNac(rset.getInt("iCveEstadoNac"));
				vEPICisCita.setCExpediente(rset.getString("cExpediente"));
				vEPICisCita.setICvePersonal(rset.getInt("iCvePersonal"));
				vEPICisCita.setCCalle(rset.getString("cCalle"));
				vEPICisCita.setCNumExt(rset.getString("cNumExt"));
				vEPICisCita.setCNumInt(rset.getString("cNumInt"));
				vEPICisCita.setCColonia(rset.getString("cColonia"));
				vEPICisCita.setICP(rset.getInt("iCP"));
				vEPICisCita.setCCiudad(rset.getString("cCiudad"));
				vEPICisCita.setICvePais(rset.getInt("iCvePais"));
				vEPICisCita.setICveEstado(rset.getInt("iCveEstado"));
				vEPICisCita.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vEPICisCita.setCTelefono(rset.getString("cTel"));
				vEPICisCita.setLCambioDir(rset.getInt("lCambioDir"));
				vEPICisCita.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vEPICisCita.setICvePuesto(rset.getInt("iCvePuesto"));
				vEPICisCita.setICveCategoria(rset.getInt("iCveCategoria"));
				vEPICisCita.setICveMotivo(rset.getInt("iCveMotivo"));
				vEPICisCita.setCObservacion(rset.getString("cObservacion"));
				vEPICisCita.setICveSituacion(rset.getInt("iCveSituacion"));
				vEPICisCita.setICveUsuCita(rset.getInt("iCveUsuCita"));
				vEPICisCita.setLRealizoExam(rset.getInt("lRealizoExam"));
				vEPICisCita.setICveUsuMCIS(rset.getInt("iCveUsuMCIS"));
				vEPICisCita.setLProdNoConf(rset.getInt("lProdNoConf"));
				vEPICisCita.setCDscUniMed(rset.getString("cDscUniMed"));
				vEPICisCita.setCDscPaisNac(rset.getString("cDscPaisNac"));
				vEPICisCita.setCDscEstadoNac(rset.getString("cDscEstadoNac"));
				vEPICisCita.setCDscPais(rset.getString("cDscPais"));
				vEPICisCita.setCDscEstado(rset.getString("cDscEstado"));
				vEPICisCita.setCDscMunicipio(rset.getString("cDscMunicipio"));
				vEPICisCita
						.setCDscMdoTransporte(rset.getString("cDscMdoTrans"));
				vEPICisCita.setCDscPuesto(rset.getString("cDscPuesto"));
				vEPICisCita.setCDscMotivo(rset.getString("cDscMotivo"));
				vEPICisCita.setCDscSituacion(rset.getString("cDscSituacion"));
				vEPICisCita.setCDscModulo(rset.getString("cDscModulo"));
				vEPICisCita.setCDscCategoria(rset.getString("cDscBreve"));
				vEPICisCita.setCLicencia(rset.getString("cLicencia"));
				vEPICisCita.setCLiceniaInt(rset.getString("cLicenciaInt"));
				
				vEPICisCita.setCSexo_DGIS(rset.getString("cSexo_DGIS"));
				vEPICisCita.setICveLocalidad(rset.getInt("iCveLocalidad"));

				if (vEPICisCita.getDtFecha() != null) {
					vEPICisCita.setCDscDtFecha(dtFecha.getFechaDDMMYYYY(
							vEPICisCita.getDtFecha(), "/"));
				} else {
					vEPICisCita.setCDscDtFecha("");

				}
				if (vEPICisCita.getDtNacimiento() != null) {
					vEPICisCita.setCDscDtFechaNac(dtFecha.getFechaDDMMYYYY(
							vEPICisCita.getDtNacimiento(), "/"));
					vEPICisCita.setCDscDtFechaNacNom024(dtFecha.getFechaYYYYMMMDDNom024(
							vEPICisCita.getDtNacimiento()));
				} else {
					vEPICisCita.setCDscDtFechaNac("");
					vEPICisCita.setCDscDtFechaNacNom024("");
				}

				StringBuffer cSQL2 = new StringBuffer();
				cSQL2.append(
						"SELECT E.cTel2, E.iCveVivienda, V.cConcepto, E.iCveDiscapacidad, ")
						.append(" D.cDcsDiscapacidad, E.iCveGpoEtnico,T.cGpoEtnico,E.iCveReligion,R.cCodDsc,E.iCveNivelSec,N.cNivelSec,")
						.append(" E.iCveParPol,P.cDscParPol,E.iCveTpoSangre,S.cTpoSangre,E.iCveECivil,C.cECivil FROM EPICISCITAADNL AS E,GRLVIVIENDA AS V,")
						.append(" GRLDISCAPACIDAD AS D, GRLGPOETNICO AS T,GRLRELIGION AS R,GRLNIVELSEC AS N,GRLPARPOL AS P,GRLTPOSANGRE AS S, GRLECIVIL AS C")
						.append(" WHERE E.ICVEVIVIENDA = V.ICVEVIVIENDA AND E.ICVEDISCAPACIDAD = D.ICVEDISCAPACIDAD AND E.ICVEGPOETNICO = T.ICVEGPOETNICO AND")
						.append(" E.ICVERELIGION = R.ICVERELIGION AND E.ICVENIVELSEC = N.ICVENIVELSEC AND E.ICVEPARPOL = P.ICVEPARPOL  AND ")
						.append(" E.ICVETPOSANGRE = S.ICVETPOSANGRE AND E.ICVEECIVIL = C.ICVEECIVIL AND   E.iCveUniMed = ?  ")
						.append(" and E.dtFecha = ?  and E.iCveModulo = ?  AND E.ICVECITA = ? order by E.iCveCita");

				pstmt2 = conn.prepareStatement(cSQL2.toString());

				String cTmp2 = (String) hmFiltro.get("iCveUniMed");
				if (cTmp2 != null && cTmp2.length() > 0) {
					pstmt2.setInt(1, Integer.parseInt(cTmp2));
				}
				cTmp2 = (String) hmFiltro.get("dtFecha");
				if (cTmp2 != null && cTmp2.length() > 0) {
					pstmt2.setDate(2, new java.sql.Date(sdfDate.parse(cTmp2)
							.getTime()));
				}
				cTmp2 = (String) hmFiltro.get("iCveModulo");
				if (cTmp2 != null && cTmp2.length() > 0) {
					pstmt2.setInt(3, Integer.parseInt(cTmp2));
				}
				cTmp2 = (String) hmFiltro.get("iCveCita");
				if (cTmp2 != null && cTmp2.length() > 0) {
					pstmt2.setInt(4, Integer.parseInt(cTmp2));
				}

				rset2 = pstmt2.executeQuery();

				while (rset2.next()) {
					vEPICisCita.setCTelefono2(rset2.getString(1));
					vEPICisCita.setICveVivienda(rset2.getInt(2));
					vEPICisCita.setCConcepto(rset2.getString(3));
					vEPICisCita.setICveDiscapacidad(rset2.getInt(4));
					vEPICisCita.setCDcsDiscapacidad(rset2.getString(5));
					vEPICisCita.setICveGpoEtnico(rset2.getInt(6));
					vEPICisCita.setCGpoEtnico(rset2.getString(7));
					vEPICisCita.setICveReligion(rset2.getInt(8));
					vEPICisCita.setCCodDsc(rset2.getString(9));
					vEPICisCita.setICveNivelSEC(rset2.getInt(10));
					vEPICisCita.setCNivelSec(rset2.getString(11));
					vEPICisCita.setICveParPOL(rset2.getInt(12));
					vEPICisCita.setCDscParPol(rset2.getString(13));
					vEPICisCita.setICveTpoSangre(rset2.getInt(14));
					vEPICisCita.setCTpoSangre(rset2.getString(15));
					vEPICisCita.setICveECivil(rset2.getInt(16));
					vEPICisCita.setCECivil(rset2.getString(17));
				}

				vcEPICisCita.addElement(vEPICisCita);
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
			ex.printStackTrace();
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
				ex2.printStackTrace();
			}
			return vcEPICisCita;
		}
	}

	/**
	 * Metodo para actualizar la informaci�n de la cita.
	 * 
	 * @param conGral
	 *            Connection Conexi�n a la Base de Datos.
	 * @param obj
	 *            Object Objeto con la informaci�n de la Cita.
	 * @throws DAOException
	 * @return Object Objeto actualizado.
	 */
	public Object updateAlta(Connection conGral, Object obj)
			throws DAOException {
		// System.out.println("updateAlta");;
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
			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EPICisCita set " + "cApPaterno   = ?, " + // 1
					"cApMaterno   = ?, " + // 2
					"cNombre      = ?, " + // 3
					"dtNacimiento = ?, " + // 4
					"cRFC         = ?, " + // 5
					"cCURP        = ?, " + // 6
					"iCvePaisNAc  = ?, " + // 7
					"ICVEESTADONAC = ?, " + // 8
					"CEXPEDIENTE   = ?, " + // 9
					"CCALLE		= ?, " + // 10
					"CNUMEXT		= ?, " + // 11
					"CNUMINT		= ?, " + // 12
					"CCOLONIA	        = ?, " + // 13
					"ICP		= ?, " + // 14
					"CCIUDAD		= ?, " + // 15
					"ICVEPAIS	        = ?, " + // 16
					"ICVEESTADO	= ?, " + // 17
					"ICVEMUNICIPIO	= ?, " + // 18
					"CTEL		= ?, " + // 19
					"CGENERO          = ?,  " + // 20
					"CSEXO_DGIS       = ?,  " + // 21
					"ICVELOCALIDAD       = ?  " + // 22
					"where iCveUniMed = ? " + // 23
					"and dtFecha    = ? " + // 24
					"and iCveCita   = ? " + // 25
					"and iCveModulo = ?"; // 26
			
			pstmt = conn.prepareStatement(cSQL);

			GregorianCalendar g = new GregorianCalendar(0, 0, 0,
					vEPICisCita.getIHora(), vEPICisCita.getIMinutos());
			Time t = new Time(g.getTimeInMillis());
			pstmt.setString(1, vEPICisCita.getCApPaterno());
			pstmt.setString(2, vEPICisCita.getCApMaterno());
			pstmt.setString(3, vEPICisCita.getCNombre());
			pstmt.setDate(4, vEPICisCita.getDtNacimiento());
			pstmt.setString(5, vEPICisCita.getCRFC());
			pstmt.setString(6, vEPICisCita.getCCURP());
			pstmt.setInt(7, vEPICisCita.getICvePaisNac());
			pstmt.setInt(8, vEPICisCita.getICveEstadoNac());
			pstmt.setString(9, vEPICisCita.getCExpediente());
			pstmt.setString(10, vEPICisCita.getCCalle());
			pstmt.setString(11, vEPICisCita.getCNumExt());
			pstmt.setString(12, vEPICisCita.getCNumInt());
			pstmt.setString(13, vEPICisCita.getCColonia());
			pstmt.setInt(14, vEPICisCita.getICP());
			pstmt.setString(15, vEPICisCita.getCCiudad());
			pstmt.setInt(16, vEPICisCita.getICvePais());
			pstmt.setInt(17, vEPICisCita.getICveEstado());
			pstmt.setInt(18, vEPICisCita.getICveMunicipio());
			pstmt.setString(19, vEPICisCita.getCTelefono());
			pstmt.setString(20, vEPICisCita.getCGenero());
			
			pstmt.setString(21, vEPICisCita.getCSexo_DGIS());
			pstmt.setInt(22, vEPICisCita.getICveLocalidad());

			pstmt.setInt(23, vEPICisCita.getICveUniMedA());
			pstmt.setDate(24, vEPICisCita.getDtFechaA());
			pstmt.setInt(25, vEPICisCita.getICveCita());
			pstmt.setInt(26, vEPICisCita.getICveModuloA());
			pstmt.executeUpdate();
			cClave = "Exito";
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
				warn("TDEPICisCita.updateAlta", ex1);
				ex1.printStackTrace();
			}
			warn("TDEPICisCita.updateAlta", ex);
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
				warn("TDEPICisCita.updateNombre.close", ex2);
			}
			return cClave;
		}
	}

	public Object updateCveExpediente(Connection conGral, Object obj)
			throws DAOException {
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
			TVEPICisCita vEPICisCita = (TVEPICisCita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update EPICisCita set " + " iCvePersonal = ? " + // 1
					"where iCveUniMed = ? " + // 2
					"and dtFecha    = ? " + // 3
					"and iCveCita   = ? " + // 4
					"and iCveModulo = ?"; // 5
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vEPICisCita.getICvePersonal());
			pstmt.setInt(2, vEPICisCita.getICveUniMedA());
			pstmt.setDate(3, vEPICisCita.getDtFechaA());
			pstmt.setInt(4, vEPICisCita.getICveCita());
			pstmt.setInt(5, vEPICisCita.getICveModuloA());
			pstmt.executeUpdate();
			cClave = String.valueOf(vEPICisCita.getICvePersonal());
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
				warn("TDEPICisCita.updateAlta", ex1);
				ex1.printStackTrace();
			}
			warn("TDEPICisCita.updateAlta", ex);
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
				warn("TDEPICisCita.updateNombre.close", ex2);
			}
			return cClave;
		}
	}

	public void prepareDate(Vector v) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update EPICisCita set  " + " icvemdotrans  = 2, "
					+ " icvepuesto    = 7, " + " icvecategoria = 7  "
					+ "where dtFecha  = ?  " + "  and icvemdotrans = 0 ";
			if (v != null && v.size() > 0) {
				for (int k = 0; k < v.size(); k++) {
					TVEPICisCita tvCisCita = (TVEPICisCita) v.get(k);
					pstmt = conn.prepareStatement(cSQL);
					pstmt.setDate(1, tvCisCita.getDtFecha());
					pstmt.executeUpdate();
				}

			}
			conn.commit();
		} catch (Exception ex) {
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
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
	}
	public String FindCURPByCita(String cCveUniMed,String cCveModulo ,String cFecha,String cCveCita) throws DAOException {
		//System.out.println("cCveUniMed: "+cCveUniMed +" cCveModulo: "+cCveModulo +" cFecha: "+cFecha+" cCveCita: "+cCveCita);
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		//Vector vcEPICisCita = new Vector();
		String cCURP=null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			//String cFecha = "";
			//TFechas dtFecha = new TFechas();			
			//TVEPICisCita vEPICisCita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select "					
					+ "cCURP "
					+ " from EPICisCita "					
					+ "where"
					+ " iCveCita =" + cCveCita 
					+ " AND dtFecha='" +cFecha
					+ "' AND iCveModulo="+cCveModulo
					+ " AND iCveUniMed="+cCveUniMed;
							
					//+ "order by iCveUniMed";
			pstmt = conn.prepareStatement(cSQL);
			//System.out.println("CONSULTA: "+cSQL);
			rset = pstmt.executeQuery();			
			while(rset.next()){ 								
				cCURP=rset.getString(1);
			}
			//System.out.println(cCURP);
		} catch (Exception ex) {
			warn("FindCURPByCita", ex);
			throw new DAOException("FindCURPByCita");
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
				warn("FindCURPByCita.close", ex2);
			}
			return cCURP;
		}
	}


}
