package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLUniMed
 * </p>
 * <p>
 * Description: Data Acces Object de GRLUniMed
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

public class TDGRLAyudaDGPMPT extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private String dataSourceName2 = VParametros.getPropEspecifica("ConDB");
	public String Imagenes = VParametros.getPropEspecifica("RutaImgServer");

	public TDGRLAyudaDGPMPT() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		return FindByAll(new HashMap(), " order by ICVEAYUDA");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cOrderBy) throws DAOException {
		return FindByAll(new HashMap(), cOrderBy);
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros) throws DAOException {
		return FindByAll(hmFiltros, " order by ICVEAYUDA");
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(HashMap hmFiltros, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUniMed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLUniMed vGRLUniMed;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cWhereAnd = " where";
			String cSQL = "select "
					+ "iCveUniMed,cDscUniMed,iCvePais,iCveEstado,iCveMunicipio,lCis,lPago "
					+ "from GRLUniMed";
			String cCveUniMed = (String) hmFiltros.get("iCveUniMed");
			String cCis = (String) hmFiltros.get("lCis");
			if (cCveUniMed != null) {
				cSQL += " where iCveUniMed=?";
				cWhereAnd = " and";
			}
			if (cCis != null) {
				cSQL += cWhereAnd + " lCis=?";
			}
			if (cOrderBy != null) {
				cSQL += cOrderBy;
			}
			pstmt = conn.prepareStatement(cSQL);
			int i = 1;
			if (cCveUniMed != null) {
				pstmt.setInt(i++, Integer.parseInt(cCveUniMed));
			}
			if (cCis != null) {
				pstmt.setInt(i++, Integer.parseInt(cCis));
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUniMed = new TVGRLUniMed();
				vGRLUniMed.setICveUniMed(rset.getInt("iCveUniMed"));
				vGRLUniMed.setCDscUniMed(rset.getString("cDscUniMed"));
				vGRLUniMed.setICvePais(rset.getInt("iCvePais"));
				vGRLUniMed.setICveEstado(rset.getInt("iCveEstado"));
				vGRLUniMed.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLUniMed.setLCis(rset.getInt("lCis"));
				vGRLUniMed.setLPago(rset.getInt("lPago"));
				vcGRLUniMed.addElement(vGRLUniMed);
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
		}
		return vcGRLUniMed;
	}

	/**
	 * Metodo Find By All con Filtro y Orden
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLAyudaDGPMPT = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLAyudaDGPMPT vGRLAyudaDGPMPT;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT ICVEAYUDA, " + "CDSAYUDA, " + "CDSCDESCRIPCION, "
					+ "LMANUAL, " + "LSOFTWARE, " + "LGUIA, " + "CURL, "
					+ "LVIGENTE, " + "LADMIN " + "FROM GRLAyudaDGPMPT   "
					+ cWhere + cOrderBy;

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLAyudaDGPMPT = new TVGRLAyudaDGPMPT();
				vGRLAyudaDGPMPT.setICveAyuda(rset.getInt(1));
				vGRLAyudaDGPMPT.setCDsAyuda(rset.getString(2));
				vGRLAyudaDGPMPT.setCDscDescripcion(rset.getString(3));
				vGRLAyudaDGPMPT.setLManual(rset.getInt(4));
				vGRLAyudaDGPMPT.setLSoftware(rset.getInt(5));
				vGRLAyudaDGPMPT.setLGuia(rset.getInt(6));
				vGRLAyudaDGPMPT.setCUrl(rset.getString(7));
				vGRLAyudaDGPMPT.setLVigente(rset.getInt(8));
				vGRLAyudaDGPMPT.setLAdmin(rset.getInt(9));
				vcGRLAyudaDGPMPT.addElement(vGRLAyudaDGPMPT);
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
			return vcGRLAyudaDGPMPT;
		}
	}

	/**
	 * Metodo FindUniMed
	 * 
	 * @author Marco A. Gonz�lez Paz.
	 */
	public Vector FindUniMed(String cUniMed) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUniMed = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVGRLUniMed vGRLUniMed;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "iCveUniMed," + "cDscUniMed,"
					+ "iCvePais," + "iCveEstado," + "iCveMunicipio," + "lCis,"
					+ "lPago, " + "icveuddadmiva " + "from GRLUniMed ";
			cSQL = cSQL + " where iCveUniMed = " + cUniMed;

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUniMed = new TVGRLUniMed();
				vGRLUniMed.setICveUniMed(rset.getInt("iCveUniMed"));
				vGRLUniMed.setCDscUniMed(rset.getString("cDscUniMed"));
				vGRLUniMed.setICvePais(rset.getInt("iCvePais"));
				vGRLUniMed.setICveEstado(rset.getInt("iCveEstado"));
				vGRLUniMed.setICveMunicipio(rset.getInt("iCveMunicipio"));
				vGRLUniMed.setLCis(rset.getInt("lCis"));
				vGRLUniMed.setLPago(rset.getInt("lPago"));
				vGRLUniMed.setICveUddAdmiva(rset.getString("iCveUddadmiva"));
				vcGRLUniMed.addElement(vGRLUniMed);
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
		}
		return vcGRLUniMed;
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			// TVGRLUniMed vGRLUniMed = (TVGRLUniMed) obj;
			TVGRLAyudaDGPMPT VGRLAyudaDGPMPT = (TVGRLAyudaDGPMPT) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLAyudaDGPMPT(" + "ICVEAYUDA," + "CDSAYUDA,"
					+ "CDSCDESCRIPCION," + "LMANUAL," + "LSOFTWARE," + "LGUIA,"
					+ "CURL," + "LVIGENTE," + "LADMIN"
					+ ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(ICVEAYUDA) from GRLAyudaDGPMPT";
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
			VGRLAyudaDGPMPT.setICveAyuda(iMax);
			// ******************************************************************
			pstmt.setInt(1, VGRLAyudaDGPMPT.getICveAyuda());
			pstmt.setString(2, VGRLAyudaDGPMPT.getCDsAyuda());
			pstmt.setString(3, VGRLAyudaDGPMPT.getCDscDescripcion());
			pstmt.setInt(4, VGRLAyudaDGPMPT.getLManual());
			pstmt.setInt(5, VGRLAyudaDGPMPT.getLSoftware());
			pstmt.setInt(6, VGRLAyudaDGPMPT.getLGuia());
			pstmt.setString(7, VGRLAyudaDGPMPT.getCUrl());
			pstmt.setInt(8, VGRLAyudaDGPMPT.getLVigente());
			pstmt.setInt(9, VGRLAyudaDGPMPT.getLAdmin());

			pstmt.executeUpdate();
			cClave = "" + VGRLAyudaDGPMPT.getICveAyuda();
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
		}
		return cClave;
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
			TVGRLAyudaDGPMPT VGRLAyudaDGPMPT = (TVGRLAyudaDGPMPT) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLAyudaDGPMPT" + " set CDSAYUDA =  ?,"
					+ "CDSCDESCRIPCION =  ?," + "LMANUAL =  ?,"
					+ "LSOFTWARE =  ?," + "LGUIA =  ?," + "CURL =  ?,"
					+ "LVIGENTE =  ?," + "LADMIN = ? " + "where iCveAyuda = ?";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setString(1, VGRLAyudaDGPMPT.getCDsAyuda());
			pstmt.setString(2, VGRLAyudaDGPMPT.getCDscDescripcion());
			pstmt.setInt(3, VGRLAyudaDGPMPT.getLManual());
			pstmt.setInt(4, VGRLAyudaDGPMPT.getLSoftware());
			pstmt.setInt(5, VGRLAyudaDGPMPT.getLGuia());
			pstmt.setString(6, VGRLAyudaDGPMPT.getCUrl());
			pstmt.setInt(7, VGRLAyudaDGPMPT.getLVigente());
			pstmt.setInt(8, VGRLAyudaDGPMPT.getLAdmin());
			pstmt.setInt(9, VGRLAyudaDGPMPT.getICveAyuda());

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
		}
		return cClave;
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
			TVGRLAyudaDGPMPT VGRLAyudaDGPMPT = (TVGRLAyudaDGPMPT) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLAyudaDGPMPT" + " where iCveAyuda = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, VGRLAyudaDGPMPT.getICveAyuda());
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
				warn("delete.closeGRLUniMed", ex2);
			}
		}
		return cClave;
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
			TVGRLAyudaDGPMPT vGRLAyudaDGPMPT = (TVGRLAyudaDGPMPT) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLAyudaDGPMPT" + " set lVigente= ? "
					+ "where iCveAyuda = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vGRLAyudaDGPMPT.getICveAyuda());
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

	public static String removeExtension(String s) {

		String separator = ".";
		String filename;

		// Remove the path upto the filename.
		int lastSeparatorIndex = s.lastIndexOf(separator);
		if (lastSeparatorIndex == -1) {
			filename = s;
		} else {
			filename = s.substring(lastSeparatorIndex + 1);
		}

		// Remove the extension.
		int extensionIndex = filename.lastIndexOf(".");
		if (extensionIndex == -1) {
			return filename;
		}
		return filename.substring(0, extensionIndex);
	}

	public static String cUrl(String s) {
		String URL = "Otros.png";
		if (s.equals("doc") || s.equals("docx")) {
			URL = "Word.png";
		}
		if (s.equals("xls") || s.equals("xlsx")) {
			URL = "Excel.png";
		}
		if (s.equals("ppsx") || s.equals("ppt") || s.equals("pptx")) {
			URL = "Power-point.png";
		}
		if (s.equals("pdf")) {
			URL = "pdf.png";
		}
		if (s.equals("exe")) {
			URL = "ejecutables.png";
		}
		if (s.equals("zip") || s.equals("gz") || s.equals("rar")) {
			URL = "comprimidos.png";
		}
		if (s.equals("")) {
			URL = "Otros.png";
		}

		return URL;
	}

	/**
	 * Metodo FindBolean responde si el administrador tien un perfil asignado
	 */
	public boolean FindByBoolean(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLAyudaDGPMPT = new Vector();
		boolean admin = false;
		try {
			dbConn = new DbConnection(dataSourceName2);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLAyudaDGPMPT vGRLAyudaDGPMPT;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (rset.getInt(1) >= 1) {
					admin = true;
				}
			}
		} catch (Exception ex) {
			warn("FindByBoolean", ex);
			throw new DAOException("FindByBoolean");
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
			return admin;
		}
	}

}
