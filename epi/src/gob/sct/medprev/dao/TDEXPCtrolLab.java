package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.seguridad.vo.TVDinRep02;

/**
 * <p>
 * Title: Data Acces Object de EXPCtrolLab DAO
 * </p>
 * <p>
 * Description: DAO Tabla EXPCtrolLab
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Itzia Ma. del C. S�nchez M�ndez
 * @version 1.0
 */

public class TDEXPCtrolLab extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private int iInserted; // Agregada por Rafael Alcocer Caldera 02/Nov/2006

	public TDEXPCtrolLab() {
	}

	public Vector FindByCustomWhere(Object vDatos, int iCveServicio)
			throws DAOException {
		TVEXPExamAplica vExamAplica = null;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vResultado = new Vector();
		try {
			vExamAplica = (TVEXPExamAplica) vDatos;
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVDinRep02 vCtrolLab = null;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(
					" select ES.iCveExpediente, ES.iNumExamen, ES.iCveServicio, ")
					.append("        ES.iOrden, ")
					.append("        ECL.iConsAnalisis, ECL.iAnio, ECL.iCveUniMed, ")
					.append("        ECL.iCveModulo, ECL.iCveAnalisis, ECL.iCveExpediente, ")
					.append("        ECL.cUsuAplicado, ECL.cEstudios, ECL.cMotivo, ")
					.append("        ECL.dtSolicitud ")
					.append(" from EXPServicio ES ")
					.append(" left join EXPCtrolLab ECL on ECL.iConsAnalisis = ES.iOrden ")
					.append(" where ES.iCveExpediente  = ")
					.append(vExamAplica.getICveExpediente())
					.append("   and ES.iNumExamen      = ")
					.append(vExamAplica.getINumExamen())
					.append("   and ES.iCveServicio    = ")
					.append(iCveServicio);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCtrolLab = new TVDinRep02();
				vResultado.addElement(vCtrolLab);
			}
		} catch (Exception ex) {
			warn("FindByCustomWhere", ex);
			ex.printStackTrace();
			throw new DAOException("FindByCustomWhere");
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
				warn("FindByCustomWhere.close", ex2);
			}
			return vResultado;
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
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into ALMAlmacen(" + "iCveAlmacen," + "iCveUniMed,"
					+ "cDscAlmacen," + "iCveUsuResp," + "lActivo"
					+ ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveAlmacen) from ALMAlmacen";
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
			vALMAlmacen.setICveAlmacen(iMax);
			// ******************************************************************
			pstmt.setInt(1, vALMAlmacen.getICveAlmacen());
			pstmt.setInt(2, vALMAlmacen.getICveUniMed());
			pstmt.setString(3, vALMAlmacen.getCDscAlmacen().toUpperCase()
					.trim());
			pstmt.setInt(4, vALMAlmacen.getICveUsuResp());
			pstmt.setInt(5, vALMAlmacen.getLActivo());
			pstmt.executeUpdate();
			cClave = "" + vALMAlmacen.getICveAlmacen();
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
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMAlmacen" + " set iCveUniMed= ?, "
					+ "cDscAlmacen= ?, " + "iCveUsuResp= ?, " + "lActivo= ? "
					+ "where iCveAlmacen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMAlmacen.getICveUniMed());
			pstmt.setString(2, vALMAlmacen.getCDscAlmacen().toUpperCase()
					.trim());
			pstmt.setInt(3, vALMAlmacen.getICveUsuResp());
			pstmt.setInt(4, vALMAlmacen.getLActivo());
			pstmt.setInt(5, vALMAlmacen.getICveAlmacen());
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
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from ALMAlmacen" + " where iCveAlmacen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vALMAlmacen.getICveAlmacen());
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
				warn("delete.closeALMAlmacen", ex2);
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
			TVALMAlmacen vALMAlmacen = (TVALMAlmacen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update ALMAlmacen" + " set lActivo= ? "
					+ "where iCveAlmacen = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, vALMAlmacen.getICveAlmacen());

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
	 * Agregado por Rafael Alcocer Caldera 01/Nov/2006
	 * ***********************************************
	 * 
	 * Encuentra todos los datos de la tabla EXPCTROLLAB a partir del maximo
	 * numero consecutivo que se pasa de parametro.
	 * 
	 * @param filtro
	 *            String
	 * @throws DAOException
	 * @return Vector
	 */
	public Vector findByWhere(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vResultado = new Vector();

		try {
			TVExpCtrolLab vExpCtrolLab = new TVExpCtrolLab();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(" select iConsAnalisis, ").append("        iAnio, ")
					.append("        iCveUniMed, ")
					.append("        iCveModulo, ")
					.append("        iCveAnalisis, ")
					.append("        iCveExpediente, ")
					.append("        cUsuAplicado, ")
					.append("        cEstudios, ").append("        cMotivo, ")
					.append("        dtSolicitud ")
					.append(" from EXPCTROLLAB ").append(filtro);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vExpCtrolLab.setIConsAnalisis(rset.getInt(1));
				vExpCtrolLab.setIAnio(rset.getInt(2));
				vExpCtrolLab.setICveUniMed(rset.getInt(3));
				vExpCtrolLab.setICveModulo(rset.getInt(4));
				vExpCtrolLab.setICveAnalisis(rset.getInt(5));
				vExpCtrolLab.setICveExpediente(rset.getInt(6));
				vExpCtrolLab.setCUsuaAplicado(rset.getString(7));
				vExpCtrolLab.setCEstudios(rset.getString(8));
				vExpCtrolLab.setCMotivo(rset.getString(9));
				vExpCtrolLab.setDtSolicitud(rset.getDate(10));
				vResultado.addElement(vExpCtrolLab);
			}
		} catch (Exception ex) {
			warn("findByMaxConsecutivo", ex);
			ex.printStackTrace();
			throw new DAOException("findByMaxConsecutivo");
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
				warn("findByMaxConsecutivo.close", ex2);
			}

			return vResultado;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 01/Nov/2006
	 * ***********************************************
	 * 
	 * Encuentra todos los datos de la tabla EXPCTROLLAB a partir del maximo
	 * numero consecutivo que se pasa de parametro.
	 * 
	 * @param obj
	 *            Object
	 * @throws DAOException
	 * @return Vector
	 */
	public Vector findByMaxConsecutivo() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vResultado = new Vector();

		try {
			TVExpCtrolLab vExpCtrolLab = new TVExpCtrolLab();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(" select iConsAnalisis, ").append("        iAnio, ")
					.append("        iCveUniMed, ")
					.append("        iCveModulo, ")
					.append("        iCveAnalisis, ")
					.append("        iCveExpediente, ")
					.append("        cUsuAplicado, ")
					.append("        cEstudios, ").append("        cMotivo, ")
					.append("        dtSolicitud ")
					.append(" from EXPCTROLLAB ")
					.append(" where iConsAnalisis  = ")
					.append(getINextConsecutivo() - 1);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vExpCtrolLab.setIConsAnalisis(rset.getInt(1));
				vExpCtrolLab.setIAnio(rset.getInt(2));
				vExpCtrolLab.setICveUniMed(rset.getInt(3));
				vExpCtrolLab.setICveModulo(rset.getInt(4));
				vExpCtrolLab.setICveAnalisis(rset.getInt(5));
				vExpCtrolLab.setICveExpediente(rset.getInt(6));
				vExpCtrolLab.setCUsuaAplicado(rset.getString(7));
				vExpCtrolLab.setCEstudios(rset.getString(8));
				vExpCtrolLab.setCMotivo(rset.getString(9));
				vExpCtrolLab.setDtSolicitud(rset.getDate(10));
				vResultado.addElement(vExpCtrolLab);
			}
		} catch (Exception ex) {
			warn("findByMaxConsecutivo", ex);
			ex.printStackTrace();
			throw new DAOException("findByMaxConsecutivo");
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
				warn("findByMaxConsecutivo.close", ex2);
			}

			return vResultado;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 01/Nov/2006
	 * *********************************************** Obtiene el siguiente
	 * numero consecutivo de la tabla EXPCTROLLAB. Lo hace obteniendo el
	 * m�ximo que hay actualmente y despu�s le suma 1.
	 * 
	 * @throws DAOException
	 * @return int
	 */
	public int getINextConsecutivo() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		int iMax = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iConsAnalisis) from EXPCTROLLAB";
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
		} catch (Exception ex) {
			warn("getIMaxConsecutivo", ex);
			throw new DAOException("");
		} finally {
			try {
				if (pstmtMax != null) {
					pstmtMax.close();
				}

				if (rsetMax != null) {
					rsetMax.close();
				}

				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("getIMaxConsecutivo.close", ex2);
			}

			return iMax;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 01/Nov/2006
	 * *********************************************** Inserta los datos en la
	 * tabla EXPCTROLLAB
	 * 
	 * @param conGral
	 *            Connection
	 * @param obj
	 *            Object
	 * @throws DAOException
	 * @return Object
	 */
	public Object insertExpCtrolLab(Connection conGral, Object obj)
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
			TVExpCtrolLab vExpCtrolLab = (TVExpCtrolLab) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into EXPCTROLLAB(" + "       iAnio,"
					+ "       iCveUniMed," + "       iCveModulo,"
					+ "       iCveAnalisis," + "       iCveExpediente,"
					+ "       cUsuAplicado," + "       cEstudios,"
					+ "       cMotivo," + "       dtSolicitud"
					+ ") values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vExpCtrolLab.getIAnio());
			pstmt.setInt(2, vExpCtrolLab.getICveUniMed());
			pstmt.setInt(3, vExpCtrolLab.getICveModulo());
			pstmt.setInt(4, vExpCtrolLab.getICveAnalisis());
			pstmt.setInt(5, vExpCtrolLab.getICveExpediente());
			pstmt.setString(6, vExpCtrolLab.getCUsuaAplicado());
			pstmt.setString(7, vExpCtrolLab.getCEstudios());
			pstmt.setString(8, vExpCtrolLab.getCMotivo());
			pstmt.setDate(9, vExpCtrolLab.getDtSolicitud());
			iInserted = pstmt.executeUpdate();

			cClave = "" + vExpCtrolLab.getIConsAnalisis();

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
				warn("insertExpCtrolLab", ex1);
			}

			// warn("insertExpCtrolLab", ex);
			// throw new DAOException("");
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
				warn("insertExpCtrolLab.close", ex2);
			}

			return cClave;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 02/Nov/2006
	 * 
	 * @return int
	 */
	public int getIInserted() {
		return iInserted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 02/Nov/2006
	 * 
	 * @param inserted
	 *            int
	 */
	public void setIInserted(int inserted) {
		iInserted = inserted;
	}
}
