package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de VEHVehiculo DAO
 * </p>
 * <p>
 * Description: DAO para la Administraci�n de la Informaci�n de la tabla
 * VEHVehiculo
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

public class TDVEHVehiculo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHVehiculo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHVehiculo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHVehiculo vVEHVehiculo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "   select   "
					+ "   VEHVehiculo.iCveVehiculo,   "
					+ "   VEHVehiculo.iCveTpoVehiculo,    "
					+ "   VEHVehiculo.iCveMarca,     "
					+ "   VEHVehiculo.iCveModelo,    "
					+ "   VEHVehiculo.iAnioVeh,      "
					+ "   VEHVehiculo.cNumSerie,     "
					+ "   VEHVehiculo.cNumMotor,      "
					+ "   VEHVehiculo.cInventario,  "
					+ "   VEHVehiculo.iCveColor,        "
					+ "   VEHVehiculo.cPlacas,      "
					+ "   VEHVehiculo.iKmMantto,      "
					+ "   VEHVehiculo.iMesMantto,     "
					+ "   VEHVehiculo.cPoliza,     "
					+ "   VEHVehiculo.dtInicioVig,      "
					+ "   VEHVehiculo.dtFinVig,      "
					+ "   VEHVehiculo.dtAlta,      "
					+ "   VEHVehiculo.iCveEstadoVEH,  "
					+ "   VEHVehiculo.lActivo,         "
					+ "   VEHVehiculo.lBaja,        "
					+ "   VEHVehiculo.dtBaja,         "
					+ "   VEHVehiculo.iCveMtvoBaja,       "
					+ "   VEHTpoVehiculo.cDscBreve,       "
					+ "   VEHMarca.cDscBreve,       "
					+ "   VEHModelo.cDscBreve,       "
					+ "   VEHMtvoBaja.cDscMtvoBaja,  "
					+ "   VEHEstado.cDscEstadoVEH, "
					+ "   VEHColor.cDscColor, "
					+ "   VEHVehiculo.iKmFinal, "
					+ "   VEHVehiculo.dtIniMantto, "
					+ "   VEHVehiculo.iKmGarantia, "
					+ "   VEHVehiculo.iMesGarantia, "
					+ "   VEHVehiculo.cCobertura, "
					+ "   VEHVehiculo.cAseguradora "
					+ "   from VEHVehiculo "
					+ "   left join VEHTpoVehiculo ON VEHTpoVehiculo.iCveTpoVehiculo = VEHVehiculo.iCveTpoVehiculo "
					+ "   left join VEHMarca ON VEHMarca.iCveMarca = VEHVehiculo.iCveMarca "
					+ "   left join VEHModelo ON VEHModelo.iCveMarca = VEHVehiculo.iCveMarca AND  "
					+ "                          VEHModelo.iCveModelo = VEHVehiculo.iCveModelo "
					+ "   left join VEHMtvoBaja ON VEHMtvoBaja.iCveMtvoBaja = VEHVehiculo.iCveMtvoBaja "
					+ "   left join VEHEstado ON VEHEstado.iCveEstadoVEH = VEHVehiculo.iCveEstadoVEH "
					+ "   left join VEHColor ON VEHColor.iCveColor = VEHVehiculo.iCveColor "
					+ cFiltro + " " + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHVehiculo = new TVVEHVehiculo();
				vVEHVehiculo.setICveVehiculo(rset.getInt(1));
				vVEHVehiculo.setICveTpoVehiculo(rset.getInt(2));
				vVEHVehiculo.setICveMarca(rset.getInt(3));
				vVEHVehiculo.setICveModelo(rset.getInt(4));
				vVEHVehiculo.setIAnioVeh(rset.getInt(5));
				vVEHVehiculo.setCNumSerie(rset.getString(6));
				vVEHVehiculo.setCNumMotor(rset.getString(7));
				vVEHVehiculo.setCInventario(rset.getString(8));
				vVEHVehiculo.setICveColor(rset.getInt(9));
				vVEHVehiculo.setCPlacas(rset.getString(10));
				vVEHVehiculo.setIKmMantto(rset.getInt(11));
				vVEHVehiculo.setIMesMantto(rset.getInt(12));
				vVEHVehiculo.setCPoliza(rset.getString(13));
				vVEHVehiculo.setDtInicioVig(rset.getDate(14));
				vVEHVehiculo.setDtFinVig(rset.getDate(15));
				vVEHVehiculo.setDtAlta(rset.getDate(16));
				vVEHVehiculo.setICveEstadoVEH(rset.getInt(17));
				vVEHVehiculo.setLActivo(rset.getInt(18));
				vVEHVehiculo.setLBaja(rset.getInt(19));
				vVEHVehiculo.setDtBaja(rset.getDate(20));
				vVEHVehiculo.setICveMtvoBaja(rset.getInt(21));
				vVEHVehiculo.setCDscTpoVehiculo(rset.getString(22));
				vVEHVehiculo.setCDscMarca(rset.getString(23));
				vVEHVehiculo.setCDscModelo(rset.getString(24));
				vVEHVehiculo.setCDscMtvoBaja(rset.getString(25));
				vVEHVehiculo.setCDscEstadoVEH(rset.getString(26));
				vVEHVehiculo.setCDscColor(rset.getString(27));
				vVEHVehiculo.setIKmFinal(rset.getInt(28));
				vVEHVehiculo.setDtIniMantto(rset.getDate(29));
				vVEHVehiculo.setIKmGarantia(rset.getInt(30));
				vVEHVehiculo.setIMesGarantia(rset.getInt(31));
				vVEHVehiculo.setCCobertura(rset.getString(32));
				vVEHVehiculo.setCAseguradora(rset.getString(33));
				vcVEHVehiculo.addElement(vVEHVehiculo);
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
			return vcVEHVehiculo;
		}
	}

	/**
	 * Metodo Find By Mantenimientos - Proceso que encuntra los Vehiculos con
	 * respecto a los Mantenimientos.
	 */
	public Vector FindByMant(Object Obj, String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHVehiculo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHVehiculo vVEHVehiculo = (TVVEHVehiculo) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select  "
					+ " VEHVehiculo.iCveVehiculo, "
					+ " VEHVehiculo.cNumSerie, "
					+ " VEHVehiculo.cInventario, "
					+ " VEHVehiculo.cPlacas, "
					+ " VEHVehiculo.dtIniMantto, "
					+ " VEHVehiculo.iKmFinal, "
					+ " VEHVehiculo.iKmMantto, "
					+ " VEHVehiculo.iMesMantto, "
					+ " VEHTpoVehiculo.cDscBreve, "
					+ " VEHMarca.cDscBreve, "
					+ " VEHModelo.cDscBreve, "
					+ " VEHEstado.cDscEstadoVEH, "
					+ " VEHColor.cDscColor, "
					+ " GRLUniMed.cDscUniMed, "
					+ " x.iMax, "
					+ " M.dtInicia, "
					+ " M.iKilometraje"
					+ " FROM VEHVehiculo "
					+ " LEFT JOIN (SELECT VEHMantenimiento.iCveVehiculo,MAX(VEHMantenimiento.iCveMantenimiento) iMax "
					+ " FROM VEHMantenimiento ";
			if (vVEHVehiculo.getICveTpoMantto() > 0)
				cSQL += " WHERE VEHMantenimiento.lCancelado = 0 AND VEHMantenimiento.lConcluido = 1 "
						+ "   AND VEHMantenimiento.iCveTpoMantto = "
						+ vVEHVehiculo.getICveTpoMantto();
			cSQL += " GROUP BY iCveVehiculo) x "
					+ " ON x.iCveVehiculo = VEHVehiculo.iCveVehiculo "
					+ " left join VEHMantenimiento M ON M.iCveVehiculo = VEHVehiculo.iCveVehiculo AND "
					+ "                                 M.iCveMantenimiento = x.iMax "
					+ " left join VEHTpoVehiculo ON VEHTpoVehiculo.iCveTpoVehiculo = VEHVehiculo.iCveTpoVehiculo "
					+ " left join VEHMarca ON VEHMarca.iCveMarca = VEHVehiculo.iCveMarca "
					+ " left join VEHModelo ON VEHModelo.iCveMarca = VEHVehiculo.iCveMarca "
					+ "                    AND VEHModelo.iCveModelo = VEHVehiculo.iCveModelo "
					+ " left join VEHEstado ON VEHEstado.iCveEstadoVEH = VEHVehiculo.iCveEstadoVEH "
					+ " left join VEHColor ON VEHColor.iCveColor = VEHVehiculo.iCveColor "
					+ " left join VEHUbicacion ON VEHUbicacion.iCveVehiculo = VEHVehiculo.iCveVehiculo "
					+ "                                  AND VEHUbicacion.lActivo = 1 ";
			if (vVEHVehiculo.getICveUniMed() <= 0)
				cSQL += " left";
			cSQL += " join GRLUniMed ON GRLUniMed.iCveUniMed = VEHUbicacion.iCveUniMed ";
			if (vVEHVehiculo.getICveUniMed() > 0)
				cSQL += " AND GRLUniMed.iCveUniMed =  "
						+ vVEHVehiculo.getICveUniMed();
			cSQL += cFiltro + "  " + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHVehiculo = new TVVEHVehiculo();
				vVEHVehiculo.setICveVehiculo(rset.getInt(1));
				vVEHVehiculo.setCNumSerie(rset.getString(2));
				vVEHVehiculo.setCInventario(rset.getString(3));
				vVEHVehiculo.setCPlacas(rset.getString(4));
				vVEHVehiculo.setDtIniMantto(rset.getDate(5));
				vVEHVehiculo.setIKmFinal(rset.getInt(6));
				vVEHVehiculo.setIKmMantto(rset.getInt(7));
				vVEHVehiculo.setIMesMantto(rset.getInt(8));
				vVEHVehiculo.setCDscTpoVehiculo(rset.getString(9));
				vVEHVehiculo.setCDscMarca(rset.getString(10));
				vVEHVehiculo.setCDscModelo(rset.getString(11));
				vVEHVehiculo.setCDscEstadoVEH(rset.getString(12));
				vVEHVehiculo.setCDscColor(rset.getString(13));
				vVEHVehiculo.setCDscUniMed(rset.getString(14));
				// Datos del Mantenimiento:
				vVEHVehiculo.setICveMantenimiento(rset.getInt(15));
				vVEHVehiculo.setDtInicia(rset.getDate(16));
				vVEHVehiculo.setIKilometraje(rset.getInt(17));
				vcVEHVehiculo.addElement(vVEHVehiculo);
			}
		} catch (Exception ex) {
			warn("FindByMant", ex);
			throw new DAOException("FindByMant");
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
				warn("FindByMant.close", ex2);
			}
			return vcVEHVehiculo;
		}
	}

	/**
	 * 
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
			TVVEHVehiculo vVEHVehiculo = (TVVEHVehiculo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "SELECT MAX(iCveVehiculo) FROM VEHVehiculo ";
			pstmtMax = conn.prepareStatement(cSQL);
			rsetMax = pstmtMax.executeQuery();
			if (rsetMax.next())
				iMax = rsetMax.getInt(1);
			iMax++;
			vVEHVehiculo.setICveVehiculo(iMax);
			cClave = "" + iMax;
			cSQL = "insert into VEHVehiculo("
					+ "iCveVehiculo,"
					+ "iCveTpoVehiculo,"
					+ "iCveMarca,"
					+ "iCveModelo,"
					+ "iAnioVeh,"
					+ "cNumSerie,"
					+ "cNumMotor,"
					+ "cInventario,"
					+ "iCveColor,"
					+ "cPlacas,"
					+ "iKmMantto,"
					+ "iMesMantto,"
					+ "cPoliza,"
					+ "dtInicioVig,"
					+ "dtFinVig,"
					+ "dtAlta,"
					+ "iCveEstadoVEH,"
					+ "lActivo,"
					+ "lBaja,"
					+ "dtBaja,"
					+ "iCveMtvoBaja,iKmFinal,dtIniMantto,"
					+ "iKmGarantia,"
					+ "iMesGarantia,"
					+ "cCobertura,"
					+ "cAseguradora"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vVEHVehiculo.getICveVehiculo());
			pstmt.setInt(2, vVEHVehiculo.getICveTpoVehiculo());
			pstmt.setInt(3, vVEHVehiculo.getICveMarca());
			pstmt.setInt(4, vVEHVehiculo.getICveModelo());
			pstmt.setInt(5, vVEHVehiculo.getIAnioVeh());
			pstmt.setString(6, vVEHVehiculo.getCNumSerie().toUpperCase().trim());
			pstmt.setString(7, vVEHVehiculo.getCNumMotor().toUpperCase().trim());
			pstmt.setString(8, vVEHVehiculo.getCInventario().toUpperCase()
					.trim());
			pstmt.setInt(9, vVEHVehiculo.getICveColor());
			pstmt.setString(10, vVEHVehiculo.getCPlacas().toUpperCase().trim());
			pstmt.setInt(11, vVEHVehiculo.getIKmMantto());
			pstmt.setInt(12, vVEHVehiculo.getIMesMantto());
			pstmt.setString(13, vVEHVehiculo.getCPoliza().toUpperCase().trim());
			pstmt.setDate(14, vVEHVehiculo.getDtInicioVig());
			pstmt.setDate(15, vVEHVehiculo.getDtFinVig());
			pstmt.setDate(16, vVEHVehiculo.getDtAlta());
			pstmt.setInt(17, vVEHVehiculo.getICveEstadoVEH());
			pstmt.setInt(18, vVEHVehiculo.getLActivo());
			pstmt.setInt(19, vVEHVehiculo.getLBaja());
			pstmt.setDate(20, vVEHVehiculo.getDtBaja());
			pstmt.setInt(21, vVEHVehiculo.getICveMtvoBaja());
			pstmt.setInt(22, vVEHVehiculo.getIKmFinal());
			pstmt.setDate(23, vVEHVehiculo.getDtIniMantto());
			pstmt.setInt(24, vVEHVehiculo.getIKmGarantia());
			pstmt.setInt(25, vVEHVehiculo.getIMesGarantia());
			pstmt.setString(26, vVEHVehiculo.getCCobertura().toUpperCase()
					.trim());
			pstmt.setString(27, vVEHVehiculo.getCAseguradora().toUpperCase()
					.trim());
			pstmt.executeUpdate();
			cClave = "" + vVEHVehiculo.getICveVehiculo();
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
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
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
			TVVEHVehiculo vVEHVehiculo = (TVVEHVehiculo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHVehiculo" + " set iCveTpoVehiculo= ?, "
					+ "iCveMarca= ?, " + "iCveModelo= ?, " + "iAnioVeh= ?, "
					+ "cNumSerie= ?, " + "cNumMotor= ?, " + "cInventario= ?, "
					+ "iCveColor= ?, " + "cPlacas= ?, " + "iKmMantto= ?, "
					+ "iMesMantto= ?, " + "cPoliza= ?, " + "dtInicioVig= ?, "
					+ "dtFinVig= ?, " + "dtAlta= ?, " + "iCveEstadoVEH= ?, "
					+ "lActivo= ?, " + "lBaja= ?, " + "dtBaja= ?, "
					+ "iCveMtvoBaja = ?,iKmFinal = ?, dtIniMantto = ?, "
					+ "iKmGarantia = ?," + "iMesGarantia = ?,"
					+ "cCobertura = ?," + "cAseguradora = ?"
					+ " where iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHVehiculo.getICveTpoVehiculo());
			pstmt.setInt(2, vVEHVehiculo.getICveMarca());
			pstmt.setInt(3, vVEHVehiculo.getICveModelo());
			pstmt.setInt(4, vVEHVehiculo.getIAnioVeh());
			pstmt.setString(5, vVEHVehiculo.getCNumSerie().toUpperCase().trim());
			pstmt.setString(6, vVEHVehiculo.getCNumMotor().toUpperCase().trim());
			pstmt.setString(7, vVEHVehiculo.getCInventario().toUpperCase()
					.trim());
			pstmt.setInt(8, vVEHVehiculo.getICveColor());
			pstmt.setString(9, vVEHVehiculo.getCPlacas().toUpperCase().trim());
			pstmt.setInt(10, vVEHVehiculo.getIKmMantto());
			pstmt.setInt(11, vVEHVehiculo.getIMesMantto());
			pstmt.setString(12, vVEHVehiculo.getCPoliza().toUpperCase().trim());
			pstmt.setDate(13, vVEHVehiculo.getDtInicioVig());
			pstmt.setDate(14, vVEHVehiculo.getDtFinVig());
			pstmt.setDate(15, vVEHVehiculo.getDtAlta());
			pstmt.setInt(16, vVEHVehiculo.getICveEstadoVEH());
			pstmt.setInt(17, vVEHVehiculo.getLActivo());
			pstmt.setInt(18, vVEHVehiculo.getLBaja());
			pstmt.setDate(19, vVEHVehiculo.getDtBaja());
			pstmt.setInt(20, vVEHVehiculo.getICveMtvoBaja());
			pstmt.setInt(21, vVEHVehiculo.getIKmFinal());
			pstmt.setDate(22, vVEHVehiculo.getDtIniMantto());
			pstmt.setInt(23, vVEHVehiculo.getIKmGarantia());
			pstmt.setInt(24, vVEHVehiculo.getIMesGarantia());
			pstmt.setString(25, vVEHVehiculo.getCCobertura().toUpperCase()
					.trim());
			pstmt.setString(26, vVEHVehiculo.getCAseguradora().toUpperCase()
					.trim());
			pstmt.setInt(27, vVEHVehiculo.getICveVehiculo());
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
	 * Metodo updateBaja - Metodo que solo actualiza el balor de baja
	 */
	public Object updateBaja(Connection conGral, Object obj)
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
			TVVEHVehiculo vVEHVehiculo = (TVVEHVehiculo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHVehiculo"
					+ " set iCveTpoVehiculo= ?, "
					+ "lBaja= ?, "
					+ "dtBaja= ?, "
					+ "iCveMtvoBaja = ?,iKmFinal = ?, dtIniMantto = ? where iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHVehiculo.getICveTpoVehiculo());
			pstmt.setInt(2, vVEHVehiculo.getICveMarca());
			pstmt.setInt(3, vVEHVehiculo.getICveModelo());
			pstmt.setInt(4, vVEHVehiculo.getIAnioVeh());
			pstmt.setString(5, vVEHVehiculo.getCNumSerie().toUpperCase().trim());
			pstmt.setString(6, vVEHVehiculo.getCNumMotor().toUpperCase().trim());
			pstmt.setString(7, vVEHVehiculo.getCInventario().toUpperCase()
					.trim());
			pstmt.setInt(8, vVEHVehiculo.getICveColor());
			pstmt.setString(9, vVEHVehiculo.getCPlacas().toUpperCase().trim());
			pstmt.setInt(10, vVEHVehiculo.getIKmMantto());
			pstmt.setInt(11, vVEHVehiculo.getIMesMantto());
			pstmt.setString(12, vVEHVehiculo.getCPoliza().toUpperCase().trim());
			pstmt.setDate(13, vVEHVehiculo.getDtInicioVig());
			pstmt.setDate(14, vVEHVehiculo.getDtFinVig());
			pstmt.setDate(15, vVEHVehiculo.getDtAlta());
			pstmt.setInt(16, vVEHVehiculo.getICveEstadoVEH());
			pstmt.setInt(17, vVEHVehiculo.getLActivo());
			pstmt.setInt(18, vVEHVehiculo.getLBaja());
			pstmt.setDate(19, vVEHVehiculo.getDtBaja());
			pstmt.setInt(20, vVEHVehiculo.getICveMtvoBaja());
			pstmt.setInt(21, vVEHVehiculo.getIKmFinal());
			pstmt.setDate(22, vVEHVehiculo.getDtIniMantto());
			pstmt.setInt(23, vVEHVehiculo.getICveVehiculo());
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
				warn("updateBaja", ex1);
			}
			warn("updateBaja", ex);
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
				warn("updateBaja.close", ex2);
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
			TVVEHVehiculo vVEHVehiculo = (TVVEHVehiculo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VEHVehiculo" + " where iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHVehiculo.getICveVehiculo());
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
				warn("delete.closeVEHVehiculo", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All Detalle Mantenimiento
	 */
	public Vector FindByAllDetalleM(String sWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHVehiculo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHVehiculo vVEHVehiculo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " Select VehVehiculo.iCveVehiculo, VehTpoVehiculo.cDscBreve, "
					+ "VehVehiculo.cPlacas, VehMarca.cDscBreve, VehModelo.cDscBreve, "
					+ "VehVehiculo.cNumSerie, VehVehiculo.cNumMotor, VehVehiculo.cInventario, "
					+ "VehVehiculo.iAnioVeh "
					+ "from VehVehiculo "
					+ "Left Join VehTpoVehiculo on VehTpoVehiculo.iCveTpoVehiculo=VehVehiculo.iCveTpoVehiculo "
					+ "Left Join VehModelo on VehModelo.iCveModelo=VehVehiculo.iCveModelo "
					+ "and VehModelo.iCveMarca=VehVehiculo.iCveMarca "
					+ "Left Join VehMarca on VehMarca.iCveMarca=VehModelo.iCveMarca "
					+ sWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHVehiculo = new TVVEHVehiculo();
				vVEHVehiculo.setICveVehiculo(rset.getInt(1));
				vVEHVehiculo.setCDscTpoVehiculo(rset.getString(2));
				vVEHVehiculo.setCPlacas(rset.getString(3));
				vVEHVehiculo.setCDscMarca(rset.getString(4));
				vVEHVehiculo.setCDscModelo(rset.getString(5));
				vVEHVehiculo.setCNumSerie(rset.getString(6));
				vVEHVehiculo.setCNumMotor(rset.getString(7));
				vVEHVehiculo.setCInventario(rset.getString(8));
				vVEHVehiculo.setIAnioVeh(rset.getInt(9));
				vcVEHVehiculo.addElement(vVEHVehiculo);
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
			return vcVEHVehiculo;
		}
	}

	/**
	 * Metodo update
	 */
	public Object updateRevision(Connection conGral, Object obj)
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
			TVVEHVehiculo vVEHVehiculo = (TVVEHVehiculo) obj;
			cSQL = "update VEHVehiculo"
					+ " set iKmFinal = ? where iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHVehiculo.getIKmFinal());
			pstmt.setInt(2, vVEHVehiculo.getICveVehiculo());
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
}
