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
 * Title: Data Acces Object de VEHUbicacion DAO
 * </p>
 * <p>
 * Description: DAO para acceso a la tabla VEHUbicacion
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LSC. Rafael Miranda Blumenkron
 * @version 1.0
 */

public class TDVEHUbicacion extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHUbicacion() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHUbicacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHUbicacion vVEHUbicacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select "
					+ " VEHUbicacion.iCveVehiculo, "
					+ " VEHUbicacion.iCveUbicacion, "
					+ " VEHUbicacion.iCveUniMed, "
					+ " GRLUniMed.cDscUniMed, "
					+ " VEHUbicacion.dtAsignacion, "
					+ " VEHUbicacion.dtDesasigna, "
					+ " VEHUbicacion.lActivo, "
					+ " VEHVehiculo.cNumSerie, "
					+ " VEHVehiculo.cInventario, "
					+ " VEHVehiculo.cPlacas,  "
					+ " VEHTpoVehiculo.cDscBreve, "
					+ " VEHMarca.cDscBreve,  "
					+ " VEHModelo.cDscBreve,  "
					+ " VEHMtvoBaja.cDscMtvoBaja, "
					+ " VEHEstado.cDscEstadoVEH, "
					+ " VEHColor.cDscColor   "
					+ " from VEHUbicacion "
					+ " left join GRLUniMed ON GRLUniMed.iCveUniMed = VEHUbicacion.iCveUniMed "
					+ " left join VEHVehiculo ON VEHVehiculo.iCveVehiculo = VEHUbicacion.iCveVehiculo    "
					+ " left join VEHTpoVehiculo ON VEHTpoVehiculo.iCveTpoVehiculo = VEHVehiculo.iCveTpoVehiculo "
					+ " left join VEHMarca ON VEHMarca.iCveMarca = VEHVehiculo.iCveMarca   "
					+ " left join VEHModelo ON VEHModelo.iCveMarca = VEHVehiculo.iCveMarca AND "
					+ "                        VEHModelo.iCveModelo = VEHVehiculo.iCveModelo   "
					+ " left join VEHMtvoBaja ON VEHMtvoBaja.iCveMtvoBaja = VEHVehiculo.iCveMtvoBaja   "
					+ " left join VEHEstado ON VEHEstado.iCveEstadoVEH = VEHVehiculo.iCveEstadoVEH   "
					+ " left join VEHColor ON VEHColor.iCveColor = VEHVehiculo.iCveColor "
					+ cFiltro + " " + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHUbicacion = new TVVEHUbicacion();
				vVEHUbicacion.setICveVehiculo(rset.getInt(1));
				vVEHUbicacion.setICveUbicacion(rset.getInt(2));
				vVEHUbicacion.setICveUniMed(rset.getInt(3));
				vVEHUbicacion.setCDscUniMed(rset.getString(4));
				vVEHUbicacion.setDtAsignacion(rset.getDate(5));
				vVEHUbicacion.setDtDesasigna(rset.getDate(6));
				vVEHUbicacion.setLActivo(rset.getInt(7));

				vVEHUbicacion.setCNumSerie(rset.getString(8));
				vVEHUbicacion.setCInventario(rset.getString(9));
				vVEHUbicacion.setCPlacas(rset.getString(10));
				vVEHUbicacion.setCDscTpoVehiculo(rset.getString(11));
				vVEHUbicacion.setCDscMarca(rset.getString(12));
				vVEHUbicacion.setCDscModelo(rset.getString(13));
				vVEHUbicacion.setCDscMtvoBaja(rset.getString(14));
				vVEHUbicacion.setcDscEstadoVeh(rset.getString(15));
				vVEHUbicacion.setCDscColor(rset.getString(16));
				vcVEHUbicacion.addElement(vVEHUbicacion);
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
			return vcVEHUbicacion;
		}
	}

	/**
	 * 
	 * Metodo Insert
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHUbicacion vVEHUbicacion = (TVVEHUbicacion) obj;
			cSQL = "insert into VEHUbicacion(" + "iCveVehiculo,"
					+ "iCveUbicacion," + "iCveUniMed," + "dtAsignacion,"
					+ "dtDesasigna," + "lActivo" + ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vVEHUbicacion.getICveVehiculo());
			pstmt.setInt(2, vVEHUbicacion.getICveUbicacion());
			pstmt.setInt(3, vVEHUbicacion.getICveUniMed());
			pstmt.setDate(4, vVEHUbicacion.getDtAsignacion());
			pstmt.setDate(5, vVEHUbicacion.getDtDesasigna());
			pstmt.setInt(6, vVEHUbicacion.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vVEHUbicacion.getICveVehiculo();
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
					dbConn.closeConnection();
				}
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHUbicacion vVEHUbicacion = (TVVEHUbicacion) obj;
			cSQL = "update VEHUbicacion" + " set iCveUniMed= ?, "
					+ "dtAsignacion= ?, " + "dtDesasigna= ?, " + "lActivo= ? "
					+ "where iCveVehiculo = ? " + " and iCveUbicacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHUbicacion.getICveUniMed());
			pstmt.setDate(2, vVEHUbicacion.getDtAsignacion());
			pstmt.setDate(3, vVEHUbicacion.getDtDesasigna());
			pstmt.setInt(4, vVEHUbicacion.getLActivo());
			pstmt.setInt(5, vVEHUbicacion.getICveVehiculo());
			pstmt.setInt(6, vVEHUbicacion.getICveUbicacion());
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
				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);
			}
			String cSQL = "";
			TVVEHUbicacion vVEHUbicacion = (TVVEHUbicacion) obj;
			cSQL = "delete from VEHUbicacion" + " where iCveVehiculo = ?"
					+ " and iCveUbicacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHUbicacion.getICveVehiculo());
			pstmt.setInt(2, vVEHUbicacion.getICveUbicacion());
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
					dbConn.closeConnection();
				}
			} catch (Exception ex2) {
				warn("delete.closeVEHUbicacion", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Disable
	 */
	public Object disable(Connection conGral, Object obj) throws DAOException {
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
			TVVEHUbicacion vVEHUbicacion = (TVVEHUbicacion) obj;
			cSQL = "update VEHUbicacion" + " set lActivo= ? "
					+ "where iCveVehiculo = ?" + " and iCveUbicacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHUbicacion.getLActivo());
			pstmt.setInt(2, vVEHUbicacion.getICveVehiculo());
			pstmt.setInt(3, vVEHUbicacion.getICveUbicacion());
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
				warn("disable.close", ex2);
			}
			return cClave;
		}
	}

	public Object insertWithSequence(Connection conGral, Object obj)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iMax = 0;
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
			TVVEHUbicacion vVEHUbicacion = (TVVEHUbicacion) obj;
			cSQL = "SELECT MAX(iCveUbicacion) FROM VEHUbicacion WHERE iCveVehiculo= ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHUbicacion.getICveVehiculo());
			rset = pstmt.executeQuery();
			if (rset.next())
				iMax = rset.getInt(1);
			iMax++;
			vVEHUbicacion.setICveUbicacion(iMax);
			cClave = "" + this.insert(conn, vVEHUbicacion);
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("insertWithSequence", ex1);
			}
			warn("insertWithSequence", ex);
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
				warn("insertWithSequence.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All Ubicacion de Vehiculos
	 */
	public Vector FindByAllUbicacion(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHUbicacion = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHUbicacion vVEHUbicacion;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select VehVehiculo.iCveVehiculo, VehUbicacion.iCveUbicacion, VehTpoVehiculo.cDscTpoVehiculo, "
					+ "VehMarca.cDscMarca, VehModelo.cDscModelo, VehVehiculo.cNumSerie, VehVehiculo.cPlacas "
					+ "from VehVehiculo "
					+ "left join VehUbicacion on VehVehiculo.iCveVehiculo=VehUbicacion.iCveVehiculo "
					+ "     and VehUbicacion.lActivo=1 "
					+ "left join VehTpoVehiculo on VehTpoVehiculo.iCveTpoVehiculo=VehVehiculo.iCveTpoVehiculo "
					+ "join VehModelo on VehModelo.iCveMarca=VehVehiculo.iCveMarca "
					+ "     and VehModelo.iCveModelo=VehVehiculo.iCveModelo "
					+ "left join VehMarca on VehMarca.iCveMarca=VehModelo.iCveMarca "
					+ "where VehVehiculo.lBaja=0" + cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHUbicacion = new TVVEHUbicacion();
				vVEHUbicacion.setICveVehiculo(rset.getInt(1));
				vVEHUbicacion.setICveUbicacion(rset.getInt(2));
				vVEHUbicacion.setCDscTpoVehiculo(rset.getObject(3) == null ? ""
						: rset.getString(3));
				vVEHUbicacion.setCDscMarca(rset.getString(4));
				vVEHUbicacion.setCDscModelo(rset.getString(5));
				vVEHUbicacion.setCNumSerie(rset.getString(6));
				vVEHUbicacion.setCPlacas(rset.getString(7));
				vcVEHUbicacion.addElement(vVEHUbicacion);
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
			return vcVEHUbicacion;
		}
	}

	/**
	 * Metodo update Ubicaci�n
	 */
	public Object updateUbicacion(Connection conGral, Object obj)
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
			TVVEHUbicacion vVEHUbicacion = (TVVEHUbicacion) obj;
			cSQL = "update VEHUbicacion" + " set dtDesasigna= ?, "
					+ "lActivo= ? " + "where iCveVehiculo = ? "
					+ " and iCveUbicacion = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vVEHUbicacion.getDtDesasigna());
			pstmt.setInt(2, vVEHUbicacion.getLActivo());
			pstmt.setInt(3, vVEHUbicacion.getICveVehiculo());
			pstmt.setInt(4, vVEHUbicacion.getICveUbicacion());
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