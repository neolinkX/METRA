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
 * Title: Data Acces Object de MEDSintExamenTMP DAO
 * </p>
 * <p>
 * Description: DAO Consulta de configuracion de la rama
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrej�n Adame
 * @version 1.0
 */

public class TDMEDSintExamenTMP extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDMEDSintExamenTMP() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveProceso,  " + " iCveMotivo,   "
					+ " iCveServicio, " + " iCveRama,     " + " iCveSintoma,  "
					+ " iCveMdoTrans  " + " from MEDSintExamenTMP " + cWhere
					+ " ";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveProceso(rset.getInt(1));
				vMEDSintExamen.setICveMotivo(rset.getInt(2));
				vMEDSintExamen.setICveServicio(rset.getInt(3));
				vMEDSintExamen.setICveRama(rset.getInt(4));
				vMEDSintExamen.setICveSintoma(rset.getInt(5));
				vMEDSintExamen.setICveMdoTrans(rset.getInt(6));
				vcMEDSintExamen.addElement(vMEDSintExamen);
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
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo FindAllExam
	 */
	public Vector FindAllExam(StringBuffer cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct(S.iCveSintoma), SN.iCveRama, SN.iCveServicio, SN.cValRef "
					+ "from MEDSintExamenTMP S "
					+ "inner join MEDServicio SR on SR.iCveServicio = S.iCveServicio "
					+ "                    and SR.lActivo = 1 "
					+ "inner join MEDRama R on R.iCveServicio = S.iCveServicio "
					+ "                    and R.iCveRama = S.iCveRama "
					+ "                    and R.lActivo = 1 "
					+ "inner join MEDSintomas SN on SN.iCveServicio = S.iCveServicio "
					+ "                       and SN.iCveRama     = S.iCveRama "
					+ "                       and SN.iCveSintoma  = S.iCveSintoma "
					+ "                       and SN.lActivo      = 1 "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveSintoma(rset.getInt(1));
				vMEDSintExamen.setICveRama(rset.getInt(2));
				vMEDSintExamen.setICveServicio(rset.getInt(3));
				vMEDSintExamen.setCValRef(rset.getString(4));
				vcMEDSintExamen.addElement(vMEDSintExamen);
			}
		} catch (Exception ex) {
			warn("FindAllExam", ex);
			throw new DAOException("FindAllExam");
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
				warn("FindAllExam.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo FindAllRama
	 */
	public Vector FindAllRama(StringBuffer cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct(SN.iCveRama), SN.iCveServicio "
					+ "from MEDSintExamenTMP S "
					+ "inner join MEDServicio SR on SR.iCveServicio = S.iCveServicio "
					+ "                    and SR.lActivo = 1 "
					+ "inner join MEDRama R on R.iCveServicio = S.iCveServicio "
					+ "                    and R.iCveRama = S.iCveRama "
					+ "                    and R.lActivo = 1 "
					+ "inner join MEDSintomas SN on SN.iCveServicio = S.iCveServicio "
					+ "                       and SN.iCveRama     = S.iCveRama "
					+ "                       and SN.iCveSintoma  = S.iCveSintoma "
					+ "                       and SN.lActivo      = 1 "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveRama(rset.getInt(1));
				vMEDSintExamen.setICveServicio(rset.getInt(2));
				vcMEDSintExamen.addElement(vMEDSintExamen);
			}
		} catch (Exception ex) {
			warn("FindAllRama", ex);
			throw new DAOException("FindAllRama");
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
				warn("FindAllRama.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo FindAllServ
	 */
	public Vector FindAllServ(StringBuffer cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct(SN.iCveServicio) "
					+ "from MEDSintExamenTMP S "
					+ "inner join MEDServicio SR on SR.iCveServicio = S.iCveServicio "
					+ "                    and SR.lActivo = 1 "
					+ "inner join MEDRama R on R.iCveServicio = S.iCveServicio "
					+ "                    and R.iCveRama = S.iCveRama "
					+ "                    and R.lActivo = 1 "
					+ "inner join MEDSintomas SN on SN.iCveServicio = S.iCveServicio "
					+ "                       and SN.iCveRama     = S.iCveRama "
					+ "                       and SN.iCveSintoma  = S.iCveSintoma "
					+ "                       and SN.lActivo      = 1 "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveServicio(rset.getInt(1));
				vcMEDSintExamen.addElement(vMEDSintExamen);
			}
		} catch (Exception ex) {
			warn("FindAllServ", ex);
			throw new DAOException("FindAllServ");
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
				warn("FindAllServ.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo FindDSerRama
	 */
	public Vector FindDSerRama(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "distinct(MEDSintExamenTMP.iCveServicio), "
					+ "MEDSintExamenTMP.iCveRama,  "
					+ "MEDSintExamenTMP.iCveProceso "
					+ "from MEDSintExamenTMP "
					+ "join MEDRama on MEDRama.iCveServicio = MEDSintExamenTMP.iCveServicio "
					+ "and MEDRama.iCveRama = MEDSintExamenTMP.iCveRama "
					+ "and MEDRama.lActivo = 1 " + " " + cWhere + " ";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveServicio(rset.getInt(1));
				vMEDSintExamen.setICveRama(rset.getInt(2));
				vMEDSintExamen.setICveProceso(rset.getInt(3));
				vcMEDSintExamen.addElement(vMEDSintExamen);
			}
		} catch (Exception ex) {
			warn("FindDSerRama", ex);
			throw new DAOException("FindDSerRama");
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
				warn("FindDSerRama.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllDistinct(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select Distinct " + " iCveProceso,  " + " iCveMotivo,   "
					+ " iCveServicio, " + " iCveRama,     " + " iCveSintoma,  "
					+ " iCveMdoTrans  " + " from MEDSintExamenTMP " + cWhere
					+ " ";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveProceso(rset.getInt(1));
				vMEDSintExamen.setICveMotivo(rset.getInt(2));
				vMEDSintExamen.setICveServicio(rset.getInt(3));
				vMEDSintExamen.setICveRama(rset.getInt(4));
				vMEDSintExamen.setICveSintoma(rset.getInt(5));
				vMEDSintExamen.setICveMdoTrans(rset.getInt(6));
				vcMEDSintExamen.addElement(vMEDSintExamen);
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
			return vcMEDSintExamen;
		}
	}

	public Vector FindByServicio(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select Distinct " + " iCveServicio "
					+ " from MEDSintExamenTMP " + cWhere + " ";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveServicio(rset.getInt(1));
				vcMEDSintExamen.addElement(vMEDSintExamen);
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
			return vcMEDSintExamen;
		}
	}

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
			TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " delete from MEDSintExamenTMP "
					+ " where iCveProceso = ?     "
					+ "   and iCveMotivo = ?      "
					+ "   and iCveServicio= ?     "
					+ "   and iCveRama = ?        "
					+ "   and iCveSintoma = ?     "
					+ "   and iCveMdoTrans = ?    " + "";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vMEDSintExamen.getICveProceso());
			pstmt.setInt(2, vMEDSintExamen.getICveMotivo());
			pstmt.setInt(3, vMEDSintExamen.getICveServicio());
			pstmt.setInt(4, vMEDSintExamen.getICveRama());
			pstmt.setInt(5, vMEDSintExamen.getICveSintoma());
			pstmt.setInt(6, vMEDSintExamen.getICveMdoTrans());
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
				warn("delete.closeMEDSintExamenTMP", ex2);
			}
			return cClave;
		}
	}

	public Object deleteCustomWhere(Connection conGral, String cSQL)
			throws DAOException {
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
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			pstmt = conn.prepareStatement(cSQL);

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
				warn("delete.closeMEDSintExamenTMP", ex2);
			}
			return cClave;
		}
	}

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
			TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " insert into MEDSintExamenTMP( " + " iCveProceso, "
					+ " iCveMotivo," + " iCveServicio," + " iCveRama,"
					+ " iCveSintoma," + " iCveMdoTrans "
					+ " ) values (?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vMEDSintExamen.getICveProceso());
			pstmt.setInt(2, vMEDSintExamen.getICveMotivo());
			pstmt.setInt(3, vMEDSintExamen.getICveServicio());
			pstmt.setInt(4, vMEDSintExamen.getICveRama());
			pstmt.setInt(5, vMEDSintExamen.getICveSintoma());
			pstmt.setInt(6, vMEDSintExamen.getICveMdoTrans());
			pstmt.executeUpdate();
			cClave = "" + vMEDSintExamen.getICveMdoTrans();
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

	public Object insert2(Connection conGral, Object obj) throws DAOException {
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
			TVMEDSintExamen vMEDSintExamen = (TVMEDSintExamen) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " insert into MEDSintExamenTMP( " + " iCveProceso, "
					+ " iCveMotivo," + " iCveServicio," + " iCveRama,"
					+ " iCveSintoma," + " iCveMdoTrans "
					+ " ) values (?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vMEDSintExamen.getICveProceso());
			pstmt.setInt(2, vMEDSintExamen.getICveMotivo());
			pstmt.setInt(3, vMEDSintExamen.getICveServicio());
			pstmt.setInt(4, vMEDSintExamen.getICveRama());
			pstmt.setInt(5, vMEDSintExamen.getICveSintoma());
			pstmt.setInt(6, vMEDSintExamen.getICveMdoTrans());
			pstmt.executeUpdate();
			cClave = "" + vMEDSintExamen.getICveMdoTrans();
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
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveProceso,  " + " iCveMotivo,   "
					+ " iCveServicio, " + " iCveRama,     " + " iCveSintoma,  "
					+ " iCveMdoTrans  " + " from MEDSintExamenTMP " + cWhere
					+ " ";
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveProceso(rset.getInt(1));
				vMEDSintExamen.setICveMotivo(rset.getInt(2));
				vMEDSintExamen.setICveServicio(rset.getInt(3));
				vMEDSintExamen.setICveRama(rset.getInt(4));
				vMEDSintExamen.setICveSintoma(rset.getInt(5));
				vMEDSintExamen.setICveMdoTrans(rset.getInt(6));
				vcMEDSintExamen.addElement(vMEDSintExamen);
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
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo FindAllExam
	 */
	public Vector FindAllExam2(StringBuffer cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct(S.iCveSintoma), SN.iCveRama, SN.iCveServicio, SN.cValRef "
					+ "from MEDSintExamenTMP S "
					+ "inner join MEDServicio SR on SR.iCveServicio = S.iCveServicio "
					+ "                    and SR.lActivo = 1 "
					+ "inner join MEDRama R on R.iCveServicio = S.iCveServicio "
					+ "                    and R.iCveRama = S.iCveRama "
					+ "                    and R.lActivo = 1 "
					+ "inner join MEDSintomas SN on SN.iCveServicio = S.iCveServicio "
					+ "                       and SN.iCveRama     = S.iCveRama "
					+ "                       and SN.iCveSintoma  = S.iCveSintoma "
					+ "                       and SN.lActivo      = 1 "
					+ cWhere;

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveSintoma(rset.getInt(1));
				vMEDSintExamen.setICveRama(rset.getInt(2));
				vMEDSintExamen.setICveServicio(rset.getInt(3));
				vMEDSintExamen.setCValRef(rset.getString(4));
				vcMEDSintExamen.addElement(vMEDSintExamen);
			}
		} catch (Exception ex) {
			warn("FindAllExam", ex);
			throw new DAOException("FindAllExam");
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
				warn("FindAllExam.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo FindAllServ
	 */
	public Vector FindAllServ2(StringBuffer cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct(SN.iCveServicio) "
					+ "from MEDSintExamenTMP S "
					+ "inner join MEDServicio SR on SR.iCveServicio = S.iCveServicio "
					+ "                    and SR.lActivo = 1 "
					+ "inner join MEDRama R on R.iCveServicio = S.iCveServicio "
					+ "                    and R.iCveRama = S.iCveRama "
					+ "                    and R.lActivo = 1 "
					+ "inner join MEDSintomas SN on SN.iCveServicio = S.iCveServicio "
					+ "                       and SN.iCveRama     = S.iCveRama "
					+ "                       and SN.iCveSintoma  = S.iCveSintoma "
					+ "                       and SN.lActivo      = 1 "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveServicio(rset.getInt(1));
				vcMEDSintExamen.addElement(vMEDSintExamen);
			}
		} catch (Exception ex) {
			warn("FindAllServ", ex);
			throw new DAOException("FindAllServ");
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
				warn("FindAllServ.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

	/**
	 * Metodo FindAllRama
	 */
	public Vector FindAllRama2(StringBuffer cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcMEDSintExamen = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVMEDSintExamen vMEDSintExamen;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select distinct(SN.iCveRama), SN.iCveServicio "
					+ "from MEDSintExamenTMP S "
					+ "inner join MEDServicio SR on SR.iCveServicio = S.iCveServicio "
					+ "                    and SR.lActivo = 1 "
					+ "inner join MEDRama R on R.iCveServicio = S.iCveServicio "
					+ "                    and R.iCveRama = S.iCveRama "
					+ "                    and R.lActivo = 1 "
					+ "inner join MEDSintomas SN on SN.iCveServicio = S.iCveServicio "
					+ "                       and SN.iCveRama     = S.iCveRama "
					+ "                       and SN.iCveSintoma  = S.iCveSintoma "
					+ "                       and SN.lActivo      = 1 "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vMEDSintExamen = new TVMEDSintExamen();
				vMEDSintExamen.setICveRama(rset.getInt(1));
				vMEDSintExamen.setICveServicio(rset.getInt(2));
				vcMEDSintExamen.addElement(vMEDSintExamen);
			}
		} catch (Exception ex) {
			warn("FindAllRama", ex);
			throw new DAOException("FindAllRama");
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
				warn("FindAllRama.close", ex2);
			}
			return vcMEDSintExamen;
		}
	}

}
