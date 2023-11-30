package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLUsuario DAO
 * </p>
 * <p>
 * Description: Generales - Usuarios
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

public class TDGRLUsuario extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLUsuario() {
	}

	public String findMedicoDictaminador(int numExp, int numExam) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String nombre = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "SELECT * FROM EXPSERVICIO WHERE "
					+ "ICVEEXPEDIENTE = " + numExp + " " + "AND INUMEXAMEN = "
					+ numExam + " AND ICVESERVICIO = 31";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// System.out.println(rset.getInt("LCONCLUIDO"));
				if (rset.getInt("LCONCLUIDO") > 0) {
					TDGRLUsuario MEDICO = new TDGRLUsuario();
					Vector medicos = MEDICO.FindByAll2(" WHERE iCveUsuario = "
							+ rset.getString("ICVEUSUAPLICA"));
					// System.out.println(rset.getString("ICVEUSUAPLICA"));
					nombre = ((TVGRLUsuario) medicos.get(0)).getCSiglasProf()
							+ " ";
					nombre += ((TVGRLUsuario) medicos.get(0)).vUsuario
							.getCNombre() + " <br>";
					nombre += "Cedula: "
							+ ((TVGRLUsuario) medicos.get(0)).getCCedula()
							+ " ";
				}
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
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
		return nombre;
	}

	public String findMedicoDictaminador2(int numExp, int numExam) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String nombre = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "SELECT * FROM EXPSERVICIO WHERE "
					+ "ICVEEXPEDIENTE = " + numExp + " " +
					// "AND INUMEXAMEN = " + numExam;
					"AND INUMEXAMEN = " + numExam + " AND ICVESERVICIO = 31";

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// System.out.println(rset.getInt("LCONCLUIDO"));
				if (rset.getInt("LCONCLUIDO") > 0) {
					TDGRLUsuario MEDICO = new TDGRLUsuario();
					Vector medicos = MEDICO.FindByAll2(" WHERE iCveUsuario = "
							+ rset.getString("ICVEUSUAPLICA"));
					// System.out.println(rset.getString("ICVEUSUAPLICA"));
					nombre = ((TVGRLUsuario) medicos.get(0)).getCSiglasProf()
							+ " ";
					nombre += ((TVGRLUsuario) medicos.get(0)).vUsuario
							.getCNombre() + " ";
					nombre += "Cedula: "
							+ ((TVGRLUsuario) medicos.get(0)).getCCedula()
							+ " ";
				}
			}
		} catch (Exception ex) {
			warn("FindByAll", ex);
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
		return nombre;
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVGRLUsuario vGRLUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select U.iCveUsuario, U.dtRegistro, U.cUsuario,")
					// 1, 2, 3
					.append("        U.cApPaterno || ' ' || U.cApMaterno || ' ' || U.cNombre as cNombre, ")
					// 4
					.append("        U.cCalle || ' ' || U.cColonia || '<br>' || M.cNombre || ', ' || E.cNombre || ', ' || P.cNombre as cDireccion, ")
					// 5
					.append("        C.cRFC, C.iCveProfesion, C.cSiglasProf, C.cCedula,")
					// 6, 7, 8, 9
					.append("        PF.cPrefesion, C.iCveUsuario ")
					// 10,11
					.append(" from SEGUsuario U ")
					.append(" inner join GRLPais P on P.iCvePais = U.iCvePais ")
					.append(" inner join GRLEntidadFed E on E.iCvePais       = U.iCvePais ")
					.append("                           and E.iCveEntidadFed = U.iCveEntidadFed ")
					.append(" inner join GRLMunicipio M on M.iCvePais       = U.iCvePais ")
					.append("                          and M.iCveEntidadFed = U.iCveEntidadFed ")
					.append("                          and M.iCveMunicipio  = U.iCveMunicipio ")
					.append(" left join GRLUsuario C on C.iCveUsuario = U.iCveUsuario ")
					.append(" left join GRLProfesion PF on PF.iCveProfesion = C.iCveProfesion ")
					.append(cWhere);
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUsuario = new TVGRLUsuario();
				vGRLUsuario.vUsuario = new TVSEGUsuario();
				if (rset.getString(6) != null)
					vGRLUsuario.vUsuario.setICveUsuario(rset.getInt(11));
				else
					vGRLUsuario.vUsuario.setICveUsuario(0);
				vGRLUsuario.vUsuario.setDtRegistro(rset.getDate(2));
				vGRLUsuario.vUsuario.setCUsuario(rset.getString(3));
				vGRLUsuario.vUsuario.setCNombre(rset.getString(4));
				vGRLUsuario.vUsuario.setCCalle(rset.getString(5));
				vGRLUsuario.setCRFC(rset.getString(6));
				vGRLUsuario.setICveProfesion(rset.getInt(7));
				vGRLUsuario.setCSiglasProf(rset.getString(8));
				vGRLUsuario.setCCedula(rset.getString(9));
				vGRLUsuario.setCProfesion(rset.getString(10));
				vGRLUsuario.setICveUsuario(rset.getInt(1));
				vcGRLUsuario.addElement(vGRLUsuario);
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
			return vcGRLUsuario;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUsuario = new Vector();
		int icveusuarioD = 0;
		String nombre = "";

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			StringBuffer cSQL2 = new StringBuffer();
			TVGRLUsuario vGRLUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count = 0;
			cSQL.append(
					" SELECT ICVEUSUARIO, cApPaterno || ' ' || cApMaterno || ' ' || cNombre as cNombre FROM SEGUSUARIO")
					.append(cWhere);
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				icveusuarioD = rset.getInt(1);
				nombre = rset.getString(2);
			}

			cSQL2.append(
					" SELECT CCEDULA,CSIGLASPROF FROM GRLUSUARIO WHERE ICVEUSUARIO = ")
					.append(icveusuarioD);
			// System.out.println(cSQL2);
			pstmt = conn.prepareStatement(cSQL2.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUsuario = new TVGRLUsuario();
				vGRLUsuario.vUsuario = new TVSEGUsuario();
				vGRLUsuario.vUsuario.setICveUsuario(icveusuarioD);
				vGRLUsuario.vUsuario.setCNombre(nombre);
				vGRLUsuario.setCCedula(rset.getString(1));
				vGRLUsuario.setCSiglasProf(rset.getString(2));
				count = 1;
				vcGRLUsuario.addElement(vGRLUsuario);
			}
			if (count == 0) {
				vGRLUsuario = new TVGRLUsuario();
				vGRLUsuario.vUsuario = new TVSEGUsuario();
				vGRLUsuario.vUsuario.setICveUsuario(icveusuarioD);
				vGRLUsuario.vUsuario.setCNombre(nombre);
				vGRLUsuario.setCCedula(" ");
				vGRLUsuario.setCSiglasProf(" ");
				vcGRLUsuario.addElement(vGRLUsuario);
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
			return vcGRLUsuario;
		}
	}

	/**
	 * Metodo getSiglas
	 */
	public Vector getSiglas(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUsuario vGRLUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "GRLProfesion.cSiglas "
					+ "from "
					+ "GRLUsuario "
					+ "join GRLProfesion on GRLProfesion.iCveProfesion = GRLUsuario.iCveProfesion "
					+ cWhere;

			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUsuario = new TVGRLUsuario();
				vGRLUsuario.setCSiglas(rset.getString(1));
				vcGRLUsuario.addElement(vGRLUsuario);
			}
		} catch (Exception ex) {
			warn("getSiglas", ex);
			throw new DAOException("getSiglas");
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
				warn("getSiglas.close", ex2);
			}
			return vcGRLUsuario;
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
			TVGRLUsuario vGRLUsuario = (TVGRLUsuario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLUsuario(" + "iCveUsuario,"
					+ "iCveProfesion," + "cRFC," + "cSiglasProf," + "cCedula"
					+ ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vGRLUsuario.getICveUsuario());
			pstmt.setInt(2, vGRLUsuario.getICveProfesion());
			pstmt.setString(3, vGRLUsuario.getCRFC());
			pstmt.setString(4, vGRLUsuario.getCSiglasProf());
			pstmt.setString(5, vGRLUsuario.getCCedula());
			pstmt.executeUpdate();
			cClave = "" + vGRLUsuario.getICveUsuario();
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
			TVGRLUsuario vGRLUsuario = (TVGRLUsuario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLUsuario" + " set iCveProfesion= ?, "
					+ "cRFC= ?, " + "cSiglasProf= ?, " + "cCedula= ? "
					+ "where iCveUsuario = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUsuario.getICveProfesion());
			pstmt.setString(2, vGRLUsuario.getCRFC());
			pstmt.setString(3, vGRLUsuario.getCSiglasProf());
			pstmt.setString(4, vGRLUsuario.getCCedula());
			pstmt.setInt(5, vGRLUsuario.getICveUsuario());
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
			TVGRLUsuario vGRLUsuario = (TVGRLUsuario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLUsuario" + " where iCveUsuario = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUsuario.getICveUsuario());
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
				warn("delete.closeGRLUsuario", ex2);
			}
			return cClave;
		}
	}
}
