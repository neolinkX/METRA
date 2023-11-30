package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Data Acces Object de INVRegistro DAO
 * </p>
 * <p>
 * Description: Data Access Object para INVRegistro
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

public class TDINVRegistro extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVRegistro() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll("", "");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrder) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		String cFecha = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			TFechas dtFecha = new TFechas();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "a.iAnio,a.iCveMdoTrans,a.iIDDGPMPT,a.iIDMdoTrans,a.dtAccidente,"
					+ "a.dtNotifica,a.cDscBreve,a.iCveMedInforma,a.cLugar,a.iCvePais,"
					+ "a.iCveEstado,a.iCveMunicipio,a.iCveTpoCausa,a.iCveCausa,"
					+ "a.cObservacion,a.cDscAccidente,a.iVehFedInvolucra,a.iVehEdoInvolucra,"
					+ "a.iVehPartInvolucra,a.iPerFedInvolucra,a.iPerEdoInvolucra,"
					+ "a.iPerPartInvolucra,a.iCveUniMed,a.dtAsigna,a.lInvestigado,"
					+ "a.cConclusion,a.iCveMotivo,a.lConcluido,a.dtConcluido,a.lCancelado,"
					+ "a.dtCancelado,a.lFinRegistro,b.cNombre cPais,c.cNombre cEstado,"
					+ "d.cNombre cMunicipio,e.cDscTpoCausa,f.cDscCausa,g.cDscMedInforma "
					+ ",h.cDscUniMed, mod.cdscmodulo, a.iCveModulo "
					+ "from "
					+ "INVRegistro a left join GRLPais b on (a.iCvePais=b.iCvePais) "
					+ "left join GRLEntidadFed c on (a.iCvePais=c.iCvePais and "
					+ "a.iCveEstado=c.iCveEntidadFed) left join GRLMunicipio d on ("
					+ "a.iCvePais=d.iCvePais and a.iCveEstado=d.iCveEntidadFed and "
					+ "a.iCveMunicipio=d.iCveMunicipio) left join INVTpoCausa e on ("
					+ "a.iCveTpoCausa=e.iCveTpoCausa) left join INVCausa f on ("
					+ "a.iCveTpoCausa=f.iCveTpoCausa and a.iCveCausa=f.iCveCausa) "
					+ "left join INVMedInforma g on (a.iCveMedInforma=g.iCveMedInforma) "
					+ "left join GRLUniMed h on (a.iCveUniMed = h.iCveUniMed) "
					+ "left join grlmodulo mod on a.icveunimed = mod.icveunimed "
					+ "and  a.icvemodulo = mod.icvemodulo ";

			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			if (cOrder != null && cOrder.length() != 0) {
				cSQL += cOrder;
			} else {
				cSQL += " order by iAnio,iCveMdoTrans,iIDDGPMPT";
			}
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setIAnio(rset.getInt("iAnio"));
				vINVRegistro.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vINVRegistro.setIIDDGPMPT(rset.getInt("iIDDGPMPT"));
				vINVRegistro.setIIDMdoTrans(rset.getInt("iIDMdoTrans"));
				vINVRegistro.setDtAccidente(rset.getDate("dtAccidente"));
				vINVRegistro.setDtNotifica(rset.getDate("dtNotifica"));
				vINVRegistro.setCDscBreve(rset.getString("cDscBreve"));
				vINVRegistro.setICveMedInforma(rset.getInt("iCveMedInforma"));
				vINVRegistro.setCLugar(rset.getString("cLugar"));
				vINVRegistro.setICvePais(rset.getInt("iCvePais"));
				vINVRegistro.setICveEstado(rset.getInt("iCveEstado"));
				vINVRegistro.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vINVRegistro.setICveTpoCausa(rset.getInt("iCveTpoCausa"));
				vINVRegistro.setICveCausa(rset.getInt("iCveCausa"));
				vINVRegistro.setCObservacion(rset.getString("cObservacion"));
				vINVRegistro.setCDscAccidente(rset.getString("cDscAccidente"));
				vINVRegistro.setIVehFedInvolucra(rset
						.getInt("iVehFedInvolucra"));
				vINVRegistro.setIVehEdoInvolucra(rset
						.getInt("iVehEdoInvolucra"));
				vINVRegistro.setIVehPartInvolucra(rset
						.getInt("iVehPartInvolucra"));
				vINVRegistro.setIPerFedInvolucra(rset
						.getInt("iPerFedInvolucra"));
				vINVRegistro.setIPerEdoInvolucra(rset
						.getInt("iPerEdoInvolucra"));
				vINVRegistro.setIPerPartInvolucra(rset
						.getInt("iPerPartInvolucra"));
				vINVRegistro.setICveUniMed(rset.getInt("iCveUniMed"));
				vINVRegistro.setDtAsigna(rset.getDate("dtAsigna"));
				vINVRegistro.setLInvestigado(rset.getInt("lInvestigado"));
				vINVRegistro.setCConclusion(rset.getString("cConclusion"));
				vINVRegistro.setICveMotivo(rset.getInt("iCveMotivo"));
				vINVRegistro.setLConcluido(rset.getInt("lConcluido"));
				vINVRegistro.setDtConcluido(rset.getDate("dtConcluido"));
				vINVRegistro.setLCancelado(rset.getInt("lCancelado"));
				vINVRegistro.setDtCancelado(rset.getDate("dtCancelado"));
				vINVRegistro.setLFinRegistro(rset.getInt("lFinRegistro"));
				vINVRegistro.setCPais(rset.getString("cPais"));
				vINVRegistro.setCEstado(rset.getString("cEstado"));
				vINVRegistro.setCMunicipio(rset.getString("cMunicipio"));
				vINVRegistro.setCDscTpoCausa(rset.getString("cDscTpoCausa"));
				vINVRegistro.setCDscCausa(rset.getString("cDscCausa"));
				vINVRegistro
						.setCDscMedInforma(rset.getString("cDscMedInforma"));
				vINVRegistro.setCDscUniMed(rset.getString("cDscUniMed"));
				vINVRegistro.setCDscModulo(rset.getString("cDscModulo"));
				vINVRegistro.setICveModulo(rset.getInt("iCveModulo"));

				if (vINVRegistro.getDtAsigna() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vINVRegistro.getDtAsigna(), "/");
					vINVRegistro.setCDscDtAsigna(cFecha);
				} else {
					vINVRegistro.setCDscDtAsigna("");
				}

				if (vINVRegistro.getDtAccidente() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vINVRegistro.getDtAccidente(), "/");
					vINVRegistro.setCDscDtAccidente(cFecha);
				} else {
					vINVRegistro.setCDscDtAccidente("");
				}

				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	/**
	 * Metodo FindPersonal
	 * 
	 * @author Marco A. Gonz�lez Paz
	 * @return Regresa un vector que indicara si ya existen registros para el
	 *         personal involucrado en el accidente
	 */
	public Vector FindPersonal(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iAnio,iCveMdoTrans,iIDDGPMPT,iIDMdoTrans,dtAccidente,dtNotifica,"
					+ "cDscBreve,iCveMedInforma,cLugar,iCvePais,iCveEstado,iCveMunicipio,"
					+ "iCveTpoCausa,iCveCausa,cObservacion,cDscAccidente,iVehFedInvolucra,"
					+ "iVehEdoInvolucra,iVehPartInvolucra,iPerFedInvolucra,iPerEdoInvolucra,"
					+ "iPerPartInvolucra,iCveUniMed,dtAsigna,lInvestigado,cConclusion,"
					+ "iCveMotivo,lConcluido,dtConcluido,lCancelado,dtCancelado "
					+ "from INVRegistro";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			cSQL += " order by iAnio,iCveMdoTrans,iIDDGPMPT";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setIAnio(rset.getInt("iAnio"));
				vINVRegistro.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vINVRegistro.setIIDDGPMPT(rset.getInt("iIDDGPMPT"));
				vINVRegistro.setIIDMdoTrans(rset.getInt("iIDMdoTrans"));
				vINVRegistro.setDtAccidente(rset.getDate("dtAccidente"));
				vINVRegistro.setDtNotifica(rset.getDate("dtNotifica"));
				vINVRegistro.setCDscBreve(rset.getString("cDscBreve"));
				vINVRegistro.setICveMedInforma(rset.getInt("iCveMedInforma"));
				vINVRegistro.setCLugar(rset.getString("cLugar"));
				vINVRegistro.setICvePais(rset.getInt("iCvePais"));
				vINVRegistro.setICveEstado(rset.getInt("iCveEstado"));
				vINVRegistro.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vINVRegistro.setICveTpoCausa(rset.getInt("iCveTpoCausa"));
				vINVRegistro.setICveCausa(rset.getInt("iCveCausa"));
				vINVRegistro.setCObservacion(rset.getString("cObservacion"));
				vINVRegistro.setCDscAccidente(rset.getString("cDscAccidente"));
				vINVRegistro.setIVehFedInvolucra(rset
						.getInt("iVehFedInvolucra"));
				vINVRegistro.setIVehEdoInvolucra(rset
						.getInt("iVehEdoInvolucra"));
				vINVRegistro.setIVehPartInvolucra(rset
						.getInt("iVehPartInvolucra"));
				vINVRegistro.setIPerFedInvolucra(rset
						.getInt("iPerFedInvolucra"));
				vINVRegistro.setIPerEdoInvolucra(rset
						.getInt("iPerEdoInvolucra"));
				vINVRegistro.setIPerPartInvolucra(rset
						.getInt("iPerPartInvolucra"));
				vINVRegistro.setICveUniMed(rset.getInt("iCveUniMed"));
				vINVRegistro.setDtAsigna(rset.getDate("dtAsigna"));
				vINVRegistro.setLInvestigado(rset.getInt("lInvestigado"));
				vINVRegistro.setCConclusion(rset.getString("cConclusion"));
				vINVRegistro.setICveMotivo(rset.getInt("iCveMotivo"));
				vINVRegistro.setLConcluido(rset.getInt("lConcluido"));
				vINVRegistro.setDtConcluido(rset.getDate("dtConcluido"));
				vINVRegistro.setLCancelado(rset.getInt("lCancelado"));
				vINVRegistro.setDtCancelado(rset.getDate("dtCancelado"));
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("FindPersonal", ex);
			throw new DAOException("FindPersonal");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindPersonal.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	/**
	 * Metodo FindUniMed
	 * 
	 * @author Marco A. Gonz�lez Paz
	 * @return Regresa un String que indicara si ya ese asigno la UniMed y la
	 *         hora al Accidente
	 */
	public int FindUniMed(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iAnio,iCveMdoTrans,iIDDGPMPT,iIDMdoTrans,dtAccidente,dtNotifica,"
					+ "cDscBreve,iCveMedInforma,cLugar,iCvePais,iCveEstado,iCveMunicipio,"
					+ "iCveTpoCausa,iCveCausa,cObservacion,cDscAccidente,iVehFedInvolucra,"
					+ "iVehEdoInvolucra,iVehPartInvolucra,iPerFedInvolucra,iPerEdoInvolucra,"
					+ "iPerPartInvolucra,iCveUniMed,dtAsigna,lInvestigado,cConclusion,"
					+ "iCveMotivo,lConcluido,dtConcluido,lCancelado,dtCancelado "
					+ "from INVRegistro";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			cSQL += " order by iAnio,iCveMdoTrans,iIDDGPMPT";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setIAnio(rset.getInt("iAnio"));
				vINVRegistro.setICveMdoTrans(rset.getInt("iCveMdoTrans"));
				vINVRegistro.setIIDDGPMPT(rset.getInt("iIDDGPMPT"));
				vINVRegistro.setIIDMdoTrans(rset.getInt("iIDMdoTrans"));
				vINVRegistro.setDtAccidente(rset.getDate("dtAccidente"));
				vINVRegistro.setDtNotifica(rset.getDate("dtNotifica"));
				vINVRegistro.setCDscBreve(rset.getString("cDscBreve"));
				vINVRegistro.setICveMedInforma(rset.getInt("iCveMedInforma"));
				vINVRegistro.setCLugar(rset.getString("cLugar"));
				vINVRegistro.setICvePais(rset.getInt("iCvePais"));
				vINVRegistro.setICveEstado(rset.getInt("iCveEstado"));
				vINVRegistro.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vINVRegistro.setICveTpoCausa(rset.getInt("iCveTpoCausa"));
				vINVRegistro.setICveCausa(rset.getInt("iCveCausa"));
				vINVRegistro.setCObservacion(rset.getString("cObservacion"));
				vINVRegistro.setCDscAccidente(rset.getString("cDscAccidente"));
				vINVRegistro.setIVehFedInvolucra(rset
						.getInt("iVehFedInvolucra"));
				vINVRegistro.setIVehEdoInvolucra(rset
						.getInt("iVehEdoInvolucra"));
				vINVRegistro.setIVehPartInvolucra(rset
						.getInt("iVehPartInvolucra"));
				vINVRegistro.setIPerFedInvolucra(rset
						.getInt("iPerFedInvolucra"));
				vINVRegistro.setIPerEdoInvolucra(rset
						.getInt("iPerEdoInvolucra"));
				vINVRegistro.setIPerPartInvolucra(rset
						.getInt("iPerPartInvolucra"));
				vINVRegistro.setICveUniMed(rset.getInt("iCveUniMed"));
				iClave = vINVRegistro.getICveUniMed();
				vINVRegistro.setDtAsigna(rset.getDate("dtAsigna"));
				vINVRegistro.setLInvestigado(rset.getInt("lInvestigado"));
				vINVRegistro.setCConclusion(rset.getString("cConclusion"));
				vINVRegistro.setICveMotivo(rset.getInt("iCveMotivo"));
				vINVRegistro.setLConcluido(rset.getInt("lConcluido"));
				vINVRegistro.setDtConcluido(rset.getDate("dtConcluido"));
				vINVRegistro.setLCancelado(rset.getInt("lCancelado"));
				vINVRegistro.setDtCancelado(rset.getDate("dtCancelado"));
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("FindUniMed", ex);
			throw new DAOException("FindUniMed");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindUniMed.close", ex2);
			}
		}
		return iClave;
	}

	/**
	 * Metodo FindMotivo
	 * 
	 * @author Marco A. Gonz�lez Paz
	 * @return Regresa un objeto int que contiene la clave del motivo
	 */
	public int FindMotivo(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveMotivo " + "from INVRegistro";
			if (cWhere != null && cWhere.length() != 0) {
				cSQL += " where " + cWhere;
			}
			cSQL += " order by iAnio,iCveMdoTrans,iIDDGPMPT";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveMotivo(rset.getInt("iCveMotivo"));
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("FindMotivo", ex);
			throw new DAOException("FindMotivo");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindMotivo.close", ex2);
			}
		}
		return iClave;
	}

	public Vector CountByAnioUniMed(int ivAnio, int ivMdoTransporte)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select INVRegistro.iCveUniMed,           "
					+ "        INVRegistro.iAnio,                "
					+ "        month(INVRegistro.dtAccidente),   "
					+ "        count(INVRegistro.dtAccidente)    "
					+ "   from INVRegistro                       "
					+ "  where INVRegistro.iCveUniMed <> 0       "
					+ "    and year(INVRegistro.dtAccidente) =   " + ivAnio
					+ "    and INVRegistro.iCveMdoTrans      =   "
					+ ivMdoTransporte
					+ "  group by INVRegistro.iCveUniMed,        "
					+ "           INVRegistro.iAnio,             "
					+ "           month(INVRegistro.dtAccidente) ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveUniMed(rset.getInt(1)); // Es el Valor de la
															// Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(2)); // Es el Valor del Anio.
				vINVRegistro.setIIDDGPMPT(rset.getInt(3)); // Es el Mes de la
															// Fecha.
				vINVRegistro.setICveMotivo(rset.getInt(4)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioUniMed", ex);
			throw new DAOException("CountByAnioUniMed");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioUniMed.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioPaisEdo(int ivAnio, int ivMdoTransporte, int ivPais)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select INVRegistro.iCveUniMed,           "
					+ "        INVRegistro.iAnio,                "
					+ "        INVRegistro.iCvePais,             "
					+ "        INVRegistro.iCveEstado,           "
					+ "        month(INVRegistro.dtAccidente),   "
					+ "        count(INVRegistro.dtAccidente)    "
					+ "   from INVRegistro                       "
					+ "   where                                  "
					+
					// -- INVRegistro.iCveUniMed <> 0 and " +
					"        year(INVRegistro.dtAccidente) =   " + ivAnio
					+ "    and INVRegistro.iCveMdoTrans      =   "
					+ ivMdoTransporte
					+ "    and INVRegistro.iCvePais          =   " + ivPais
					+ "  group by INVRegistro.iCveUniMed,        "
					+ "           INVRegistro.iAnio,             "
					+ "        INVRegistro.iCvePais,             "
					+ "        INVRegistro.iCveEstado,           "
					+ "           month(INVRegistro.dtAccidente) ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveUniMed(rset.getInt(1)); // Es el Valor de la
															// Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(2)); // Es el Valor del Anio.
				vINVRegistro.setIIDDGPMPT(rset.getInt(5)); // Es el Mes de la
															// Fecha.
				vINVRegistro.setICvePais(rset.getInt(3));
				vINVRegistro.setICveEstado(rset.getInt(4));
				vINVRegistro.setICveMotivo(rset.getInt(6)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioUniMed", ex);
			throw new DAOException("CountByAnioUniMed");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioUniMed.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioSituacion(int ivAnio, int ivMdoTransporte)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select INVRegistro.iCveUniMed,           "
					+ "        INVRegistro.iAnio,                "
					+ "        month(INVRegistro.dtAccidente),   "
					+ "        INVPersonal.iCveSituacion,        "
					+ "        count(INVPersonal.iCveSituacion)  "
					+ "   from INVRegistro                       "
					+ "   join INVPersonal on                    "
					+ "        INVPersonal.iAnio        = INVRegistro.iAnio "
					+ "    and INVPersonal.iCveMdoTrans = INVRegistro.iCveMdoTrans "
					+ "    and INVPersonal.iIDDGPMPT    = INVRegistro.iIDDGPMPT "
					+ "  where                                   "
					+
					// "  where INVRegistro.iCveUniMed <> 0 and      " +
					"        year(INVRegistro.dtAccidente) =   " + ivAnio
					+ "    and INVRegistro.iCveMdoTrans      =   "
					+ ivMdoTransporte
					+ "  group by INVRegistro.iCveUniMed,        "
					+ "           INVRegistro.iAnio,             "
					+ "           month(INVRegistro.dtAccidente),"
					+ "           INVPersonal.iCveSituacion      ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveUniMed(rset.getInt(1)); // Es el Valor de la
															// Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(2)); // Es el Valor del Anio.
				vINVRegistro.setIIDDGPMPT(rset.getInt(3)); // Es el Mes de la
															// Fecha.
				vINVRegistro.setICveEstado(rset.getInt(4));
				vINVRegistro.setICveMotivo(rset.getInt(5)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioSituacion", ex);
			throw new DAOException("CountByAnioSituacion");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioSituacion.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioDiaSemana(int ivAnio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select                "
					+
					// String cSQL =
					// " select INVRegistro.iCveUniMed,               " +
					"        INVRegistro.iAnio,                    "
					+ "        INVRegistro.iCveMdoTrans,             "
					+ "        dayofweek(INVRegistro.dtAccidente),   "
					+ "        count(INVRegistro.dtAccidente)        "
					+ "   from INVRegistro                           "
					+ "  where                                       "
					+
					// "  where INVRegistro.iCveUniMed <> 0 and       " +
					"        year(INVRegistro.dtAccidente) =       "
					+ ivAnio
					+
					// "  group by INVRegistro.iCveUniMed,            " +
					"  group by  "
					+ "           INVRegistro.iAnio,                 "
					+ "           INVRegistro.iCveMdoTrans,          "
					+ "           dayofweek(INVRegistro.dtAccidente) ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				// vINVRegistro.setICveUniMed(rset.getInt(1)); //Es el Valor de
				// la Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(1)); // Es el Valor del Anio.
				vINVRegistro.setICveMdoTrans(rset.getInt(2));
				vINVRegistro.setIIDDGPMPT(rset.getInt(3)); // Es el Mes de la
															// Fecha.
				vINVRegistro.setICveMotivo(rset.getInt(4)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioDiaSemana", ex);
			throw new DAOException("CountByAnioDiaSemana");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioDiaSemana.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioLicVigente(int ivAnio, java.sql.Date dtVigencia)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select INVRegistro.iCveUniMed,               "
					+ "        INVRegistro.iAnio,                    "
					+ "        INVRegistro.iCveMdoTrans,             "
					+ "        count(INVRegistro.dtAccidente)        "
					+ "   from INVRegistro                           "
					+ "join INVPersonal on INVPersonal.iAnio        = INVRegistro.iAnio "
					+ "                and INVPersonal.iCveMdoTrans = INVRegistro.iCveMdoTrans "
					+ "                and INVPersonal.iIDDGPMPT    = INVRegistro.iIDDGPMPT "
					+ "                and INVPersonal.dtVigencia   >= '"
					+ dtVigencia + "'"
					+ "  where INVRegistro.iCveUniMed <> 0           "
					+ "    and year(INVRegistro.dtAccidente) =       " + ivAnio
					+ "  group by INVRegistro.iCveUniMed,            "
					+ "           INVRegistro.iAnio,                 "
					+ "           INVRegistro.iCveMdoTrans           ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveUniMed(rset.getInt(1)); // Es el Valor de la
															// Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(2)); // Es el Valor del Anio.
				vINVRegistro.setICveMdoTrans(rset.getInt(3));
				vINVRegistro.setICveMotivo(rset.getInt(4)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioLicVigente", ex);
			throw new DAOException("CountByAnioLicVigente");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioLicVigente.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioLicNoVigente(int ivAnio, java.sql.Date dtVigencia)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select INVRegistro.iCveUniMed,               "
					+ "        INVRegistro.iAnio,                    "
					+ "        INVRegistro.iCveMdoTrans,             "
					+ "        count(INVRegistro.dtAccidente)        "
					+ "   from INVRegistro                           "
					+ "join INVPersonal on INVPersonal.iAnio        = INVRegistro.iAnio "
					+ "                and INVPersonal.iCveMdoTrans = INVRegistro.iCveMdoTrans "
					+ "                and INVPersonal.iIDDGPMPT    = INVRegistro.iIDDGPMPT "
					+ "                and INVPersonal.dtVigencia   < '"
					+ dtVigencia + "'"
					+ "  where INVRegistro.iCveUniMed <> 0           "
					+ "    and year(INVRegistro.dtAccidente) =       " + ivAnio
					+ "  group by INVRegistro.iCveUniMed,            "
					+ "           INVRegistro.iAnio,                 "
					+ "           INVRegistro.iCveMdoTrans           ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveUniMed(rset.getInt(1)); // Es el Valor de la
															// Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(2)); // Es el Valor del Anio.
				vINVRegistro.setICveMdoTrans(rset.getInt(3));
				vINVRegistro.setICveMotivo(rset.getInt(4)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioLicNoVigente", ex);
			throw new DAOException("CountByAnioLicNoVigente");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioLicNoVigente.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioLicSeIgnora(int ivAnio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select INVRegistro.iCveUniMed,               "
					+ "        INVRegistro.iAnio,                    "
					+ "        INVRegistro.iCveMdoTrans,             "
					+ "        count(INVRegistro.dtAccidente)        "
					+ "   from INVRegistro                           "
					+ "join INVPersonal on INVPersonal.iAnio        = INVRegistro.iAnio "
					+ "                and INVPersonal.iCveMdoTrans = INVRegistro.iCveMdoTrans "
					+ "                and INVPersonal.iIDDGPMPT    = INVRegistro.iIDDGPMPT "
					+ "                and INVPersonal.dtVigencia   is null "
					+ "  where INVRegistro.iCveUniMed <> 0           "
					+ "    and year(INVRegistro.dtAccidente) =       " + ivAnio
					+ "  group by INVRegistro.iCveUniMed,            "
					+ "           INVRegistro.iAnio,                 "
					+ "           INVRegistro.iCveMdoTrans           ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveUniMed(rset.getInt(1)); // Es el Valor de la
															// Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(2)); // Es el Valor del Anio.
				vINVRegistro.setICveMdoTrans(rset.getInt(3));
				vINVRegistro.setICveMotivo(rset.getInt(4)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioLicSeIgnora", ex);
			throw new DAOException("CountByAnioLicSeIgnora");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioLicSeIgnora.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioEdad(int ivAnio, java.sql.Date dtFecha)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select INVRegistro.iCveUniMed,   "
					+ "       INVRegistro.iAnio,        "
					+ "       INVRegistro.iCveMdoTrans, "
					+ "       (year('"
					+ dtFecha
					+ "') - year(PERDatos.dtNacimiento)),"
					+ "       count(year('"
					+ dtFecha
					+ "') - year(PERDatos.dtNacimiento))"
					+ " from INVRegistro "
					+ " join INVPersonal on INVPersonal.iAnio        = INVRegistro.iAnio  "
					+ "                and INVPersonal.iCveMdoTrans = INVRegistro.iCveMdoTrans  "
					+ "                and INVPersonal.iIDDGPMPT    = INVRegistro.iIDDGPMPT  "
					+ " join PERDatos on PERDatos.iCvePersonal = INVPersonal.iCvePersonal "
					+ " where INVRegistro.iCveUniMed <> 0 "
					+ "  and year(INVRegistro.dtAccidente) = " + ivAnio
					+ " group by INVRegistro.iCveUniMed, "
					+ "          INVRegistro.iAnio, "
					+ "          INVRegistro.iCveMdoTrans, "
					+ "          (year('" + dtFecha
					+ "') - year(PERDatos.dtNacimiento)) ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setICveUniMed(rset.getInt(1)); // Es el Valor de la
															// Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(2)); // Es el Valor del Anio.
				vINVRegistro.setICveMdoTrans(rset.getInt(3));
				vINVRegistro.setIIDDGPMPT(rset.getInt(4));
				vINVRegistro.setICveMotivo(rset.getInt(5)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioEdad", ex);
			throw new DAOException("CountByAnioEdad");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioEdad.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioServicioPrestado(int ivAnio, int ivMdoTransporte)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select INVRegistro.iAnio,                  "
					+ "        INVRegVehic.iCveServPrestado,       "
					+ "        month(INVRegistro.dtAccidente),     "
					+ "        count(INVRegVehic.iCveServPrestado) "
					+ "   from INVRegistro                         "
					+ " join INVRegVehic on INVRegVehic.iAnio        = INVRegistro.iAnio        "
					+ "                 and INVRegVehic.iCveMdoTrans = INVRegistro.iCveMdoTrans "
					+ "                 and INVRegVehic.iIDDGPMPT    = INVRegistro.iIDDGPMPT    "
					+ "  where "
					+
					// INVRegistro.iCveUniMed <> 0 and " +
					"     year(INVRegistro.dtAccidente) =     " + ivAnio
					+ "    and INVRegistro.iCveMdoTrans      =     "
					+ ivMdoTransporte
					+ " group by INVRegistro.iAnio,           "
					+ "          INVRegVehic.iCveServPrestado, "
					+ "          month(INVRegistro.dtAccidente) " + "";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				vINVRegistro.setIAnio(rset.getInt(1)); // Es el Valor del Anio.
				vINVRegistro.setICveUniMed(rset.getInt(2)); // Es el Servicio
															// Prestado.
				vINVRegistro.setIIDDGPMPT(rset.getInt(3)); // Es el Valor del
															// Mes.
				vINVRegistro.setICveMotivo(rset.getInt(4)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioServicioPrestado", ex);
			throw new DAOException("CountByAnioServicioPrestado");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioServicioPrestado.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	public Vector CountByAnioCausa(int ivAnio, int ivMdoTransporte,
			int ivTpoCausa) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcINVRegistro = new Vector();
		int iClave = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVINVRegistro vINVRegistro;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = " select            "
					+
					// String cSQL =
					// " select INVRegistro.iCveUniMed,           " +
					"        INVRegistro.iAnio,                "
					+ "        month(INVRegistro.dtAccidente),   "
					+ "        INVRegistro.iCveCausa,            "
					+ "        count(INVRegistro.iCveCausa)      "
					+ "   from INVRegistro                       "
					+
					// "  where INVRegistro.iCveUniMed <> 0       " +
					"  where year(INVRegistro.dtAccidente) =   "
					+ ivAnio
					+ "    and INVRegistro.iCveMdoTrans      =   "
					+ ivMdoTransporte
					+ "    and INVRegistro.icveTpoCausa      =   "
					+ ivTpoCausa
					+
					// "  group by INVRegistro.iCveUniMed,        " +
					"  group by INVRegistro.iAnio,             "
					+ "           month(INVRegistro.dtAccidente),"
					+ "           INVRegistro.iCveCausa          ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vINVRegistro = new TVINVRegistro();
				// vINVRegistro.setICveUniMed(rset.getInt(1)); //Es el Valor de
				// la Unidad M�dica.
				vINVRegistro.setIAnio(rset.getInt(1)); // Es el Valor del Anio.
				vINVRegistro.setIIDDGPMPT(rset.getInt(2)); // Es el Mes de la
															// Fecha.
				vINVRegistro.setICveCausa(rset.getInt(3));
				vINVRegistro.setICveMotivo(rset.getInt(4)); // Es el Valor de
															// Cuantos.
				iClave = vINVRegistro.getICveMotivo();
				vcINVRegistro.addElement(vINVRegistro);
			}
		} catch (Exception ex) {
			warn("CountByAnioCausa", ex);
			throw new DAOException("CountByAnioCausa");
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("CountByAnioCausa.close", ex2);
			}
		}
		return vcINVRegistro;
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, TVINVRegistro vINVRegistro)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		ResultSet rslt = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "select "
					+ "max(iIDDGPMPT) cta from INVRegistro where iAnio=? and iCveMdoTrans=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRegistro.getIAnio());
			pstmt.setInt(2, vINVRegistro.getICveMdoTrans());
			rslt = pstmt.executeQuery();
			if (rslt.next()) {
				vINVRegistro.setIIDDGPMPT(rslt.getInt("cta") + 1);
			} else {
				vINVRegistro.setIIDDGPMPT(1);
			}
			rslt.close();
			pstmt.close();

			cSQL = "insert into INVRegistro ("
					+ "iAnio,iCveMdoTrans,iIDDGPMPT,iIDMdoTrans,dtAccidente,dtNotifica,"
					+ "cDscBreve,iCveMedInforma,cLugar,iCvePais,iCveEstado,iCveMunicipio,"
					+ "iCveTpoCausa,iCveCausa,cObservacion,cDscAccidente,iVehFedInvolucra,"
					+ "iVehEdoInvolucra,iVehPartInvolucra,iPerFedInvolucra,iPerEdoInvolucra,"
					+ "iPerPartInvolucra,iCveUniMed,dtAsigna,lInvestigado,cConclusion,"
					+ "iCveMotivo,lConcluido,dtConcluido,lCancelado,dtCancelado,lFinRegistro) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRegistro.getIAnio());
			pstmt.setInt(2, vINVRegistro.getICveMdoTrans());
			pstmt.setInt(3, vINVRegistro.getIIDDGPMPT());
			pstmt.setInt(4, vINVRegistro.getIIDMdoTrans());
			pstmt.setDate(5, vINVRegistro.getDtAccidente());
			pstmt.setDate(6, vINVRegistro.getDtNotifica());
			pstmt.setString(7, vINVRegistro.getCDscBreve());
			pstmt.setInt(8, vINVRegistro.getICveMedInforma());
			pstmt.setString(9, vINVRegistro.getCLugar());
			pstmt.setInt(10, vINVRegistro.getICvePais());
			pstmt.setInt(11, vINVRegistro.getICveEstado());
			pstmt.setInt(12, vINVRegistro.getICveMunicipio());
			pstmt.setInt(13, vINVRegistro.getICveTpoCausa());
			pstmt.setInt(14, vINVRegistro.getICveCausa());
			pstmt.setString(15, vINVRegistro.getCObservacion());
			pstmt.setString(16, vINVRegistro.getCDscAccidente());
			pstmt.setInt(17, vINVRegistro.getIVehFedInvolucra());
			pstmt.setInt(18, vINVRegistro.getIVehEdoInvolucra());
			pstmt.setInt(19, vINVRegistro.getIVehPartInvolucra());
			pstmt.setInt(20, vINVRegistro.getIPerFedInvolucra());
			pstmt.setInt(21, vINVRegistro.getIPerEdoInvolucra());
			pstmt.setInt(22, vINVRegistro.getIPerPartInvolucra());
			pstmt.setInt(23, 0);
			pstmt.setDate(24, vINVRegistro.getDtAsigna());
			pstmt.setInt(25, vINVRegistro.getLInvestigado());
			pstmt.setString(26, vINVRegistro.getCConclusion());
			pstmt.setInt(27, vINVRegistro.getICveMotivo());
			pstmt.setInt(28, vINVRegistro.getLConcluido());
			pstmt.setDate(29, vINVRegistro.getDtConcluido());
			pstmt.setInt(30, vINVRegistro.getLCancelado());
			pstmt.setDate(31, vINVRegistro.getDtCancelado());
			pstmt.setInt(32, vINVRegistro.getLFinRegistro());
			pstmt.executeUpdate();
			cClave = "" + vINVRegistro.getIAnio() + "|"
					+ vINVRegistro.getICveMdoTrans() + "|"
					+ vINVRegistro.getIIDDGPMPT();
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
		}
		return cClave;
	}

	/**
	 * Metodo update
	 */
	public Object update(Connection conGral, TVINVRegistro vINVRegistro)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVRegistro"
					+ " set iIDMdoTrans=?,dtAccidente=?,dtNotifica=?,cDscBreve=?,"
					+ "iCveMedInforma=?,cLugar=?,iCvePais=?,iCveEstado=?,iCveMunicipio=?,"
					+ "iCveTpoCausa=?,iCveCausa=?,cObservacion=?,cDscAccidente=?,"
					+ "iVehFedInvolucra=?,iVehEdoInvolucra=?,iVehPartInvolucra=?,"
					+ "iPerFedInvolucra=?,iPerEdoInvolucra=?,iPerPartInvolucra=?,"
					+ "iCveUniMed=?,dtAsigna=?,lInvestigado=?,cConclusion=?,iCveMotivo=?,"
					+ "lConcluido=?,dtConcluido=?,lCancelado=?,dtCancelado=?,lFinRegistro=? "
					+ "where iAnio=? " + "and iCveMdoTrans=?"
					+ " and iIDDGPMPT=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRegistro.getIIDMdoTrans());
			pstmt.setDate(2, vINVRegistro.getDtAccidente());
			pstmt.setDate(3, vINVRegistro.getDtNotifica());
			pstmt.setString(4, vINVRegistro.getCDscBreve());
			pstmt.setInt(5, vINVRegistro.getICveMedInforma());
			pstmt.setString(6, vINVRegistro.getCLugar());
			pstmt.setInt(7, vINVRegistro.getICvePais());
			pstmt.setInt(8, vINVRegistro.getICveEstado());
			pstmt.setInt(9, vINVRegistro.getICveMunicipio());
			pstmt.setInt(10, vINVRegistro.getICveTpoCausa());
			pstmt.setInt(11, vINVRegistro.getICveCausa());
			pstmt.setString(12, vINVRegistro.getCObservacion());
			pstmt.setString(13, vINVRegistro.getCDscAccidente());
			pstmt.setInt(14, vINVRegistro.getIVehFedInvolucra());
			pstmt.setInt(15, vINVRegistro.getIVehEdoInvolucra());
			pstmt.setInt(16, vINVRegistro.getIVehPartInvolucra());
			pstmt.setInt(17, vINVRegistro.getIPerFedInvolucra());
			pstmt.setInt(18, vINVRegistro.getIPerEdoInvolucra());
			pstmt.setInt(19, vINVRegistro.getIPerPartInvolucra());
			pstmt.setInt(20, vINVRegistro.getICveUniMed());
			pstmt.setDate(21, vINVRegistro.getDtAsigna());
			pstmt.setInt(22, vINVRegistro.getLInvestigado());
			pstmt.setString(23, vINVRegistro.getCConclusion());
			pstmt.setInt(24, vINVRegistro.getICveMotivo());
			pstmt.setInt(25, vINVRegistro.getLConcluido());
			pstmt.setDate(26, vINVRegistro.getDtConcluido());
			pstmt.setInt(27, vINVRegistro.getLCancelado());
			pstmt.setDate(28, vINVRegistro.getDtCancelado());
			pstmt.setInt(29, vINVRegistro.getLFinRegistro());
			pstmt.setInt(30, vINVRegistro.getIAnio());
			pstmt.setInt(31, vINVRegistro.getICveMdoTrans());
			pstmt.setInt(32, vINVRegistro.getIIDDGPMPT());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo updateStatus
	 */
	public Object updateStatus(Connection conGral, Vector vcINVRegistro)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVRegistro"
					+ " set lInvestigado=?,lCancelado=? " + "where iAnio=? "
					+ "and iCveMdoTrans=?" + " and iIDDGPMPT=?";
			pstmt = conn.prepareStatement(cSQL);
			for (Enumeration e = vcINVRegistro.elements(); e.hasMoreElements();) {
				TVINVRegistro vINVRegistro = (TVINVRegistro) e.nextElement();
				pstmt.setInt(1, vINVRegistro.getLInvestigado());
				pstmt.setInt(2, vINVRegistro.getLCancelado());
				pstmt.setInt(3, vINVRegistro.getIAnio());
				pstmt.setInt(4, vINVRegistro.getICveMdoTrans());
				pstmt.setInt(5, vINVRegistro.getIIDDGPMPT());
				pstmt.executeUpdate();
			}
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo updateRegistro
	 */
	public Object updateRegistro(Connection conGral, TVINVRegistro vINVRegistro)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVRegistro "
					+ "set iIDMdoTrans=?,cDscBreve=?,cLugar=?,iCvePais=?,iCveEstado=?,"
					+ "iCveMunicipio=?,iCveTpoCausa=?,iCveCausa=?,cDscAccidente=?,lFinRegistro=?, iCveMotivo=?"
					+ "where iAnio=? and iCveMdoTrans=? and iIDDGPMPT=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRegistro.getIIDMdoTrans());
			pstmt.setString(2, vINVRegistro.getCDscBreve());
			pstmt.setString(3, vINVRegistro.getCLugar());
			pstmt.setInt(4, vINVRegistro.getICvePais());
			pstmt.setInt(5, vINVRegistro.getICveEstado());
			pstmt.setInt(6, vINVRegistro.getICveMunicipio());
			pstmt.setInt(7, vINVRegistro.getICveTpoCausa());
			pstmt.setInt(8, vINVRegistro.getICveCausa());
			pstmt.setString(9, vINVRegistro.getCDscAccidente());
			pstmt.setInt(10, vINVRegistro.getLFinRegistro());
			pstmt.setInt(11, vINVRegistro.getICveMotivo());
			pstmt.setInt(12, vINVRegistro.getIAnio());
			pstmt.setInt(13, vINVRegistro.getICveMdoTrans());
			pstmt.setInt(14, vINVRegistro.getIIDDGPMPT());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo updateUniMed
	 * 
	 * @author Marco A. Gonz�lez Paz
	 */
	public Object updateUniMed(Connection conGral, TVINVRegistro vINVRegistro)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "update INVRegistro " + "set iCveUniMed=?,"
					+ "iCveModulo=?, " + "dtAsigna=? "
					+ "where iAnio=? and iCveMdoTrans=? and iIDDGPMPT=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRegistro.getICveUniMed());
			pstmt.setInt(2, vINVRegistro.getICveModulo());
			pstmt.setDate(3, vINVRegistro.getDtAsigna());
			pstmt.setInt(4, vINVRegistro.getIAnio());
			pstmt.setInt(5, vINVRegistro.getICveMdoTrans());
			pstmt.setInt(6, vINVRegistro.getIIDDGPMPT());
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
				warn("updateUniMed", ex1);
			}
			warn("updateUniMed", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conGral == null) {
					conn.close();
				}
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("updateUniMed.close", ex2);
			}
		}
		return cClave;
	}

	/**
	 * Metodo Delete
	 */
	public Object delete(Connection conGral, TVINVRegistro vINVRegistro)
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "delete from INVRegistro"
					+ " where iAnio=? and iCveMdoTrans=? and iIDDGPMPT=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vINVRegistro.getIAnio());
			pstmt.setInt(2, vINVRegistro.getICveMdoTrans());
			pstmt.setInt(3, vINVRegistro.getIIDDGPMPT());
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
				if (dbConn != null) {
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeINVRegistro", ex2);
			}
		}
		return cClave;
	}
}