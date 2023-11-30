package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.util.*;

/**
 * <p>
 * Title: Data Acces Object de TDPERAdicional DAO
 * </p>
 * <p>
 * Description: DAO de la entidad TDPERAdicional
 * </p>
 * 
 * @author AG SA.
 * @version 1.0
 */

/*
 * JESR: Solo estoy utilizando el Metodo findBySelector;
 */

public class TDPERAdicional extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDPERAdicional() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author: AG SA
	 * @param: cCvePersona - Clave del Personal en Caracter. Incluye Join con
	 *         las Direcciones
	 */
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
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update PERAdicional " + "set " +

			"ICveVivienda= ?, " + "ICveDiscapacidad= ?, "
					+ "ICveGpoEtnico= ?, " + "ICveReligion= ?, "
					+ "ICveNivelSEC= ?, " + "ICveParPOL= ?, "
					+ "ICveECivil= ?, " + "CTel2= ?, " + "ICveTpoSangre= ? "
					+ "where iCvePersonal = ?";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vPERDatos.getICveVivienda());
			pstmt.setInt(2, vPERDatos.getICveDiscapacidad());
			pstmt.setInt(3, vPERDatos.getICveGpoEtnico());
			pstmt.setInt(4, vPERDatos.getICveReligion());
			pstmt.setInt(5, vPERDatos.getICveNivelSEC());
			pstmt.setInt(6, vPERDatos.getICveParPOL());
			pstmt.setInt(7, vPERDatos.getICveECivil());
			pstmt.setString(8, vPERDatos.getCTelefono2());
			pstmt.setInt(9, vPERDatos.getICveTpoSangre());
			pstmt.setInt(10, vPERDatos.getICvePersonal());

			// System.out.println(cSQL);

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
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtCveExpediente = null;
		ResultSet rsetCvePersona = null;
		ResultSet rsetCveExpediente = null;
		int iPersonal = 0;
		int iExpediente = 0;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String cSQLCvePersonal = "";
			String cSQLCveExpediente = "";
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			/*
			 * cSQLCvePersonal = "select max(iCvePersonal) from PERDatos ";
			 * pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			 * rsetCvePersona = pstmtCvePersonal.executeQuery(); while
			 * (rsetCvePersona.next()) { iPersonal = rsetCvePersona.getInt(1); }
			 * vPERDatos.setICvePersonal(iPersonal + 1);
			 * 
			 * cSQLCveExpediente = "select max(iCveExpediente) from PERDatos ";
			 * pstmtCveExpediente = conn.prepareStatement(cSQLCveExpediente);
			 * rsetCveExpediente = pstmtCveExpediente.executeQuery(); while
			 * (rsetCveExpediente.next()) { iExpediente =
			 * rsetCveExpediente.getInt(1); }
			 * vPERDatos.setICveExpediente(iExpediente + 1);
			 */
			cSQL = "insert into PERADICIONAL(" + "iCvePersonal,"
					+ "iCveVivienda," + "iCveDiscapacidad," + "iCveGpoEtnico,"
					+ "iCveReligion," + "iCveNivelSec," + "iCveParPol,"
					+ "iCveECivil," + "cTel2," + "iCveTpoSangre"
					+ ")values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vPERDatos.getICvePersonal());
			pstmt.setInt(2, vPERDatos.getICveVivienda());
			pstmt.setInt(3, vPERDatos.getICveDiscapacidad());
			pstmt.setInt(4, vPERDatos.getICveGpoEtnico());
			pstmt.setInt(5, vPERDatos.getICveReligion());
			pstmt.setInt(6, vPERDatos.getICveNivelSEC());
			pstmt.setInt(7, vPERDatos.getICveParPOL());
			pstmt.setInt(8, vPERDatos.getICveECivil());
			pstmt.setString(9, vPERDatos.getCTelefono2());
			pstmt.setInt(10, vPERDatos.getICveTpoSangre());

			pstmt.executeUpdate();
			cClave = "" + vPERDatos.getICvePersonal();
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

				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}

				if (rsetCvePersona != null) {
					rsetCvePersona.close();
				}

				if (pstmtCveExpediente != null) {
					pstmtCveExpediente.close();
				}

				if (rsetCveExpediente != null) {
					rsetCveExpediente.close();
				}

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
	 * Metodo Ficha de identificacion
	 */
	public Object FicId(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtCvePersonal = null;
		PreparedStatement pstmtCveExpediente = null;
		ResultSet rsetCvePersona = null;
		ResultSet rsetCveExpediente = null;
		int iPersonal = 0;
		int iExpediente = 0;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			String cSQLCvePersonal = "";
			String cSQLCveExpediente = "";
			TVPERDatos vPERDatos = (TVPERDatos) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQLCvePersonal = "select count(iCvePersonal) from PERAdicional where icvepersonal = ?";
			pstmtCvePersonal = conn.prepareStatement(cSQLCvePersonal);
			// System.out.println(vPERDatos.getICvePersonal());
			pstmtCvePersonal.setInt(1, vPERDatos.getICvePersonal());
			rsetCvePersona = pstmtCvePersonal.executeQuery();
			while (rsetCvePersona.next()) {
				iPersonal = rsetCvePersona.getInt(1);
			}
			if (iPersonal == 0) {
				this.insert(null, obj);
			} else {
				this.update(null, obj);
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

				if (pstmtCvePersonal != null) {
					pstmtCvePersonal.close();
				}

				if (rsetCvePersona != null) {
					rsetCvePersona.close();
				}

				if (pstmtCveExpediente != null) {
					pstmtCveExpediente.close();
				}

				if (rsetCveExpediente != null) {
					rsetCveExpediente.close();
				}

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
	 * Metodo Find By All
	 * 
	 * @author: AG SA L
	 * @param: cCvePersona - Clave del Personal en Caracter. Incluye Join con
	 *         las Direcciones
	 */
	public int FindByValida(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtDir = null;
		ResultSet rsetDir = null;
		Vector vcPERDatos = new Vector();
		int Suma = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLDir = "";
			TVPERDatos vPERDatos;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			int iMaxDir = 0;
			/*
			 * cSQLDir = "select max(iCveDireccion) from PERDireccion " +
			 * "where iCvePersonal = " + cCvePersona;
			 * 
			 * pstmtDir = conn.prepareStatement(cSQLDir); rsetDir =
			 * pstmtDir.executeQuery(); while (rsetDir.next()) { iMaxDir =
			 * rsetDir.getInt(1); }
			 */
			cSQL = "SELECT COUNT(ICVEPERSONAL) "
					+ "FROM	PERADICIONAL WHERE ICVEPERSONAL = " + cCvePersona;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Suma = rset.getInt(1);
			}

		} catch (Exception ex) {
			warn("FindByAll", ex);
			throw new DAOException("FindByAll");
		} finally {
			try {

				if (pstmtDir != null) {
					pstmtDir.close();
				}
				if (rsetDir != null) {
					rsetDir.close();
				}

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
			return Suma;
		}
	}

}
