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
 * Title: Data Acces Object de MEDServExamen DAO
 * </p>
 * <p>
 * Description: Configuracion de examenes
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */

public class TDMEDServExamen extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDServExamen() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDServExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServExamen vMEDServExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select a.iCveProceso,"
					+ "a.iCveMotivo,"
					+ "a.iCveServicio,"
					+ "cDscProceso,"
					+ "cDscMotivo,"
					+ "cDscServicio"
					+ " From MEDServExamen a"
					+ " left join  GrlProceso on GrlProceso.iCveProceso    = a.iCveProceso  "
					+ " left join  GrlMotivo  on GrlMotivo.iCveMotivo      = a.iCveMotivo   "
					+ " left join  MedServicio on MedServicio.iCveServicio = a.iCveServicio "
					+ cWhere + " order by a.iCveProceso";

			// System.out.println("cSQL==>> "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServExamen = new TVMEDServExamen();
				vMEDServExamen.setICveProceso(rset.getInt(1));
				vMEDServExamen.setICveMotivo(rset.getInt(2));
				vMEDServExamen.setICveServicio(rset.getInt(3));
				vMEDServExamen.setCDscProceso(rset.getString(4));
				vMEDServExamen.setCDscMotivo(rset.getString(5));
				vMEDServExamen.setCDscServicio(rset.getString(6));
				vcMEDServExamen.addElement(vMEDServExamen);
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
			return vcMEDServExamen;
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
			TVMEDServExamen vMEDServExamen = (TVMEDServExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDServExamen(" + "iCveProceso,"
					+ "iCveMotivo," + "iCveServicio" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vMEDServExamen.getICveProceso());
			pstmt.setInt(2, vMEDServExamen.getICveMotivo());
			pstmt.setInt(3, vMEDServExamen.getICveServicio());
			pstmt.executeUpdate();
			cClave = "" + vMEDServExamen.getICveProceso();
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
	 * Metodo update
	 * 
	 * @param conGral
	 * @param obj
	 * @param int iServicio
	 * @param int iMotivo
	 * @return
	 * @throws DAOException
	 * @Modificacion: Javier Mendoza
	 * @Fecha de Modificacion: Julio 5, 2004
	 * @Descripcion: se altera este metodo update con el objetivo de insertar a
	 *               la tabla MEDServExamen por completo basado unicamente en el
	 *               id del proceso, antes de insertar los registros para una
	 *               actualizacion "fake" dado que los registros no se
	 *               actualizan, se borran y se insertan nuevamente, se realiza
	 *               sin utilizar el hashtable ya que los campos son generados
	 *               dinamicamente en el jsp
	 */
	public Object update(Connection conGral, Object obj, int iServicio,
			int iMotivo) throws DAOException {
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
			TVMEDServExamen vMEDServExamen = (TVMEDServExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDServExamen(" + "iCveProceso,"
					+ "iCveMotivo," + "iCveServicio" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from MedServExamen "
					+ "Where iCveProceso = " + vMEDServExamen.getICveProceso()
					+ " And  iCveMotivo = " + iMotivo + " And  iCveServicio = "
					+ iServicio;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			pstmt.setInt(1, vMEDServExamen.getICveProceso());
			pstmt.setInt(2, iMotivo);
			pstmt.setInt(3, iServicio);
			if (iMax == 0) {
				pstmt.executeUpdate();
			}
			cClave = "" + vMEDServExamen.getICveProceso();
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
	 * @ Metodo Delete
	 * 
	 * @param conGral
	 * @param obj
	 * @return
	 * @throws DAOException
	 * @Modificacion: Javier Mendoza
	 * @Fecha de Modificacion: Julio 5, 2004
	 * @Descripcion: se altera este metodo delete con el objetivo de borrar la
	 *               tabla MEDServExamen por completo basado unicamente en el id
	 *               del proceso, antes de insertar los registros para una
	 *               actualizacion "fake" dado que los registros no se
	 *               actualizan, se borran y se insertan nuevamente
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
			TVMEDServExamen vMEDServExamen = (TVMEDServExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDServExamen" + " where iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDServExamen.getICveProceso());
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
				warn("delete.closeMEDServExamen", ex2);
			}
			return cClave;
		}
	}

	/**
	 * @ Metodo Delete
	 * 
	 * @param conGral
	 * @param obj
	 * @return
	 * @throws DAOException
	 * @Modificacion: Javier Mendoza
	 * @Fecha de Modificacion: Julio 5, 2004
	 * @Descripcion: se altera este metodo delete con el objetivo de borrar la
	 *               tabla MEDServExamen por completo basado unicamente en el id
	 *               del proceso, antes de insertar los registros para una
	 *               actualizacion "fake" dado que los registros no se
	 *               actualizan, se borran y se insertan nuevamente
	 */

	public Object delete2(Connection conGral, Object obj) throws DAOException {
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
			TVMEDServExamen vMEDServExamen = (TVMEDServExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from MEDServExamenTMP" + " where iCveProceso = ?";

			// System.out.println();
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDServExamen.getICveProceso());
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
				warn("delete.closeMEDServExamen", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo update
	 * 
	 * @param conGral
	 * @param obj
	 * @param int iServicio
	 * @param int iMotivo
	 * @return
	 * @throws DAOException
	 * @Modificacion: Javier Mendoza
	 * @Fecha de Modificacion: Julio 5, 2004
	 * @Descripcion: se altera este metodo update con el objetivo de insertar a
	 *               la tabla MEDServExamen por completo basado unicamente en el
	 *               id del proceso, antes de insertar los registros para una
	 *               actualizacion "fake" dado que los registros no se
	 *               actualizan, se borran y se insertan nuevamente, se realiza
	 *               sin utilizar el hashtable ya que los campos son generados
	 *               dinamicamente en el jsp
	 */
	public Object update2(Connection conGral, Object obj, int iServicio,
			int iMotivo) throws DAOException {
		// System.out.println("Actualizando");
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
			TVMEDServExamen vMEDServExamen = (TVMEDServExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into MEDServExamenTMP(" + "iCveProceso,"
					+ "iCveMotivo," + "iCveServicio" + ")values(?,?,?)";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);

			// ////////////////////////////////////////

			String cSQLMax = "select count(*) from MedServExamenTMP "
					+ "Where iCveProceso = " + vMEDServExamen.getICveProceso()
					+ " And  iCveMotivo = " + iMotivo + " And  iCveServicio = "
					+ iServicio;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ////////////////////////////////////////

			pstmt.setInt(1, vMEDServExamen.getICveProceso());
			pstmt.setInt(2, iMotivo);
			pstmt.setInt(3, iServicio);
			if (iMax == 0) {
				pstmt.executeUpdate();
			}
			cClave = "" + vMEDServExamen.getICveProceso();
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
	 * Metodo Find By All
	 */
	public Vector FindByAll2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDServExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDServExamen vMEDServExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select a.iCveProceso,             "
					+ "   a.iCveMotivo,             "
					+ "   a.iCveServicio,             "
					+ "   p.cDscProceso,             "
					+ "   m.cDscMotivo,             "
					+ "   s.cDscServicio              " + "From 		"
					+ "   MEDServExamenTMP as a,		" + "   GrlProceso as p,		"
					+ "   GrlMotivo as m,		" + "   MedServicio as s	      "
					+ cWhere + "  "
					+ "   a.iCveProceso = p.iCveProceso   and		"
					+ "   a.iCveProceso = m.iCveProceso and		"
					+ "   a.iCveMotivo = m.iCveMotivo and		"
					+ "   a.iCveServicio = s.iCveServicio and	"
					+ "   m.lactivo = 1 and " + "   s.lactivo = 1";

			// System.out.println("cSQL==>> "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDServExamen = new TVMEDServExamen();
				vMEDServExamen.setICveProceso(rset.getInt(1));
				vMEDServExamen.setICveMotivo(rset.getInt(2));
				vMEDServExamen.setICveServicio(rset.getInt(3));
				vMEDServExamen.setCDscProceso(rset.getString(4));
				vMEDServExamen.setCDscMotivo(rset.getString(5));
				vMEDServExamen.setCDscServicio(rset.getString(6));
				vcMEDServExamen.addElement(vMEDServExamen);
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
			return vcMEDServExamen;
		}
	}

}