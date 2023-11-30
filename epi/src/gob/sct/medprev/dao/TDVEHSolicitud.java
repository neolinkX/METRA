package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.TFechas;

/**
 * <p>
 * Title: Data Acces Object de VEHSolicitud DAO
 * </p>
 * <p>
 * Description: Control de Veh�culos - Solicitud
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */

public class TDVEHSolicitud extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iCveProceso = Integer.parseInt(VParametros
			.getPropEspecifica("CtrlVeh")); // 7

	public TDVEHSolicitud() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSolicitud = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveUniMed," + "iCveSolicitud,"
					+ "dtRegistro," + "dtSolicitud," + "iCveUsuSolic,"
					+ "iCveModulo," + "iCveArea," + "iCveMotivo," + "cDestino,"
					+ "cLicencia," + "dtVenceLic," + "iTmpAsignado,"
					+ "iCveTpoVehiculo," + "dtAsignado," + "iCveUsuAsigna,"
					+ "lAsignado," + "dtEntrega," + "dtCancelacion,"
					+ "iCveVehiculo," + "iKmInicial," + "iKmFinal,"
					+ "lLicPermanente" + " from VEHSolicitud " + cCondicion
					+ "order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSolicitud = new TVVEHSolicitud();
				vVEHSolicitud.setIAnio(rset.getInt(1));
				vVEHSolicitud.setICveUniMed(rset.getInt(2));
				vVEHSolicitud.setICveSolicitud(rset.getInt(3));
				vVEHSolicitud.setDtRegistro(rset.getDate(4));
				vVEHSolicitud.setDtSolicitud(rset.getDate(5));
				vVEHSolicitud.setICveUsuSolic(rset.getInt(6));
				vVEHSolicitud.setICveModulo(rset.getInt(7));
				vVEHSolicitud.setICveArea(rset.getInt(8));
				vVEHSolicitud.setICveMotivo(rset.getInt(9));
				vVEHSolicitud.setCDestino(rset.getString(10));
				vVEHSolicitud.setCLicencia(rset.getString(11));
				vVEHSolicitud.setDtVenceLic(rset.getDate(12));
				vVEHSolicitud.setITmpAsignado(rset.getInt(13));
				vVEHSolicitud.setICveTpoVehiculo(rset.getInt(14));
				vVEHSolicitud.setDtAsignado(rset.getDate(15));
				vVEHSolicitud.setICveUsuAsigna(rset.getInt(16));
				vVEHSolicitud.setLAsignado(rset.getInt(17));
				vVEHSolicitud.setDtEntrega(rset.getDate(18));
				vVEHSolicitud.setDtCancelacion(rset.getDate(19));
				vVEHSolicitud.setICveVehiculo(rset.getInt(20));
				vVEHSolicitud.setIKmInicial(rset.getInt(21));
				vVEHSolicitud.setIKmFinal(rset.getInt(22));
				vVEHSolicitud.setLLicPermanente(rset.getInt(23));
				vcVEHSolicitud.addElement(vVEHSolicitud);
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
			return vcVEHSolicitud;
		}
	}

	/**
	 * Solicitudes Vigentes
	 */
	public Vector FindSolicXUsr2(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSolicitud = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "  VEHSolicitud.iAnio, "
					+ "  VEHSolicitud.iCveSolicitud, "
					+ "  VEHSolicitud.dtSolicitud, "
					+ "  VEHSolicitud.tsSolicitud, "
					+ "  VEHSolicitud.iCveUsuSolic, "
					+ "  VEHSolicitud.iCveUniMed, "
					+ "  VEHSolicitud.iCveModulo, "
					+ "  VEHSolicitud.iCveArea, "
					+ "  VEHSolicitud.iCveTpoVehiculo, "
					+ "  VEHSolicitud.iCveUsuAsigna, "
					+ "  VEHSolicitud.iCveVehiculo, "
					+ "  GRLUniMed.cDscUniMed, "
					+ "  GRLModulo.cDscModulo, "
					+ "  GRLArea.cDscArea, "
					+ "  VEHTpoVehiculo.cDscBreve, "
					+ " (SEGUsuario.cNombre || ' ' || SEGUsuario.cApPaterno || ' ' || SEGUsuario.cApMaterno) as cDscUsuSolic, "
					+ "  GRLMotivo.iCveMotivo, "
					+ "  GRLMotivo.cDscMotivo, "
					+ " (SEGUsuario2.cNombre ||  ' ' || SEGUsuario2.cApPaterno ||  ' ' || SEGUsuario2.cApMaterno) as cDscUsuAsigna, "
					+ "  VEHSolicitud.dtRegistro, "
					+ "  VEHSolicitud.cLicencia, "
					+ "  VEHSolicitud.dtVenceLic, "
					+ "  VEHSolicitud.cDestino, "
					+ "  VEHSolicitud.iTmpAsignado, "
					+ "  VEHSolicitud.lLicPermanente, "
					+ "  VEHVehiculo.iCveTpoVehiculo,"
					+ "  VEHVehiculo.iCveMarca,"
					+ "  VEHVehiculo.iCveModelo, "
					+ " (SEGUsuario3.cNombre ||  ' ' || SEGUsuario3.cApPaterno ||  ' ' || SEGUsuario3.cApMaterno) as cDscUsuConductor, "
					+ "  SEGUsuario.cNombre, SEGUsuario.cApPaterno, SEGUsuario.cApMaterno ,"
					+ " VEHSolicitud.iCveUsuConductor, "
					+ " VEHSolicitud.dtCancelacion "
					+ "  from VEHSolicitud "
					+ "  left  join GRLUniMed on GRLUniMed.iCveUniMed = VEHSolicitud.iCveUniMed "
					+ "  left  join GRLModulo on GRLModulo.iCveUniMed = VEHSolicitud.iCveUniMed "
					+ "                      and GRLModulo.iCveModulo = VEHSolicitud.iCveModulo "
					+ "  left  join GRLAreaModulo on GRLAreaModulo.iCveUniMed = VEHSolicitud.iCveUniMed AND "
					+ "                              GRLAreaModulo.iCveModulo = VEHSolicitud.iCveModulo AND "
					+ "                              GRLAreaModulo.iCveArea = VEHSolicitud.iCveArea "
					+ "  left join GRLArea on GRLArea.iCveArea = GRLAreaModulo.iCveArea "
					+ "  left join VEHTpoVehiculo on VEHTpoVehiculo.iCveTpoVehiculo = VEHSolicitud.iCveTpoVehiculo "
					+ "  left join SEGUsuario on SEGUsuario.iCveUsuario = VEHSolicitud.iCveUsuSolic "
					+ "  left join GRLMotivo on GRLMotivo.iCveProceso = "
					+ iCveProceso
					+ "                     AND GRLMotivo.iCveMotivo = VEHSolicitud.iCveMotivo "
					+ "  left join SEGUsuario SEGUsuario2 on SEGUsuario2.iCveUsuario = VEHSolicitud.iCveUsuAsigna "
					+ "  left join VEHVehiculo on VEHVehiculo.iCveVehiculo = VEHSolicitud.iCveVehiculo "
					+ "  left join SEGUsuario SEGUsuario3 on SEGUsuario3.iCveUsuario = VEHSolicitud.iCveUsuConductor "
					+ cWhere + cOrderBy;
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSolicitud = new TVVEHSolicitud();

				vVEHSolicitud.setIAnio(rset.getInt(1));
				vVEHSolicitud.setICveSolicitud(rset.getInt(2));
				vVEHSolicitud.setDtSolicitud(rset.getDate(3));
				vVEHSolicitud.setTsSolicitud(rset.getTimestamp(4));
				vVEHSolicitud.setICveUsuSolic(rset.getInt(5));
				vVEHSolicitud.setICveUniMed(rset.getInt(6));
				vVEHSolicitud.setICveModulo(rset.getInt(7));
				vVEHSolicitud.setICveArea(rset.getInt(8));
				vVEHSolicitud.setICveTpoVehiculo(rset.getInt(9));
				vVEHSolicitud.setICveUsuAsigna(rset.getInt(10));
				vVEHSolicitud.setICveVehiculo(rset.getInt(11));
				vVEHSolicitud.setCDscUniMed(rset.getString(12));
				vVEHSolicitud.setCDscModulo(rset.getString(13));
				vVEHSolicitud.setCDscArea(rset.getString(14));
				vVEHSolicitud.setCDscBreveTpoVeh(rset.getString(15));
				vVEHSolicitud.setCDscUsuSolic(rset.getString(16));
				vVEHSolicitud.setICveMotivo(rset.getInt(17));
				vVEHSolicitud.setCDscMotivo(rset.getString(18));
				vVEHSolicitud.setCDscUsuAsigna(rset.getString(19));
				vVEHSolicitud.setDtRegistro(rset.getDate(20));
				vVEHSolicitud.setCLicencia(rset.getString(21));
				vVEHSolicitud.setDtVenceLic(rset.getDate(22));
				vVEHSolicitud.setCDestino(rset.getString(23));
				vVEHSolicitud.setITmpAsignado(rset.getInt(24));
				vVEHSolicitud.setLLicPermanente(rset.getInt(25));
				vVEHSolicitud.setICveTpoVehiculoVEH(rset.getInt(26));
				vVEHSolicitud.setICveMarca(rset.getInt(27));
				vVEHSolicitud.setICveModelo(rset.getInt(28));
				vVEHSolicitud.setCDscUsuConductor(rset.getString(29));
				vVEHSolicitud.setCNombre(rset.getString(30));
				vVEHSolicitud.setCApPaterno(rset.getString(31));
				vVEHSolicitud.setCApMaterno(rset.getString(32));
				vVEHSolicitud.setICveUsuConductor(rset.getInt(33));
				vVEHSolicitud.setDtCancelacion(rset.getDate(34));
				vcVEHSolicitud.addElement(vVEHSolicitud);
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
				warn("FindSolicXUsr.close", ex2);
			}
			return vcVEHSolicitud;
		}
	}

	/**
	 * Solicitudes Vigentes
	 */
	public Vector FindSolicVig(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSolicitud = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "  VEHSolicitud.iAnio, "
					+ "  VEHSolicitud.iCveSolicitud, "
					+ "  VEHSolicitud.dtSolicitud, "
					+ "  VEHSolicitud.tsSolicitud, "
					+ "  VEHSolicitud.iCveUsuSolic, "
					+ "  VEHSolicitud.iCveUniMed, "
					+ "  VEHSolicitud.iCveModulo, "
					+ "  VEHSolicitud.iCveArea, "
					+ "  VEHSolicitud.iCveTpoVehiculo, "
					+ "  VEHSolicitud.iCveUsuAsigna, "
					+ "  VEHSolicitud.iCveVehiculo, "
					+ "  GRLUniMed.cDscUniMed, "
					+ "  GRLModulo.cDscModulo, "
					+ "  GRLArea.cDscArea, "
					+ "  VEHTpoVehiculo.cDscBreve, "
					+ "  VEHEtapaXSolic.iCveEtapaSolic, "
					+ " (SEGUsuario.cNombre || ' ' || SEGUsuario.cApPaterno || ' ' || SEGUsuario.cApMaterno) as cDscUsuSolic, "
					+ "  GRLMotivo.iCveMotivo, "
					+ "  GRLMotivo.cDscMotivo, "
					+ " (SEGUsuario2.cNombre ||  ' ' || SEGUsuario2.cApPaterno ||  ' ' || SEGUsuario2.cApMaterno) as cDscUsuAsigna, "
					+ "  VEHSolicitud.dtRegistro, "
					+ "  VEHSolicitud.cLicencia, "
					+ "  VEHSolicitud.dtVenceLic, "
					+ "  VEHSolicitud.cDestino, "
					+ "  VEHSolicitud.iTmpAsignado, "
					+ "  VEHSolicitud.lLicPermanente, "
					+ "  VEHVehiculo.iCveTpoVehiculo,"
					+ "  VEHVehiculo.iCveMarca,"
					+ "  VEHVehiculo.iCveModelo, "
					+ " (SEGUsuario3.cNombre ||  ' ' || SEGUsuario3.cApPaterno ||  ' ' || SEGUsuario3.cApMaterno) as cDscUsuConductor, "
					+ "  SEGUsuario.cNombre, SEGUsuario.cApPaterno, SEGUsuario.cApMaterno "
					+ "  from VEHSolicitud "
					+ "  left  join GRLUniMed on GRLUniMed.iCveUniMed = VEHSolicitud.iCveUniMed "
					+ "  left  join GRLModulo on GRLModulo.iCveUniMed = VEHSolicitud.iCveUniMed "
					+ "                      and GRLModulo.iCveModulo = VEHSolicitud.iCveModulo "
					+ "  left  join GRLAreaModulo on GRLAreaModulo.iCveUniMed = VEHSolicitud.iCveUniMed AND "
					+ "                              GRLAreaModulo.iCveModulo = VEHSolicitud.iCveModulo AND "
					+ "                              GRLAreaModulo.iCveArea = VEHSolicitud.iCveArea "
					+ "  left join GRLArea on GRLArea.iCveArea = GRLAreaModulo.iCveArea "
					+ "  left join VEHTpoVehiculo on VEHTpoVehiculo.iCveTpoVehiculo = VEHSolicitud.iCveTpoVehiculo "
					+ "  left  join VEHEtapaXSolic on VEHEtapaXSolic.iAnio = VEHSolicitud.iAnio "
					+ "                           and VEHEtapaXSolic.iCveUniMed = VEHSolicitud.iCveUniMed "
					+ "                           and VEHEtapaXSolic.iCveSolicitud = VEHSolicitud.iCveSolicitud "
					+ "  left join SEGUsuario on SEGUsuario.iCveUsuario = VEHSolicitud.iCveUsuSolic "
					+ "  left join GRLMotivo on GRLMotivo.iCveProceso = "
					+ iCveProceso
					+ "                     AND GRLMotivo.iCveMotivo = VEHSolicitud.iCveMotivo "
					+ "  left join SEGUsuario SEGUsuario2 on SEGUsuario2.iCveUsuario = VEHSolicitud.iCveUsuAsigna "
					+ "  left join VEHVehiculo on VEHVehiculo.iCveVehiculo = VEHSolicitud.iCveVehiculo "
					+ "  left join SEGUsuario SEGUsuario3 on SEGUsuario3.iCveUsuario = VEHSolicitud.iCveUsuConductor "
					+ cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSolicitud = new TVVEHSolicitud();

				vVEHSolicitud.setIAnio(rset.getInt(1));
				vVEHSolicitud.setICveSolicitud(rset.getInt(2));
				vVEHSolicitud.setDtSolicitud(rset.getDate(3));
				vVEHSolicitud.setTsSolicitud(rset.getTimestamp(4));
				vVEHSolicitud.setICveUsuSolic(rset.getInt(5));
				vVEHSolicitud.setICveUniMed(rset.getInt(6));
				vVEHSolicitud.setICveModulo(rset.getInt(7));
				vVEHSolicitud.setICveArea(rset.getInt(8));
				vVEHSolicitud.setICveTpoVehiculo(rset.getInt(9));
				vVEHSolicitud.setICveUsuAsigna(rset.getInt(10));
				vVEHSolicitud.setICveVehiculo(rset.getInt(11));
				vVEHSolicitud.setCDscUniMed(rset.getString(12));
				vVEHSolicitud.setCDscModulo(rset.getString(13));
				vVEHSolicitud.setCDscArea(rset.getString(14));
				vVEHSolicitud.setCDscBreveTpoVeh(rset.getString(15));
				vVEHSolicitud.setICveEtapaSolic(rset.getInt(16));
				vVEHSolicitud.setCDscUsuSolic(rset.getString(17));
				vVEHSolicitud.setICveMotivo(rset.getInt(18));
				vVEHSolicitud.setCDscMotivo(rset.getString(19));
				vVEHSolicitud.setCDscUsuAsigna(rset.getString(20));
				vVEHSolicitud.setDtRegistro(rset.getDate(21));
				vVEHSolicitud.setCLicencia(rset.getString(22));
				vVEHSolicitud.setDtVenceLic(rset.getDate(23));
				vVEHSolicitud.setCDestino(rset.getString(24));
				vVEHSolicitud.setITmpAsignado(rset.getInt(25));
				vVEHSolicitud.setLLicPermanente(rset.getInt(26));
				vVEHSolicitud.setICveTpoVehiculoVEH(rset.getInt(27));
				vVEHSolicitud.setICveMarca(rset.getInt(28));
				vVEHSolicitud.setICveModelo(rset.getInt(29));
				vVEHSolicitud.setCDscUsuConductor(rset.getString(30));
				vVEHSolicitud.setCNombre(rset.getString(31));
				vVEHSolicitud.setCApPaterno(rset.getString(32));
				vVEHSolicitud.setCApMaterno(rset.getString(33));
				vcVEHSolicitud.addElement(vVEHSolicitud);
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
			return vcVEHSolicitud;
		}
	}

	/**
	 * Encuentra las Solicitudes Vigentes
	 */
	public Vector FindSolicVig2(String cWhere, String cOrderBy,
			String cVEHEtapaSolFin) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSolicitud = new Vector();
		try {
			TVVEHEtapaXSolic vVEHEtapaXSolic = new TVVEHEtapaXSolic();
			TDVEHEtapaXSolic dVEHEtapaXSolic = new TDVEHEtapaXSolic();
			Vector vcVEHEtapaXSolic = new Vector();

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "  VEHSolicitud.iAnio, "
					+ "  VEHSolicitud.iCveSolicitud, "
					+ "  VEHSolicitud.dtSolicitud, "
					+ "  VEHSolicitud.tsSolicitud, "
					+ "  VEHSolicitud.iCveUsuSolic, "
					+ "  VEHSolicitud.iCveUniMed, "
					+ "  VEHSolicitud.iCveModulo, "
					+ "  VEHSolicitud.iCveArea, "
					+ "  VEHSolicitud.iCveTpoVehiculo, "
					+ "  VEHSolicitud.iCveUsuAsigna, "
					+ "  VEHSolicitud.iCveVehiculo, "
					+ "  GRLUniMed.cDscUniMed, "
					+ "  GRLModulo.cDscModulo, "
					+ "  GRLArea.cDscArea, "
					+ "  VEHTpoVehiculo.cDscBreve, "
					+ "  SEGUsuario.cNombre, "
					+ "  SEGUsuario.cApPaterno, "
					+ "  SEGUsuario.cApMaterno, "
					+ "  VEHSolicitud.dtEntrega "
					+ "from VEHSolicitud "
					+ "  join (GRLAreaModulo "
					+ "    join GRLModulo on GRLModulo.iCveUniMed = GRLAreaModulo.iCveUniMed "
					+ "                  and GRLModulo.iCveModulo = GRLAreaModulo.iCveModulo "
					+ "    join GRLUniMed on GRLUniMed.iCveUniMed = GRLAreaModulo.iCveUniMed "
					+ "    join GRLArea on GRLArea.iCveArea = GRLAreaModulo.iCveArea "
					+ "    ) on GRLAreaModulo.iCveUniMed = VEHSolicitud.iCveUniMed "
					+ "    and GRLAreaModulo.iCveModulo = VEHSolicitud.iCveModulo "
					+ "    and GRLAreaModulo.iCveArea = VEHSolicitud.iCveArea "
					+ "  left join VEHTpoVehiculo on VEHTpoVehiculo.iCveTpoVehiculo = VEHSolicitud.iCveTpoVehiculo "
					+ "  join SEGUsuario on SEGUsuario.iCveUsuario = VEHSolicitud.iCveUsuSolic "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSolicitud = new TVVEHSolicitud();

				vVEHSolicitud.setIAnio(rset.getInt(1));
				vVEHSolicitud.setICveSolicitud(rset.getInt(2));
				vVEHSolicitud.setDtSolicitud(rset.getDate(3));
				vVEHSolicitud.setTsSolicitud(rset.getTimestamp(4));
				vVEHSolicitud.setICveUsuSolic(rset.getInt(5));
				vVEHSolicitud.setICveUniMed(rset.getInt(6));
				vVEHSolicitud.setICveModulo(rset.getInt(7));
				vVEHSolicitud.setICveArea(rset.getInt(8));
				vVEHSolicitud.setICveTpoVehiculo(rset.getInt(9));
				vVEHSolicitud.setICveUsuAsigna(rset.getInt(10));
				vVEHSolicitud.setICveVehiculo(rset.getInt(11));
				vVEHSolicitud.setCDscUniMed(rset.getString(12));
				vVEHSolicitud.setCDscModulo(rset.getString(13));
				vVEHSolicitud.setCDscArea(rset.getString(14));
				vVEHSolicitud.setCDscBreveTpoVeh(rset.getString(15));
				vVEHSolicitud.setCNombre(rset.getString(16));
				vVEHSolicitud.setCApPaterno(rset.getString(17));
				vVEHSolicitud.setCApMaterno(rset.getString(18));
				vVEHSolicitud.setDtEntrega(rset.getDate(19));

				vcVEHEtapaXSolic = dVEHEtapaXSolic.FindByAll(
						" where iAnio = " + vVEHSolicitud.getIAnio()
								+ " and iCveUniMed = "
								+ vVEHSolicitud.getICveUniMed()
								+ " and iCveSolicitud = "
								+ vVEHSolicitud.getICveSolicitud(), "");
				boolean lEncontrado = false;
				for (int i = 0; i < vcVEHEtapaXSolic.size(); i++) {
					vVEHEtapaXSolic = (TVVEHEtapaXSolic) vcVEHEtapaXSolic
							.get(i);
					if (vVEHEtapaXSolic.getICveEtapaSolic() == Integer
							.parseInt(cVEHEtapaSolFin))
						lEncontrado = true;
				}

				// if(!lEncontrado)
				vcVEHSolicitud.addElement(vVEHSolicitud);
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
			return vcVEHSolicitud;
		}
	}

	/**
	 * Solicitudes por Usuario
	 */
	public Vector FindSolicXUsr(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSolicitud = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "  VEHSolicitud.iAnio, "
					+ "  VEHSolicitud.iCveSolicitud, "
					+ "  VEHSolicitud.dtSolicitud, "
					+ "  VEHSolicitud.tsSolicitud, "
					+ "  VEHSolicitud.iCveUsuSolic, "
					+ "  VEHSolicitud.iCveUniMed, "
					+ "  VEHSolicitud.iCveModulo, "
					+ "  VEHSolicitud.iCveArea, "
					+ "  VEHSolicitud.iCveTpoVehiculo, "
					+ "  VEHSolicitud.iCveVehiculo, "
					+ "  GRLUniMed.cDscUniMed, "
					+ "  GRLModulo.cDscModulo, "
					+ "  GRLArea.cDscArea, "
					+ "  VEHTpoVehiculo.cDscBreve, "
					+ "  VEHSolicitud.iCveMotivo, "
					+ "  GRLMotivo.cDscMotivo, "
					+ "  VEHSolicitud.cDestino, "
					+ "  VEHSolicitud.dtAsignado, "
					+ "  VEHSolicitud.dtEntrega, "
					+ "  VEHSolicitud.dtCancelacion, "
					+ "  VEHSolicitud.iKmInicial, "
					+ "  VEHSolicitud.iKmFinal, "
					+ "  SEGUsuario.cNombre, "
					+ "  SEGUsuario.cApPaterno, "
					+ "  SEGUsuario.cApMaterno, "
					+ "  VEHSolicitud.lAsignado, "
					+ "  VEHVehiculo.iAnioVeh, "
					+ "  VEHVehiculo.cPlacas, "
					+ "  VEHMarca.cDscBreve, "
					+ "  VEHModelo.cDscBreve "
					+ "  from VEHSolicitud "
					+ "  join (GRLAreaModulo "
					+ "    join (GRLModulo "
					+ "      join GRLUniMed on GRLUniMed.iCveUniMed = GRLModulo.iCveUniMed "
					+ "    ) on GRLModulo.iCveUniMed = GRLAReaModulo.iCveUniMed "
					+ "     and GRLModulo.iCveModulo = GRLAReaModulo.iCveModulo "
					+ "    join GRLARea on GRLARea.iCveArea = GRLAReaModulo.iCveArea "
					+ "  ) on GRLAreaModulo.iCveUniMed = VEHSolicitud.iCveUniMed "
					+ "   and GRLAreaModulo.iCveModulo = VEHSolicitud.iCveModulo "
					+ "   and GRLAreaModulo.iCveArea = VEHSolicitud.iCveArea "
					+ "  left join VEHTpoVehiculo on VEHTpoVehiculo.iCveTpoVehiculo = VEHSolicitud.iCveTpoVehiculo "
					+ "  join GRLMotivo on GRLMotivo.iCveMotivo = VEHSolicitud.iCveMotivo "
					+ "                and GRLMotivo.iCveProceso = "
					+ iCveProceso
					+ "  join SEGUsuario on SEGUsuario.iCveUsuario = VEHSolicitud.iCveUsuSolic "
					+ "  left join VEHVehiculo ON VEHVehiculo.iCveVehiculo = VEHSolicitud.iCveVehiculo "
					+ "  left join VEHMarca ON VEHMarca.iCveMarca = VEHVehiculo.iCveMarca "
					+ "  left join VEHModelo ON VEHModelo.iCveMarca = VEHVehiculo.iCveMarca "
					+ "                     AND VEHModelo.iCveModelo = VEHVehiculo.iCveModelo "
					+ cWhere + cOrderBy;
			// System.out.println("VEH_____RMB_____" + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSolicitud = new TVVEHSolicitud();

				vVEHSolicitud.setIAnio(rset.getInt(1));
				vVEHSolicitud.setICveSolicitud(rset.getInt(2));
				vVEHSolicitud.setDtSolicitud(rset.getDate(3));
				vVEHSolicitud.setTsSolicitud(rset.getTimestamp(4));
				vVEHSolicitud.setICveUsuSolic(rset.getInt(5));
				vVEHSolicitud.setICveUniMed(rset.getInt(6));
				vVEHSolicitud.setICveModulo(rset.getInt(7));
				vVEHSolicitud.setICveArea(rset.getInt(8));
				vVEHSolicitud.setICveTpoVehiculo(rset.getInt(9));
				vVEHSolicitud.setICveVehiculo(rset.getInt(10));
				vVEHSolicitud.setCDscUniMed(rset.getString(11));
				vVEHSolicitud.setCDscModulo(rset.getString(12));
				vVEHSolicitud.setCDscArea(rset.getString(13));
				vVEHSolicitud.setCDscBreveTpoVeh(rset.getString(14));
				vVEHSolicitud.setICveMotivo(rset.getInt(15));
				vVEHSolicitud.setCDscMotivo(rset.getString(16));
				vVEHSolicitud.setCDestino(rset.getString(17));
				vVEHSolicitud.setDtAsignado(rset.getDate(18));
				vVEHSolicitud.setDtEntrega(rset.getDate(19));
				vVEHSolicitud.setDtCancelacion(rset.getDate(20));
				vVEHSolicitud.setIKmInicial(rset.getInt(21));
				vVEHSolicitud.setIKmFinal(rset.getInt(22));
				vVEHSolicitud.setCNombre(rset.getString(23));
				vVEHSolicitud.setCApPaterno(rset.getString(24));
				vVEHSolicitud.setCApMaterno(rset.getString(25));
				vVEHSolicitud.setLAsignado(rset.getInt(26));
				vVEHSolicitud.setIAnioVeh(rset.getInt(27));
				vVEHSolicitud.setCPlacas(rset.getString(28));
				vVEHSolicitud.setCDscMarca(rset.getString(29));
				vVEHSolicitud.setCDscModelo(rset.getString(30));

				vcVEHSolicitud.addElement(vVEHSolicitud);
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
			return vcVEHSolicitud;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null, pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT MAX(iCveSolicitud) FROM VEHSolicitud WHERE iAnio = ? AND iCveUniMed = ?";
			pstmtMax = conn.prepareStatement(cSQL);
			pstmtMax.setInt(1, vVEHSolicitud.getIAnio());
			pstmtMax.setInt(2, vVEHSolicitud.getICveUniMed());
			rsetMax = pstmtMax.executeQuery();
			if (rsetMax.next())
				iMax = rsetMax.getInt(1);
			iMax++;
			vVEHSolicitud.setICveSolicitud(iMax);
			cClave = "" + iMax;
			cSQL = "insert into VEHSolicitud("
					+ "iAnio,"
					+ "iCveUniMed,"
					+ "iCveSolicitud,"
					+ "dtRegistro,"
					+ "dtSolicitud,"
					+ "iCveUsuSolic,"
					+ "iCveModulo,"
					+ "iCveArea,"
					+ "iCveMotivo,"
					+ "cDestino,"
					+ "cLicencia,"
					+ "dtVenceLic,"
					+ "iTmpAsignado,"
					+ "iCveTpoVehiculo,"
					+ "dtAsignado,"
					+ "iCveUsuAsigna,"
					+ "lAsignado,"
					+ "dtEntrega,"
					+ "dtCancelacion,"
					+ "iCveVehiculo,"
					+ "iKmInicial,"
					+ "iKmFinal,"
					+ "lLicPermanente,"
					+ "tsSolicitud,"
					+ "iCveUsuConductor "
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vVEHSolicitud.getIAnio());
			pstmt.setInt(2, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(3, vVEHSolicitud.getICveSolicitud());
			pstmt.setDate(4, vVEHSolicitud.getDtRegistro());
			pstmt.setDate(5, vVEHSolicitud.getDtSolicitud());
			pstmt.setInt(6, vVEHSolicitud.getICveUsuSolic());
			pstmt.setInt(7, vVEHSolicitud.getICveModulo());
			pstmt.setInt(8, vVEHSolicitud.getICveArea());
			pstmt.setInt(9, vVEHSolicitud.getICveMotivo());
			pstmt.setString(10, vVEHSolicitud.getCDestino().toUpperCase()
					.trim());
			pstmt.setString(11, vVEHSolicitud.getCLicencia().toUpperCase()
					.trim());
			pstmt.setDate(12, vVEHSolicitud.getDtVenceLic());
			pstmt.setInt(13, vVEHSolicitud.getITmpAsignado());
			pstmt.setInt(14, vVEHSolicitud.getICveTpoVehiculo());
			pstmt.setDate(15, vVEHSolicitud.getDtAsignado());
			pstmt.setInt(16, vVEHSolicitud.getICveUsuAsigna());
			pstmt.setInt(17, vVEHSolicitud.getLAsignado());
			pstmt.setDate(18, vVEHSolicitud.getDtEntrega());
			pstmt.setDate(19, vVEHSolicitud.getDtCancelacion());
			pstmt.setInt(20, vVEHSolicitud.getICveVehiculo());
			pstmt.setInt(21, vVEHSolicitud.getIKmInicial());
			pstmt.setInt(22, vVEHSolicitud.getIKmFinal());
			pstmt.setInt(23, vVEHSolicitud.getLLicPermanente());
			pstmt.setTimestamp(24, vVEHSolicitud.getTsSolicitud());
			pstmt.setInt(25, vVEHSolicitud.getICveUsuConductor());
			pstmt.executeUpdate();
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
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update VEHSolicitud" + " set " + " dtRegistro= ?, "
					+ " dtSolicitud= ?, " + " iCveUsuSolic= ?, "
					+ " iCveModulo= ?, " + " iCveArea= ?, "
					+ " iCveMotivo= ?, " + " cDestino= ?, " + " cLicencia= ?, "
					+ " dtVenceLic= ?, " + " iTmpAsignado= ?, "
					+ " iCveTpoVehiculo= ?, " + " dtAsignado= ?, "
					+ " iCveUsuAsigna= ?, " + " lAsignado= ?, "
					+ " dtEntrega= ?, " + " dtCancelacion= ?, "
					+ " iCveVehiculo= ?, " + " iKmInicial= ?, "
					+ " iKmFinal= ?, " + " lLicPermanente= ?, "
					+ " iCveUsuConductor= ?, " + " tsSolicitud = ? "
					+ " where iAnio = ? " + " and iCveUniMed = ? "
					+ " and iCveSolicitud = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vVEHSolicitud.getDtRegistro());
			pstmt.setDate(2, vVEHSolicitud.getDtSolicitud());
			pstmt.setInt(3, vVEHSolicitud.getICveUsuSolic());
			pstmt.setInt(4, vVEHSolicitud.getICveModulo());
			pstmt.setInt(5, vVEHSolicitud.getICveArea());
			pstmt.setInt(6, vVEHSolicitud.getICveMotivo());
			pstmt.setString(7, vVEHSolicitud.getCDestino().toUpperCase().trim());
			pstmt.setString(8, vVEHSolicitud.getCLicencia().toUpperCase()
					.trim());
			pstmt.setDate(9, vVEHSolicitud.getDtVenceLic());
			pstmt.setInt(10, vVEHSolicitud.getITmpAsignado());
			pstmt.setInt(11, vVEHSolicitud.getICveTpoVehiculo());
			pstmt.setDate(12, vVEHSolicitud.getDtAsignado());
			pstmt.setInt(13, vVEHSolicitud.getICveUsuAsigna());
			pstmt.setInt(14, vVEHSolicitud.getLAsignado());
			pstmt.setDate(15, vVEHSolicitud.getDtEntrega());
			pstmt.setDate(16, vVEHSolicitud.getDtCancelacion());
			pstmt.setInt(17, vVEHSolicitud.getICveVehiculo());
			pstmt.setInt(18, vVEHSolicitud.getIKmInicial());
			pstmt.setInt(19, vVEHSolicitud.getIKmFinal());
			pstmt.setInt(20, vVEHSolicitud.getLLicPermanente());
			pstmt.setInt(21, vVEHSolicitud.getICveUsuConductor());
			pstmt.setTimestamp(22, vVEHSolicitud.getTsSolicitud());

			pstmt.setInt(23, vVEHSolicitud.getIAnio());
			pstmt.setInt(24, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(25, vVEHSolicitud.getICveSolicitud());
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
	 * Metodo updateVEH - Actualiza unicamente la Clave del Vehiculo
	 */
	public Object updateVEH(Connection conGral, Object obj) throws DAOException {
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
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " update VEHSolicitud" + " set " + " iCveVehiculo= ?, "
					+ " iCveTpoVehiculo= ?, " + " iCveUsuAsigna= ?, "
					+ " lAsignado= ?, " + " dtAsignado= ? "
					+ " where iAnio = ? " + " and iCveUniMed = ?"
					+ " and iCveSolicitud = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHSolicitud.getICveVehiculo());
			pstmt.setInt(2, vVEHSolicitud.getICveTpoVehiculo());
			pstmt.setInt(3, vVEHSolicitud.getICveUsuAsigna());
			pstmt.setInt(4, vVEHSolicitud.getLAsignado());
			pstmt.setDate(5, vVEHSolicitud.getDtAsignado());
			pstmt.setInt(6, vVEHSolicitud.getIAnio());
			pstmt.setInt(7, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(8, vVEHSolicitud.getICveSolicitud());
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
	 * Metodo Cancel
	 */
	public Object cancel(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		TFechas pFecha = new TFechas();
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHSolicitud " + " set " + " dtCancelacion = ? "
					+ " where iAnio = ?" + " and iCveUniMed = ?"
					+ " and iCveSolicitud = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, pFecha.TodaySQL());
			pstmt.setInt(2, vVEHSolicitud.getIAnio());
			pstmt.setInt(3, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(4, vVEHSolicitud.getICveSolicitud());
			pstmt.executeUpdate();
			cClave = vVEHSolicitud.getICveSolicitud() + "";
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
				warn("cancel", ex1);
			}
			warn("cancel", ex);
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
				warn("cancel.closeVEHSolicitud", ex2);
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
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VEHSolicitud" + " where iAnio = ?"
					+ " and iCveUniMed = ?" + " and iCveSolicitud = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHSolicitud.getIAnio());
			pstmt.setInt(2, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(3, vVEHSolicitud.getICveSolicitud());
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
				warn("delete.closeVEHSolicitud", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Datos del Solicitante
	 */
	public Vector FindDatosSolic(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHSolicitud = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select GrlUniMed.cDscUniMed, GrlModulo.cDscModulo, GrlArea.cDscArea, "
					+ "S.cNombre, S.cApPaterno, S.cApMaterno, "
					+ "VehSolicitud.iAnio, VehSolicitud.dtSolicitud, VehSolicitud.iCveVehiculo, "
					+ "U.cNombre,U.cApPaterno,U.cApMaterno,VehSolicitud.lLicPermanente, VehSolicitud.dtVenceLic, VehSolicitud.cLicencia, "
					+ "VEHSolicitud.iKmInicial, VEHSolicitud.iKmFinal, VEHSolicitud.iCveSolicitud "
					+ "from VehSolicitud "
					+ "Left Join GrlUniMed on VehSolicitud.iCveUniMed=GrlUniMed.iCveUniMed "
					+ "Left Join GrlModulo on VehSolicitud.iCveModulo=GrlModulo.iCveModulo "
					+ "     and VehSolicitud.iCveUniMed=GrlModulo.iCveUniMed "
					+ "Left Join GrlArea on VehSolicitud.iCveArea=GrlArea.iCveArea "
					+ "Left Join SegUsuario S on VehSolicitud.iCveUsuSolic=S.iCveUsuario "
					+ "left join SegUsuario U on VehSolicitud.iCveUsuConductor=U.iCveUsuario "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHSolicitud = new TVVEHSolicitud();
				vVEHSolicitud.setCDscUniMed(rset.getString(1));
				vVEHSolicitud.setCDscModulo(rset.getString(2));
				vVEHSolicitud.setCDscArea(rset.getString(3));
				vVEHSolicitud.setCDscUsuSolic(rset.getString(4) + " "
						+ rset.getString(5) + " " + rset.getString(6));
				vVEHSolicitud.setIAnio(rset.getInt(7));
				vVEHSolicitud.setDtSolicitud(rset.getDate(8));
				vVEHSolicitud.setICveVehiculo(rset.getInt(9));
				vVEHSolicitud.setCDscUsuConductor(rset.getString(10) + " "
						+ rset.getString(11) + " " + rset.getString(12));
				vVEHSolicitud.setLLicPermanente(rset.getInt(13));
				vVEHSolicitud.setDtVenceLic(rset.getDate(14));
				vVEHSolicitud.setCLicencia(rset.getString(15));
				vVEHSolicitud.setIKmInicial(rset.getInt(16));
				vVEHSolicitud.setIKmFinal(rset.getInt(17));
				vVEHSolicitud.setICveSolicitud(rset.getInt(18));
				vcVEHSolicitud.addElement(vVEHSolicitud);
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
			return vcVEHSolicitud;
		}
	}

	/**
	 * Metodo update
	 */
	public Object updateRevisionInicial(Connection conGral, Object obj)
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
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			cSQL = " update VEHSolicitud" + " set iKmInicial= ? "
					+ " where iAnio = ? " + " and iCveUniMed = ?"
					+ " and iCveSolicitud = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHSolicitud.getIKmInicial());
			pstmt.setInt(2, vVEHSolicitud.getIAnio());
			pstmt.setInt(3, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(4, vVEHSolicitud.getICveSolicitud());
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo update
	 */
	public Object updateRevisionFinal(Connection conGral, Object obj)
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
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			cSQL = " update VEHSolicitud" + " set iKmFinal= ?, "
					+ " dtEntrega = ? " + " where iAnio = ? "
					+ " and iCveUniMed = ?" + " and iCveSolicitud = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHSolicitud.getIKmFinal());
			pstmt.setDate(2, vVEHSolicitud.getDtEntrega());
			pstmt.setInt(3, vVEHSolicitud.getIAnio());
			pstmt.setInt(4, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(5, vVEHSolicitud.getICveSolicitud());
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("update.close", ex2);
			}
			return cClave;
		}
	}

	public Object updateKilimentraje(Connection conGral, Object obj)
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
			String cSQL = "";
			TVVEHSolicitud vVEHSolicitud = (TVVEHSolicitud) obj;
			cSQL = " update VEHSolicitud" + " set iKmFinal   = ?, "
					+ "     iKmInicial = ? " + " where iAnio         = ? "
					+ "   and iCveUniMed    = ?" + "   and iCveSolicitud = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHSolicitud.getIKmFinal());
			pstmt.setInt(2, vVEHSolicitud.getIKmInicial());
			pstmt.setInt(3, vVEHSolicitud.getIAnio());
			pstmt.setInt(4, vVEHSolicitud.getICveUniMed());
			pstmt.setInt(5, vVEHSolicitud.getICveSolicitud());
			pstmt.executeUpdate();
			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				ex.printStackTrace();
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("updateKilimentraje", ex1);
			}
			warn("updateKilimentraje", ex);
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
				warn("updateKilimentraje.close", ex2);
			}
			return cClave;
		}
	}

}
