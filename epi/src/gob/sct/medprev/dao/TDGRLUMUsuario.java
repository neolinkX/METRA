package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Data Acces Object de GRLUMUsuario DAO para la obtenci�n del personal
 * asignado a un laboratorio
 * </p>
 * <p>
 * Description: DAO de la entidad GRLUMUsuario
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */

public class TDGRLUMUsuario extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDGRLUMUsuario() {
	}

	/**
	 * Metodo getUniMed
	 */
	public Vector getUniMed(int iCveProceso, int iCveUniMed) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuarios = new Vector();
		TVGRLUMUsuario vGRLUMUsuario;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select GRLUniMed.iCveUniMed,cDscUniMed,GRLProceso.iCveProceso,cDscProceso, "
					+ "SEGUsuario.iCveUsuario,SEGUsuario.cNombre,SEGUsuario.cApPaterno,SEGUsuario.cApMaterno "
					+ "from GRLUMUsuario  "
					+ "JOIN GRLProceso ON GRLUMUsuario.iCveProceso = GRLProceso.iCveProceso "
					+ "JOIN GRLUniMed ON GRLUMUsuario.iCveUniMed = GRLUniMed.iCveUniMed  "
					+ "JOIN SEGUsuario ON GRLUMUsuario.iCveUsuario = SEGUsuario.iCveUsuario "
					+ "WHERE GRLProceso.iCveProceso = "
					+ iCveProceso
					+ " AND GRLUniMed.iCveUniMed = "
					+ iCveUniMed
					+ " order by SEGUsuario.cNombre";

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUniMed(lRSet.getInt(1));
				vGRLUMUsuario.setCDscUniMed(lRSet.getString(2));
				vGRLUMUsuario.setICveProceso(lRSet.getInt(3));
				vGRLUMUsuario.setCDscProceso(lRSet.getString(4));
				vGRLUMUsuario.setICveUsuario(lRSet.getInt(5));
				vGRLUMUsuario.setCNombre(lRSet.getString(6));
				vGRLUMUsuario.setCApPaterno(lRSet.getString(7));
				vGRLUMUsuario.setCApMaterno(lRSet.getString(8));
				vUMUsuarios.add(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			// System.out.println("getUniMed");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("getUniMed.Close");
			}
			return vUMUsuarios;
		}
	}

	/**
	 * Metodo getUniMedMod
	 */
	public Vector getUniMedMod(int iCveProceso, int iCveUniMed, int iCveModulo) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuarios = new Vector();
		TVGRLUMUsuario vGRLUMUsuario;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select distinct(GRLUMUsuario.iCveUsuario),GRLUniMed.iCveUniMed,cDscUniMed,GRLProceso.iCveProceso,cDscProceso, "
					+ "SEGUsuario.iCveUsuario,SEGUsuario.cNombre,SEGUsuario.cApPaterno,SEGUsuario.cApMaterno "
					+ "from GRLUMUsuario  "
					+ "JOIN GRLProceso ON GRLUMUsuario.iCveProceso = GRLProceso.iCveProceso "
					+ "JOIN GRLUniMed ON GRLUMUsuario.iCveUniMed = GRLUniMed.iCveUniMed  "
					+ "JOIN GRLModulo ON GRLModulo.iCveUniMed = GRLUMUsuario.iCveUniMed "
					+ "AND GRLModulo.iCveModulo = "
					+ iCveModulo
					+ " "
					+ "JOIN SEGUsuario ON GRLUMUsuario.iCveUsuario = SEGUsuario.iCveUsuario AND SEGUsuario.lBLOQUEADO = 0 "
					+ "JOIN GRLUSUMedicos on GRLUSUMedicos.iCveUsuario = GRLUMUsuario.iCveUsuario "
					+ "and GRLUSUMedicos.iCveUniMed =  GRLUMUsuario.iCveUniMed "
					+ "and GRLUSUMedicos.iCveProceso =  GRLUMUsuario.iCveProceso "
					+ "and GRLUSUMedicos.iCveModulo =  "
					+ iCveModulo
					+ " "
					+ "WHERE GRLProceso.iCveProceso = "
					+ iCveProceso
					+ " AND GRLUniMed.iCveUniMed = "
					+ iCveUniMed
					+ " order by SEGUsuario.cNombre";

			//System.out.println("getUniMedMod: " + lSQL);

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUniMed(lRSet.getInt(2));
				vGRLUMUsuario.setCDscUniMed(lRSet.getString(3));
				vGRLUMUsuario.setICveProceso(lRSet.getInt(4));
				vGRLUMUsuario.setCDscProceso(lRSet.getString(5));
				vGRLUMUsuario.setICveUsuario(lRSet.getInt(6));
				vGRLUMUsuario.setCNombre(lRSet.getString(7));
				vGRLUMUsuario.setCApPaterno(lRSet.getString(8));
				vGRLUMUsuario.setCApMaterno(lRSet.getString(9));
				vUMUsuarios.add(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			// System.out.println("getUniMedMod");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("getUniMedMod.Close");
			}
			return vUMUsuarios;
		}
	}

	/**
	 * Metodo getUniMedxUsu
	 */
	public Vector getUniMedxUsu(int iUsuario) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuarios = new Vector();
		TVGRLUMUsuario vGRLUMUsuario;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select distinct(GRLUMUsuario.iCveUnimed),cDscUniMed "
					+ "from GRLUMUsuario  "
					+ "JOIN GRLUniMed ON GRLUMUsuario.iCveUniMed = GRLUniMed.iCveUniMed  "
					+ "WHERE GRLUMUsuario.iCveUsuario = "
					+ iUsuario
					+ " ORDER BY cDscUniMed";

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUniMed(lRSet.getInt(1));
				vGRLUMUsuario.setCDscUniMed(lRSet.getString(2));
				vUMUsuarios.add(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			// System.out.println("getUniMedxUsu");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("getUniMedxUsu.Close");
			}
			return vUMUsuarios;
		}
	}

	/**
	 * Metodo getUniMedxUsu
	 */
	public Vector getUniMedxUsu2(int iUsuario) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuarios = new Vector();
		TVGRLUMUsuarioMP vGRLUMUsuario;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select distinct(GRLUMUsuario.iCveUnimed),cDscUniMed "
					+ "from GRLUMUsuario  "
					+ "JOIN GRLUniMed ON GRLUMUsuario.iCveUniMed = GRLUniMed.iCveUniMed  "
					+ "WHERE GRLUMUsuario.iCveUsuario = "
					+ iUsuario
					+ " ORDER BY cDscUniMed";
			// System.out.println("Busqueda del usuario -- "+ lSQL);
			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuarioMP();
				vGRLUMUsuario.setICveUniMed(lRSet.getInt(1));
				vGRLUMUsuario.setCDscUniMed(lRSet.getString(2));
				vUMUsuarios.add(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			// System.out.println("getUniMedxUsu2");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("getUniMedxUsu2.Close");
			}
			return vUMUsuarios;
		}
	}

	/**
	 * Metodo getUniMedxUsu Se extraen todos los Laboratorios que realizan un
	 * proceso en especifico.
	 */
	public Vector getUniMedxUsu2(int iUsuario, String cFiltro) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuarios = new Vector();
		TVGRLUMUsuario vGRLUMUsuario;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select distinct(GRLUMUsuario.iCveUnimed),cDscUniMed "
					+ "from GRLUMUsuario  "
					+ "JOIN GRLUniMed ON GRLUMUsuario.iCveUniMed = GRLUniMed.iCveUniMed  "
					+ "WHERE GRLUMUsuario.iCveUsuario = " + iUsuario + cFiltro;

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUniMed(lRSet.getInt(1));
				vGRLUMUsuario.setCDscUniMed(lRSet.getString(2));
				vUMUsuarios.add(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			// System.out.println("getUniMedxUsu");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("getUniMedxUsu.Close");
			}
			return vUMUsuarios;
		}
	}

	/**
	 * Metodo getProcesos
	 */
	public Vector getProcesos(int iUsuario, int iUniMed) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuarios = new Vector();
		TVGRLUMUsuario vGRLUMUsuario;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select GRLUMUsuario.iCveProceso,GRLProceso.cDscProceso "
					+ "from GRLUMUsuario "
					+ "JOIN GRLProceso ON GRLUMUsuario.iCveProceso = GRLProceso.iCveProceso "
					+ "WHERE GRLUMUsuario.iCveUsuario = "
					+ iUsuario
					+ " "
					+ "AND GRLUMUsuario.iCveUniMed = " + iUniMed;

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveProceso(lRSet.getInt(1));
				vGRLUMUsuario.setCDscProceso(lRSet.getString(2));
				vUMUsuarios.add(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			// System.out.println("getProcesos");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("getProcesos.Close");
			}
			return vUMUsuarios;
		}
	}

	/**
	 * Metodo getProcesos2
	 */
	public Vector getProcesos2(int iUsuario, int iUniMed) {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		Vector vUMUsuarios = new Vector();
		TVGRLUMUsuarioMP vGRLUMUsuario;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setTransactionIsolation(2);

			String lSQL = "select GRLUMUsuario.iCveProceso,GRLProceso.cDscProceso "
					+ "from GRLUMUsuario "
					+ "JOIN GRLProceso ON GRLUMUsuario.iCveProceso = GRLProceso.iCveProceso "
					+ "WHERE GRLUMUsuario.iCveUsuario = "
					+ iUsuario
					+ " "
					+ "AND GRLUMUsuario.iCveUniMed = "
					+ iUniMed
					+ "  ORDER BY GRLProceso.cDscProceso ";

			lPStmt = conn.prepareStatement(lSQL);
			lRSet = lPStmt.executeQuery();

			while (lRSet.next()) {
				vGRLUMUsuario = new TVGRLUMUsuarioMP();
				vGRLUMUsuario.setICveProceso(lRSet.getInt(1));
				vGRLUMUsuario.setCDscProceso(lRSet.getString(2));
				vUMUsuarios.add(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			// System.out.println("getProcesos");
			ex.printStackTrace();
		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				// System.out.println("getProcesos.Close");
			}
			return vUMUsuarios;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUMUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUMUsuario vGRLUMUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select segusuario.iCveusuario, cusuario, cNombre, cappaterno, capmaterno, cdscunimed, cdscproceso, grlumusuario.icveunimed, grlumusuario.icveproceso "
					+ "from grlumusuario "
					+ "join segusuario on grlumusuario.icveusuario = segusuario.icveusuario "
					+ "join grlunimed on grlumusuario.icveunimed = grlunimed.icveunimed "
					+ "join grlproceso on grlumusuario.icveproceso = grlproceso.icveproceso "
					+ cWhere
					+ " order by grlumusuario.icveusuario, grlumusuario.icveunimed, grlumusuario.icveproceso ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUsuario(rset.getInt(1));
				vGRLUMUsuario.setCUsuario(rset.getString(2));
				vGRLUMUsuario.setCNombre(rset.getString(3));
				vGRLUMUsuario.setCApPaterno(rset.getString(4));
				vGRLUMUsuario.setCApMaterno(rset.getString(5));
				vGRLUMUsuario.setCDscUniMed(rset.getString(6));
				vGRLUMUsuario.setCDscProceso(rset.getString(7));
				vGRLUMUsuario.setICveUniMed(rset.getInt(8));
				vGRLUMUsuario.setICveProceso(rset.getInt(9));
				vcGRLUMUsuario.addElement(vGRLUMUsuario);
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
			return vcGRLUMUsuario;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByDistinct(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUMUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUMUsuario vGRLUMUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "        distinct GRLUMUsuario.iCveUsuario, "
					+ "        SEGUsuario.cNombre, "
					+ "        SEGUsuario.cApPaterno, "
					+ "        SEGUsuario.cApMaterno "
					+ "from GRLUMUsuario "
					+ "join SEGUsuario on GRLUMUsuario.iCveUsuario = SEGUsuario.iCveUsuario "
					+ cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUsuario(rset.getInt(1));
				vGRLUMUsuario.setCNombre(rset.getString(2));
				vGRLUMUsuario.setCApPaterno(rset.getString(3));
				vGRLUMUsuario.setCApMaterno(rset.getString(4));
				vcGRLUMUsuario.addElement(vGRLUMUsuario);
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
			return vcGRLUMUsuario;
		}
	}

	/**
	 * Metodo Insert 2
	 */
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
			TVGRLUMUsuarioMP vGRLUMUsuario = (TVGRLUMUsuarioMP) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into GRLUMUsuario(" + "iCveUsuario," + "iCveUniMed,"
					+ "iCveProceso" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vGRLUMUsuario.getICveUsuario());
			pstmt.setInt(2, vGRLUMUsuario.getICveUniMed());
			pstmt.setInt(3, vGRLUMUsuario.getICveProceso());
			pstmt.executeUpdate();
			cClave = "" + vGRLUMUsuario.getICveUniMed();
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
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		boolean lExc = false;
		TVGRLUMUsuario vGRLUMUsuario = (TVGRLUMUsuario) obj;
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
			cSQL = "insert into GRLUMUsuario(" + "iCveUsuario," + "iCveUniMed,"
					+ "iCveProceso" + ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUMUsuario.getICveUsuario());
			pstmt.setInt(2, vGRLUMUsuario.getICveUniMed());
			pstmt.setInt(3, vGRLUMUsuario.getICveProceso());
			pstmt.executeUpdate();
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
			lExc = true;
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
			if (lExc) {
				throw new DAOException("");
			}
			return vGRLUMUsuario;
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
			TVGRLUMUsuario vGRLUMUsuario = (TVGRLUMUsuario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update GRLUMUsuario" + "where iCveUsuario = ? "
					+ "and iCveUniMed = ?" + " and iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUMUsuario.getICveUsuario());
			pstmt.setInt(2, vGRLUMUsuario.getICveUniMed());
			pstmt.setInt(3, vGRLUMUsuario.getICveProceso());
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
			TVGRLUMUsuario vGRLUMUsuario = (TVGRLUMUsuario) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLUMUsuario" + " where iCveUsuario = ?"
					+ " and iCveUniMed = ?" + " and iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUMUsuario.getICveUsuario());
			pstmt.setInt(2, vGRLUMUsuario.getICveUniMed());
			pstmt.setInt(3, vGRLUMUsuario.getICveProceso());
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
				warn("delete.closeGRLUMUsuario", ex2);
			}
			return cClave;
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
		Vector vcGRLUMUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUMUsuario vGRLUMUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ " GRLUMUsuario.iCveUsuario,"
					+ " GRLUMUsuario.iCveUniMed,"
					+ " GRLUMUsuario.iCveProceso,"
					+ " GRLProceso.cDscProceso"
					+ " from GRLUMUsuario "
					+ " join GRLProceso on GRLProceso.iCveProceso = GRLUMUsuario.iCveProceso "
					+ cWhere + cOrderBy;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUMUsuario = new TVGRLUMUsuario();
				vGRLUMUsuario.setICveUsuario(rset.getInt(1));
				vGRLUMUsuario.setICveUniMed(rset.getInt(2));
				vGRLUMUsuario.setICveProceso(rset.getInt(3));
				vGRLUMUsuario.setCDscProceso(rset.getString(4));
				vcGRLUMUsuario.addElement(vGRLUMUsuario);
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
			return vcGRLUMUsuario;
		}
	}

	/**
	 * Metodo Find By All Simple
	 */
	public Vector FindByAllSimple(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcGRLUMUsuario = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVGRLUMUsuarioMP vGRLUMUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + " iCveUsuario, " + " iCveUniMed, "
					+ " iCveProceso " + " from GRLUMUsuario " + cWhere;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vGRLUMUsuario = new TVGRLUMUsuarioMP();
				vGRLUMUsuario.setICveUsuario(rset.getInt(1));
				vGRLUMUsuario.setICveUniMed(rset.getInt(2));
				vGRLUMUsuario.setICveProceso(rset.getInt(3));
				vcGRLUMUsuario.addElement(vGRLUMUsuario);
			}
		} catch (Exception ex) {
			warn("FindByAllSimple", ex);
			throw new DAOException("FindByAllSimple");
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
				warn("FindByAllSimple.close", ex2);
			}
			return vcGRLUMUsuario;
		}
	}

	/**
	 * Metodo Delete 2
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
			TVGRLUMUsuarioMP vGRLUMUsuario = (TVGRLUMUsuarioMP) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from GRLUMUsuario" + " where iCveUsuario = ?"
					+ " and iCveUniMed = ?" + " and iCveProceso = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vGRLUMUsuario.getICveUsuario());
			pstmt.setInt(2, vGRLUMUsuario.getICveUniMed());
			pstmt.setInt(3, vGRLUMUsuario.getICveProceso());
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
				warn("delete.closeGRLUMUsuario", ex2);
			}
			return cClave;
		}
	}
}
