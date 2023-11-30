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
 * Title: Data Acces Object de ALMSolicSumin DAO
 * </p>
 * <p>
 * Description: TD para la tabla ALMSolicSumin
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */

public class TDALMSolicSumin extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDALMSolicSumin() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSolicSumin = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSolicSumin vALMSolicSumin;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select   ALMSolicSumin.iAnio, "
					+ "          ALMSolicSumin.iCveAlmacen, "
					+ "          iCveSolicSum, "
					+ "          ALMSolicSumin.iCveUniMed, "
					+ "          ALMSolicSumin.iCveModulo, "
					+ "          ALMSolicSumin.iCveArea, "
					+ "          iCveUsuSolicita, "
					+ "          iCveUsuAutoriza, "
					+ "          dtSolicitud, "
					+ "          dtSuministro, "
					+ "          lProgramada, "
					+ "          almtpoprioridad.iCvePrioridad, "
					+ "          ALMSolicSumin.iCvePeriodo, "
					+ "          lAutorizada, "
					+ "          lRevisada, "
					+ "          lSuministrada, "
					+ "          grlunimed.cdscunimed, "
					+ "          grlmodulo.cdscmodulo, "
					+ "          grlarea.cdscarea, "
					+ "          almalmacen.cdscalmacen, "
					+ "          (segusuario.cnombre ||  ' ' || segusuario.cappaterno ||  ' ' || segusuario.capmaterno) as cdscususolicita, "
					+ "          (segusuario2.cnombre ||  ' ' || segusuario2.cappaterno ||  ' ' || segusuario2.capmaterno) as cdscusuautoriza, "
					+ "          almtpoprioridad.cdscprioridad, "
					+ "          almperiodo.cdscperiodo "
					+ "          from ALMSolicSumin "
					+ "          left join grlunimed on grlunimed.icveunimed = ALMSolicSumin.icveunimed "
					+ "          left join grlmodulo on grlmodulo.icveunimed = ALMSolicSumin.icveunimed and "
					+ "                                 grlmodulo.icvemodulo = ALMSolicSumin.icvemodulo "
					+ " left join grlareamodulo on grlareamodulo.icveunimed = ALMSolicSumin.icveunimed and "
					+ "                            grlareamodulo.icvemodulo = ALMSolicSumin.icvemodulo and "
					+ "                            grlareamodulo.icvearea = ALMSolicSumin.icvearea "
					+ " left join grlarea on grlarea.icvearea =  GRLAreaModulo.iCveArea "
					+ " left join almalmacen on almalmacen.icveunimed = ALMSolicSumin.icveunimed and  "
					+ "                         almalmacen.icvealmacen = ALMSolicSumin.icvealmacen "
					+ " left join segusuario on segusuario.icveusuario = ALMSolicSumin.icveususolicita "
					+ " left join segusuario segusuario2 on segusuario2.icveusuario = ALMSolicSumin.icveusuautoriza "
					+ " left join almtpoprioridad on almtpoprioridad.icveprioridad = ALMSolicSumin.icveprioridad "
					+ " left join almperiodo on almperiodo.ianio = ALMSolicSumin.ianio and almperiodo.icveperiodo = ALMSolicSumin.icveperiodo "
					+ cFiltro + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSolicSumin = new TVALMSolicSumin();
				vALMSolicSumin.setIAnio(rset.getInt(1));
				vALMSolicSumin.setICveAlmacen(rset.getInt(2));
				vALMSolicSumin.setICveSolicSum(rset.getInt(3));
				vALMSolicSumin.setICveUniMed(rset.getInt(4));
				vALMSolicSumin.setICveModulo(rset.getInt(5));
				vALMSolicSumin.setICveArea(rset.getInt(6));
				vALMSolicSumin.setICveUsuSolicita(rset.getInt(7));
				vALMSolicSumin.setICveUsuAutoriza(rset.getInt(8));
				vALMSolicSumin.setDtSolicitud(rset.getDate(9));
				vALMSolicSumin.setDtSuministro(rset.getDate(10));
				vALMSolicSumin.setLProgramada(rset.getInt(11));
				vALMSolicSumin.setICvePrioridad(rset.getInt(12));
				vALMSolicSumin.setICvePeriodo(rset.getInt(13));
				vALMSolicSumin.setLAutorizada(rset.getInt(14));
				vALMSolicSumin.setLRevisada(rset.getInt(15));
				vALMSolicSumin.setLSuministrada(rset.getInt(16));
				vALMSolicSumin.setCDscUniMed(rset.getString(17));
				vALMSolicSumin.setCDscModulo(rset.getString(18));
				vALMSolicSumin.setCDscArea(rset.getString(19));
				vALMSolicSumin.setCDscAlmacen(rset.getString(20));
				vALMSolicSumin.setCDscUsuSolicita(rset.getString(21));
				vALMSolicSumin.setCDscUsuAutoriza(rset.getString(22));
				vALMSolicSumin.setCDscPrioridad(rset.getString(23));
				vALMSolicSumin.setCDscPeriodo(rset.getString(24));

				vcALMSolicSumin.addElement(vALMSolicSumin);
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
			return vcALMSolicSumin;
		}
	}

	/**
	 * Metodo Find By All2, proceso especial para la pantalla pg070803030,
	 * "CONCENTRADO".
	 */
	public Vector FindByAll2(String cFiltro, String cOrden, Object Obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSolicSumin = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select  sum(x.dunidsolicita), sum(x.dunidautor), "
					+ " x.icvearticulo,x.cdscarticulo, "
					+ " x.cdsctpoarticulo,x.cdscfamilia, "
					+ " x.dexistencia,x.lprogramada,x.lAnalizada "
					+ " from(  "
					+ " select almsumart.icvearticulo,almarticulo.cdscarticulo,  "
					+ " almtpoarticulo.cdscbreve as cdsctpoarticulo,almfamilia.cdscbreve as cdscfamilia,  "
					+ " almsumart.dunidsolicita,almsumart.dunidautor,almartxalm.dexistencia,almsolicsumin.lprogramada, almsumart.lAnalizada "
					+ " from almsolicsumin  "
					+ " join almsumart on almsumart.ianio = almsolicsumin.ianio and  "
					+ "                                   almsumart.icvealmacen = almsolicsumin.icvealmacen and  "
					+ "                                   almsumart.icvesolicsum = almsolicsumin.icvesolicsum  "
					+ " join almarticulo on almarticulo.icvearticulo = almsumart.icvearticulo ";
			if (vALMSolicSumin.getICveTpoArticulo() > 0)
				cSQL += "         and almarticulo.icvetpoarticulo = "
						+ vALMSolicSumin.getICveTpoArticulo();
			if (vALMSolicSumin.getICveFamilia() > 0)
				cSQL += "         and  almarticulo.icvefamilia = "
						+ vALMSolicSumin.getICveFamilia();

			cSQL += " join almtpoarticulo on almtpoarticulo.icvetpoarticulo = almarticulo.icvetpoarticulo "
					+ " join almfamilia on almfamilia.icvetpoarticulo = almarticulo.icvetpoarticulo  "
					+ "                          and almfamilia.icvefamilia = almarticulo.icvefamilia "
					+ " join almartxalm on almartxalm.icvealmacen = almsumart.icvealmacen and "
					+ "                                    almartxalm.icvearticulo = almsumart.icvearticulo "
					+ " where almsolicsumin.lautorizada = 1  and almsolicsumin.lRevisada = 0  and almsolicsumin.lSuministrada = 0 ";
			if (vALMSolicSumin.getIAnio() > 0)
				cSQL += "   and almsolicsumin.ianio       = "
						+ vALMSolicSumin.getIAnio();
			if (vALMSolicSumin.getICvePeriodo() > 0)
				cSQL += "   and almsolicsumin.icveperiodo = "
						+ vALMSolicSumin.getICvePeriodo();
			cSQL += "   and almsolicsumin.lprogramada = "
					+ vALMSolicSumin.getLProgramada();
			if (vALMSolicSumin.getICveSolicSum() > 0)
				cSQL += " and almsolicsumin.iCveSolicSum = "
						+ vALMSolicSumin.getICveSolicSum();

			if (cFiltro.compareTo("") != 0)
				cFiltro = " and " + cFiltro;

			cSQL += cFiltro + " " + cOrden;
			cSQL += ") as x group by  " + " x.icvearticulo,x.cdscarticulo, "
					+ " x.cdsctpoarticulo,x.cdscfamilia, "
					+ " x.dexistencia,x.lprogramada,x.lAnalizada ";
			pstmt = conn.prepareStatement(cSQL);
			System.out.println(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSolicSumin = new TVALMSolicSumin();
				vALMSolicSumin.setDUnidSolicita(rset.getFloat(1));
				vALMSolicSumin.setDUnidAutor(rset.getFloat(2));
				vALMSolicSumin.setICveArticulo(rset.getInt(3));
				vALMSolicSumin.setCDscArticulo(rset.getString(4));
				vALMSolicSumin.setCDscTpoArticulo(rset.getString(5));
				vALMSolicSumin.setCDscFamilia(rset.getString(6));
				vALMSolicSumin.setDExistencia(rset.getFloat(7));
				vALMSolicSumin.setLProgramada(rset.getInt(8));
				vALMSolicSumin.setLAnalizada(rset.getInt(9));
				vcALMSolicSumin.addElement(vALMSolicSumin);
			}
		} catch (Exception ex) {
			warn("FindByAll2", ex);
			throw new DAOException("FindByAll2");
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
				warn("FindByAll2.close", ex2);
			}
			return vcALMSolicSumin;
		}
	}

	/**
	 * Metodo Find By All3, proceso especial para la pantalla pg070803031,
	 * "CONCENTRADO".
	 */
	public Vector FindByAll3(String cFiltro, String cOrden, Object Obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSolicSumin = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select almfamilia.cdscbreve as cdscfamilia, "
					+ "        almtpoarticulo.cdscbreve as cdsctpoarticulo, "
					+ "        almartxalm.dexistencia,  "
					+ "        grlunimed.cdscunimed, "
					+ "        grlmodulo.cdscmodulo, "
					+ "        grlarea.cdscarea, "
					+ "        almsolicsumin.icvesolicsum, "
					+ "        almsumart.icvearticulo,  "
					+ "        almarticulo.cdscbreve as cdscarticulo,    "
					+ "        almsumart.dunidsolicita, "
					+ "        almsumart.lAutorizada, "
					+ "        almsumart.lAnalizada, "
					+ "        almsumart.iAnio, "
					+ "        almsumart.iCveAlmacen, "
					+ "        almsumart.dUnidAutor "
					+ " from almsolicsumin    "
					+ " join almsumart on almsumart.ianio = almsolicsumin.ianio  "
					+ "                       and    almsumart.icvealmacen = almsolicsumin.icvealmacen  "
					+ "                       and    almsumart.icvesolicsum = almsolicsumin.icvesolicsum "
					+ "                       and    almsumart.icvearticulo = "
					+ vALMSolicSumin.getICveArticulo()
					+ " join almarticulo on almarticulo.icvearticulo = almsumart.icvearticulo   ";
			if (vALMSolicSumin.getICveTpoArticulo() > 0)
				cSQL += "         and almarticulo.icvetpoarticulo = "
						+ vALMSolicSumin.getICveTpoArticulo();
			if (vALMSolicSumin.getICveFamilia() > 0)
				cSQL += "         and  almarticulo.icvefamilia = "
						+ vALMSolicSumin.getICveFamilia();
			cSQL += " join almtpoarticulo on almtpoarticulo.icvetpoarticulo = almarticulo.icvetpoarticulo   "
					+ " join almfamilia on almfamilia.icvetpoarticulo = almarticulo.icvetpoarticulo               "
					+ "                          and almfamilia.icvefamilia = almarticulo.icvefamilia "
					+ " join grlunimed on grlunimed.icveunimed = almsolicsumin.icveunimed "
					+ " join grlmodulo on grlmodulo.icveunimed = almsolicsumin.icveunimed "
					+ "                          and grlmodulo.icvemodulo = almsolicsumin.icvemodulo "
					+ " join grlareamodulo on grlareamodulo.icveunimed = ALMSolicSumin.icveunimed and  "
					+ "                                         grlareamodulo.icvemodulo = ALMSolicSumin.icvemodulo and  "
					+ "                                         grlareamodulo.icvearea = ALMSolicSumin.icvearea  "
					+ " join grlarea on grlarea.icvearea =  GRLAreaModulo.iCveArea  "
					+ " join almartxalm on almartxalm.icvealmacen = almsumart.icvealmacen  "
					+ "                          and almartxalm.icvearticulo = almsumart.icvearticulo   "
					+ "where almsolicsumin.lautorizada = 1 and almsolicsumin.lSuministrada = 0  ";
			if (vALMSolicSumin.getIAnio() > 0)
				cSQL += "   and almsolicsumin.ianio       = "
						+ vALMSolicSumin.getIAnio();

			if (vALMSolicSumin.getICvePeriodo() > 0)
				cSQL += "   and almsolicsumin.icveperiodo = "
						+ vALMSolicSumin.getICvePeriodo();

			cSQL += "   and almsolicsumin.lprogramada = "
					+ vALMSolicSumin.getLProgramada();

			if (vALMSolicSumin.getICveSolicSum() > 0)
				cSQL += " and almsolicsumin.iCveSolicSum = "
						+ vALMSolicSumin.getICveSolicSum();

			if (cFiltro.compareTo("") != 0)
				cFiltro = " and " + cFiltro;
			cSQL += " " + cFiltro + " " + cOrden;
			// System.out.println("ALMACEN_____RMB_____" + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSolicSumin = new TVALMSolicSumin();
				vALMSolicSumin.setCDscFamilia(rset.getString(1));
				vALMSolicSumin.setCDscTpoArticulo(rset.getString(2));
				vALMSolicSumin.setDExistencia(rset.getFloat(3));
				vALMSolicSumin.setCDscUniMed(rset.getString(4));
				vALMSolicSumin.setCDscModulo(rset.getString(5));
				vALMSolicSumin.setCDscArea(rset.getString(6));
				vALMSolicSumin.setICveSolicSum(rset.getInt(7));
				vALMSolicSumin.setICveArticulo(rset.getInt(8));
				vALMSolicSumin.setCDscArticulo(rset.getString(9));
				vALMSolicSumin.setDUnidSolicita(rset.getFloat(10));
				vALMSolicSumin.setLAutorizada(rset.getInt(11));
				vALMSolicSumin.setLAnalizada(rset.getInt(12));
				vALMSolicSumin.setIAnio(rset.getInt(13));
				vALMSolicSumin.setICveAlmacen(rset.getInt(14));
				vALMSolicSumin.setDUnidAutor(rset.getFloat(15));
				vcALMSolicSumin.addElement(vALMSolicSumin);
			}
		} catch (Exception ex) {
			warn("FindByAll3", ex);
			throw new DAOException("FindByAll3");
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
				warn("FindByAll3.close", ex2);
			}
			return vcALMSolicSumin;
		}
	}

	/**
	 * Metodo Find By All Custom
	 */
	public Vector FindByAllCustom(String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSolicSumin = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSolicSumin vALMSolicSumin;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select   S.iAnio, " + "          S.iCveAlmacen, "
					+ "          S.iCveSolicSum, " + "          S.iCveUniMed, "
					+ "          S.iCveModulo, " + "          S.iCveArea, "
					+ "          S.iCveUsuSolicita, "
					+ "          S.iCveUsuAutoriza, "
					+ "          S.dtSolicitud, "
					+ "          S.dtSuministro, "
					+ "          S.lProgramada, "
					+ "          S.iCvePrioridad, "
					+ "          S.iCvePeriodo, " + "          S.lAutorizada, "
					+ "          S.lRevisada, " + "          S.lSuministrada "
					+ "          from ALMSolicSumin S " + cFiltro + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSolicSumin = new TVALMSolicSumin();
				vALMSolicSumin.setIAnio(rset.getInt(1));
				vALMSolicSumin.setICveAlmacen(rset.getInt(2));
				vALMSolicSumin.setICveSolicSum(rset.getInt(3));
				vALMSolicSumin.setICveUniMed(rset.getInt(4));
				vALMSolicSumin.setICveModulo(rset.getInt(5));
				vALMSolicSumin.setICveArea(rset.getInt(6));
				vALMSolicSumin.setICveUsuSolicita(rset.getInt(7));
				vALMSolicSumin.setICveUsuAutoriza(rset.getInt(8));
				vALMSolicSumin.setDtSolicitud(rset.getDate(9));
				vALMSolicSumin.setDtSuministro(rset.getDate(10));
				vALMSolicSumin.setLProgramada(rset.getInt(11));
				vALMSolicSumin.setICvePrioridad(rset.getInt(12));
				vALMSolicSumin.setICvePeriodo(rset.getInt(13));
				vALMSolicSumin.setLAutorizada(rset.getInt(14));
				vALMSolicSumin.setLRevisada(rset.getInt(15));
				vALMSolicSumin.setLSuministrada(rset.getInt(16));

				vcALMSolicSumin.addElement(vALMSolicSumin);
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
			return vcALMSolicSumin;
		}
	}

	/*
	 * Metodo Find By All Tablas: ALMSolicSumin, ALMSumArt
	 */
	public Vector FindByAll3(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcALMSolicSumin = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVALMSolicSumin vALMSolicSumin;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "        ALMSolicSumin.iAnio, "
					+ "        ALMSolicSumin.iCveAlmacen, "
					+ "        ALMSolicSumin.iCveSolicSum, "
					+ "        ALMSolicSumin.iCveUniMed, "
					+ "        ALMSolicSumin.iCveModulo, "
					+ "        ALMSolicSumin.iCveArea, "
					+ "        ALMSolicSumin.dtSolicitud, "
					+ "        ALMSolicSumin.dtSuministro, "
					+ "        ALMSolicSumin.lProgramada, "
					+ "        ALMSolicSumin.iCvePrioridad, "
					+ "        ALMSolicSumin.iCvePeriodo, "
					+ "        GRLUniMed.cDscUniMed, "
					+ "        GRLArea.cDscArea, "
					+ "        ALMTpoPrioridad.cDscPrioridad "
					+ "from	ALMSolicSumin "
					+ "        join GRLUniMed on GRLUniMed.iCveUniMed = ALMSolicSumin.iCveUniMed "
					+ "        join GRLArea on GRLArea.iCveArea = ALMSolicSumin.iCveArea "
					+ "        join ALMTpoPrioridad on ALMTpoPrioridad.iCvePrioridad = ALMSolicSumin.iCvePrioridad "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSolicSumin = new TVALMSolicSumin();
				vALMSolicSumin.setIAnio(rset.getInt(1));
				vALMSolicSumin.setICveAlmacen(rset.getInt(2));
				vALMSolicSumin.setICveSolicSum(rset.getInt(3));
				vALMSolicSumin.setICveUniMed(rset.getInt(4));
				vALMSolicSumin.setICveModulo(rset.getInt(5));
				vALMSolicSumin.setICveArea(rset.getInt(6));
				vALMSolicSumin.setDtSolicitud(rset.getDate(7));
				vALMSolicSumin.setDtSuministro(rset.getDate(8));
				vALMSolicSumin.setLProgramada(rset.getInt(9));
				vALMSolicSumin.setICvePrioridad(rset.getInt(10));
				vALMSolicSumin.setICvePeriodo(rset.getInt(11));
				vALMSolicSumin.setCDscUniMed(rset.getString(12));
				vALMSolicSumin.setCDscArea(rset.getString(13));
				vALMSolicSumin.setCDscPrioridad(rset.getString(14));

				vcALMSolicSumin.addElement(vALMSolicSumin);
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
			return vcALMSolicSumin;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		ResultSet rset = null;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) obj;
			vALMSolicSumin.setLAutorizada(0);
			vALMSolicSumin.setLRevisada(0);
			vALMSolicSumin.setLSuministrada(0);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select max(iCveSolicSum) from ALMSolicSumin where iAnio = ? and iCveAlmacen = ?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMSolicSumin.getIAnio());
			pstmt.setInt(2, vALMSolicSumin.getICveAlmacen());

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vALMSolicSumin.setICveSolicSum(new Integer(rset.getInt(1))
						.intValue() + 1);
			}

			cSQL = "insert into ALMSolicSumin(" + "iAnio," + "iCveAlmacen,"
					+ "iCveSolicSum," + "iCveUniMed," + "iCveModulo,"
					+ "iCveArea," + "iCveUsuSolicita," + "iCveUsuAutoriza,"
					+ "dtSolicitud," + "dtSuministro," + "lProgramada,"
					+ "iCvePrioridad," + "iCvePeriodo," + "lAutorizada,"
					+ "lRevisada," + "lSuministrada"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt.close();
			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vALMSolicSumin.getIAnio());
			pstmt.setInt(2, vALMSolicSumin.getICveAlmacen());
			pstmt.setInt(3, vALMSolicSumin.getICveSolicSum());
			pstmt.setInt(4, vALMSolicSumin.getICveUniMed());
			pstmt.setInt(5, vALMSolicSumin.getICveModulo());
			pstmt.setInt(6, vALMSolicSumin.getICveArea());
			pstmt.setInt(7, vALMSolicSumin.getICveUsuSolicita());
			pstmt.setInt(8, vALMSolicSumin.getICveUsuAutoriza());
			pstmt.setDate(9, vALMSolicSumin.getDtSolicitud());
			pstmt.setDate(10, vALMSolicSumin.getDtSuministro());
			pstmt.setInt(11, vALMSolicSumin.getLProgramada());
			pstmt.setInt(12, vALMSolicSumin.getICvePrioridad());
			pstmt.setInt(13, vALMSolicSumin.getICvePeriodo());
			pstmt.setInt(14, vALMSolicSumin.getLAutorizada());
			pstmt.setInt(15, vALMSolicSumin.getLRevisada());
			pstmt.setInt(16, vALMSolicSumin.getLSuministrada());
			pstmt.executeUpdate();
			cClave = "" + vALMSolicSumin.getICveSolicSum();
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
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMSolicSumin" + " set iCveUniMed= ?, "
					+ "iCveModulo= ?, " + "iCveArea= ?, "
					+ "iCveUsuSolicita= ?, " + "iCveUsuAutoriza= ?, "
					+ "dtSolicitud= ?, " + "dtSuministro= ?, "
					+ "lProgramada= ?, " + "iCvePrioridad= ?, "
					+ "iCvePeriodo= ?, " + "lAutorizada= ?, "
					+ "lRevisada= ?, " + "lSuministrada= ? "
					+ "where iAnio = ? " + "and iCveAlmacen = ?"
					+ " and iCveSolicSum = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMSolicSumin.getICveUniMed());
			pstmt.setInt(2, vALMSolicSumin.getICveModulo());
			pstmt.setInt(3, vALMSolicSumin.getICveArea());
			pstmt.setInt(4, vALMSolicSumin.getICveUsuSolicita());
			pstmt.setInt(5, vALMSolicSumin.getICveUsuAutoriza());
			pstmt.setDate(6, vALMSolicSumin.getDtSolicitud());
			pstmt.setDate(7, vALMSolicSumin.getDtSuministro());
			pstmt.setInt(8, vALMSolicSumin.getLProgramada());
			pstmt.setInt(9, vALMSolicSumin.getICvePrioridad());
			pstmt.setInt(10, vALMSolicSumin.getICvePeriodo());
			pstmt.setInt(11, vALMSolicSumin.getLAutorizada());
			pstmt.setInt(12, vALMSolicSumin.getLRevisada());
			pstmt.setInt(13, vALMSolicSumin.getLSuministrada());
			pstmt.setInt(14, vALMSolicSumin.getIAnio());
			pstmt.setInt(15, vALMSolicSumin.getICveAlmacen());
			pstmt.setInt(16, vALMSolicSumin.getICveSolicSum());
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
	 * Metodo update2, solo para actualizar lAutorizada = 1 e iCveUsuAutoriza =
	 * Usuario de la Session.
	 */
	public Object update2(Connection conGral, Object obj) throws DAOException {
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
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update ALMSolicSumin       "
					+ "    set lAutorizada= ?,     "
					+ "        iCveUsuAutoriza = ? "
					+ "  where iAnio = ?           "
					+ "    and iCveAlmacen = ?     "
					+ "    and iCveSolicSum = ?    ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, vALMSolicSumin.getICveUsuAutoriza());
			pstmt.setInt(3, vALMSolicSumin.getIAnio());
			pstmt.setInt(4, vALMSolicSumin.getICveAlmacen());
			pstmt.setInt(5, vALMSolicSumin.getICveSolicSum());
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
				warn("update2", ex1);
			}
			warn("update2", ex);
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
				warn("update2.close", ex2);
			}
			return cClave;
		}
	}

	public Object updSumistrada(Connection conGral, Object obj)
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
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update ALMSolicSumin" + " set dtSuministro  = ? ,"
					+ "     lSuministrada = ?  " + " where iAnio = ? "
					+ " and iCveAlmacen = ?" + " and iCveSolicSum = ?";
			pstmt = conn.prepareStatement(cSQL);

			if (vALMSolicSumin.getDtSuministro() == null)
				pstmt.setNull(1, Types.DATE);
			else
				pstmt.setDate(1, vALMSolicSumin.getDtSuministro());

			if (new Integer(vALMSolicSumin.getLSuministrada()) == null)
				pstmt.setNull(2, Types.INTEGER);
			else
				pstmt.setInt(2, vALMSolicSumin.getLSuministrada());

			pstmt.setInt(3, vALMSolicSumin.getIAnio());
			pstmt.setInt(4, vALMSolicSumin.getICveAlmacen());
			pstmt.setInt(5, vALMSolicSumin.getICveSolicSum());
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

	public Object updRevisada(Connection conGral, Object obj)
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
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update ALMSolicSumin     " + "    set lRevisada    = ?  "
					+ "  where iAnio        = ?  "
					+ "    and iCveAlmacen  = ?  "
					+ "    and iCveSolicSum = ?  ";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, 1);
			pstmt.setInt(2, vALMSolicSumin.getIAnio());
			pstmt.setInt(3, vALMSolicSumin.getICveAlmacen());
			pstmt.setInt(4, vALMSolicSumin.getICveSolicSum());
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
			TVALMSolicSumin vALMSolicSumin = (TVALMSolicSumin) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMSolicSumin" + " where iAnio = ?"
					+ " and iCveAlmacen = ?" + " and iCveSolicSum = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMSolicSumin.getIAnio());
			pstmt.setInt(2, vALMSolicSumin.getICveAlmacen());
			pstmt.setInt(3, vALMSolicSumin.getICveSolicSum());
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
				warn("delete.closeALMSolicSumin", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo buscaSolicitud
	 */
	public Vector buscaSolicitud(HashMap hm, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		Vector vRegresa = new Vector();
		TVALMSolicSumin vALMSolicSumin;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			String cSQL = " SELECT ss.iCveSolicSum, ss.dtSolicitud, ss.dtSuministro, ss.iCveModulo, ss.iCveArea, "
					+ " ss.iCveUsuSolicita, a.iCveTpoArticulo, a.iCveFamilia, sa.iCveArticulo, um.cDscUniMed, "
					+ " sa.dUnidSolicita, sa.dUnidAutor, p.cDScPeriodo, mp.cDscMeta, u.cDscUnidad, "
					+ " grla.cDscArea, ta.cDscBreve AS cDscTpoArticulo, a.cDscBreve AS cDscArticulo, "
					+ " f.cDscBreve AS cDscFamilia, m.cDscModulo, s.cNombre, s.cApPaterno, s.cApMaterno "
					+ " FROM ALMSolicSumin ss "
					+ " JOIN ALMPeriodo p ON p.iAnio = ss.iAnio "
					+ " AND p.iCvePeriodo = ss.iCvePeriodo "
					+ " JOIN ALMSumArt sa ON sa.iAnio = ss.iAnio "
					+ " AND sa.iCveAlmacen = ss.iCveAlmacen "
					+ " AND sa.iCveSolicSum = ss.iCveSolicSum "
					+ " JOIN GRLMetaProceso mp ON mp.iAnio = sa.iAnio "
					+ " AND mp.iCveMeta = sa.iCveMeta "
					+ " JOIN ALMArticulo a ON a.iCveArticulo = sa.iCveArticulo "
					+ " JOIN ALMUnidad u ON u.iCveUnidad = a.iCveUniSum "
					+ " JOIN GRLArea grla ON grla.iCveArea = ss.iCveArea "
					+ " JOIN GRLModulo m ON m.iCveModulo = ss.iCveModulo "
					+ " AND m.iCveUniMed = ss.iCveUniMed "
					+ " JOIN GRLUniMed um ON um.iCveUniMed = ss.iCveUniMed "
					+ " JOIN SEGUSUARIO s ON s.iCveUsuario = ss.iCveUsuSolicita "
					+ " JOIN ALMTpoArticulo ta ON ta.iCveTpoArticulo = a.iCveTpoArticulo "
					+ " JOIN ALMFamilia f ON f.iCveTpoArticulo = a.iCveTpoArticulo "
					+ " AND f.iCveFamilia = a.iCveFamilia "
					+ (hm.get("iAnio") != null ? " WHERE sa.iAnio = "
							+ hm.get("iAnio") : "")
					+ (hm.get("iCveUniMed") != null ? " AND ss.iCveUniMed = "
							+ hm.get("iCveUniMed") : "")
					+ (hm.get("iCveModulo") != null ? " AND ss.iCveModulo = "
							+ hm.get("iCveModulo") : "")
					+ (hm.get("iCveArea") != null ? " AND ss.iCveArea = "
							+ hm.get("iCveArea") : "")
					+ (hm.get("iCvePeriodo") != null ? " AND ss.iCvePeriodo = "
							+ hm.get("iCvePeriodo") : "")
					+ (hm.get("iCveTpoArticulo") != null ? " AND a.iCveTpoArticulo = "
							+ hm.get("iCveTpoArticulo")
							: "")
					+ (hm.get("iCveFamilia") != null ? " AND a.iCveFamilia = "
							+ hm.get("iCveFamilia") : "")
					+ (hm.get("iCveArticulo") != null ? " AND a.iCveArticulo = "
							+ hm.get("iCveArticulo")
							: "") + " " + cOrden;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				vALMSolicSumin = new TVALMSolicSumin();
				vALMSolicSumin.setICveSolicSum(rset.getInt("iCveSolicSum"));
				vALMSolicSumin.setDtSolicitud(rset.getDate("dtSolicitud"));
				vALMSolicSumin.setDtSuministro(rset.getDate("dtSuministro"));
				vALMSolicSumin.setICveModulo(rset.getInt("iCveModulo"));
				vALMSolicSumin.setICveArea(rset.getInt("iCveArea"));
				vALMSolicSumin.setICveUsuSolicita(rset
						.getInt("iCveUsuSolicita"));
				vALMSolicSumin.setICveTpoArticulo(rset
						.getInt("iCveTpoArticulo"));
				vALMSolicSumin.setICveFamilia(rset.getInt("iCveFamilia"));
				vALMSolicSumin.setICveArticulo(rset.getInt("iCveArticulo"));
				vALMSolicSumin.setDUnidSolicita(rset.getFloat("dUnidSolicita"));
				vALMSolicSumin.setDUnidAutor(rset.getFloat("dUnidAutor"));
				vALMSolicSumin.setCDscPeriodo(rset.getString("cDScPeriodo"));
				vALMSolicSumin.setCDscMeta(rset.getString("cDscMeta"));
				vALMSolicSumin.setCDscUnidad(rset.getString("cDscUnidad"));
				vALMSolicSumin.setCDscArea(rset.getString("cDscArea"));
				vALMSolicSumin.setCDscTpoArticulo(rset
						.getString("cDscTpoArticulo"));
				vALMSolicSumin.setCDscArticulo(rset.getString("cDscArticulo"));
				vALMSolicSumin.setCDscFamilia(rset.getString("cDscFamilia"));
				vALMSolicSumin.setCDscModulo(rset.getString("cDscModulo"));
				vALMSolicSumin.setCDscUniMed(rset.getString("cDscUniMed"));
				vALMSolicSumin.setCNombre(rset.getString("cNombre"));
				vALMSolicSumin.setCApPaterno(rset.getString("cApPaterno"));
				vALMSolicSumin.setCApMaterno(rset.getString("cApMaterno"));
				vRegresa.addElement(vALMSolicSumin);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return vRegresa;
		}
	}

}
