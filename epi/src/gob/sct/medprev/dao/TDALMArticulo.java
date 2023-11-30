package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de ALMArticulo DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrej�n Adame
 * @version 1.0
 */

public class TDALMArticulo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMArticulo() {
	}

	public Vector FindByCustomWhere(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArticulo vALMArticulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select distinct " + " ALMArticulo.iCveArticulo,  "
					+ " ALMArticulo.iCveTpoArticulo,  "
					+ " ALMArticulo.iCveFamilia,  "
					+ " ALMArticulo.cCveArticulo,  "
					+ " ALMArticulo.cDscArticulo,   "
					+ " ALMArticulo.cDscBreve, "
					+ " ALMArticulo.iCveUniAlm,   "
					+ " ALMArticulo.iCveUniSum,   "
					+ " ALMArticulo.lMaxMin,   "
					+ " ALMArticulo.cObservacion,   " + " ALMArticulo.lLote  "
					+ " from ALMArticulo " + cWhere + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArticulo = new TVALMArticulo();
				vALMArticulo.setiCveArticulo(rset.getInt(1));
				vALMArticulo.setiCveTpoArticulo(rset.getInt(2));
				vALMArticulo.setiCveFamilia(rset.getInt(3));
				vALMArticulo.setcCveArticulo(rset.getString(4));
				vALMArticulo.setcDscArticulo(rset.getString(5));
				vALMArticulo.setcDscBreve(rset.getString(6));
				vALMArticulo.setiCveUniAlm(rset.getInt(7));
				vALMArticulo.setiCveUniSum(rset.getInt(8));
				vALMArticulo.setlMaxMin(rset.getInt(9));
				vALMArticulo.setcObservacion(rset.getString(10));
				vALMArticulo.setlLote(rset.getInt(11));
				vcALMArticulo.addElement(vALMArticulo);
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
			return vcALMArticulo;
		}
	}

	public Vector FindByAllMov(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArticulo vALMArticulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " ALMArticulo.iCveArticulo,  "
					+ " ALMArticulo.cCveArticulo,  "
					+ " ALMArticulo.cDscBreve " + " from ALMArticulo " + cWhere
					+ " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArticulo = new TVALMArticulo();
				vALMArticulo.setiCveArticulo(rset.getInt(1));
				vALMArticulo.setcCveArticulo(rset.getString(2));
				vALMArticulo.setcDscBreve(rset.getString(3));
				vcALMArticulo.addElement(vALMArticulo);
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
			return vcALMArticulo;
		}
	}

	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArticulo vALMArticulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "ALMArticulo.iCveArticulo, "
					+ "ALMArticulo.cCveArticulo, "
					+ "ALMArticulo.iCveTpoArticulo, "
					+ "ALMTpoArticulo.cDscTpoArticulo, "
					+ "ALMArticulo.iCveFamilia, "
					+ "ALMFamilia.cDscFamilia, "
					+ "ALMArticulo.cDscArticulo, "
					+ "ALMArticulo.cDscBreve, "
					+ "ALMArticulo.lLote, "
					+ "ALMArticulo.lMaxMin, "
					+ "ALMArticulo.cObservacion, "
					+ "ALMArticulo.iCveUniAlm, "
					+ "ALMArticulo.iCveUniSum, "
					+ "ALMUnidad.cDscUnidad  as cDscUniAlm, "
					+ "ALMUnidad2.cDscUnidad as cDscUniSum "
					+ "from ALMArticulo   "
					+ "join ALMTpoArticulo on ALMTpoArticulo.iCveTpoArticulo = ALMArticulo.iCveTpoArticulo   "
					+ "join ALMFamilia on ALMFamilia.iCveTpoArticulo = ALMArticulo.iCveTpoArticulo "
					+ "               and ALMFamilia.iCveFamilia = ALMArticulo.iCveFamilia "
					+ "join ALMUnidad on ALMUnidad.iCveUnidad = ALMArticulo.iCveUniAlm   "
					+ "join ALMUnidad ALMUnidad2 on ALMUnidad2.iCveUnidad = ALMArticulo.iCveUniSum "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArticulo = new TVALMArticulo();
				vALMArticulo.setiCveArticulo(rset.getInt(1));
				vALMArticulo.setcCveArticulo(rset.getString(2));
				vALMArticulo.setiCveTpoArticulo(rset.getInt(3));
				vALMArticulo.setcDscTpoArticulo(rset.getString(4));
				vALMArticulo.setiCveFamilia(rset.getInt(5));
				vALMArticulo.setcDscFamilia(rset.getString(6));
				vALMArticulo.setcDscArticulo(rset.getString(7));
				vALMArticulo.setcDscBreve(rset.getString(8));
				vALMArticulo.setlLote(rset.getInt(9));
				vALMArticulo.setlMaxMin(rset.getInt(10));
				vALMArticulo.setcObservacion(rset.getString(11));
				vALMArticulo.setiCveUniAlm(rset.getInt(12));
				vALMArticulo.setiCveUniSum(rset.getInt(13));
				vALMArticulo.setcDscUniAlm(rset.getString(14));
				vALMArticulo.setcDscUniSum(rset.getString(15));

				vcALMArticulo.addElement(vALMArticulo);
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
			return vcALMArticulo;
		}
	}

	/*
	 * FindBySumArt, toma en cuanta las Autorizadas
	 */
	public Vector FindBySumArt(Object Obj, String cWhere, String cOrder,
			String cTipo, int lAutorizada) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArticulo vALMArticulo = (TVALMArticulo) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select ALMArticulo.iCveArticulo,   "
					+ "        ALMArticulo.cCveArticulo,   "
					+ "        ALMArticulo.iCveTpoArticulo,   "
					+ "        ALMTpoArticulo.cDscTpoArticulo,   "
					+ "        ALMArticulo.iCveFamilia,   "
					+ "        ALMFamilia.cDscFamilia,   "
					+ "        ALMArticulo.cDscArticulo,   "
					+ "        ALMArticulo.cDscBreve,   "
					+ "        ALMArticulo.lLote,   "
					+ "        ALMArticulo.lMaxMin,   "
					+ "        ALMArticulo.cObservacion,  "
					+ "        ALMArticulo.iCveUniAlm,   "
					+ "        ALMArticulo.iCveUniSum,   "
					+ "        ALMUnidad.cDscUnidad  as cDscUniAlm, "
					+ "        ALMUnidad2.cDscUnidad as cDscUniSum, "
					+ "        ALMSumArt.iCveMeta, "
					+ "        ALMSumArt.dUnidSolicita , "
					+ "        ALMSumArt.iAnio , "
					+ "        ALMSumArt.iCveAlmacen , "
					+ "        ALMSumArt.iCveSolicSum , "
					+ "        GRLMetaProceso.cDscMeta "
					+ "        from ALMArticulo   "
					+ "        join ALMTpoArticulo on ALMArticulo.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo   "
					+ "        join ALMFamilia on ALMArticulo.iCveFamilia = ALMFamilia.iCveFamilia and "
					+ "                           ALMArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo "
					+ "        join ALMUnidad on ALMArticulo.iCveUniAlm = ALMUnidad.iCveUnidad   "
					+ "        join ALMUnidad ALMUnidad2 on ALMArticulo.iCveUniSum = ALMUnidad2.iCveUnidad  "
					+ cTipo
					+ " join ALMSumArt on ALMSumArt.iAnio = "
					+ vALMArticulo.getIAnio()
					+ "                           and ALMSumArt.iCveAlmacen = "
					+ vALMArticulo.getICveAlmacen()
					+ "                           and ALMSumArt.iCveSolicSum = "
					+ vALMArticulo.getICveSolicSum()
					+ "                           and ALMSumArt.iCveArticulo = ALMArticulo.iCveArticulo "
					+ "                           and ALMSumArt.lAutorizada = "
					+ lAutorizada
					+ "   left join GRLMetaProceso on  GRLMetaProceso.iAnio = "
					+ vALMArticulo.getIAnio()
					+ "                           and  GRLMetaProceso.iCveMeta = ALMSumArt.iCveMeta "
					+ cWhere + cOrder;
			// System.out.print("cSQL--->> " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArticulo = new TVALMArticulo();
				vALMArticulo.setiCveArticulo(rset.getInt(1));
				vALMArticulo.setcCveArticulo(rset.getString(2));
				vALMArticulo.setiCveTpoArticulo(rset.getInt(3));
				vALMArticulo.setcDscTpoArticulo(rset.getString(4));
				vALMArticulo.setiCveFamilia(rset.getInt(5));
				vALMArticulo.setcDscFamilia(rset.getString(6));
				vALMArticulo.setcDscArticulo(rset.getString(7));
				vALMArticulo.setcDscBreve(rset.getString(8));
				vALMArticulo.setlLote(rset.getInt(9));
				vALMArticulo.setlMaxMin(rset.getInt(10));
				vALMArticulo.setcObservacion(rset.getString(11));
				vALMArticulo.setiCveUniAlm(rset.getInt(12));
				vALMArticulo.setiCveUniSum(rset.getInt(13));
				vALMArticulo.setcDscUniAlm(rset.getString(14));
				vALMArticulo.setcDscUniSum(rset.getString(15));
				vALMArticulo.setICveMeta(rset.getInt(16));
				vALMArticulo.setDUnidSolicita(rset.getFloat(17));
				vALMArticulo.setIAnio(rset.getInt(18));
				vALMArticulo.setICveAlmacen(rset.getInt(19));
				vALMArticulo.setICveSolicSum(rset.getInt(20));
				vALMArticulo.setCDscMeta(rset.getString(21));

				vcALMArticulo.addElement(vALMArticulo);
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
			return vcALMArticulo;
		}
	}

	/*
	 * FindBySumArt2, NO toma en cuanta las Autorizadas
	 */
	public Vector FindBySumArt2(Object Obj, String cWhere, String cOrder,
			String cTipo, int lAutorizada) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArticulo vALMArticulo = (TVALMArticulo) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select ALMArticulo.iCveArticulo,   "
					+ "        ALMArticulo.cCveArticulo,   "
					+ "        ALMArticulo.iCveTpoArticulo,   "
					+ "        ALMTpoArticulo.cDscTpoArticulo,   "
					+ "        ALMArticulo.iCveFamilia,   "
					+ "        ALMFamilia.cDscFamilia,   "
					+ "        ALMArticulo.cDscArticulo,   "
					+ "        ALMArticulo.cDscBreve,   "
					+ "        ALMArticulo.lLote,   "
					+ "        ALMArticulo.lMaxMin,   "
					+ "        ALMArticulo.cObservacion,  "
					+ "        ALMArticulo.iCveUniAlm,   "
					+ "        ALMArticulo.iCveUniSum,   "
					+ "        ALMUnidad.cDscUnidad  as cDscUniAlm, "
					+ "        ALMUnidad2.cDscUnidad as cDscUniSum, "
					+ "        ALMSumArt.iCveMeta, "
					+ "        ALMSumArt.dUnidSolicita , "
					+ "        ALMSumArt.iAnio , "
					+ "        ALMSumArt.iCveAlmacen , "
					+ "        ALMSumArt.iCveSolicSum , "
					+ "        GRLMetaProceso.cDscMeta, "
					+ "        GRLArea.cDscArea, "
					+ "        ALMAlmacen.cDscAlmacen, "
					+ "        (SEGUsuario1.cNombre || ' ' || SEGUsuario1.cApPaterno || ' ' || SEGUsuario1.cApMaterno) as cDscUsuResp, "
					+ "        (SEGUsuario2.cNombre || ' ' || SEGUsuario2.cApPaterno || ' ' || SEGUsuario2.cApMaterno) as cDscUsuSolicita, "
					+ "        GRLUniMed.cDscUniMed, "
					+ "        GRLModulo.cDscModulo, "
					+ "        ALMSolicSumin.dtSolicitud, "
					+ "        GRLAreaModulo.cResponsable "
					+ "        from ALMArticulo   "
					+ "        join ALMTpoArticulo on ALMArticulo.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo   "
					+ "        join ALMFamilia on ALMArticulo.iCveFamilia = ALMFamilia.iCveFamilia and "
					+ "                           ALMArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo "
					+ "        join ALMUnidad on ALMArticulo.iCveUniAlm = ALMUnidad.iCveUnidad   "
					+ "        join ALMUnidad ALMUnidad2 on ALMArticulo.iCveUniSum = ALMUnidad2.iCveUnidad  "
					+ cTipo
					+ " join ALMSumArt on ALMSumArt.iAnio = "
					+ vALMArticulo.getIAnio()
					+ "                           and ALMSumArt.iCveAlmacen = "
					+ vALMArticulo.getICveAlmacen()
					+ "                           and ALMSumArt.iCveSolicSum = "
					+ vALMArticulo.getICveSolicSum()
					+ "                           and ALMSumArt.iCveArticulo = ALMArticulo.iCveArticulo "
					+
					// "                           and ALMSumArt.lAutorizada = "
					// + lAutorizada +
					"   left join GRLMetaProceso on  GRLMetaProceso.iAnio = "
					+ vALMArticulo.getIAnio()
					+ "                           and  GRLMetaProceso.iCveMeta = ALMSumArt.iCveMeta "
					+ "        join ALMSolicSumin on ALMSolicSumin.iAnio = "
					+ vALMArticulo.getIAnio()
					+ "                          and ALMSolicSumin.iCveAlmacen = "
					+ vALMArticulo.getICveAlmacen()
					+ "                          and ALMSolicSumin.iCveSolicSum = "
					+ vALMArticulo.getICveSolicSum()
					+ "        join ALMArtXAlm AXA on AXA.iCveAlmacen = ALMSolicSumin.iCveAlmacen "
					+ "                           and AXA.iCveArticulo = ALMArticulo.iCveArticulo "
					+ "        join GRLArea on GRLArea.iCveArea = ALMSolicSumin.iCveArea "
					+ "        join ALMAlmacen on ALMAlmacen.iCveAlmacen = "
					+ vALMArticulo.getICveAlmacen()
					+ "        join SEGUsuario SEGUsuario1 on SEGUsuario1.iCveUsuario = ALMAlmacen.iCveUsuResp "
					+ "        join SEGUsuario SEGUsuario2 on SEGUsuario2.iCveUsuario = ALMSolicSumin.iCveUsuSolicita "
					+ "        join GRLUniMed on GRLUniMed.iCveUniMed = ALMSolicSumin.iCveUniMed "
					+ "        join GRLModulo on GRLModulo.iCveUniMed = ALMSolicSumin.iCveUniMed and "
					+ "                          GRLModulo.iCveModulo = ALMSolicSumin.iCveModulo "
					+ "   left join GRLAreaModulo on GRLAreaModulo.iCveUniMed = ALMSolicSumin.iCveUniMed and "
					+ "                              GRLAreaModulo.iCveModulo = ALMSolicSumin.iCveModulo and "
					+ "                              GRLAreaModulo.iCveArea   = ALMSolicSumin.iCveArea "
					+ cWhere + cOrder;
			// System.out.print("cSQL---->>  " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArticulo = new TVALMArticulo();
				vALMArticulo.setiCveArticulo(rset.getInt(1));
				vALMArticulo.setcCveArticulo(rset.getString(2));
				vALMArticulo.setiCveTpoArticulo(rset.getInt(3));
				vALMArticulo.setcDscTpoArticulo(rset.getString(4));
				vALMArticulo.setiCveFamilia(rset.getInt(5));
				vALMArticulo.setcDscFamilia(rset.getString(6));
				vALMArticulo.setcDscArticulo(rset.getString(7));
				vALMArticulo.setcDscBreve(rset.getString(8));
				vALMArticulo.setlLote(rset.getInt(9));
				vALMArticulo.setlMaxMin(rset.getInt(10));
				vALMArticulo.setcObservacion(rset.getString(11));
				vALMArticulo.setiCveUniAlm(rset.getInt(12));
				vALMArticulo.setiCveUniSum(rset.getInt(13));
				vALMArticulo.setcDscUniAlm(rset.getString(14));
				vALMArticulo.setcDscUniSum(rset.getString(15));
				vALMArticulo.setICveMeta(rset.getInt(16));
				vALMArticulo.setDUnidSolicita(rset.getFloat(17));
				vALMArticulo.setIAnio(rset.getInt(18));
				vALMArticulo.setICveAlmacen(rset.getInt(19));
				vALMArticulo.setICveSolicSum(rset.getInt(20));
				vALMArticulo.setCDscMeta(rset.getString(21));
				vALMArticulo.setCDscArea(rset.getString(22));
				vALMArticulo.setCDscAlmacen(rset.getString(23));
				vALMArticulo.setCDscUsuResp(rset.getString(24));
				vALMArticulo.setCDscUsuSolicita(rset.getString(25));
				vALMArticulo.setCDscUniMed(rset.getString(26));
				vALMArticulo.setCDscModulo(rset.getString(27));
				vALMArticulo.setDtSolicitud(rset.getDate(28));
				vALMArticulo.setCRespArea(rset.getString(29));
				vcALMArticulo.addElement(vALMArticulo);
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
			return vcALMArticulo;
		}
	}

	public Vector FindByLotes(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMArticulo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMArticulo vALMArticulo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "ALMArticulo.iCveArticulo, "
					+ "ALMArticulo.cCveArticulo, "
					+ "ALMArticulo.iCveTpoArticulo, "
					+ "ALMTpoArticulo.cDscTpoArticulo, "
					+ "ALMArticulo.iCveFamilia, "
					+ "ALMFamilia.cDscFamilia, "
					+ "ALMArticulo.cDscArticulo, "
					+ "ALMArticulo.cDscBreve, "
					+ "ALMArticulo.lLote, "
					+ "ALMArticulo.lMaxMin, "
					+ "ALMArticulo.cObservacion, "
					+ "ALMArticulo.iCveUniAlm, "
					+ "ALMArticulo.iCveUniSum, "
					+ "ALMUnidad.cDscUnidad  as cDscUniAlm, "
					+ "ALMUnidad2.cDscUnidad as cDscUniSum, "
					+ "ALMLote.iCveLote, "
					+ "ALMLote.cLote, "
					+ "ALMLote.dUnidades, "
					+ "ALMLote.dtCaducidad, "
					+ "ALMTpoArticulo.iIDPartida "
					+ "from ALMArticulo   "
					+ "join ALMTpoArticulo on ALMArticulo.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo   "
					+ "join ALMFamilia on ALMArticulo.iCveFamilia = ALMFamilia.iCveFamilia  and "
					+ "                   ALMArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo "
					+ "join ALMUnidad on ALMArticulo.iCveUniAlm = ALMUnidad.iCveUnidad   "
					+ "join ALMUnidad ALMUnidad2 on ALMArticulo.iCveUniSum = ALMUnidad2.iCveUnidad "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArticulo = new TVALMArticulo();
				vALMArticulo.setiCveArticulo(rset.getInt(1));
				vALMArticulo.setcCveArticulo(rset.getString(2));
				vALMArticulo.setiCveTpoArticulo(rset.getInt(3));
				vALMArticulo.setcDscTpoArticulo(rset.getString(4));
				vALMArticulo.setiCveFamilia(rset.getInt(5));
				vALMArticulo.setcDscFamilia(rset.getString(6));
				vALMArticulo.setcDscArticulo(rset.getString(7));
				vALMArticulo.setcDscBreve(rset.getString(8));
				vALMArticulo.setlLote(rset.getInt(9));
				vALMArticulo.setlMaxMin(rset.getInt(10));
				vALMArticulo.setcObservacion(rset.getString(11));
				vALMArticulo.setiCveUniAlm(rset.getInt(12));
				vALMArticulo.setiCveUniSum(rset.getInt(13));
				vALMArticulo.setcDscUniAlm(rset.getString(14));
				vALMArticulo.setcDscUniSum(rset.getString(15));
				vALMArticulo.setiCveLote(rset.getInt(16));
				vALMArticulo.setcLote(rset.getString(17));
				vALMArticulo.setdUnidades(rset.getDouble(18));
				vALMArticulo.setDtCaducidad(rset.getDate(19));
				vALMArticulo.setIPartida(rset.getInt(20));
				vcALMArticulo.addElement(vALMArticulo);
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
			return vcALMArticulo;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVALMArticulo vALMArticulo = (TVALMArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into ALMArticulo(" + "iCveArticulo,"
					+ "iCveTpoArticulo," + "iCveFamilia," + "cCveArticulo,"
					+ "cDscArticulo," + "cDscBreve," + "iCveUniAlm,"
					+ "iCveUniSum," + "lMaxMin," + "cObservacion," + "lLote"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveArticulo) from ALMArticulo";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vALMArticulo.setiCveArticulo(iMax);
			// ******************************************************************
			pstmt.setInt(1, vALMArticulo.getiCveArticulo());
			pstmt.setInt(2, vALMArticulo.getiCveTpoArticulo());
			pstmt.setInt(3, vALMArticulo.getiCveFamilia());
			pstmt.setString(4, vALMArticulo.getcCveArticulo().toUpperCase()
					.trim());
			pstmt.setString(5, vALMArticulo.getcDscArticulo().toUpperCase()
					.trim());
			pstmt.setString(6, vALMArticulo.getcDscBreve().toUpperCase().trim());
			pstmt.setInt(7, vALMArticulo.getiCveUniAlm());
			pstmt.setInt(8, vALMArticulo.getiCveUniSum());
			pstmt.setInt(9, vALMArticulo.getlMaxMin());
			pstmt.setString(10, vALMArticulo.getcObservacion().toUpperCase()
					.trim());
			pstmt.setInt(11, vALMArticulo.getlLote());
			pstmt.executeUpdate();
			cClave = "" + vALMArticulo.getiCveArticulo();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
			TVALMArticulo vALMArticulo = (TVALMArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMArticulo" + " set iCveTpoArticulo= ?, "
					+ "iCveFamilia= ?, " + "cCveArticulo= ?, "
					+ "cDscArticulo= ?, " + "cDscBreve= ?, "
					+ "iCveUniAlm= ?, " + "iCveUniSum= ?, " + "lMaxMin= ?, "
					+ "cObservacion= ?, " + "lLote= ? "
					+ "where iCveArticulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMArticulo.getiCveTpoArticulo());
			pstmt.setInt(2, vALMArticulo.getiCveFamilia());
			pstmt.setString(3, vALMArticulo.getcCveArticulo().toUpperCase()
					.trim());
			pstmt.setString(4, vALMArticulo.getcDscArticulo().toUpperCase()
					.trim());
			pstmt.setString(5, vALMArticulo.getcDscBreve().toUpperCase().trim());
			pstmt.setInt(6, vALMArticulo.getiCveUniAlm());
			pstmt.setInt(7, vALMArticulo.getiCveUniSum());
			pstmt.setInt(8, vALMArticulo.getlMaxMin());
			pstmt.setString(9, vALMArticulo.getcObservacion().toUpperCase()
					.trim());
			pstmt.setInt(10, vALMArticulo.getlLote());
			pstmt.setInt(11, vALMArticulo.getiCveArticulo());
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
			TVALMArticulo vALMArticulo = (TVALMArticulo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMArticulo" + " where iCveArticulo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMArticulo.getiCveArticulo());
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
				warn("delete.closeALMArticulo", ex2);
			}
			return cClave;
		}
	}

	public Vector FindByAllWithMinMax(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcResultado = new Vector();
		Vector vcArticuloExist;
		TVALMArticulo vALMArticulo;
		TVALMArtxAlm vALMArtxAlm;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer("select ");
			conn.setTransactionIsolation(2);
			conn.setReadOnly(true);
			cSQL.append("ALMArticulo.iCveArticulo, ")
					.append("ALMArticulo.cCveArticulo, ")
					.append("ALMArticulo.iCveTpoArticulo, ")
					.append("ALMTpoArticulo.cDscTpoArticulo, ")
					.append("ALMArticulo.iCveFamilia, ")
					.append("ALMFamilia.cDscFamilia, ")
					.append("ALMArticulo.cDscArticulo, ")
					.append("ALMArticulo.cDscBreve, ")
					.append("ALMArticulo.lLote, ")
					.append("ALMArticulo.lMaxMin, ")
					.append("ALMArticulo.cObservacion, ")
					.append("ALMArticulo.iCveUniAlm, ")
					.append("ALMArticulo.iCveUniSum, ")
					.append("ALMUnidad.cDscUnidad  as cDscUniAlm, ")
					.append("ALMUnidad2.cDscUnidad as cDscUniSum, ")
					.append("GRLUniMed.cDscUniMed, ")
					.append("ALMAlmacen.cDscAlmacen, ")
					.append("ALMArtxAlm.iCveAlmacen, ")
					.append("ALMArtxAlm.iCveArticulo, ")
					.append("ALMArtxAlm.dExistencia, ")
					.append("ALMArtxAlm.dMaximo, ")
					.append("ALMArtxAlm.dMinimo ")
					.append("from ALMArticulo ")
					.append(" join ALMTpoArticulo on ALMArticulo.iCveTpoArticulo = ALMTpoArticulo.iCveTpoArticulo   ")
					.append(" join ALMFamilia on ALMArticulo.iCveFamilia = ALMFamilia.iCveFamilia  and ")
					.append("     ALMArticulo.iCveTpoArticulo = ALMFamilia.iCveTpoArticulo ")
					.append(" join ALMUnidad on ALMArticulo.iCveUniAlm = ALMUnidad.iCveUnidad   ")
					.append(" join ALMUnidad ALMUnidad2 on ALMArticulo.iCveUniSum = ALMUnidad2.iCveUnidad ");
			if (!cWhere.equals(""))
				cSQL.append(cWhere);
			if (!cOrderBy.equals(""))
				cSQL.append(cOrderBy);

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMArticulo = new TVALMArticulo();
				vALMArtxAlm = new TVALMArtxAlm();
				vcArticuloExist = new Vector();
				vALMArticulo.setiCveArticulo(rset.getInt(1));
				vALMArticulo.setcCveArticulo(rset.getString(2));
				vALMArticulo.setiCveTpoArticulo(rset.getInt(3));
				vALMArticulo.setcDscTpoArticulo(rset.getString(4));
				vALMArticulo.setiCveFamilia(rset.getInt(5));
				vALMArticulo.setcDscFamilia(rset.getString(6));
				vALMArticulo.setcDscArticulo(rset.getString(7));
				vALMArticulo.setcDscBreve(rset.getString(8));
				vALMArticulo.setlLote(rset.getInt(9));
				vALMArticulo.setlMaxMin(rset.getInt(10));
				vALMArticulo.setcObservacion(rset.getString(11));
				vALMArticulo.setiCveUniAlm(rset.getInt(12));
				vALMArticulo.setiCveUniSum(rset.getInt(13));
				vALMArticulo.setcDscUniAlm(rset.getString(14));
				vALMArticulo.setcDscUniSum(rset.getString(15));
				vALMArticulo.setCDscUniMed(rset.getString(16));
				vALMArticulo.setCDscAlmacen(rset.getString(17));

				vALMArtxAlm.setiCveAlmacen(rset.getInt(18));
				vALMArtxAlm.setiCveArticulo(rset.getInt(19));
				vALMArtxAlm.setdExistencia(rset.getDouble(20));
				vALMArtxAlm.setdMaximo(rset.getDouble(21));
				vALMArtxAlm.setdMinimo(rset.getDouble(22));

				vcArticuloExist.insertElementAt(vALMArticulo, 0);
				vcArticuloExist.insertElementAt(vALMArtxAlm, 1);
				vcResultado.add(vcArticuloExist);
			}
		} catch (Exception ex) {
			warn("FindByAllWithMinMax", ex);
			throw new DAOException("FindByAllWithMinMax");
		} finally {
			try {
				if (rset != null)
					rset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null) {
					if (!conn.isClosed())
						conn.close();
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("FindByAllWithMinMax.close", ex2);
			}
			return vcResultado;
		}
	}
}
