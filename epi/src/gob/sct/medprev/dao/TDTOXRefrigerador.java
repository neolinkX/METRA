package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dao.*;
import gob.sct.medprev.vo.*;
import com.micper.seguridad.dao.*;
import com.micper.seguridad.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXRefrigerador DAO
 * </p>
 * <p>
 * Description: DAO de la Tabla TOXRefrigerador
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Ernesto Avalos
 * @version 1.0
 */

public class TDTOXRefrigerador extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXRefrigerador() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXRefrigerador = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXRefrigerador vTOXRefrigerador;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select " + " iCveRefrigerador, " + " r.iCveEquipo, "
					+ "  e.cDscEquipo, " + " a.cDscArea, e.cCveEquipo "
					+ " from TOXRefrigerador r, eqmequipo e, toxarea a   "
					+ " where r.icveequipo=e.icveequipo "
					+ " and r.icvearea = a.icvearea " + filtro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXRefrigerador = new TVTOXRefrigerador();
				vTOXRefrigerador.setICveRefrigerador(rset.getInt(1));
				vTOXRefrigerador.setICveEquipo(rset.getInt(2));
				vTOXRefrigerador.setCDscEquipo(rset.getString(5) + " / "
						+ rset.getString(3));
				vTOXRefrigerador.setCDscArea(rset.getString(4));
				vcTOXRefrigerador.addElement(vTOXRefrigerador);
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
			return vcTOXRefrigerador;
		}
	}

	/**
	 * /** Metodo Find By All 2, Tablas: TOXRefrigerador, EQMEquipo
	 */
	public Vector FindByAll2(Object Obj, String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXRefrigerador = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXRefrigerador vTOXRefrigerador = (TVTOXRefrigerador) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			String cSQL = "";

			cSQL = "select  TOXRefrigerador.iCveRefrigerador, "
					+ "    EQMequipo.cNumSerie, "
					+ "    TOXArea.iCveArea, "
					+ "    TOXTemperRefr.dTemperatura, "
					+ "    EQMEquipo.cCveEquipo "
					+ "from TOXRefrigerador "
					+ "    inner join EQMEquipo on TOXRefrigerador.iCveEquipo = EQMEquipo.iCveEquipo "
					+ "    inner join TOXArea on TOXRefrigerador.iCveArea = TOXArea.iCveArea "
					+ "    inner join TOXTurnoRef on TOXTurnoRef.iAnio = "
					+ vTOXRefrigerador.getIAnio()
					+ "            and TOXTurnoRef.iCveTurnoValida = "
					+ vTOXRefrigerador.getICveTurnoValida()
					+ "            and TOXTurnoRef.iMes = "
					+ vTOXRefrigerador.getIMes()
					+ "            and TOXTurnoRef.iDia =  "
					+ vTOXRefrigerador.getIDia()
					+ "    inner join TOXTemperRefr on TOXTemperRefr.iAnio = TOXTurnoRef.iAnio "
					+ "            and TOXTemperRefr.iCveTurnoRef = TOXTurnoRef.iCveTurnoRef "
					+ "            and TOXTemperRefr.iCveRefrigerador = TOXRefrigerador.iCveRefrigerador "
					+ "where TOXRefrigerador.iCveArea = TOXArea.iCveArea "
					+ "and TOXRefrigerador.iCveArea = "
					+ vTOXRefrigerador.getICveArea() + cFiltro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXRefrigerador = new TVTOXRefrigerador();
				vTOXRefrigerador.setICveRefrigerador(rset.getInt(1));
				vTOXRefrigerador.setCNumSerie(rset.getString(2).toString());
				vTOXRefrigerador.setICveArea(rset.getInt(3));
				vTOXRefrigerador.setDTemperatura(rset.getFloat(4));
				vTOXRefrigerador.setCDscEquipo(rset.getString(5));
				vcTOXRefrigerador.addElement(vTOXRefrigerador);
			}
		} catch (Exception ex) {
			warn("FindByAll2", ex);
			throw new DAOException("FindByAll2");
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
				warn("FindByAll2.close", ex2);
			}
			return vcTOXRefrigerador;
		}
	}

	/**
	 * /** Metodo Find By All 3
	 */
	public Vector FindByAll3(Object Obj, String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXRefrigerador = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXRefrigerador vTOXRefrigerador = (TVTOXRefrigerador) Obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			String cSQL = "";

			cSQL = " select  TOXRefrigerador.iCveRefrigerador, EQMequipo.cNumSerie, TOXArea.iCveArea,  "
					+ "         EQMEquipo.cCveEquipo "
					+ " from TOXRefrigerador "
					+ " inner join EQMEquipo on TOXRefrigerador.iCveEquipo = EQMEquipo.iCveEquipo "
					+ " inner join TOXArea on TOXRefrigerador.iCveArea = TOXArea.iCveArea "
					+ " inner join TOXTurnoValida on TOXTurnoValida.iCveTurnoValida = "
					+ vTOXRefrigerador.getICveTurnoValida()
					+ "                          and TOXTurnoValida.iCveArea = TOXRefrigerador.iCveArea "
					+ " where TOXRefrigerador.iCveArea = "
					+ vTOXRefrigerador.getICveArea()
					+ " and TOXRefrigerador.iCveArea = TOXArea.iCveArea "
					+ cFiltro;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXRefrigerador = new TVTOXRefrigerador();
				vTOXRefrigerador.setICveRefrigerador(rset.getInt(1));
				vTOXRefrigerador.setCNumSerie(rset.getString(2).toString());
				vTOXRefrigerador.setICveArea(rset.getInt(3));
				vTOXRefrigerador.setCDscEquipo(rset.getString(4));
				vcTOXRefrigerador.addElement(vTOXRefrigerador);
			}
		} catch (Exception ex) {
			warn("FindByAll3", ex);
			throw new DAOException("FindByAll3");
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
				warn("FindByAll3.close", ex2);
			}
			return vcTOXRefrigerador;
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
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXRefrigerador vTOXRefrigerador = (TVTOXRefrigerador) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXRefrigerador(" + "       iCveRefrigerador,"
					+ "       iCveEquipo,      " + "       iCveArea         "
					+ ")values(?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cNewClave = "SELECT MAX(ICVEREFRIGERADOR) FROM TOXREFRIGERADOR";
			PreparedStatement psNewCve = conn.prepareStatement(cNewClave);
			ResultSet rsNewClave = psNewCve.executeQuery();
			int newCve = 0;
			if (rsNewClave.next()) {
				newCve = rsNewClave.getInt(1) + 1;
			}
			rsNewClave.close();
			psNewCve.close();

			pstmt.setInt(1, newCve);
			pstmt.setInt(2, vTOXRefrigerador.getICveEquipo());
			pstmt.setInt(3, vTOXRefrigerador.getICveArea());
			pstmt.executeUpdate();
			cClave = "" + newCve;
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
			TVTOXRefrigerador vTOXRefrigerador = (TVTOXRefrigerador) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXRefrigerador" + " set iCveEquipo= ?, "
					+ " iCveArea= ? " + "where iCveRefrigerador = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXRefrigerador.getICveEquipo());
			pstmt.setInt(2, vTOXRefrigerador.getICveArea());
			pstmt.setInt(3, vTOXRefrigerador.getICveRefrigerador());
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
			TVTOXRefrigerador vTOXRefrigerador = (TVTOXRefrigerador) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXRefrigerador"
					+ " where iCveRefrigerador = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXRefrigerador.getICveRefrigerador());
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
				warn("delete.closeTOXRefrigerador", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All x �rea
	 */
	public Vector FindByAllxArea(String filtro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXRefrigerador = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXRefrigerador vTOXRefrigerador;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "r.iCveRefrigerador,"
					+ "r.iCveEquipo,"
					+ "e.cDscEquipo, "
					+ "a.iCveArea, "
					+ "a.cDscArea, "
					+ "e.cCveEquipo, "
					+ "e.cNumSerie, e.cModelo "
					+ " from TOXRefrigerador r, eqmequipo e, ToxArea a "
					+ " where r.icveequipo=e.icveequipo and r.iCveArea=a.iCveArea "
					+ filtro;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXRefrigerador = new TVTOXRefrigerador();
				vTOXRefrigerador.setICveRefrigerador(rset.getInt(1));
				vTOXRefrigerador.setICveEquipo(rset.getInt(2));
				vTOXRefrigerador.setCDscEquipo(rset.getString(6) + " / "
						+ rset.getString(3));
				vTOXRefrigerador.setICveArea(rset.getInt(4));
				vTOXRefrigerador.setCDscArea(rset.getString(5));
				vTOXRefrigerador.setCNumSerie(rset.getString(7));
				vcTOXRefrigerador.addElement(vTOXRefrigerador);
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
			return vcTOXRefrigerador;
		}
	}

}
