package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXTurnoValida DAO
 * </p>
 * <p>
 * Description: DAO para la tabla TOXTurnoValida
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban viveros
 * @version 1.0
 */

public class TDTOXTurnoValida extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXTurnoValida() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltra, String cOrdena) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTurnoValida = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cWhere = cFiltra != null && cFiltra.length() != 0 ? " where "
					+ cFiltra
					: "";
			String cOrderBy = cOrdena != null && cOrdena.length() != 0 ? cOrdena
					: " order by iCveTurnoValida";
			TVTOXTurnoValida vTOXTurnoValida = null;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iCveTurnoValida," + "cDscTurno," + "cDscBreve,"
					+ "iCveUsuRespon" + " from TOXTurnoValida";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTurnoValida = new TVTOXTurnoValida();
				vTOXTurnoValida.setICveTurnoValida(rset.getInt(1));
				vTOXTurnoValida.setCDscTurno(rset.getString(2));
				vTOXTurnoValida.setCDscBreve(rset.getString(3));
				vTOXTurnoValida.setICveUsuRespon(rset.getInt(4));
				vcTOXTurnoValida.addElement(vTOXTurnoValida);
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
			return vcTOXTurnoValida;
		}
	}

	/**
	 * Metodo Find By All 2
	 */
	public Vector FindByAll2(Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTurnoValida = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXTurnoValida vTOXTurnoValida = (TVTOXTurnoValida) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			String cSQL = "";

			cSQL = "select icveturnovalida, cdscturno, toxturnovalida.icvearea "
					+ "from toxturnovalida "
					+ "join toxarea on toxturnovalida.icvearea = toxarea.icvearea "
					+ "where toxturnovalida.icvearea = "
					+ vTOXTurnoValida.getICveArea();

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTurnoValida = new TVTOXTurnoValida();
				vTOXTurnoValida.setICveTurnoValida(rset.getInt(1));
				vTOXTurnoValida.setCDscTurno(rset.getString(2));
				vTOXTurnoValida.setICveArea(rset.getInt(3));
				vcTOXTurnoValida.addElement(vTOXTurnoValida);
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
			return vcTOXTurnoValida;
		}
	}

	/**
	 * Metodo Find By All 3, Tablas: TOXTurnoValida, TOXArea
	 */
	public Vector FindByAll3(String cWhere, String cOrderBy)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTurnoValida = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXTurnoValida vTOXTurnoValida = null;
			int count;
			cSQL = "select iCveTurnoValida, TOXTurnoValida.iCveArea, cDscTurno, "
					+ "cDscBreve, iCveUsuRespon, TOXArea.cDscArea, "
					+ "SEGUsuario.cNombre||' '||SEGUsuario.cApPaterno||' '||SEGUsuario.cApMaterno as cNomCompletoAutoriza "
					+ "from TOXTurnoValida "
					+ "join TOXArea on TOXTurnoValida.iCveArea = TOXArea.iCveArea "
					+ "left join SEGUsuario on TOXTurnoValida.iCveUsuRespon = SEGUsuario.iCveUsuario "
					+ cWhere + cOrderBy;
			// System.out.println("--Responsable  = " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTurnoValida = new TVTOXTurnoValida();
				vTOXTurnoValida.setICveTurnoValida(rset.getInt(1));
				vTOXTurnoValida.setICveArea(rset.getInt(2));
				vTOXTurnoValida.setCDscTurno(rset.getString(3));
				vTOXTurnoValida.setCDscBreve(rset.getString(4));
				vTOXTurnoValida.setICveUsuRespon(rset.getInt(5));
				vTOXTurnoValida.setCDscArea(rset.getString(6));
				vTOXTurnoValida.setCDscUsuResponsable(rset.getString(7));
				vcTOXTurnoValida.addElement(vTOXTurnoValida);
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
			return vcTOXTurnoValida;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		ResultSet rslt = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			TVTOXTurnoValida vTOXTurnoValida = (TVTOXTurnoValida) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			stmt = conn.createStatement();
			rslt = stmt
					.executeQuery("select max(iCveTurnoValida) cta from TOXTurnoValida");
			if (rslt.next()) {
				vTOXTurnoValida.setICveTurnoValida(rslt.getInt("cta") + 1);
			} else {
				vTOXTurnoValida.setICveTurnoValida(1);
			}
			rslt.close();
			stmt.close();
			rslt = null;
			stmt = null;

			if (vTOXTurnoValida.getICveTurnoValida() > 0) {
				String cSQL = "insert into TOXTurnoValida("
						+ "iCveTurnoValida," + "cDscTurno," + "cDscBreve,"
						+ "iCveUsuRespon, " + "iCveArea "
						+ ") values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vTOXTurnoValida.getICveTurnoValida());
				pstmt.setString(2, vTOXTurnoValida.getCDscTurno());
				pstmt.setString(3, vTOXTurnoValida.getCDscBreve());
				pstmt.setInt(4, vTOXTurnoValida.getICveUsuRespon());
				pstmt.setInt(5, vTOXTurnoValida.getICveArea());
				pstmt.executeUpdate();
				cClave = "" + vTOXTurnoValida.getICveTurnoValida();
				if (conGral == null) {
					conn.commit();
				}
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
				if (rslt != null) {
					rslt.close();
				}
				if (stmt != null) {
					stmt.close();
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
			TVTOXTurnoValida vTOXTurnoValida = (TVTOXTurnoValida) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXTurnoValida" + " set cDscTurno= ?, "
					+ "cDscBreve= ?, " + "iCveUsuRespon= ?, " + "iCveArea= ? "
					+ "where iCveTurnoValida = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vTOXTurnoValida.getCDscTurno());
			pstmt.setString(2, vTOXTurnoValida.getCDscBreve());
			pstmt.setInt(3, vTOXTurnoValida.getICveUsuRespon());
			pstmt.setInt(4, vTOXTurnoValida.getICveArea());
			pstmt.setInt(5, vTOXTurnoValida.getICveTurnoValida());
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
			TVTOXTurnoValida vTOXTurnoValida = (TVTOXTurnoValida) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXTurnoValida" + " where iCveTurnoValida = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXTurnoValida.getICveTurnoValida());
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
				warn("delete.closeTOXTurnoValida", ex2);
			}
			return cClave;
		}
	}
}
