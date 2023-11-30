package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de CTRRepresentante DAO
 * </p>
 * <p>
 * Description: Data Access Object Tabla CTRRepresentante
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

public class TDCTRRepresentante extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDCTRRepresentante() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String where) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRRepresentante = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRRepresentante vCTRRepresentante;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveEmpresa," + "iCveRepresentante," + "cRFC,"
					+ "cCURP," + "cNombre," + "cApPaterno," + "cApMaterno,"
					+ "cPuesto," + "cCalle," + "cColonia," + "iCP,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "cTel,"
					+ "cFax," + "cCorreoElec," + "lActivo, " + "cObservacion "
					+ " from CTRRepresentante " + where;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRRepresentante = new TVCTRRepresentante();
				vCTRRepresentante.setICveEmpresa(rset.getInt(1));
				vCTRRepresentante.setICveRepresentante(rset.getInt(2));
				vCTRRepresentante.setCRFC(rset.getString(3));
				vCTRRepresentante.setCCURP(rset.getString(4));
				vCTRRepresentante.setCNombre(rset.getString(5));
				vCTRRepresentante.setCApPaterno(rset.getString(6));
				vCTRRepresentante.setCApMaterno(rset.getString(7));
				vCTRRepresentante.setCPuesto(rset.getString(8));
				vCTRRepresentante.setCCalle(rset.getString(9));
				vCTRRepresentante.setCColonia(rset.getString(10));
				vCTRRepresentante.setICP(rset.getInt(11));
				vCTRRepresentante.setICvePais(rset.getInt(12));
				vCTRRepresentante.setICveEstado(rset.getInt(13));
				vCTRRepresentante.setICveMunicipio(rset.getInt(14));
				vCTRRepresentante.setCTel(rset.getString(15));
				vCTRRepresentante.setCFax(rset.getString(16));
				vCTRRepresentante.setCCorreoElec(rset.getString(17));
				vCTRRepresentante.setLActivo(rset.getInt(18));
				vCTRRepresentante.setcObservacion(rset.getString(19));
				vcCTRRepresentante.addElement(vCTRRepresentante);
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
			return vcCTRRepresentante;
		}
	}

	/**
	 * Metodo Find By All Representante join Pais, Estado, Municipio
	 */
	public Vector FindByAllRepPaisEdoMcpio(String where) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRRepresentante = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRRepresentante vCTRRepresentante;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select CTRRepresentante.iCveEmpresa,CTRRepresentante.iCveRepresentante,"
					+ "CTRRepresentante.cRFC,CTRRepresentante.cCURP,CTRRepresentante.cNombre,"
					+ "CTRRepresentante.cApPaterno,CTRRepresentante.cApMaterno,CTRRepresentante.cPuesto,"
					+ "CTRRepresentante.cCalle,CTRRepresentante.cColonia,CTRRepresentante.iCP,"
					+ "CTRRepresentante.iCvePais,CTRRepresentante.iCveEstado,"
					+ "CTRRepresentante.iCveMunicipio,CTRRepresentante.cTel,CTRRepresentante.cFax,"
					+ "CTRRepresentante.cCorreoElec,CTRRepresentante.lActivo,"
					+ "GrlPais.cNombre,GrlEntidadFed.cNombre,GrlMunicipio.cNombre, "
					+ "GrlEmpresas.cIDDGPMPT, GrlEmpresas.iIdMdoTrans, GrlMdoTrans.cDscMdoTrans, CTRRepresentante.cObservacion "
					+ "from CTRRepresentante "
					+ "join GrlPais on CTRRepresentante.iCvePais=GrlPais.iCvePais "
					+ "join GrlEntidadFed on CTRRepresentante.iCvePais=GrlEntidadFed.iCvePais "
					+ "and CTRRepresentante.iCveEstado=GrlEntidadFed.iCveEntidadFed "
					+ "join GrlMunicipio on CTRRepresentante.iCvePais=GrlMunicipio.iCvePais "
					+ "and CTRRepresentante.iCveEstado=GrlMunicipio.iCveEntidadFed "
					+ "and CTRRepresentante.iCveMunicipio=GrlMunicipio.iCveMunicipio "
					+ "join GrlEmpresas on CTRRepresentante.iCveEmpresa=GrlEmpresas.iCveEmpresa "
					+ "left join GrlMdoTrans on GrlEmpresas.iCveMdoTrans=GrlMdoTrans.iCveMdoTrans "
					+ where;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRRepresentante = new TVCTRRepresentante();
				vCTRRepresentante.setICveEmpresa(rset.getInt(1));
				vCTRRepresentante.setICveRepresentante(rset.getInt(2));
				vCTRRepresentante.setCRFC(rset.getString(3));
				vCTRRepresentante.setCCURP(rset.getString(4));
				vCTRRepresentante.setCNombre(rset.getString(5));
				vCTRRepresentante.setCApPaterno(rset.getString(6));
				vCTRRepresentante.setCApMaterno(rset.getString(7));
				vCTRRepresentante.setCPuesto(rset.getString(8));
				vCTRRepresentante.setCCalle(rset.getString(9));
				vCTRRepresentante.setCColonia(rset.getString(10));
				vCTRRepresentante.setICP(rset.getInt(11));
				vCTRRepresentante.setICvePais(rset.getInt(12));
				vCTRRepresentante.setICveEstado(rset.getInt(13));
				vCTRRepresentante.setICveMunicipio(rset.getInt(14));
				vCTRRepresentante.setCTel(rset.getString(15));
				vCTRRepresentante.setCFax(rset.getString(16));
				vCTRRepresentante.setCCorreoElec(rset.getString(17));
				vCTRRepresentante.setLActivo(rset.getInt(18));
				vCTRRepresentante.setCPais(rset.getString(19));
				vCTRRepresentante.setCDscEstado(rset.getString(20));
				vCTRRepresentante.setCMunicipio(rset.getString(21));
				vCTRRepresentante.setcIDDGPMPT(rset.getString(22));
				vCTRRepresentante.setIIDMdoTrans(rset.getInt(23));
				vCTRRepresentante.setCDscMdoTrans(rset.getString(24));
				vCTRRepresentante.setcObservacion(rset.getString(25));
				vcCTRRepresentante.addElement(vCTRRepresentante);
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
			return vcCTRRepresentante;
		}
	}

	/**
	 * Metodo Find By All Empresas por usuario
	 */
	public Vector FindByAllEmprUsu(int iUsuario) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vEmpresas = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLEmpresas tvEmpresas;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select GrlEmpresas.iCveEmpresa,GrlEmpresas.cDscEmpresa "
					+ "From GrlEmpresas "
					+ "join GrlUmUsuario on GrlEmpresas.iCveUniMed=GrlUmUsuario.iCveUniMed "
					+ "where GrlUmUsuario.iCveProceso=5 and GrlUmUsuario.iCveUsuario=?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iUsuario);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				tvEmpresas = new TVGRLEmpresas();
				tvEmpresas.setICveEmpresa(rset.getInt(1));
				tvEmpresas.setcDscEmpresa(rset.getString(2));
				vEmpresas.addElement(tvEmpresas);
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
			return vEmpresas;
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
			}
			String cSQL = "";
			TVCTRRepresentante vCTRRepresentante = (TVCTRRepresentante) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into CTRRepresentante(" + "iCveEmpresa,"
					+ "iCveRepresentante," + "cRFC," + "cCURP," + "cNombre,"
					+ "cApPaterno," + "cApMaterno," + "cPuesto," + "cCalle,"
					+ "cColonia," + "iCP," + "iCvePais," + "iCveEstado,"
					+ "iCveMunicipio," + "cTel," + "cFax," + "cCorreoElec,"
					+ "lActivo, " + "cObservacion "
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String q = "select max(icveRepresentante) from CtrRepresentante Where iCveEmpresa="
					+ vCTRRepresentante.getICveEmpresa();
			int iNewCve = 0;
			PreparedStatement psNewCve = conn.prepareStatement(q);
			ResultSet rsNewCve = psNewCve.executeQuery();
			while (rsNewCve.next()) {
				iNewCve = rsNewCve.getInt(1) + 1;
			}
			rsNewCve.close();
			psNewCve.close();

			// Actualizar registro Activo
			String qa = "update CtrRepresentante set lActivo = 0 where iCveEmpresa=?";
			PreparedStatement psAct = conn.prepareStatement(qa);
			psAct.setInt(1, vCTRRepresentante.getICveEmpresa());
			psAct.executeUpdate();
			psAct.close();

			pstmt.setInt(1, vCTRRepresentante.getICveEmpresa());
			pstmt.setInt(2, iNewCve);
			pstmt.setString(3, vCTRRepresentante.getCRFC());
			pstmt.setString(4, vCTRRepresentante.getCCURP());
			pstmt.setString(5, vCTRRepresentante.getCNombre());
			pstmt.setString(6, vCTRRepresentante.getCApPaterno());
			pstmt.setString(7, vCTRRepresentante.getCApMaterno());
			pstmt.setString(8, vCTRRepresentante.getCPuesto());
			pstmt.setString(9, vCTRRepresentante.getCCalle());
			pstmt.setString(10, vCTRRepresentante.getCColonia());
			pstmt.setInt(11, vCTRRepresentante.getICP());
			pstmt.setInt(12, vCTRRepresentante.getICvePais());
			pstmt.setInt(13, vCTRRepresentante.getICveEstado());
			pstmt.setInt(14, vCTRRepresentante.getICveMunicipio());
			pstmt.setString(15, vCTRRepresentante.getCTel());
			pstmt.setString(16, vCTRRepresentante.getCFax());
			pstmt.setString(17, vCTRRepresentante.getCCorreoElec());
			pstmt.setInt(18, 1);
			pstmt.setString(19, vCTRRepresentante.getcObservacion());
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
	 * Metodo disable
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
			TVCTRRepresentante vCTRRepresentante = (TVCTRRepresentante) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRRepresentante" + " set lActivo= ? "
					+ "where iCveEmpresa = ? " + " and iCveRepresentante = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vCTRRepresentante.getICveEmpresa());
			pstmt.setInt(3, vCTRRepresentante.getICveRepresentante());
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
			TVCTRRepresentante vCTRRepresentante = (TVCTRRepresentante) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRRepresentante" + " set cRFC= ?, " + "cCURP= ?, "
					+ "cNombre= ?, " + "cApPaterno= ?, " + "cApMaterno= ?, "
					+ "cPuesto= ?, " + "cCalle= ?, " + "cColonia= ?, "
					+ "iCP= ?, " + "iCvePais= ?, " + "iCveEstado= ?, "
					+ "iCveMunicipio= ?, " + "cTel= ?, " + "cFax= ?, "
					+ "cCorreoElec= ?, " + "cObservacion = ? "
					+ "where iCveEmpresa = ? " + " and iCveRepresentante = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vCTRRepresentante.getCRFC());
			pstmt.setString(2, vCTRRepresentante.getCCURP());
			pstmt.setString(3, vCTRRepresentante.getCNombre());
			pstmt.setString(4, vCTRRepresentante.getCApPaterno());
			pstmt.setString(5, vCTRRepresentante.getCApMaterno());
			pstmt.setString(6, vCTRRepresentante.getCPuesto());
			pstmt.setString(7, vCTRRepresentante.getCCalle());
			pstmt.setString(8, vCTRRepresentante.getCColonia());
			pstmt.setInt(9, vCTRRepresentante.getICP());
			pstmt.setInt(10, vCTRRepresentante.getICvePais());
			pstmt.setInt(11, vCTRRepresentante.getICveEstado());
			pstmt.setInt(12, vCTRRepresentante.getICveMunicipio());
			pstmt.setString(13, vCTRRepresentante.getCTel());
			pstmt.setString(14, vCTRRepresentante.getCFax());
			pstmt.setString(15, vCTRRepresentante.getCCorreoElec());
			pstmt.setString(16, vCTRRepresentante.getcObservacion());
			pstmt.setInt(17, vCTRRepresentante.getICveEmpresa());
			pstmt.setInt(18, vCTRRepresentante.getICveRepresentante());
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
			TVCTRRepresentante vCTRRepresentante = (TVCTRRepresentante) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from CTRRepresentante" + " where iCveEmpresa = ?"
					+ " and iCveRepresentante = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRRepresentante.getICveEmpresa());
			pstmt.setInt(2, vCTRRepresentante.getICveRepresentante());
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
				warn("delete.closeCTRRepresentante", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo cambiarRepresentante
	 */
	public Object cambiarRepresentante(Connection conGral, int iEmp, int iRep)
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
			cSQL = "update CTRRepresentante" + " set lActivo= ? "
					+ " where iCveEmpresa = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, iEmp);
			pstmt.executeUpdate();

			String cUpd = "Update CTRRepresentante " + " set lActivo=? "
					+ " where iCveEmpresa=? " + " and iCveRepresentante=?";
			PreparedStatement psNewRep = conn.prepareStatement(cUpd);
			psNewRep.setInt(1, 1);
			psNewRep.setInt(2, iEmp);
			psNewRep.setInt(3, iRep);
			psNewRep.executeUpdate();
			psNewRep.close();

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

}