package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de VEHEmpMantto DAO
 * </p>
 * <p>
 * Description: Control de Veh�culos - Empresas de Mantenimiento
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

public class TDVEHEmpMantto extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDVEHEmpMantto() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHEmpMantto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHEmpMantto vVEHEmpMantto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpMantto," + "cRFC," + "cCURP,"
					+ "cTpoPersona," + "cApPaterno," + "cApMaterno,"
					+ "cNombreRS," + "cDscEmpMantto," + "cContacto,"
					+ "cCalle," + "cColonia," + "cCiudad," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cDscBreve," + "iCP,"
					+ "cTel," + "cFax," + "cCorreoElec," + "lActivo"
					+ " from VEHEmpMantto order by iCveEmpMantto";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHEmpMantto = new TVVEHEmpMantto();
				vVEHEmpMantto.setICveEmpMantto(rset.getInt(1));
				vVEHEmpMantto.setCRFC(rset.getString(2));
				vVEHEmpMantto.setCCURP(rset.getString(3));
				vVEHEmpMantto.setCTpoPersona(rset.getString(4));
				vVEHEmpMantto.setCApPaterno(rset.getString(5));
				vVEHEmpMantto.setCApMaterno(rset.getString(6));
				vVEHEmpMantto.setCNombreRS(rset.getString(7));
				vVEHEmpMantto.setCDscEmpMantto(rset.getString(8));
				vVEHEmpMantto.setCContacto(rset.getString(9));
				vVEHEmpMantto.setCCalle(rset.getString(10));
				vVEHEmpMantto.setCColonia(rset.getString(11));
				vVEHEmpMantto.setCCiudad(rset.getString(12));
				vVEHEmpMantto.setICvePais(rset.getInt(13));
				vVEHEmpMantto.setICveEstado(rset.getInt(14));
				vVEHEmpMantto.setICveMunicipio(rset.getInt(15));
				vVEHEmpMantto.setCDscBreve(rset.getString(16));
				vVEHEmpMantto.setICP(rset.getInt(17));
				vVEHEmpMantto.setCTel(rset.getString(18));
				vVEHEmpMantto.setCFax(rset.getString(19));
				vVEHEmpMantto.setCCorreoElec(rset.getString(20));
				vVEHEmpMantto.setLActivo(rset.getInt(21));
				vcVEHEmpMantto.addElement(vVEHEmpMantto);
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
			return vcVEHEmpMantto;
		}
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcVEHEmpMantto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVVEHEmpMantto vVEHEmpMantto;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "iCveEmpMantto,cRFC,cCURP,cTpoPersona,cApPaterno,cApMaterno,cNombreRS,"
					+ "cDscEmpMantto,cContacto,cCalle,cColonia,cCiudad,"
					+ "VEHEmpMantto.iCvePais,"
					+ "VEHEmpMantto.iCveEstado,"
					+ "VEHEmpMantto.iCveMunicipio,"
					+ "cDscBreve,iCP,cTel,cFax,cCorreoElec,"
					+ "lActivo,GRLPais.cNombre,GRLEntidadFed.cNombre,GRLMunicipio.cNombre"
					+ " from VEHEmpMantto "
					+ " left join GRLPais ON GRLPais.iCvePais = VEHEmpMantto.iCvePais "
					+ " left join GRLEntidadFed ON GRLEntidadFed.iCvePais = VEHEmpMantto.iCvePais "
					+ "      AND GRLEntidadFed.iCveEntidadFed = VEHEmpMantto.iCveEstado "
					+ " left join GRLMunicipio ON GRLMunicipio.iCvePais = VEHEmpMantto.iCvePais "
					+ "      AND GRLMunicipio.iCveEntidadFed = VEHEmpMantto.iCveEstado "
					+ "      AND GRLMunicipio.iCveMunicipio = VEHEmpMantto.iCveMunicipio "
					+ cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vVEHEmpMantto = new TVVEHEmpMantto();
				vVEHEmpMantto.setICveEmpMantto(rset.getInt(1));
				vVEHEmpMantto.setCRFC(rset.getString(2));
				vVEHEmpMantto.setCCURP(rset.getString(3));
				vVEHEmpMantto.setCTpoPersona(rset.getString(4));
				vVEHEmpMantto.setCApPaterno(rset.getString(5));
				vVEHEmpMantto.setCApMaterno(rset.getString(6));
				vVEHEmpMantto.setCNombreRS(rset.getString(7));
				vVEHEmpMantto.setCDscEmpMantto(rset.getString(8));
				vVEHEmpMantto.setCContacto(rset.getString(9));
				vVEHEmpMantto.setCCalle(rset.getString(10));
				vVEHEmpMantto.setCColonia(rset.getString(11));
				vVEHEmpMantto.setCCiudad(rset.getString(12));
				vVEHEmpMantto.setICvePais(rset.getInt(13));
				vVEHEmpMantto.setICveEstado(rset.getInt(14));
				vVEHEmpMantto.setICveMunicipio(rset.getInt(15));
				vVEHEmpMantto.setCDscBreve(rset.getString(16));
				vVEHEmpMantto.setICP(rset.getInt(17));
				vVEHEmpMantto.setCTel(rset.getString(18));
				vVEHEmpMantto.setCFax(rset.getString(19));
				vVEHEmpMantto.setCCorreoElec(rset.getString(20));
				vVEHEmpMantto.setLActivo(rset.getInt(21));
				vVEHEmpMantto.setCDscPais(rset.getString(22));
				vVEHEmpMantto.setCDscEstado(rset.getString(23));
				vVEHEmpMantto.setCDscMunicipio(rset.getString(24));
				vcVEHEmpMantto.addElement(vVEHEmpMantto);
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
			return vcVEHEmpMantto;
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
			TVVEHEmpMantto vVEHEmpMantto = (TVVEHEmpMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into VEHEmpMantto(" + "iCveEmpMantto," + "cRFC,"
					+ "cCURP," + "cTpoPersona," + "cApPaterno," + "cApMaterno,"
					+ "cNombreRS," + "cDscEmpMantto," + "cContacto,"
					+ "cCalle," + "cColonia," + "cCiudad," + "iCvePais,"
					+ "iCveEstado," + "iCveMunicipio," + "cDscBreve," + "iCP,"
					+ "cTel," + "cFax," + "cCorreoElec," + "lActivo"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveEmpMantto) from VEHEmpMantto ";
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
			vVEHEmpMantto.setICveEmpMantto(iMax);
			// ******************************************************************
			pstmt.setInt(1, vVEHEmpMantto.getICveEmpMantto());
			pstmt.setString(2, vVEHEmpMantto.getCRFC().toUpperCase().trim());
			pstmt.setString(3, vVEHEmpMantto.getCCURP().toUpperCase().trim());
			pstmt.setString(4, vVEHEmpMantto.getCTpoPersona().toUpperCase()
					.trim());
			pstmt.setString(5, vVEHEmpMantto.getCApPaterno().toUpperCase()
					.trim());
			pstmt.setString(6, vVEHEmpMantto.getCApMaterno().toUpperCase()
					.trim());
			pstmt.setString(7, vVEHEmpMantto.getCNombreRS().toUpperCase()
					.trim());
			pstmt.setString(8, vVEHEmpMantto.getCDscEmpMantto().toUpperCase()
					.trim());
			pstmt.setString(9, vVEHEmpMantto.getCContacto().toUpperCase()
					.trim());
			pstmt.setString(10, vVEHEmpMantto.getCCalle().toUpperCase().trim());
			pstmt.setString(11, vVEHEmpMantto.getCColonia().toUpperCase()
					.trim());
			pstmt.setString(12, vVEHEmpMantto.getCCiudad().toUpperCase().trim());
			pstmt.setInt(13, vVEHEmpMantto.getICvePais());
			pstmt.setInt(14, vVEHEmpMantto.getICveEstado());
			pstmt.setInt(15, vVEHEmpMantto.getICveMunicipio());
			pstmt.setString(16, vVEHEmpMantto.getCDscBreve());
			pstmt.setInt(17, vVEHEmpMantto.getICP());
			pstmt.setString(18, vVEHEmpMantto.getCTel());
			pstmt.setString(19, vVEHEmpMantto.getCFax().toUpperCase().trim());
			pstmt.setString(20, vVEHEmpMantto.getCCorreoElec().toUpperCase()
					.trim());
			pstmt.setInt(21, vVEHEmpMantto.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vVEHEmpMantto.getICveEmpMantto();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
				}
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
			TVVEHEmpMantto vVEHEmpMantto = (TVVEHEmpMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHEmpMantto" + " set cRFC= ?, " + "cCURP= ?, "
					+ "cTpoPersona= ?, " + "cApPaterno= ?, "
					+ "cApMaterno= ?, " + "cNombreRS= ?, "
					+ "cDscEmpMantto= ?, " + "cContacto= ?, " + "cCalle= ?, "
					+ "cColonia= ?, " + "cCiudad= ?, " + "iCvePais= ?, "
					+ "iCveEstado= ?, " + "iCveMunicipio= ?, "
					+ "cDscBreve= ?, " + "iCP= ?, " + "cTel= ?, " + "cFax= ?, "
					+ "cCorreoElec= ?, " + "lActivo= ? "
					+ "where iCveEmpMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vVEHEmpMantto.getCRFC().toUpperCase().trim());
			pstmt.setString(2, vVEHEmpMantto.getCCURP().toUpperCase().trim());
			pstmt.setString(3, vVEHEmpMantto.getCTpoPersona().toUpperCase()
					.trim());
			pstmt.setString(4, vVEHEmpMantto.getCApPaterno().toUpperCase()
					.trim());
			pstmt.setString(5, vVEHEmpMantto.getCApMaterno().toUpperCase()
					.trim());
			pstmt.setString(6, vVEHEmpMantto.getCNombreRS().toUpperCase()
					.trim());
			pstmt.setString(7, vVEHEmpMantto.getCDscEmpMantto().toUpperCase()
					.trim());
			pstmt.setString(8, vVEHEmpMantto.getCContacto().toUpperCase()
					.trim());
			pstmt.setString(9, vVEHEmpMantto.getCCalle().toUpperCase().trim());
			pstmt.setString(10, vVEHEmpMantto.getCColonia().toUpperCase()
					.trim());
			pstmt.setString(11, vVEHEmpMantto.getCCiudad().toUpperCase().trim());
			pstmt.setInt(12, vVEHEmpMantto.getICvePais());
			pstmt.setInt(13, vVEHEmpMantto.getICveEstado());
			pstmt.setInt(14, vVEHEmpMantto.getICveMunicipio());
			pstmt.setString(15, vVEHEmpMantto.getCDscBreve().toUpperCase()
					.trim());
			pstmt.setInt(16, vVEHEmpMantto.getICP());
			pstmt.setString(17, vVEHEmpMantto.getCTel().toUpperCase().trim());
			pstmt.setString(18, vVEHEmpMantto.getCFax().toUpperCase().trim());
			pstmt.setString(19, vVEHEmpMantto.getCCorreoElec().toUpperCase()
					.trim());
			pstmt.setInt(20, vVEHEmpMantto.getLActivo());
			pstmt.setInt(21, vVEHEmpMantto.getICveEmpMantto());
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
			TVVEHEmpMantto vVEHEmpMantto = (TVVEHEmpMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from VEHEmpMantto" + " where iCveEmpMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vVEHEmpMantto.getICveEmpMantto());
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
				warn("delete.closeVEHEmpMantto", ex2);
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
			}
			String cSQL = "";
			TVVEHEmpMantto vVEHEmpMantto = (TVVEHEmpMantto) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update VEHEmpMantto" + " set lActivo= ? "
					+ "where iCveEmpMantto = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vVEHEmpMantto.getICveEmpMantto());
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
				warn("disable.close", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo buscaMantXEmp
	 */
	public Vector buscaMantXEmp(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		TVVEHEmpMantto tvEmpMantto;
		Vector vEmpMantto = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL =

			" SELECT m.*, mar.cDscMarca, em.cDscEmpMantto, tv.cDscTpoVehiculo, tm.cDscTpoMantto, mod.cDscModelo, "
					+ " p.cNombre as cDscPais, ef.cNombre as cDscEntidadFed, mun.cNombre as cDscMunicipio, v.cPlacas "
					+ " FROM VEHMantenimiento m "
					+ " JOIN VEHEmpMantto em  ON em.iCveEmpMantto = m.iCveEmpMantto "
					+ " JOIN VEHVehiculo v ON v.iCveVehiculo = m.iCveVehiculo "
					+ " JOIN VEHTpoVehiculo  tv ON tv.iCveTpoVehiculo = v.iCveTpoVehiculo "
					+ " JOIN VEHMarca  mar ON mar.iCveMarca = v.iCveMarca "
					+ " JOIN VEHModelo  mod ON mod.iCveMarca = v.iCveMarca "
					+ " AND mod.iCveModelo = v.iCveModelo "
					+ " JOIN VEHTpoMantto tm ON tm.iCveTpoMantto = m.iCveTpoMantto "
					+ " JOIN GRLPais p ON p.iCvePais = em.iCvePais "
					+ " JOIN GRLEntidadFed ef ON ef.iCvePais = em.iCvePais "
					+ " AND ef.iCveEntidadFed = em.iCveEstado "
					+ " JOIN GRLMunicipio mun ON mun.iCvePais = em.iCvePais "
					+ " AND mun.iCveEntidadFed = em.iCveEstado "
					+ " AND mun.iCveMunicipio = em.iCveMunicipio " + cFiltro;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(cSQL);
			while (rset.next()) {
				tvEmpMantto = new TVVEHEmpMantto();
				tvEmpMantto.setICveEmpMantto(rset.getInt("iCveEmpMantto"));
				tvEmpMantto.setLConcluido(rset.getInt("lConcluido"));
				tvEmpMantto.setLCancelado(rset.getInt("lCancelado"));
				tvEmpMantto.setIKilometraje(rset.getInt("iKilometraje"));
				tvEmpMantto.setCDscPais(rset.getString("cDscPais"));
				tvEmpMantto.setCDscEstado(rset.getString("cDscEntidadFed"));
				tvEmpMantto.setCDscMunicipio(rset.getString("cDscMunicipio"));
				tvEmpMantto.setCDscEmpMantto(rset.getString("cDscEmpMantto"));
				tvEmpMantto.setDtSolicitud(rset.getDate("dtSolicitud"));
				tvEmpMantto.setDtProgramado(rset.getDate("dtProgramado"));
				tvEmpMantto.setDtInicia(rset.getDate("dtInicia"));
				tvEmpMantto.setDtRecepcion(rset.getDate("dtRecepcion"));
				tvEmpMantto.setDtCancelacion(rset.getDate("dtCancelacion"));
				tvEmpMantto.setCResultado(rset.getString("cResultado"));
				tvEmpMantto.setCObservaciones(rset.getString("cObservaciones"));
				tvEmpMantto.setCDscMarca(rset.getString("cDscMarca"));
				tvEmpMantto.setCDscTpoVehiculo(rset
						.getString("cDscTpoVehiculo"));
				tvEmpMantto.setCDscTpoMantto(rset.getString("cDscTpoMantto"));
				tvEmpMantto.setCDscModelo(rset.getString("cDscModelo"));
				tvEmpMantto.setCPlacas(rset.getString("cPlacas"));
				vEmpMantto.addElement(tvEmpMantto);
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
			return vEmpMantto;
		}
	}

}
