package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de MEDDiagnostico DAO
 * </p>
 * <p>
 * Description: DAO de la tabla MEDDiagnostico
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

public class TDMEDDiagnostico extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDDiagnostico() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDDiagnostico = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDDiagnostico vMEDDiagnostico;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select "
					+ "MEDDiagnostico.iCveEspecialidad, "
					+ "iCveDiagnostico, "
					+ "cDscDiagnostico, "
					+ "cDscBreve, "
					+ "cCIE, "
					+ "MEDDiagnostico.cObservacion, "
					+ "lFrecuente, "
					+ "MEDDiagnostico.lActivo, "
					+ "MEDEspecialidad.cDscEspecialidad "
					+ "from MEDDiagnostico "
					+ "join MEDEspecialidad on MEDEspecialidad.iCveEspecialidad = MEDDiagnostico.iCveEspecialidad "
					+ cWhere + cOrderBy;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDDiagnostico = new TVMEDDiagnostico();
				vMEDDiagnostico.setICveEspecialidad(rset.getInt(1));
				vMEDDiagnostico.setICveDiagnostico(rset.getInt(2));
				vMEDDiagnostico.setCDscDiagnostico(rset.getString(3));
				vMEDDiagnostico.setCDscBreve(rset.getString(4));
				vMEDDiagnostico.setCCIE(rset.getString(5));
				vMEDDiagnostico.setCObservacion(rset.getString(6));
				vMEDDiagnostico.setLFrecuente(rset.getInt(7));
				vMEDDiagnostico.setLActivo(rset.getInt(8));
				vMEDDiagnostico.setCDscEspecialidad(rset.getString(9));
				vcMEDDiagnostico.addElement(vMEDDiagnostico);
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
			return vcMEDDiagnostico;
		}
	}

	/**
	 * Metodo que busca en la tabla MEDDiagnostico los posibles diagnosticos
	 * para la especialidad y el perfil especificados
	 * 
	 * @param where
	 *            la cl�usula WHERE, incluy�ndola
	 * @param orderBy
	 *            la cl�usula ORDER BY, incluy�ndola
	 * @return un Vector de Value Objects correspondientes a los registros
	 *         encontrados
	 * @throws DAOException
	 *             al existir una excepci�n durante la consulta
	 * @author Romeo Sanchez
	 */
	public Vector findByPerfilEspec(String perfil, String espec, String where,
			String orderBy) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDPerfilDiag = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDPerfilDiag vMEDPerfilDiag;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT "
					+ "     (select iCvePerfil from MEDPerfilDiag "
					+ "      where iCvePerfil="
					+ perfil
					+ " and iCveEspecialidad=d.iCveEspecialidad and "
					+ "      iCveDiagnostico=d.iCveDiagnostico) as iCvePerfil, "
					+ " d.iCveEspecialidad, "
					+ " d.iCveDiagnostico, "
					+ // necesario para tener el diagn�stico actual
					"     (select lAlarma from MEDPerfilDiag where iCvePerfil="
					+ perfil + " and "
					+ "      iCveEspecialidad=d.iCveEspecialidad and "
					+ "      iCveDiagnostico=d.iCveDiagnostico) as lAlarma, "
					+ " d.cDscDiagnostico, " + " d.cDscBreve, " + " d.cCIE, "
					+ " d.lActivo " + " FROM MEDDiagnostico d "
					+ " WHERE d.lActivo=1 " + " and d.iCveEspecialidad = "
					+ espec + where + " " + orderBy; // mostrar s�lo
														// diagn�sticos
														// activos

			// System.out.println(this.getClass().getName()+" SQL: "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDPerfilDiag = new TVMEDPerfilDiag();
				vMEDPerfilDiag.setICvePerfil(rset.getInt(1));
				vMEDPerfilDiag.setICveEspecialidad(rset.getInt(2));
				vMEDPerfilDiag.setICveDiagnostico(rset.getInt(3));
				vMEDPerfilDiag.setLAlarma(rset.getInt(4));
				vMEDPerfilDiag.setCDscDiagnostico(rset.getString(5));
				vMEDPerfilDiag.setCDscBreve(rset.getString(6));
				vMEDPerfilDiag.setCCIE(rset.getString(7));
				vMEDPerfilDiag.setLActivo(rset.getInt(8));

				vcMEDPerfilDiag.addElement(vMEDPerfilDiag);
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
				if (dbConn != null)
					dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindByAll.close", ex2);
			}
			return vcMEDPerfilDiag;
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
			TVMEDDiagnostico vMEDDiagnostico = (TVMEDDiagnostico) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDDiagnostico(" + "iCveEspecialidad,"
					+ "iCveDiagnostico," + "cDscDiagnostico," + "cDscBreve,"
					+ "cCIE," + "cObservacion," + "lFrecuente," + "lActivo"
					+ ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveDiagnostico) from MEDDiagnostico";
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
			vMEDDiagnostico.setICveDiagnostico(iMax);
			// ******************************************************************
			pstmt.setInt(1, vMEDDiagnostico.getICveEspecialidad());
			pstmt.setInt(2, vMEDDiagnostico.getICveDiagnostico());
			pstmt.setString(3, vMEDDiagnostico.getCDscDiagnostico());
			pstmt.setString(4, vMEDDiagnostico.getCDscBreve());
			pstmt.setString(5, vMEDDiagnostico.getCCIE());
			pstmt.setString(6, vMEDDiagnostico.getCObservacion());
			pstmt.setInt(7, vMEDDiagnostico.getLFrecuente());
			pstmt.setInt(8, vMEDDiagnostico.getLActivo());

			pstmt.executeUpdate();
			cClave = "" + vMEDDiagnostico.getICveDiagnostico();
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
				if (pstmtMax != null) {
					pstmtMax.close();
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
			TVMEDDiagnostico vMEDDiagnostico = (TVMEDDiagnostico) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDDiagnostico" + " set cDscDiagnostico= ?, "
					+ "cDscBreve= ?, " + "cCIE= ?, " + "cObservacion= ?, "
					+ "lFrecuente= ?, " + "lActivo= ? "
					+ "where iCveEspecialidad = ? "
					+ " and iCveDiagnostico = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vMEDDiagnostico.getCDscDiagnostico());
			pstmt.setString(2, vMEDDiagnostico.getCDscBreve());
			pstmt.setString(3, vMEDDiagnostico.getCCIE());
			pstmt.setString(4, vMEDDiagnostico.getCObservacion());
			pstmt.setInt(5, vMEDDiagnostico.getLFrecuente());
			pstmt.setInt(6, vMEDDiagnostico.getLActivo());
			pstmt.setInt(7, vMEDDiagnostico.getICveEspecialidad());
			pstmt.setInt(8, vMEDDiagnostico.getICveDiagnostico());
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
			TVMEDDiagnostico vMEDDiagnostico = (TVMEDDiagnostico) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDDiagnostico" + " where iCveEspecialidad = ?"
					+ " and iCveDiagnostico = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDDiagnostico.getICveEspecialidad());
			pstmt.setInt(2, vMEDDiagnostico.getICveDiagnostico());
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
				warn("delete.closeMEDDiagnostico", ex2);
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
			TVMEDDiagnostico vMEDDiagnostico = (TVMEDDiagnostico) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update MEDDiagnostico" + " set lActivo= ? "
					+ "where iCveEspecialidad = ?" + " and iCveDiagnostico = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vMEDDiagnostico.getICveEspecialidad());
			pstmt.setInt(3, vMEDDiagnostico.getICveDiagnostico());
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

	/*
	 * // Obtiene el n�mero m�ximo de registros
	 */
	public int ObtenerRegMax() {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQLMax = "select max(iCveDiagnostico) from MEDDiagnostico";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
		} catch (Exception ex) {
		} finally {
			try {
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return iMax;
		}
	}

	// Genera el Archivo JS de Diagn�sticos ...
	public boolean genJS() {
		DbConnection dbConn = null;
		Connection conn = null;
		boolean lSuccess = false;
		TGeneradorJS jsDiag = new TGeneradorJS();
		PreparedStatement pstmt0 = null, pstmt1 = null, pstmt2 = null;
		ResultSet rset0 = null, rset1 = null, rset2 = null;
		String cSQL = "";
		int i = 0;
		String diagnosticos = "";
		String cont = "";
		try {
			StringBuffer sbJS = new StringBuffer();
			sbJS.append("var aMEDEsp = new Array();");

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select * "
					+ "from MEDEspecialidad where MEDEspecialidad.lActivo = 1";

			pstmt0 = conn.prepareStatement(cSQL);
			rset0 = pstmt0.executeQuery();

			while (rset0.next()) {
				sbJS.append("aMEDEsp[" + i + "]=['"
						+ rset0.getInt("ICVEESPECIALIDAD") + "','"
						+ rset0.getString("CDSCESPECIALIDAD") + "'];");
				i++;
			}

			//
			sbJS.append("var aMEDDiagCIE = new Array();");
			cSQL = "select "
					+ "iCveEspecialidad,iCveDiagnostico,cDscBreve,cObservacion,cCie "
					+ "from MEDDiagnostico where lfrecuente = 1 order by iCveEspecialidad, cCie";
			pstmt1 = conn.prepareStatement(cSQL);
			rset1 = pstmt1.executeQuery();
			i = 0;
			while (rset1.next()) {
				sbJS.append("aMEDDiagCIE[" + i + "]=['"
						+ rset1.getInt("iCveEspecialidad") + "','"
						+ rset1.getInt("iCveDiagnostico") + "','"
						+ rset1.getString("cDscBreve") + "','"
						+ rset1.getString("cObservacion") + "','"
						+ rset1.getString("cCie") + "'];");
				/*
				 * sbJS.append("aMEDDiagCIE[" + i + "]=['" +
				 * rset1.getInt("iCveEspecialidad") + "','" +
				 * rset1.getInt("iCveDiagnostico") + "','" +
				 * rset1.getString("cDscBreve") + "','" +
				 * rset1.getString("cObservacion") + "','" +
				 * rset1.getString("cCie") + "'];");
				 */
				i++;
			}

			//
			sbJS.append("var aMEDDiagDsc = new Array();");
			cSQL = "select "
					+ "iCveEspecialidad,iCveDiagnostico,cDscBreve,cObservacion,cCie "
					+ "from MEDDiagnostico where lfrecuente = 1 order by iCveEspecialidad, cDscBreve";
			pstmt2 = conn.prepareStatement(cSQL);
			rset2 = pstmt1.executeQuery();
			i = 0;

			while (rset2.next()) {
				sbJS.append("aMEDDiagDsc[" + i + "]=['"
						+ rset2.getInt("iCveEspecialidad") + "','"
						+ rset2.getInt("iCveDiagnostico") + "','"
						+ rset2.getString("cDscBreve") + "','"
						+ rset2.getString("cObservacion") + "','"
						+ rset2.getString("cCie") + "'];\n");
				sbJS.append(diagnosticos);
				i++;
			}

			lSuccess = jsDiag.createJS(
					VParametros.getPropEspecifica("RutaGenJS"), "cie.js", sbJS,
					true);
			/*lSuccess = jsDiag.createJS(
			VParametros.getPropEspecifica("RutaNAS2"), "cie.js", sbJS,
			true);*/
			
			this.genJSCif();
			System.out.println("UNO");
			this.genJSTodosCIF();
			
		} catch (Exception e) {
			warn("genJS", e);
		} finally {
			try {
				if (pstmt0 != null) {
					pstmt0.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (rset0 != null) {
					rset0.close();
				}
				if (rset1 != null) {
					rset1.close();
				}
				if (rset2 != null) {
					rset2.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("genJS.close", ex2);
			}
			return lSuccess;
		}
	}

	// Genera el Archivo JS de Diagn�sticos Todos ...
	public boolean genJSTodos() {
		DbConnection dbConn = null;
		Connection conn = null;
		boolean lSuccess = false;
		TGeneradorJS jsDiag = new TGeneradorJS();
		PreparedStatement pstmt0 = null, pstmt1 = null, pstmt2 = null;
		ResultSet rset0 = null, rset1 = null, rset2 = null;
		String cSQL = "";
		int i = 0;
		try {
			StringBuffer sbJS = new StringBuffer();
			sbJS.append("var aMEDEspTodos = new Array();");

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "select * "
					+ "from MEDEspecialidad where MEDEspecialidad.lActivo = 1";

			pstmt0 = conn.prepareStatement(cSQL);
			rset0 = pstmt0.executeQuery();

			while (rset0.next()) {
				sbJS.append("aMEDEspTodos[" + i + "]=['"
						+ rset0.getInt("ICVEESPECIALIDAD") + "','"
						+ rset0.getString("CDSCESPECIALIDAD") + "'];\n");
				i++;
			}

			//
			sbJS.append("var aMEDDiagCIETodos = new Array();");
			cSQL = "select "
					+ "iCveEspecialidad,iCveDiagnostico,cDscBreve,cObservacion,cCie "
					+ "from MEDDiagnostico where lfrecuente is null order by iCveEspecialidad, cCie";
			pstmt1 = conn.prepareStatement(cSQL);
			rset1 = pstmt1.executeQuery();
			i = 0;
			while (rset1.next()) {
				sbJS.append("aMEDDiagCIETodos[" + i + "]=['"
						+ rset1.getString("iCveEspecialidad") + "','"
						+ rset1.getString("iCveDiagnostico") + "','"
						+ rset1.getString("cDscBreve") + "','"
						+ rset1.getString("cObservacion") + "','"
						+ rset1.getString("cCie") + "'];\n");
				/*
				 * sbJS.append("aMEDDiagCIETodos[" + i + "]=['" +
				 * rset1.getInt("iCveEspecialidad") + "','" +
				 * rset1.getInt("iCveDiagnostico") + "','" +
				 * rset1.getString("cDscBreve") + "','" +
				 * rset1.getString("cObservacion") + "','" +
				 * rset1.getString("cCie") + "'];");
				 */
				i++;
			}

			//
			sbJS.append("var aMEDDiagDscTodos = new Array();");
			cSQL = "select "
					+ "iCveEspecialidad,iCveDiagnostico,cDscBreve,cObservacion,cCie "
					+ "from MEDDiagnostico where lfrecuente is null order by iCveEspecialidad, cDscBreve";
			pstmt2 = conn.prepareStatement(cSQL);
			rset2 = pstmt1.executeQuery();
			i = 0;
			while (rset2.next()) {
				sbJS.append("aMEDDiagDscTodos[" + i + "]=['"
						+ rset2.getInt("iCveEspecialidad") + "','"
						+ rset2.getInt("iCveDiagnostico") + "','"
						+ rset2.getString("cDscBreve") + "','"
						+ rset2.getString("cObservacion") + "','"
						+ rset2.getString("cCie") + "'];\n");
				i++;
			}

			lSuccess = jsDiag.createJS(
					VParametros.getPropEspecifica("RutaGenJS"),
					"ciecompleto.js", sbJS, true);
			/*lSuccess = jsDiag.createJS(
					VParametros.getPropEspecifica("RutaNAS2"),
					"ciecompleto.js", sbJS, true);*/
			System.out.println("DOS");
			this.genJSTodosCIF();
			
		} catch (Exception e) {
			warn("genJSTodos", e);
		} finally {
			try {
				if (pstmt0 != null) {
					pstmt0.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (rset0 != null) {
					rset0.close();
				}
				if (rset1 != null) {
					rset1.close();
				}
				if (rset2 != null) {
					rset2.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("genJSTodos.close", ex2);
			}
			return lSuccess;
		}
	}
	
	// Genera el Archivo JS de Diagn�sticos Todos ...
		public boolean genJSTodosCIF() {
			DbConnection dbConn = null;
			Connection conn = null;
			boolean lSuccess = false;
			TGeneradorJS jsDiag = new TGeneradorJS();
			PreparedStatement pstmt0 = null, pstmt1 = null, pstmt2 = null;
			ResultSet rset0 = null, rset1 = null, rset2 = null;
			String cSQL = "";
			int i = 0;
			try {
				StringBuffer sbJS = new StringBuffer();
				sbJS.append("var aMEDCifRTodos = new Array();");

				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();

				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);

				cSQL = "select * "
						+ "from MEDRamasCif where MEDRamasCif.lActivo = 1";

				pstmt0 = conn.prepareStatement(cSQL);
				rset0 = pstmt0.executeQuery();

				while (rset0.next()) {
					sbJS.append("aMEDCifRTodos[" + i + "]=['"
							+ rset0.getInt("ICVERAMACIF") + "','"
							+ rset0.getString("CDSCRAMACIF") + "'];\n");
					i++;
				}

				//
				sbJS.append("var aMEDCifTodos = new Array();");
				cSQL = "select "
						+ "iCveRamaCif,iCveCif,cDscRamaCif,cDscRamaCif,cDscRamaCif "
						+ "from MEDCif order by iCveRamaCif, cDscRamaCif";
				pstmt1 = conn.prepareStatement(cSQL);
				rset1 = pstmt1.executeQuery();
				i = 0;
				while (rset1.next()) {
					sbJS.append("aMEDCifTodos[" + i + "]=['"
							+ rset1.getString("iCveRamaCif") + "','"
							+ rset1.getString("iCveCif") + "','"
							+ rset1.getString("cDscRamaCif") + "','"
							+ rset1.getString("cDscRamaCif") + "','"
							+ rset1.getString("cDscRamaCif") + "'];\n");
					/*
					 * sbJS.append("aMEDDiagCIETodos[" + i + "]=['" +
					 * rset1.getInt("iCveEspecialidad") + "','" +
					 * rset1.getInt("iCveDiagnostico") + "','" +
					 * rset1.getString("cDscBreve") + "','" +
					 * rset1.getString("cObservacion") + "','" +
					 * rset1.getString("cCie") + "'];");
					 */
					i++;
				}

				//
				sbJS.append("var aMEDCifDscTodos = new Array();");
				cSQL = "select "
						+ "iCveRamaCif,iCveCif,cDscRamaCif "
						+ "from MEDCif order by iCveRamaCif, cDscRamaCif";
				pstmt2 = conn.prepareStatement(cSQL);
				System.out.println(cSQL);
				rset2 = pstmt1.executeQuery();
				i = 0;
				while (rset2.next()) {
					sbJS.append("aMEDCifDscTodos[" + i + "]=['"
							+ rset2.getString("iCveRamaCif") + "','"
							+ rset2.getString("iCveCif") + "','"
							+ rset2.getString("cDscRamaCif") + "','"
							+ rset2.getString("cDscRamaCif") + "','"
							+ rset2.getString("cDscRamaCif") + "'];\n");
					i++;
				}

				lSuccess = jsDiag.createJS(
						VParametros.getPropEspecifica("RutaGenJS"),
						"cifcompleto.js", sbJS, true);
				/*lSuccess = jsDiag.createJS(
						VParametros.getPropEspecifica("RutaNAS2"),
						"cifcompleto.js", sbJS, true);*/
				System.out.println("nuevo");
			} catch (Exception e) {
				warn("genJSTodos", e);
			} finally {
				try {
					if (pstmt0 != null) {
						pstmt0.close();
					}
					if (pstmt1 != null) {
						pstmt1.close();
					}
					if (pstmt2 != null) {
						pstmt2.close();
					}
					if (rset0 != null) {
						rset0.close();
					}
					if (rset1 != null) {
						rset1.close();
					}
					if (rset2 != null) {
						rset2.close();
					}
					if (conn != null) {
						conn.close();
					}
					dbConn.closeConnection();
				} catch (Exception ex2) {
					warn("genJSTodos.close", ex2);
				}
				return lSuccess;
			}
		}
		// Genera el Archivo JS de Diagn�sticos ...
		public boolean genJSCif() {
			DbConnection dbConn = null;
			Connection conn = null;
			boolean lSuccess = false;
			TGeneradorJS jsDiag = new TGeneradorJS();
			PreparedStatement pstmt0 = null, pstmt1 = null, pstmt2 = null;
			ResultSet rset0 = null, rset1 = null, rset2 = null;
			String cSQL = "";
			int i = 0;
			String diagnosticos = "";
			String cont = "";
			try {
				StringBuffer sbJS = new StringBuffer();
				sbJS.append("var aMEDCifR = new Array();");

				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();

				conn.setAutoCommit(false);
				conn.setTransactionIsolation(2);

				cSQL = "select * "
						+ "from MEDRamasCif where MEDRamasCif.lActivo = 1";

				pstmt0 = conn.prepareStatement(cSQL);
				rset0 = pstmt0.executeQuery();

				while (rset0.next()) {
					sbJS.append("aMEDCifR[" + i + "]=['"
							+ rset0.getInt("ICVERAMACIF") + "','"
							+ rset0.getString("CDSCRAMACIF") + "'];");
					i++;
				}

				//
				sbJS.append("var aMEDCifCIE = new Array();");
				cSQL = "select "
						+ "iCveRamaCif,iCveCif,cDscRamaCif,cDscRamaCif,cDscRamaCif "
						+ "from MEDCif where lActivo = 1 order by iCveRamaCif, cDscRamaCif";
				pstmt1 = conn.prepareStatement(cSQL);
				rset1 = pstmt1.executeQuery();
				i = 0;
				while (rset1.next()) {
					sbJS.append("aMEDCifCIE[" + i + "]=['"
							+ rset1.getInt("iCveRamaCif") + "','"
							+ rset1.getInt("iCveCif") + "','"
							+ rset1.getString("cDscRamaCif") + "','"
							+ rset1.getString("cDscRamaCif") + "','"
							+ rset1.getString("cDscRamaCif") + "'];");
					i++;
				}

				//
				sbJS.append("var aMEDCifDsc = new Array();");
				cSQL = "select "
						+ "iCveRamaCif,iCveCif,cDscRamaCif,cDscRamaCif,cDscRamaCif "
						+ "from MEDDiagnostico where lActivo = 1 order by iCveRamaCif, cDscRamaCif";
				pstmt2 = conn.prepareStatement(cSQL);
				rset2 = pstmt1.executeQuery();
				i = 0;

				while (rset2.next()) {
					sbJS.append("aMEDCifDsc[" + i + "]=['"
							+ rset2.getInt("iCveRamaCif") + "','"
							+ rset2.getInt("iCveCif") + "','"
							+ rset2.getString("cDscRamaCif") + "','"
							+ rset2.getString("cDscRamaCif") + "','"
							+ rset2.getString("cDscRamaCif") + "'];\n");
					sbJS.append(diagnosticos);
					i++;
				}

				lSuccess = jsDiag.createJS(
						VParametros.getPropEspecifica("RutaGenJS"), "cif.js", sbJS,
						true);
				/*lSuccess = jsDiag.createJS(
						VParametros.getPropEspecifica("RutaNAS2"), "cif.js", sbJS,
						true);*/
				System.out.println("UNO");
				this.genJSTodosCIF();
				
			} catch (Exception e) {
				warn("genJS", e);
			} finally {
				try {
					if (pstmt0 != null) {
						pstmt0.close();
					}
					if (pstmt1 != null) {
						pstmt1.close();
					}
					if (pstmt2 != null) {
						pstmt2.close();
					}
					if (rset0 != null) {
						rset0.close();
					}
					if (rset1 != null) {
						rset1.close();
					}
					if (rset2 != null) {
						rset2.close();
					}
					if (conn != null) {
						conn.close();
					}
					dbConn.closeConnection();
				} catch (Exception ex2) {
					warn("genJS.close", ex2);
				}
				return lSuccess;
			}
		}

}
