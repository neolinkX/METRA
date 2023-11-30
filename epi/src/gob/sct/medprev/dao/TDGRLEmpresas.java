package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLEmpresas DAO
 * </p>
 * <p>
 * Description: Data Access Object de la Tabla GRLEmpresas
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */

public class TDGRLEmpresas extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	public String cValSQL = "";

	public TDGRLEmpresas() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEmpresas = new Vector();
		TDEXPExamAplica dEXPExamAplica = new TDEXPExamAplica();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas vGRLEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpresa," + "iCveGrupoEmp," + "cIDDGPMPT,"
					+ "iIDMdoTrans," + "cRFC," + "cCurp," + "iCveUniMed,"
					+ "iCveMdoTrans," + "cTpoPersona," + "cApPaterno,"
					+ "cApMaterno," + "cNombreRS," + "cDenominacion,"
					+ "dtRegistro," + "cCveRPA," + "iCveOrigenInf"
					+ " from GRLEmpresas " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLEmpresas = new TVGRLEmpresas();
				vGRLEmpresas.setICveEmpresa(rset.getInt(1));
				vGRLEmpresas.setICveGrupoEmp(rset.getInt(2));
				vGRLEmpresas.setcIDDGPMPT(rset.getString(3));
				vGRLEmpresas.setIIDMdoTrans(rset.getInt(4));
				vGRLEmpresas.setCRFC(rset.getString(5));
				vGRLEmpresas.setCCurp(rset.getString(6));
				vGRLEmpresas.setICveUniMed(rset.getInt(7));
				vGRLEmpresas.setICveMdoTrans(rset.getInt(8));
				vGRLEmpresas.setCTpoPersona(rset.getString(9));
				vGRLEmpresas.setCApPaterno(rset.getString(10));
				vGRLEmpresas.setCApMaterno(rset.getString(11));
				vGRLEmpresas.setCNombreRS(rset.getString(12));
				vGRLEmpresas.setCDenominacion(rset.getString(13));
				vGRLEmpresas.setDtRegistro(rset.getDate(14));
				vGRLEmpresas.setCCveRPA(rset.getString(15));
				vGRLEmpresas.setICveOringenInf(rset.getInt(16));

				// Verificar que contiene domicilios
				vGRLEmpresas
						.setiCveDomicilio(dEXPExamAplica
								.RegresaInt("Select count(icvedomicilio) from ctrdomicilio where icveempresa = "
										+ rset.getInt(1)));

				vcGRLEmpresas.addElement(vGRLEmpresas);
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
			return vcGRLEmpresas;
		}
	}

	/**
	 * Metodo FindByEmpresasEMO, encuentra a las empresas que se les ha
	 * realizado un EMO en un periodo dado.
	 */
	public Vector FindByEmpresasEMO(String cPeriodo, String cUniMed,
			String cModulo) throws DAOException {
		Vector vcRecords = new Vector();
		try {
			String cSQL = "select  grlempresas.icveempresa, (grlempresas.cnombrers || ' ' || grlempresas.cappaterno || ' ' || grlempresas.capmaterno ) as cnombre "
					+ "from EMOExamen   "
					+ "join expexamaplica on EMOExamen.icveexpediente = expexamaplica.icveexpediente "
					+ "and EMOExamen.inumexamen = expexamaplica.inumexamen   "
					+ " and expexamaplica.iCveUniMed = "
					+ cUniMed
					+ " and expexamaplica.iCveModulo = "
					+ cModulo
					+ cPeriodo
					+ "join grlempresas on grlempresas.icveempresa = expexamaplica.icvenumempresa "
					+ "group by grlempresas.icveempresa, grlempresas.cnombrers, grlempresas.cappaterno, grlempresas.capmaterno ";
			vcRecords = this.FindByGeneric(cSQL, dataSourceName);
		} catch (Exception ex) {
			warn("FindByEmpresasEMO", ex);
			throw new DAOException("FindByEmpresasEMO");
		} finally {
			return vcRecords;
		}
	}

	/**
	 * Metodo Find By All join Grupo y Origen de la informacion
	 */
	public Vector FindByAllGpoOrgInf(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEmpresas = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas vGRLEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "GRLEmpresas.iCveEmpresa,"
					+ "GRLEmpresas.iCveGrupoEmp,"
					+ "GRLEmpresas.cIDDGPMPT,"
					+ "GRLEmpresas.iIDMdoTrans,"
					+ "GRLEmpresas.cRFC,"
					+ "GRLEmpresas.cCurp,"
					+ "GRLEmpresas.iCveUniMed,"
					+ "GRLEmpresas.iCveMdoTrans,"
					+ "GRLEmpresas.cTpoPersona,"
					+ "GRLEmpresas.cApPaterno,"
					+ "GRLEmpresas.cApMaterno,"
					+ "GRLEmpresas.cNombreRS,"
					+ "GRLEmpresas.cDenominacion,"
					+ "GRLEmpresas.dtRegistro,"
					+ "GRLEmpresas.cCveRPA,"
					+ "GRLEmpresas.iCveOrigenInf,"
					+ "CtrGrupoEmp.cDscBreve,"
					+ "CtrOrigenInf.cDscOrigenInf"
					+ " from GRLEmpresas "
					+ "join CtrGrupoEmp on CtrGrupoEmp.iCveGrupoEmp=GrlEmpresas.iCveGrupoEmp "
					+ "join CtrOrigenInf on CtrOrigenInf.iCveOrigenInf=GrlEmpresas.iCveOrigenInf"
					+ cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLEmpresas = new TVGRLEmpresas();
				vGRLEmpresas.setICveEmpresa(rset.getInt(1));
				vGRLEmpresas.setICveGrupoEmp(rset.getInt(2));
				vGRLEmpresas.setcIDDGPMPT(rset.getString(3));
				vGRLEmpresas.setIIDMdoTrans(rset.getInt(4));
				vGRLEmpresas.setCRFC(rset.getString(5));
				vGRLEmpresas.setCCurp(rset.getString(6));
				vGRLEmpresas.setICveUniMed(rset.getInt(7));
				vGRLEmpresas.setICveMdoTrans(rset.getInt(8));
				vGRLEmpresas.setCTpoPersona(rset.getString(9));
				vGRLEmpresas.setCApPaterno(rset.getString(10));
				vGRLEmpresas.setCApMaterno(rset.getString(11));
				vGRLEmpresas.setCNombreRS(rset.getString(12));
				vGRLEmpresas.setCDenominacion(rset.getString(13));
				vGRLEmpresas.setDtRegistro(rset.getDate(14));
				vGRLEmpresas.setCCveRPA(rset.getString(15));
				vGRLEmpresas.setICveOringenInf(rset.getInt(16));
				vGRLEmpresas.setCDscBreve(rset.getString(17));
				vGRLEmpresas.setCDscOrigenInf(rset.getString(18));
				vcGRLEmpresas.addElement(vGRLEmpresas);
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
			return vcGRLEmpresas;
		}
	}

	/**
	 * Metodo Find By All 2, Tablas: GRLEmpresas, GRLMdoTrans
	 */
	public Vector FindByAll2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEmpresas = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas vGRLEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "        iCveEmpresa,  "
					+ "        cIDDGPMPT,  "
					+ "        iIDMdoTrans, "
					+ "        GRLEmpresas.iCveMdoTrans,  "
					+ "        GRLMdoTrans.cDscMdoTrans  "
					+ "from    GRLEmpresas  "
					+ "        join GRLMdoTrans on GRLMdoTrans.iCveMdoTrans = GRLEmpresas.iCveMdoTrans "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLEmpresas = new TVGRLEmpresas();
				vGRLEmpresas.setICveEmpresa(rset.getInt(1));
				vGRLEmpresas.setcIDDGPMPT(rset.getString(2));
				vGRLEmpresas.setIIDMdoTrans(rset.getInt(3));
				vGRLEmpresas.setICveMdoTrans(rset.getInt(4));
				vGRLEmpresas.setCDscMdoTrans(rset.getString(5));

				vcGRLEmpresas.addElement(vGRLEmpresas);
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
			return vcGRLEmpresas;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEmpresas = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas vGRLEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "GRLEmpresas.iCveEmpresa," + "iCveGrupoEmp,"
					+ "cIDDGPMPT," + "iIDMdoTrans," + "cRFC," + "cCurp,"
					+ "iCveUniMed," + "iCveMdoTrans," + "cTpoPersona,"
					+ "cApPaterno," + "cApMaterno," + "cNombreRS,"
					+ "cDenominacion," + "dtRegistro," + "cCveRPA,"
					+ "iCveOrigenInf, " + "cCalle, " + "cColonia, "
					+ "cCiudad " + " from GRLEmpresas " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLEmpresas = new TVGRLEmpresas();
				vGRLEmpresas.setICveEmpresa(rset.getInt(1));
				vGRLEmpresas.setICveGrupoEmp(rset.getInt(2));
				vGRLEmpresas.setcIDDGPMPT(rset.getString(3));
				vGRLEmpresas.setIIDMdoTrans(rset.getInt(4));
				vGRLEmpresas.setCRFC(rset.getString(5));
				vGRLEmpresas.setCCurp(rset.getString(6));
				vGRLEmpresas.setICveUniMed(rset.getInt(7));
				vGRLEmpresas.setICveMdoTrans(rset.getInt(8));
				vGRLEmpresas.setCTpoPersona(rset.getString(9));
				vGRLEmpresas.setCApPaterno(rset.getString(10));
				vGRLEmpresas.setCApMaterno(rset.getString(11));
				vGRLEmpresas.setCNombreRS(rset.getString(12));
				vGRLEmpresas.setCDenominacion(rset.getString(13));
				vGRLEmpresas.setDtRegistro(rset.getDate(14));
				vGRLEmpresas.setCCveRPA(rset.getString(15));
				vGRLEmpresas.setICveOringenInf(rset.getInt(16));
				vGRLEmpresas.setcCalle(rset.getString(17));
				vGRLEmpresas.setcColonia(rset.getString(18));
				vGRLEmpresas.setcCiudad(rset.getString(19));
				vcGRLEmpresas.addElement(vGRLEmpresas);
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
			return vcGRLEmpresas;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByEmpDom(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEmpresas = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas vGRLEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "GRLEmpresas.iCveEmpresa,"
					+ "iCveGrupoEmp,"
					+ "cIDDGPMPT,"
					+ "iIDMdoTrans,"
					+ "cRFC,"
					+ "cCurp,"
					+ "iCveUniMed,"
					+ "iCveMdoTrans,"
					+ "cTpoPersona,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "cNombreRS,"
					+ "cDenominacion,"
					+ "dtRegistro,"
					+ "cCveRPA,"
					+ "iCveOrigenInf, "
					+ "cCalle, "
					+ "cColonia, "
					+ "cCiudad, "
					+ "iCP, "
					+ "cTel, "
					+ "GRLEntidadFed.cNombre cDscEntidadFed, "
					+ "GRLMunicipio.cNombre cDscMunicipio, "
					+ "D.iCvePais, "
					+ "D.iCveEstado,"
					+ "D.iCveMunicipio "
					+ "from GRLEmpresas "
					+ " JOIN CTRDomicilio D ON D.iCveEmpresa = GRLEmpresas.iCveEmpresa "
					+ "  AND D.lActivo = 1 "
					+ " JOIN GRLEntidadFed ON D.iCvePais = GRLEntidadFed.iCvePais "
					+ "  AND D.iCveEstado = GRLEntidadFed.iCveEntidadFed "
					+ " JOIN GRLMunicipio ON D.iCvePais = GRLMunicipio.iCvePais "
					+ "  AND D.iCveEstado = GRLMunicipio.iCveEntidadFed "
					+ "  AND D.iCveMunicipio = GRLMunicipio.iCveMunicipio "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLEmpresas = new TVGRLEmpresas();
				vGRLEmpresas.setICveEmpresa(rset.getInt(1));
				vGRLEmpresas.setICveGrupoEmp(rset.getInt(2));
				vGRLEmpresas.setcIDDGPMPT(rset.getString(3));
				vGRLEmpresas.setIIDMdoTrans(rset.getInt(4));
				vGRLEmpresas.setCRFC(rset.getString(5));
				vGRLEmpresas.setCCurp(rset.getString(6));
				vGRLEmpresas.setICveUniMed(rset.getInt(7));
				vGRLEmpresas.setICveMdoTrans(rset.getInt(8));
				vGRLEmpresas.setCTpoPersona(rset.getString(9));
				vGRLEmpresas.setCApPaterno(rset.getString(10));
				vGRLEmpresas.setCApMaterno(rset.getString(11));
				vGRLEmpresas.setCNombreRS(rset.getString(12));
				vGRLEmpresas.setCDenominacion(rset.getString(13));
				vGRLEmpresas.setDtRegistro(rset.getDate(14));
				vGRLEmpresas.setCCveRPA(rset.getString(15));
				vGRLEmpresas.setICveOringenInf(rset.getInt(16));
				vGRLEmpresas.setcCalle(rset.getString(17));
				vGRLEmpresas.setcColonia(rset.getString(18));
				vGRLEmpresas.setcCiudad(rset.getString(19));
				vGRLEmpresas.setICP(rset.getInt(20));
				vGRLEmpresas.setCTelefono(rset.getString(21));
				vGRLEmpresas.setCDscEntidadFed(rset.getString(22));
				vGRLEmpresas.setCDscMunicipio(rset.getString(23));
				vGRLEmpresas.setICvePais(rset.getInt(24));
				vGRLEmpresas.setICveEntidadFed(rset.getInt(25));
				vGRLEmpresas.setICveMunicipio(rset.getInt(26));

				vcGRLEmpresas.addElement(vGRLEmpresas);
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
			return vcGRLEmpresas;
		}
	}

	public Vector FindByAllSituacion(String cWhere, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEmpresas = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas vGRLEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select  distinct(max(CTRPlantilla.iCvePlantilla)), "
					+ "         GRLEmpresas.iCveEmpresa,                   "
					+ "         max(CTRSeguimiento.iCveMovimiento),        "
					+ "         GRLEmpresas.iCveGrupoEmp,                  "
					+ "         GRLEmpresas.cIDDGPMPT,                     "
					+ "         GRLEmpresas.iIDMdoTrans,                   "
					+ "         GRLEmpresas.cRFC,                          "
					+ "         GRLEmpresas.cCurp,                         "
					+ "         GRLEmpresas.iCveUniMed,                    "
					+ "         GRLEmpresas.iCveMdoTrans,                  "
					+ "         GRLEmpresas.cTpoPersona,                   "
					+ "         GRLEmpresas.cApPaterno,                    "
					+ "         GRLEmpresas.cApMaterno,                    "
					+ "         GRLEmpresas.cNombreRS,                     "
					+ "         GRLEmpresas.cDenominacion,                 "
					+ "         GRLEmpresas.dtRegistro,                    "
					+ "         GRLEmpresas.cCveRPA,                       "
					+ "         GRLEmpresas.iCveOrigenInf,                 "
					+ "         GRLEmpresas.cDscEmpresa,                   "
					+ "         CTRDomicilio.iCveDomicilio                 "
					+ " from GRLEmpresas                                   "
					+ " join CTRPlantilla on CTRPlantilla.iCveEmpresa = GRLEmpresas.iCveEmpresa          "
					+ " join CTRSeguimiento on CTRSeguimiento.iCveEmpresa = GRLEmpresas.iCveEmpresa      "
					+ "                    and CTRSeguimiento.iCvePlantilla = CTRPlantilla.iCvePlantilla "
					+ " join GRLEtapa on GRLEtapa.iCveProceso = 5          "
					+ "              and GRLEtapa.iCveEtapa = CTRSeguimiento.iCveEtapa "
					+ "left join CTRDomicilio on CTRDomicilio.iCveEmpresa = GRLEmpresas.iCveEmpresa "
					+ "                      and CTRDomicilio.lActivo     = 1 "
					+ cWhere + "      group by GRLEmpresas.iCveEmpresa,   "
					+ "               GRLEmpresas.iCveGrupoEmp,  "
					+ "               GRLEmpresas.cIDDGPMPT,     "
					+ "               GRLEmpresas.iIDMdoTrans,   "
					+ "               GRLEmpresas.cRFC,          "
					+ "               GRLEmpresas.cCurp,         "
					+ "               GRLEmpresas.iCveUniMed,    "
					+ "               GRLEmpresas.iCveMdoTrans,  "
					+ "               GRLEmpresas.cTpoPersona,   "
					+ "               GRLEmpresas.cApPaterno,    "
					+ "               GRLEmpresas.cApMaterno,    "
					+ "               GRLEmpresas.cNombreRS,     "
					+ "               GRLEmpresas.cDenominacion, "
					+ "               GRLEmpresas.dtRegistro,    "
					+ "               GRLEmpresas.cCveRPA,       "
					+ "               GRLEmpresas.iCveOrigenInf, "
					+ "               GRLEmpresas.cDscEmpresa,   "
					+ "               CTRDomicilio.iCveDomicilio " + cOrden;

			cValSQL = cSQL;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLEmpresas = new TVGRLEmpresas();
				vGRLEmpresas.setiCvePlantilla(rset.getInt(1));
				vGRLEmpresas.setICveEmpresa(rset.getInt(2));
				vGRLEmpresas.setiCveMovimiento(rset.getInt(3));
				vGRLEmpresas.setICveGrupoEmp(rset.getInt(4));
				vGRLEmpresas.setcIDDGPMPT(rset.getString(5));
				vGRLEmpresas.setIIDMdoTrans(rset.getInt(6));
				vGRLEmpresas.setCRFC(rset.getString(7));
				vGRLEmpresas.setCCurp(rset.getString(8));
				vGRLEmpresas.setICveUniMed(rset.getInt(9));
				vGRLEmpresas.setICveMdoTrans(rset.getInt(10));
				vGRLEmpresas.setCTpoPersona(rset.getString(11));
				vGRLEmpresas.setCApPaterno(rset.getString(12));
				vGRLEmpresas.setCApMaterno(rset.getString(13));
				vGRLEmpresas.setCNombreRS(rset.getString(14));
				vGRLEmpresas.setCDenominacion(rset.getString(15));
				vGRLEmpresas.setDtRegistro(rset.getDate(16));
				vGRLEmpresas.setCCveRPA(rset.getString(17));
				vGRLEmpresas.setICveOringenInf(rset.getInt(18));
				vGRLEmpresas.setcDscEmpresa(rset.getString(19));
				vGRLEmpresas.setiCveDomicilio(rset.getInt(20));
				vcGRLEmpresas.addElement(vGRLEmpresas);
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
			return vcGRLEmpresas;
		}
	}

	/**
	 * Metodo Insert Modificado!
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
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
			TVGRLEmpresas vGRLEmpresas = (TVGRLEmpresas) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLEmpresas(" + "iCveEmpresa,"
					+ "iCveGrupoEmp," + "cIDDGPMPT," + "iIDMdoTrans," + "cRFC,"
					+ "cCurp," + "iCveUniMed," + "iCveMdoTrans,"
					+ "cTpoPersona," + "cApPaterno," + "cApMaterno,"
					+ "cNombreRS," + "cDenominacion," + "dtRegistro,"
					+ "cCveRPA," + "iCveOrigenInf," + "cDscEmpresa"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR Cï¿½DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String q = "Select Max(iCveEmpresa) from GrlEmpresas";
			int iNewCve = 0;
			PreparedStatement psNewCve = conn.prepareStatement(q);
			ResultSet rsNewCve = psNewCve.executeQuery();
			while (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1) + 1;
			}
			rsNewCve.close();
			psNewCve.close();

			// Tipo de Persona
			String cDscEmpresa = "";
			if (vGRLEmpresas.getCTpoPersona().equals("F")) {
				cDscEmpresa = vGRLEmpresas.getCNombreRS() + " "
						+ vGRLEmpresas.getCApPaterno() + " "
						+ vGRLEmpresas.getCApMaterno();
			} else if (vGRLEmpresas.getCTpoPersona().equals("M")) {
				cDscEmpresa = vGRLEmpresas.getCNombreRS();
			}

			pstmt.setInt(1, iNewCve);
			pstmt.setInt(2, vGRLEmpresas.getICveGrupoEmp());
			pstmt.setString(3, vGRLEmpresas.getcIDDGPMPT());
			pstmt.setInt(4, vGRLEmpresas.getIIDMdoTrans());
			pstmt.setString(5, vGRLEmpresas.getCRFC());
			pstmt.setString(6, vGRLEmpresas.getCCurp());
			pstmt.setInt(7, vGRLEmpresas.getICveUniMed());
			pstmt.setInt(8, vGRLEmpresas.getICveMdoTrans());
			pstmt.setString(9, vGRLEmpresas.getCTpoPersona());
			pstmt.setString(10, vGRLEmpresas.getCApPaterno());
			pstmt.setString(11, vGRLEmpresas.getCApMaterno());
			pstmt.setString(12, vGRLEmpresas.getCNombreRS());
			pstmt.setString(13, vGRLEmpresas.getCDenominacion());
			pstmt.setDate(14, vGRLEmpresas.getDtRegistro());
			pstmt.setString(15, vGRLEmpresas.getCCveRPA());
			pstmt.setInt(16, vGRLEmpresas.getICveOringenInf());
			pstmt.setString(17, cDscEmpresa);
			pstmt.executeUpdate();
			cClave = "" + iNewCve;
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
			TVGRLEmpresas vGRLEmpresas = (TVGRLEmpresas) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLEmpresas" + " set iCveGrupoEmp= ?, "
					+ "cIDDGPMPT= ?, " + "iIDMdoTrans= ?, " + "cRFC= ?, "
					+ "cCurp= ?, " + "iCveUniMed= ?, " + "iCveMdoTrans= ?, "
					+ "cTpoPersona= ?, " + "cApPaterno= ?, "
					+ "cApMaterno= ?, " + "cNombreRS= ?, "
					+ "cDenominacion= ?, " + "dtRegistro= ?, " + "cCveRPA= ?, "
					+ "iCveOrigenInf= ?, " + "cDscEmpresa= ? "
					+ "where iCveEmpresa = ?";
			pstmt = conn.prepareStatement(cSQL);

			// Tipo de Persona
			String cDscEmpresa = "";
			if (vGRLEmpresas.getCTpoPersona().equals("F")) {
				cDscEmpresa = vGRLEmpresas.getCNombreRS() + " "
						+ vGRLEmpresas.getCApPaterno() + " "
						+ vGRLEmpresas.getCApMaterno();
			} else if (vGRLEmpresas.getCTpoPersona().equals("M")) {
				cDscEmpresa = vGRLEmpresas.getCNombreRS();
			}

			pstmt.setInt(1, vGRLEmpresas.getICveGrupoEmp());
			pstmt.setString(2, vGRLEmpresas.getcIDDGPMPT());
			pstmt.setInt(3, vGRLEmpresas.getIIDMdoTrans());
			pstmt.setString(4, vGRLEmpresas.getCRFC());
			pstmt.setString(5, vGRLEmpresas.getCCurp());
			pstmt.setInt(6, vGRLEmpresas.getICveUniMed());
			pstmt.setInt(7, vGRLEmpresas.getICveMdoTrans());
			pstmt.setString(8, vGRLEmpresas.getCTpoPersona());
			pstmt.setString(9, vGRLEmpresas.getCApPaterno());
			pstmt.setString(10, vGRLEmpresas.getCApMaterno());
			pstmt.setString(11, vGRLEmpresas.getCNombreRS());
			pstmt.setString(12, vGRLEmpresas.getCDenominacion());
			pstmt.setDate(13, vGRLEmpresas.getDtRegistro());
			pstmt.setString(14, vGRLEmpresas.getCCveRPA());
			pstmt.setInt(15, vGRLEmpresas.getICveOringenInf());
			pstmt.setString(16, cDscEmpresa);
			pstmt.setInt(17, vGRLEmpresas.getICveEmpresa());
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

	public Object updUniMed(Connection conGral, Object obj) throws DAOException {
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
			TVGRLEmpresas vGRLEmpresas = (TVGRLEmpresas) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLEmpresas" + " set iCveUniMed= ? "
					+ "where iCveEmpresa = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLEmpresas.getICveUniMed());
			pstmt.setInt(2, vGRLEmpresas.getICveEmpresa());
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
			TVGRLEmpresas vGRLEmpresas = (TVGRLEmpresas) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLEmpresas" + " where iCveEmpresa = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLEmpresas.getICveEmpresa());
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
				warn("delete.closeGRLEmpresas", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Regresa String
	 */
	public String FindByAllR(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLEmpresas = new Vector();
		String Regresa = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas vGRLEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = cWhere;

			// System.out.println("Empresa \n"+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Regresa = rset.getString(1);
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
			return Regresa;
		}
	}

	// FCSReq7
	/**
	 * Metodo DetalleEmpresa
	 * 
	 * @param: iCveEmpresa - Clave de la Empresa.
	 * @author: FCS
	 */
	public Vector DetalleDirEmpresa(int iCveEmpresa) throws DAOException {

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcPERDatos = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVPEREmpresa vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select ge.cDscEmpresa , "
					+ // 1
					"       ge.iCveGrupoEmp, "
					+ "       ge.cIdDgpmpt, "
					+ "       ge.cRFC, "
					+ // 4
					"       gum.cDscUniMed, "
					+ // 5
					"       gmt.cDscMdoTrans, "
					+ // 6
					"       ge.cTpoPersona, "
					+ "       ge.DTRegistro, "
					+ // 8
					"       ge.cDenominacion, "
					+ "       ct.icvedomicilio, "
					+ // 10
					"       ct.ccalle, "
					+ // 11
					"       ct.ccolonia, "
					+ // 12
					"       ct.cciudad, "
					+ "       ct.icp, "
					+ // 14
					"       p.cnombre, "
					+ "       e.cnombre, "
					+ // 12
					"       m.cnombre, "
					+ "       ct.ctel, "
					+ // 14
					"       ct.cfax, "
					+ "       ct.ccorreoelec, "
					+ "       ct.lactivo, "
					+ // 14
					"       ct.icvepais, "
					+ // 14
					"       ct.icveestado, "
					+ // 14
					"       ct.icvemunicipio "
					+ // 14
					"from   GrlEmpresas ge, "
					+ "       GrlUniMed  gum, "
					+ "       GrlMdoTrans gmt, "
					+ "       CTRDOMICILIO AS ct, "
					+ "       grlpais as p, "
					+ "       grlentidadfed as e, "
					+ "       grlmunicipio as m "
					+ "where  ge.iCveEmpresa = "
					+ iCveEmpresa
					+ " "
					+ "and    gum.iCveUniMed = ge.iCveUniMed "
					+ "and    gmt.iCveMdoTrans =  ge.iCveMdoTrans and "
					+ "ge.icveempresa = ct.icveempresa and "
					+ "ct.icvepais = p.icvepais and "
					+ "ct.icvepais = e.icvepais and "
					+ "ct.icveestado = e.icveentidadfed and "
					+ "ct.icvepais = m.icvepais and "
					+ "ct.icveestado = m.icveentidadfed and "
					+ "ct.icvemunicipio = m.icvemunicipio and "
					+ "ct.icvedomicilio = (Select max(ct2.icvedomicilio) from ctrdomicilio as ct2 where ct2.icveempresa = ct.icveempresa) ";

			// System.out.println("\n********** DireccionEmp - DetalleEmpresa (cSQL): \n "
			// +cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				vPERDatos = new TVPEREmpresa();

				vPERDatos.setSDscEmpresa(rset.getString(1));
				vPERDatos.setICveGrupoEmp(rset.getInt(2));
				vPERDatos.setSIdDgpmpt(rset.getString(3));
				vPERDatos.setSRFC(rset.getString(4));
				vPERDatos.setSDscUniMed(rset.getString(5));
				vPERDatos.setSDscMdoTrans(rset.getString(6));
				vPERDatos.setSTpoPersona(rset.getString(7));
				vPERDatos.setDtRegistro(rset.getDate(8));
				vPERDatos.setSRegistro(rset.getString(8));
				vPERDatos.setSDenominacion(rset.getString(9));
				vPERDatos.seticvedomicilio(rset.getInt(10));
				vPERDatos.setccalle(rset.getString(11));
				vPERDatos.setccolonia(rset.getString(12));
				vPERDatos.setcciudad(rset.getString(13));
				vPERDatos.seticp(rset.getInt(14));
				vPERDatos.setcnombrePais(rset.getString(15));
				vPERDatos.setcnombreEstado(rset.getString(16));
				vPERDatos.setcnombreMun(rset.getString(17));
				vPERDatos.setctel(rset.getString(18));
				vPERDatos.setcfax(rset.getString(19));
				vPERDatos.setccorreoelec(rset.getString(20));
				vPERDatos.setlactivo(rset.getInt(21));
				vPERDatos.seticvepais(rset.getInt(22));
				vPERDatos.seticveestado(rset.getInt(23));
				vPERDatos.seticvemunicipio(rset.getInt(24));

				vcPERDatos.addElement(vPERDatos);

			}
		} catch (Exception ex) {
			warn("DetalleEmpresa: ", ex);
			throw new DAOException("DetalleEmpresa");
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
				warn("DetalleEmpresa.close", ex2);
			}
			return vcPERDatos;
		}
	}

	/**
	 * Metodo UpdEmpresa
	 * 
	 * @author: FCS
	 */
	public int UpdEmpresa(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int TotReg = -1;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			TVPEREmpresa tvPerEmpresa = (TVPEREmpresa) obj;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " update CTRDOMICILIO " + " set " + " CCALLE = '"
					+ tvPerEmpresa.getccalle() + "', " + " CCOLONIA = '"
					+ tvPerEmpresa.getccolonia() + "', " + " CCIUDAD = '"
					+ tvPerEmpresa.getcciudad() + "', " + " ICP = "
					+ tvPerEmpresa.geticp() + ", " + " ICVEPAIS = "
					+ tvPerEmpresa.geticvepais() + ", " + " ICVEESTADO = "
					+ tvPerEmpresa.geticveestado() + ", " + " ICVEMUNICIPIO = "
					+ tvPerEmpresa.geticvemunicipio() + ", " + " CTEL = '"
					+ tvPerEmpresa.getctel() + "', " + " CFAX = '"
					+ tvPerEmpresa.getcfax() + "', " + " CCORREOELEC = '"
					+ tvPerEmpresa.getccorreoelec() + "', " + " LACTIVO = "
					+ tvPerEmpresa.getlactivo() + " "
					+ " where  iCveEmpresa = " + tvPerEmpresa.getICveEmpresa()
					+ " and iCveDomicilio = " + tvPerEmpresa.geticvedomicilio()
					+ " ";

			// System.out.println("\n********** UpdEmpresa - cSQL: \n" +cSQL);

			pstmt = conn.prepareStatement(cSQL);
			TotReg = pstmt.executeUpdate();

			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("UpdEmpresa: ", ex1);
			}
			warn("UpdEmpresa: ", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				conn.close();

				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("UpdEmpresa.close", ex2);
			}

			return TotReg;
		}
	}

	/**
	 * Metodo Insert Modificado!
	 */
	public Object insertCT(Connection conGral, Object obj) throws DAOException {
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
			// TVGRLEmpresas vGRLEmpresas = (TVGRLEmpresas) obj;
			TVPEREmpresa vPEREmpresa = (TVPEREmpresa) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into CTRDOMICILIO(" + "iCveEmpresa,"
					+ "iCveDomicilio," + "ccalle," + "ccolonia," + "cciudad,"
					+ "icp," + "icvepais," + "icveestado," + "icvemunicipio,"
					+ "ctel," + "cfax," + "ccorreoelec," + "lactivo"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR CÓDIGO PARA CALCULAR EL CONSECUTIVO DE LA TABLA
			String q = "Select Max(iCveDomicilio) from CTRDOMICILIO where icveempresa = "
					+ vPEREmpresa.getICveEmpresa();
			int iNewCve = 0;
			PreparedStatement psNewCve = conn.prepareStatement(q);
			ResultSet rsNewCve = psNewCve.executeQuery();
			while (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1) + 1;
			}
			rsNewCve.close();
			psNewCve.close();

			if (iNewCve == 0) {
				iNewCve = 1;
			}

			pstmt.setInt(1, vPEREmpresa.getICveEmpresa());
			pstmt.setInt(2, iNewCve);
			pstmt.setString(3, vPEREmpresa.getccalle());
			pstmt.setString(4, vPEREmpresa.getccolonia());
			pstmt.setString(5, vPEREmpresa.getcciudad());
			pstmt.setInt(6, vPEREmpresa.geticp());
			pstmt.setInt(7, vPEREmpresa.geticvepais());
			pstmt.setInt(8, vPEREmpresa.geticveestado());
			pstmt.setInt(9, vPEREmpresa.geticvemunicipio());
			pstmt.setString(10, vPEREmpresa.getctel());
			pstmt.setString(11, vPEREmpresa.getcfax());
			pstmt.setString(12, vPEREmpresa.getccorreoelec());
			pstmt.setInt(13, vPEREmpresa.getlactivo());
			pstmt.executeUpdate();
			cClave = "" + iNewCve;
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

}
