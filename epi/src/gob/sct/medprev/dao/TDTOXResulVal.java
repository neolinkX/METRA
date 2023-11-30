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

/**
 * <p>
 * Title: Data Acces Object de TOXResulVal DAO
 * </p>
 * <p>
 * Description: DAO TOXResulVal
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

public class TDTOXResulVal extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXResulVal() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXResulVal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXResulVal vTOXResulVal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveLaboratorio," + "iCveValPres,"
					+ "iCveMValida," + "iCarrusel," + "iPosicion,"
					+ "dConcentracion," + "dPorcentaje," + "dResultado"
					+ " from TOXResulVal " + cFiltro;
			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXResulVal = new TVTOXResulVal();
				vTOXResulVal.setIAnio(rset.getInt(1));
				vTOXResulVal.setICveLaboratorio(rset.getInt(2));
				vTOXResulVal.setICveValPres(rset.getInt(3));
				vTOXResulVal.setICveMValida(rset.getInt(4));
				vTOXResulVal.setICarrusel(rset.getInt(5));
				vTOXResulVal.setIPosicion(rset.getInt(6));
				vTOXResulVal.setDConcentracion(rset.getFloat(7));
				vTOXResulVal.setDPorcentaje(rset.getFloat(8));
				vTOXResulVal.setDResultado(rset.getFloat(9));
				vcTOXResulVal.addElement(vTOXResulVal);
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
			return vcTOXResulVal;
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
			TVTOXResulVal vTOXResulVal = (TVTOXResulVal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXResulVal(" + "iAnio," + "iCveLaboratorio,"
					+ "iCveValPres," + "iCveMValida," + "iCarrusel,"
					+ "iPosicion," + "dConcentracion," + "dPorcentaje,"
					+ "dResultado" + ")values(?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			int iNewCve = 0;
			String q = "SELECT MAX(ICVEMVALIDA) FROM TOXRESULVAL"
					+ " WHERE IANIO=? AND ICVELABORATORIO=? AND ICVEVALPRES=?";

			PreparedStatement pNewCve = conn.prepareStatement(q);
			pNewCve.setInt(1, vTOXResulVal.getIAnio());
			pNewCve.setInt(2, vTOXResulVal.getICveLaboratorio());
			pNewCve.setInt(3, vTOXResulVal.getICveValPres());
			ResultSet rNewCve = pNewCve.executeQuery();
			while (rNewCve.next())
				iNewCve = rNewCve.getInt(1) + 1;
			rNewCve.close();
			pNewCve.close();

			pstmt.setInt(1, iNewCve);
			pstmt.setInt(2, vTOXResulVal.getICveLaboratorio());
			pstmt.setInt(3, vTOXResulVal.getICveValPres());
			pstmt.setInt(4, vTOXResulVal.getICveMValida());
			pstmt.setInt(5, vTOXResulVal.getICarrusel());
			pstmt.setInt(6, vTOXResulVal.getIPosicion());
			pstmt.setFloat(7, vTOXResulVal.getDConcentracion());
			pstmt.setFloat(8, vTOXResulVal.getDPorcentaje());
			pstmt.setFloat(9, vTOXResulVal.getDResultado());
			pstmt.executeUpdate();
			cClave = "" + vTOXResulVal.getIAnio();
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
	public Object insert(Connection conGral, Vector vDatos) throws DAOException {
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
			TVTOXResulVal vTOXResulVal = new TVTOXResulVal();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXResulVal(" + "iAnio," + "iCveLaboratorio,"
					+ "iCveValPres," + "iCveMValida," + "iCarrusel,"
					+ "iPosicion," + "dConcentracion," + "dPorcentaje,"
					+ "dResultado" + ")values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(cSQL);

			vTOXResulVal = (TVTOXResulVal) vDatos.firstElement();

			for (int i = 0; i < vDatos.size(); i++) {
				vTOXResulVal = (TVTOXResulVal) vDatos.get(i);
				int iNewCve = 0;
				String q = "SELECT MAX(ICVEMVALIDA) FROM TOXRESULVAL "
						+ "WHERE IANIO=? AND ICVELABORATORIO=? AND ICVEVALPRES=?";

				PreparedStatement pNewCve = conn.prepareStatement(q);
				pNewCve.setInt(1, vTOXResulVal.getIAnio());
				pNewCve.setInt(2, vTOXResulVal.getICveLaboratorio());
				pNewCve.setInt(3, vTOXResulVal.getICveValPres());
				ResultSet rNewCve = pNewCve.executeQuery();
				while (rNewCve.next())
					iNewCve = rNewCve.getInt(1) + 1;
				rNewCve.close();
				pNewCve.close();

				pstmt.setInt(1, vTOXResulVal.getIAnio());
				pstmt.setInt(2, vTOXResulVal.getICveLaboratorio());
				pstmt.setInt(3, vTOXResulVal.getICveValPres());
				pstmt.setInt(4, iNewCve);
				pstmt.setInt(5, 1);
				pstmt.setInt(6, i + 1);
				pstmt.setFloat(7, vTOXResulVal.getDConcentracion());
				pstmt.setFloat(8, vTOXResulVal.getDPorcentaje());
				pstmt.setFloat(9, vTOXResulVal.getDResultado());

				pstmt.executeUpdate();
				cClave = "" + vTOXResulVal.getIAnio();
				if (conGral == null) {
					conn.commit();
				}
				vTOXResulVal = null;
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
			TVTOXResulVal vTOXResulVal = (TVTOXResulVal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXResulVal" + " set iCarrusel= ?, "
					+ "iPosicion= ?, " + "dConcentracion= ?, "
					+ "dPorcentaje= ?, " + "dResultado= ? "
					+ "where iAnio = ? " + "and iCveLaboratorio = ?"
					+ "and iCveValPres = ?" + " and iCveMValida = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXResulVal.getICarrusel());
			pstmt.setInt(2, vTOXResulVal.getIPosicion());
			pstmt.setFloat(3, vTOXResulVal.getDConcentracion());
			pstmt.setFloat(4, vTOXResulVal.getDPorcentaje());
			pstmt.setFloat(5, vTOXResulVal.getDResultado());
			pstmt.setInt(6, vTOXResulVal.getIAnio());
			pstmt.setInt(7, vTOXResulVal.getICveLaboratorio());
			pstmt.setInt(8, vTOXResulVal.getICveValPres());
			pstmt.setInt(9, vTOXResulVal.getICveMValida());
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
			TVTOXResulVal vTOXResulVal = (TVTOXResulVal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXResulVal" + " where iAnio = ?"
					+ " and iCveLaboratorio = ?" + " and iCveValPres = ?"
					+ " and iCveMValida = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXResulVal.getIAnio());
			pstmt.setInt(2, vTOXResulVal.getICveLaboratorio());
			pstmt.setInt(3, vTOXResulVal.getICveValPres());
			pstmt.setInt(4, vTOXResulVal.getICveMValida());
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
				warn("delete.closeTOXResulVal", ex2);
			}
			return cClave;
		}
	}
}