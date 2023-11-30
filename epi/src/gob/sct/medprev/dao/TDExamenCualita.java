package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.msc.QueryManager;
import gob.sct.medprev.msc.QueryStructure;

/**
 * <p>
 * Title: Data Acces Object de ExamenCualita DAO
 * </p>
 * <p>
 * Description: DAO para los Examenes Cualitativos
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

public class TDExamenCualita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDExamenCualita() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamenCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVExamenCualita vExamenCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveLoteCualita,"
					+ "iCveExamCualita," + "iCveLaboratorio," + "dtEntrega,"
					+ "dtProcesado," + "dtAutorizado," + "lAutorizado,"
					+ "lReanalisis," + "iCveEquipo," + "iCveUsuPrepara,"
					+ "iCveUsuExam," + "iCveUsuAutoriza"
					+ " from TOXExamenCualita order by iAnio";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vExamenCualita = new TVExamenCualita();
				vExamenCualita.setIAnio(rset.getInt(1));
				vExamenCualita.setICveLoteCualita(rset.getInt(2));
				vExamenCualita.setICveExamCualita(rset.getInt(3));
				vExamenCualita.setICveLaboratorio(rset.getInt(4));
				vExamenCualita.setDtEntrega(rset.getDate(5));
				vExamenCualita.setDtProcesado(rset.getDate(6));
				vExamenCualita.setDtAutorizado(rset.getDate(7));
				vExamenCualita.setLAutorizado(rset.getInt(8));
				vExamenCualita.setLReanalisis(rset.getInt(9));
				vExamenCualita.setICveEquipo(rset.getInt(10));
				vExamenCualita.setICveUsuPrepara(rset.getInt(11));
				vExamenCualita.setICveUsuExam(rset.getInt(12));
				vExamenCualita.setICveUsuAutoriza(rset.getInt(13));
				vcExamenCualita.addElement(vExamenCualita);
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
			return vcExamenCualita;
		}
	}

	public Vector FindByReanalisis(String cWhere, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamenCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVExamenCualita vExamenCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select iAnio,iCveLoteCualita,iCveExamCualita,iCveEquipo,iCveLaboratorio "
					+ " from TOXExamenCualita "
					+ " where (lTerminado = 0 or lTerminado is null) "
					+ "   and (lReanalisis = 1) "
					+ "   and (lAutorizado = 1) "
					+ cWhere + " " + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vExamenCualita = new TVExamenCualita();
				vExamenCualita.setIAnio(rset.getInt(1));
				vExamenCualita.setICveLoteCualita(rset.getInt(2));
				vExamenCualita.setICveExamCualita(rset.getInt(3));
				vExamenCualita.setICveEquipo(rset.getInt(4));
				vExamenCualita.setICveLaboratorio(rset.getInt(5));
				vcExamenCualita.addElement(vExamenCualita);
			}
		} catch (Exception ex) {
			warn("FindByLastExamCualita", ex);
			throw new DAOException("FindByLastExamCualita");
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
				warn("FindByLastExamCualita.close", ex2);
			}
			return vcExamenCualita;
		}
	}

	public Vector FindByExCualita() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamenCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVExamenCualita vExamenCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select iAnio," + "iCveLoteCualita," + "iCveExamCualita,"
					+ "iCveLaboratorio," + "dtEntrega," + "dtProcesado,"
					+ "dtAutorizado," + "lAutorizado," + "lReanalisis,"
					+ "iCveEquipo," + "iCveUsuPrepara," + "iCveUsuExam,"
					+ "iCveUsuAutoriza from TOXExamenCualita "
					+ "where lAutorizado = 0 order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vExamenCualita = new TVExamenCualita();
				vExamenCualita.setIAnio(rset.getInt(1));
				vExamenCualita.setICveLoteCualita(rset.getInt(2));
				vExamenCualita.setICveExamCualita(rset.getInt(3));
				vExamenCualita.setICveLaboratorio(rset.getInt(4));
				vExamenCualita.setDtEntrega(rset.getDate(5));
				vExamenCualita.setDtProcesado(rset.getDate(6));
				vExamenCualita.setDtAutorizado(rset.getDate(7));
				vExamenCualita.setLAutorizado(rset.getInt(8));
				vExamenCualita.setLReanalisis(rset.getInt(9));
				vExamenCualita.setICveEquipo(rset.getInt(10));
				vExamenCualita.setICveUsuPrepara(rset.getInt(11));
				vExamenCualita.setICveUsuExam(rset.getInt(12));
				vExamenCualita.setICveUsuAutoriza(rset.getInt(13));
				vcExamenCualita.addElement(vExamenCualita);
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
			return vcExamenCualita;
		}
	}

	public Vector FindByLoteCualita(Object Obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamenCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVExamenCualita vExamenCualita;
			vExamenCualita = (TVExamenCualita) (Obj);
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select iCveLoteCualita from TOXExamenCualita "
					+ "where iAnio = " + vExamenCualita.getIAnio()
					+ " and iCveLaboratorio = "
					+ vExamenCualita.getICveLaboratorio()
					+ " and lAutorizado = 0 order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vExamenCualita = new TVExamenCualita();
				vExamenCualita.setICveLoteCualita(rset.getInt(1));
				vcExamenCualita.addElement(vExamenCualita);
			}
		} catch (Exception ex) {
			warn("FindByLoteCualita", ex);
			throw new DAOException("FindByLoteCualita");
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
				warn("FindByLoteCualita.close", ex2);
			}
			return vcExamenCualita;
		}
	}

	public Object InsertaExamen(String cAnio, String cCveLoteCualita,
			String cCveLaboratorio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iCve = 0;
		String cClave = "";
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVExamenCualita vExamenCualita = new TVExamenCualita();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "";
			cSQL = "Select max(iCveExamCualita)+1 from TOXExamenCualita"
					+ " where iAnio = " + cAnio + " and iCveLoteCualita = "
					+ cCveLoteCualita + " and iCveLaboratorio = "
					+ cCveLaboratorio;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1);
			}

			/* Nuevo Examen */
			vExamenCualita.setICveExamCualita(iCve);
			iCve = 0;

			/** Anio */
			iCve = Integer.parseInt(cAnio);
			vExamenCualita.setIAnio(iCve);

			/* iCveLoteCualita */
			iCve = Integer.parseInt(cCveLoteCualita);
			vExamenCualita.setICveLoteCualita(iCve);

			/* iCveLaboratorio */
			iCve = Integer.parseInt(cCveLaboratorio);
			vExamenCualita.setICveLaboratorio(iCve);

			cSQL = "insert into TOXExamenCualita(" + "iAnio,"
					+ "iCveLoteCualita," + "iCveExamCualita,"
					+ "iCveLaboratorio," + "dtEntrega," + "dtProcesado,"
					+ "dtAutorizado," + "lAutorizado," + "lReanalisis,"
					+ "iCveEquipo," + "iCveUsuPrepara," + "iCveUsuExam,"
					+ "iCveUsuAutoriza" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vExamenCualita.getIAnio());
			pstmt.setInt(2, vExamenCualita.getICveLoteCualita());
			pstmt.setInt(3, vExamenCualita.getICveExamCualita());
			pstmt.setInt(4, vExamenCualita.getICveLaboratorio());
			pstmt.setDate(5, vExamenCualita.getDtEntrega());
			pstmt.setDate(6, vExamenCualita.getDtProcesado());
			pstmt.setDate(7, vExamenCualita.getDtAutorizado());
			pstmt.setInt(8, vExamenCualita.getLAutorizado());
			pstmt.setInt(9, vExamenCualita.getLReanalisis());
			pstmt.setInt(10, vExamenCualita.getICveEquipo());
			pstmt.setInt(11, vExamenCualita.getICveUsuPrepara());
			pstmt.setInt(12, vExamenCualita.getICveUsuExam());
			pstmt.setInt(13, vExamenCualita.getICveUsuAutoriza());
			pstmt.executeUpdate();
			cClave = "" + vExamenCualita.getIAnio();

			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
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
				conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("insert.close", ex2);
			}
			return cClave;
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
		ResultSet rset = null;
		String cClave = "";
		int iCve = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVExamenCualita vExamenCualita = (TVExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "insert into TOXExamenCualita(" + "iAnio,"
					+ "iCveLoteCualita," + "iCveExamCualita,"
					+ "iCveLaboratorio," + "dtEntrega," + "dtProcesado,"
					+ "dtAutorizado," + "lAutorizado," + "lReanalisis,"
					+ "iCveEquipo," + "iCveUsuPrepara," + "iCveUsuExam,"
					+ "iCveUsuAutoriza" + ")values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vExamenCualita.getIAnio());
			pstmt.setInt(2, vExamenCualita.getICveLoteCualita());
			pstmt.setInt(3, vExamenCualita.getICveExamCualita());
			pstmt.setInt(4, vExamenCualita.getICveLaboratorio());
			pstmt.setDate(5, vExamenCualita.getDtEntrega());
			pstmt.setDate(6, vExamenCualita.getDtProcesado());
			pstmt.setDate(7, vExamenCualita.getDtAutorizado());
			pstmt.setInt(8, vExamenCualita.getLAutorizado());
			pstmt.setInt(9, vExamenCualita.getLReanalisis());
			pstmt.setInt(10, vExamenCualita.getICveEquipo());
			pstmt.setInt(11, vExamenCualita.getICveUsuPrepara());
			pstmt.setInt(12, vExamenCualita.getICveUsuExam());
			pstmt.setInt(13, vExamenCualita.getICveUsuAutoriza());
			pstmt.executeUpdate();
			cClave = "" + vExamenCualita.getIAnio();
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
			TVExamenCualita vExamenCualita = (TVExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXExamenCualita" + " set dtEntrega= ?, "
					+ "dtProcesado= ?, " + "dtAutorizado= ?, "
					+ "lAutorizado= ?, " + "lReanalisis= ?, "
					+ "iCveEquipo= ?, " + "iCveUsuPrepara= ?, "
					+ "iCveUsuExam= ?, " + "iCveUsuAutoriza= ? "
					+ "where iAnio = ? " + "and iCveLoteCualita = ?"
					+ "and iCveExamCualita = ?" + " and iCveLaboratorio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setDate(1, vExamenCualita.getDtEntrega());
			pstmt.setDate(2, vExamenCualita.getDtProcesado());
			pstmt.setDate(3, vExamenCualita.getDtAutorizado());
			pstmt.setInt(4, vExamenCualita.getLAutorizado());
			pstmt.setInt(5, vExamenCualita.getLReanalisis());
			pstmt.setInt(6, vExamenCualita.getICveEquipo());
			pstmt.setInt(7, vExamenCualita.getICveUsuPrepara());
			pstmt.setInt(8, vExamenCualita.getICveUsuExam());
			pstmt.setInt(9, vExamenCualita.getICveUsuAutoriza());
			pstmt.setInt(10, vExamenCualita.getIAnio());
			pstmt.setInt(11, vExamenCualita.getICveLoteCualita());
			pstmt.setInt(12, vExamenCualita.getICveExamCualita());
			pstmt.setInt(13, vExamenCualita.getICveLaboratorio());
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
	 * Metodo Update Importante: Solo para actualizar el Campo lTerminado = 1,
	 * proceso en la pantalla pg070303090CFG.java
	 */
	public Object updateTerminado(Connection conGral, Object obj)
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
			TVExamenCualita vExamenCualita = (TVExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXExamenCualita " + "    set lTerminado = ? "
					+ "  where iAnio = ? " + "    and iCveLoteCualita = ? "
					+ "    and iCveExamCualita = ? "
					+ "    and iCveLaboratorio = ? ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vExamenCualita.getLTerminado());
			pstmt.setInt(2, vExamenCualita.getIAnio());
			pstmt.setInt(3, vExamenCualita.getICveLoteCualita());
			pstmt.setInt(4, vExamenCualita.getICveExamCualita());
			pstmt.setInt(5, vExamenCualita.getICveLaboratorio());
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
				warn("updateTerminado", ex1);
			}
			warn("updateTerminado", ex);
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
				warn("updateTerminado.close", ex2);
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
			TVExamenCualita vExamenCualita = (TVExamenCualita) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXExamenCualita" + " where iAnio = ?"
					+ " and iCveLoteCualita = ?" + " and iCveExamCualita = ?"
					+ " and iCveLaboratorio = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vExamenCualita.getIAnio());
			pstmt.setInt(2, vExamenCualita.getICveLoteCualita());
			pstmt.setInt(3, vExamenCualita.getICveExamCualita());
			pstmt.setInt(4, vExamenCualita.getICveLaboratorio());
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
				warn("delete.closeExamenCualita", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo utilizado para consultar los lotes Cualitativos.
	 * 
	 * @return Vector de objetos TVTOXLoteExCualita.
	 * @throws DAOException
	 */
	public Vector ConsultLote(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamenCualita = new Vector();
		StringBuffer cSQL = new StringBuffer();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			TVTOXLoteExCualita vInfoLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select LC.iAnio, LC.iCveLaboratorio, ")
					// 1, 2
					.append("        LC.iCveLoteCualita, LC.dtGeneracion, ")
					// 3, 4
					.append("        EC.iCveExamCualita, ")
					// 5
					.append("        EC.dtEntrega, ")
					// 6
					.append("        UG.cNombre || ' ' || UG.cApPaterno || ' ' || UG.cApMaterno as cUsuPrepara, ")
					// 7
					.append("        EC.dtProcesado, ")
					// 8
					.append("        UE.cNombre || ' ' || UE.cApPaterno || ' ' || UE.cApMaterno as cUsuExam, ")
					// 9
					.append("        EC.dtAutorizado, ")
					// 10
					.append("        UA.cNombre || ' ' || UA.cApPaterno || ' ' || UA.cApMaterno as cUsuAutor, ")
					// 11
					.append("        EC.lReanalisis, EC.lAutorizado, ")
					// 12, 13
					.append("        EQ.cDscEquipo, ")
					// 14
					.append("        (select count(EA.iCveAnalisis) ")
					.append("           from TOXExamAnalisis EA  ")
					.append("           where EA.iAnio            = LC.iAnio ")
					.append("             and EA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and EA.iCveLoteCualita  = LC.icveLoteCualita ")
					.append("             and EA.iCveExamCualita  = EC.iCveExamCualita ) as Analizados ")
					// 15
					.append("  from TOXLoteCualita LC ")
					.append("  inner join TOXExamenCualita EC on EC.iAnio        = LC.iAnio ")
					.append("                                and EC.iCveLaboratorio = LC.iCveLaboratorio ")
					.append("                                and EC.iCveLoteCualita = LC.iCveLoteCualita ")
					.append("  left  join SEGUsuario UG on UG.iCveUsuario = EC.iCveUsuPrepara ")
					.append("  left  join SEGUsuario UE on UE.iCveUsuario = EC.iCveUsuExam ")
					.append("  left  join SEGUsuario UA on UA.iCveUsuario = EC.iCveUsuAutoriza ")
					.append("  inner join EQMEquipo  EQ on EQ.iCveEquipo  = EC.iCveEquipo ")
					.append(cFiltro);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vInfoLote = new TVTOXLoteExCualita();
				vInfoLote.VECualita.setIAnio(rset.getInt(1));
				vInfoLote.VECualita.setICveLaboratorio(rset.getInt(2));
				vInfoLote.VECualita.setICveLoteCualita(rset.getInt(3));
				vInfoLote.setDtGeneracion(rset.getDate(4));
				vInfoLote.VECualita.setICveExamCualita(rset.getInt(5));
				vInfoLote.VECualita.setDtEntrega(rset.getDate(6));
				vInfoLote.setCUsuPrepara(rset.getString(7));
				vInfoLote.VECualita.setDtProcesado(rset.getDate(8));
				vInfoLote.setCUsuExam(rset.getString(9));
				vInfoLote.VECualita.setDtAutorizado(rset.getDate(10));
				vInfoLote.setCUsuAutoriza(rset.getString(11));
				vInfoLote.VECualita.setLReanalisis(rset.getInt(12));
				vInfoLote.VECualita.setLAutorizado(rset.getInt(13));
				vInfoLote.setCEquipo(rset.getString(14));
				vInfoLote.setIAnalizados(rset.getInt("Analizados"));
				vcExamenCualita.addElement(vInfoLote);
			}
		} catch (Exception ex) {
			warn("ConsultLote", ex);
			throw new DAOException("ConsultLote");
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
				warn("ConsultLote.close", ex2);
			}
			return vcExamenCualita;
		}
	}

	/**
	 * Consulta de los Lotes Cualitativos.
	 * 
	 * @param cFiltro
	 *            condici�n de b�squeda del lote.
	 * @return Vector de los objetos que contienen la informaci�n de los
	 *         lotes.
	 * @throws DAOException
	 */
	public Vector LoteDtlle(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcExamenCualita = new Vector();
		StringBuffer cSQL = new StringBuffer();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXLoteExCualita vInfoLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(
					" select LC.iAnio, LC.iCveLaboratorio, LC.iCveLoteCualita, ")
					// 1, 2, 3
					.append("        LC.dtGeneracion, ")
					// 4
					.append("        (select count (iCveExamCualita) ")
					.append("         from TOXExamenCualita EC ")
					.append("         where EC.iAnio           = LC.iAnio ")
					.append("           and EC.iCveLaboratorio = LC.iCveLaboratorio ")
					.append("           and EC.iCveLoteCualita = LC.iCveLoteCualita )  as Exámenes, ")
					// 5
					.append("        (select count(iCveAnalisis) ")
					.append("         from TOXAnalisis A ")
					.append("         where A.iAnio           = LC.iAnio ")
					.append("           and A.iCveLaboratorio = LC.iCveLaboratorio ")
					.append("           and A.iCveLoteCualita = LC.iCveLoteCualita ) as Muestras, ")
					// 6
					.append("        (select count(iCveAnalisis) ")
					.append("         from TOXAnalisis A ")
					.append("         where A.iAnio           = LC.iAnio ")
					.append("           and A.iCveLaboratorio = LC.iCveLaboratorio ")
					.append("           and A.iCveLoteCualita = LC.iCveLoteCualita ")
					.append("           and A.lAutorizado     = 1) as Autorizados ") // 7
					.append(" from TOXLoteCualita LC ").append(cFiltro);
			// System.out.println(cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vInfoLote = new TVTOXLoteExCualita();
				vInfoLote.VECualita.setIAnio(rset.getInt(1));
				vInfoLote.VECualita.setICveLaboratorio(rset.getInt(2));
				vInfoLote.VECualita.setICveLoteCualita(rset.getInt(3));
				vInfoLote.setDtGeneracion(rset.getDate(4));
				vInfoLote.setINumExamen(rset.getInt(5));
				vInfoLote.setIAnalizados(rset.getInt(6));
				vInfoLote.setIAutorizados(rset.getInt(7));
				vInfoLote.setIPendientes(vInfoLote.getIAnalizados()
						- vInfoLote.getIAutorizados());
				vcExamenCualita.addElement(vInfoLote);
			}
		} catch (Exception ex) {
			warn("LoteDtlle", ex);
			throw new DAOException("LoteDtlle");
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
				warn("LoteDtlle.close", ex2);
			}
			return vcExamenCualita;
		}
	}

	public ArrayList findGlobal(String cCondicion) throws DAOException {
		ArrayList vResultado = new ArrayList();
		ArrayList aQueryU = new ArrayList();
		QueryStructure qSentencia;
		HashMap hCamposQ1 = new HashMap();
		try {
			QueryManager qQuery = new QueryManager("07", "ConDBModulo", false);
			StringBuffer cSQL = new StringBuffer(), cFrom = new StringBuffer();
			cSQL.append(
					"   EC.iAnio, EC.iCveLaboratorio, EC.iCveLoteCualita, EC.iCveExamCualita, ")
					.append("   EC.dtEntrega, EC.dtProcesado, EC.dtAutorizado, ")
					.append("   EC.lAutorizado, EC.iCveEquipo, ")
					.append("   EC.iCveUsuPrepara, EC.iCveUsuExam, EC.iCveUsuAutoriza,      ")
					.append("   E.cCveEquipo, E.cNumSerie, ")
					.append("   U.cNombre || ' ' || U.cApPaterno || ' ' || U.cApMaterno as cNomAnalisa, ")
					.append("   (select count(distinct ER.iCveAnalisis) ")
					.append("    from TOXExamResult ER ")
					.append("    inner join TOXSustancia S on S.iCveSustancia = ER.iCveSustancia  ")
					.append("    inner join TOXAnalisis A on A.iAnio = ER.iAnio ")
					.append("                            and A.iCveLaboratorio = ER.iCveLaboratorio ")
					.append("                            and A.iCveAnalisis    = ER.iCveAnalisis ")
					.append("    where ER.iAnio            = EC.iAnio ")
					.append("      and ER.iCveLaboratorio = EC.iCveLaboratorio ")
					.append("      and ER.iCveLoteCualita = EC.iCveLoteCualita ")
					.append("      and ER.iCveExamCualita = EC.iCveExamCualita ")
					.append("      and S.lAnalizada = 1 ")
					.append("      and S.lPositiva  = 1 ")
					.append("      and A.lControl = 0 ) as iMuestras,")
					.append("   (select count(distinct ER.iCveAnalisis) ")
					.append("    from TOXExamResult ER ")
					.append("    inner join TOXSustancia S on S.iCveSustancia = ER.iCveSustancia  ")
					.append("    inner join TOXAnalisis A on A.iAnio = ER.iAnio ")
					.append("                            and A.iCveLaboratorio = ER.iCveLaboratorio ")
					.append("                            and A.iCveAnalisis    = ER.iCveAnalisis ")
					.append("    where ER.iAnio            = EC.iAnio ")
					.append("      and ER.iCveLaboratorio = EC.iCveLaboratorio ")
					.append("      and ER.iCveLoteCualita = EC.iCveLoteCualita ")
					.append("      and ER.iCveExamCualita = EC.iCveExamCualita ")
					.append("      and ER.lPositivo = 1")
					.append("      and S.lAnalizada = 1 ")
					.append("      and S.lPositiva  = 1 ")
					.append("      and A.lControl = 0 ) as iMuestrasPost,")
					.append("   (select count(distinct ER.iCveAnalisis) ")
					.append("    from TOXExamResult ER ")
					.append("    inner join TOXSustancia S on S.iCveSustancia = ER.iCveSustancia  ")
					.append("    inner join TOXAnalisis A on A.iAnio = ER.iAnio ")
					.append("                            and A.iCveLaboratorio = ER.iCveLaboratorio ")
					.append("                            and A.iCveAnalisis    = ER.iCveAnalisis ")
					.append("    where ER.iAnio            = EC.iAnio ")
					.append("      and ER.iCveLaboratorio = EC.iCveLaboratorio ")
					.append("      and ER.iCveLoteCualita = EC.iCveLoteCualita ")
					.append("      and ER.iCveExamCualita = EC.iCveExamCualita ")
					.append("      and ER.lPositivo = -1")
					.append("      and S.lAnalizada = 1 ")
					.append("      and S.lPositiva  = 1 ")
					.append("      and A.lControl = 0 ) as iMuestrasErroneas, ")
					.append("   (select count(EA.iCveAnalisis) ")
					.append("    from TOXExamAnalisis EA ")
					.append("    inner join TOXAnalisis A on A.iAnio = EA.iAnio ")
					.append("                            and A.iCveLaboratorio = EA.iCveLaboratorio ")
					.append("                            and A.iCveAnalisis    = EA.iCveAnalisis ")
					.append("    inner join TOXCtrolCalibra C on C.iCveCtrolCalibra = A.iCveCtrolCalibra ")
					.append("    where EA.iAnio            = EC.iAnio ")
					.append("      and EA.iCveLaboratorio = EC.iCveLaboratorio ")
					.append("      and EA.iCveLoteCualita = EC.iCveLoteCualita ")
					.append("      and EA.iCveExamCualita = EC.iCveExamCualita ")
					.append("      and A.lControl         = 1")
					.append("      and C.iCveEmpleoCalib = ")
					.append(this.VParametros
							.getPropEspecifica("TOXCtrolExterno"))
					.append(") as iCtrolExt");
			cFrom.append(" TOXExamenCualita EC ")
					.append(" inner join EQMEquipo E on E.iCveEquipo = EC.iCveEquipo ")
					.append(" inner join SEGUsuario U on U.iCveUsuario = EC.iCveUsuExam ");

			hCamposQ1.put(cSQL.toString(), "");
			qSentencia = new QueryStructure(hCamposQ1, cFrom.toString(),
					cCondicion.toUpperCase().replaceAll("WHERE", " "),
					QueryStructure.SELECT);
			aQueryU.add(qSentencia);
			vResultado = qQuery.manageTransaction(aQueryU);
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findGlobal", ex);
			throw new DAOException("findGlobal");
		}
		return vResultado;
	}

	public ArrayList findCalibracion(String cCondicion) throws DAOException {
		ArrayList vResultado = new ArrayList();
		ArrayList aQueryU = new ArrayList();
		QueryStructure qSentencia;
		HashMap hCamposQ1 = new HashMap();
		try {
			QueryManager qQuery = new QueryManager("07", "ConDBModulo", false);
			StringBuffer cSQL = new StringBuffer(), cFrom = new StringBuffer();
			cSQL.append(" S.iCveSustancia, ")
					.append("      (select CC.dCorteNeg ")
					.append("       from TOXCalibCualita CC ")
					.append(cCondicion)
					.append("        and CC.iCveSustancia = S.iCveSustancia ")
					.append("        and CC.lAutorizado = 1) as dCorteNeg, ")
					.append("      (select CC.dCorte ")
					.append("       from TOXCalibCualita CC ")
					.append(cCondicion)
					.append("        and CC.iCveSustancia = S.iCveSustancia ")
					.append("        and CC.lAutorizado = 1) as dCorte, ")
					.append("      (select CC.dCortePost ")
					.append("       from TOXCalibCualita CC ")
					.append(cCondicion)
					.append("        and CC.iCveSustancia = S.iCveSustancia ")
					.append("        and CC.lAutorizado = 1) as dCortePost, ")
					.append("      (select count(1) ")
					.append("       from TOXExamAnalisis CC ")
					.append("       inner join TOXAnalisis A on A.iAnio           = CC.iAnio ")
					.append("                               and A.iCveLaboratorio = CC.iCveLaboratorio ")
					.append("                               and A.iCveAnalisis    = CC.iCveAnalisis ")
					.append("       inner join TOXCtrolCalibra C on C.iCveCtrolCalibra = A.iCveCtrolCalibra ")
					.append(cCondicion)
					.append("         and A.lControl = 1 ")
					.append("         and C.iCveSustancia = S.iCveSustancia) as iNumCtrol, ")
					.append("      (select R.iCodigo ")
					.append("       from TOXCalibCualita CC ")
					.append("       inner join TOXReactivo R on R.iCveReactivo = CC.iCveReactivo ")
					.append(cCondicion)
					.append("         and CC.iCveSustancia = S.iCveSustancia) as cCtrolCalibra ");
			cFrom.append(" TOXSustancia S ");
			hCamposQ1.put(cSQL.toString(), "");
			qSentencia = new QueryStructure(hCamposQ1, cFrom.toString(), "",
					QueryStructure.SELECT);
			aQueryU.add(qSentencia);
			vResultado = qQuery.manageTransaction(aQueryU);
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findCalibracion", ex);
			throw new DAOException("findCalibracion");
		}
		return vResultado;
	}

}
