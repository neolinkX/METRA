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
 * Title: Data Acces Object de TOXAbsorMuestra DAO
 * </p>
 * <p>
 * Description: Listado de Manejo de calibraciones
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

public class TDTOXAbsorMuestra extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXAbsorMuestra() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String xAnio, String xCve) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Vector vcTOXAbsorMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			int nAnio = 0;
			int nCve = 0;
			String zCve = "0";
			// System.out.println("xCVE "+xCve);
			if (xCve != null && xCve.length() > 0) {
				nCve = Integer.parseInt(xCve);
			}
			// System.out.println("nCVE "+nCve);
			nAnio = Integer.parseInt(xAnio);

			TVTOXAbsorMuestra vTOXAbsorMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select a.iAnio,a.iCveAbsorvancia, "
					+ " a.iCveMAbsorvancia, "
					+ " a.iCveSustancia, "
					+ " a.dConcentracion,"
					+ " a.dPorcentaje, "
					+ " a.iCarrusel, "
					+ " a.iPosicion, "
					+ " cDscSustancia "
					+ " from TOXAbsorMuestra a "
					+ " left join  ToxSustancia on ToxSustancia.iCveSustancia = a.iCveSustancia "
					+ " Where a.iAnio = " + nAnio
					+ " And   a.iCveAbsorvancia= " + nCve
					+ " order by iCveAbsorvancia";
			// System.out.println("SQL ==>> " +cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			// System.out.println("PASE SELECT");
			while (rset.next()) {
				vTOXAbsorMuestra = new TVTOXAbsorMuestra();
				vTOXAbsorMuestra.setIAnio(rset.getInt(1));
				vTOXAbsorMuestra.setICveAbsorvancia(rset.getInt(2));
				vTOXAbsorMuestra.setICveMAbsorvancia(rset.getInt(3));
				vTOXAbsorMuestra.setICveSustancia(rset.getInt(4));
				vTOXAbsorMuestra.setDConcentracion(rset.getFloat(5));
				vTOXAbsorMuestra.setDPorcentaje(rset.getFloat(6));
				vTOXAbsorMuestra.setICarrusel(rset.getInt(7));
				vTOXAbsorMuestra.setIPosicion(rset.getInt(8));
				vTOXAbsorMuestra.setCDscSustancia(rset.getString(9));
				vcTOXAbsorMuestra.addElement(vTOXAbsorMuestra);
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
			return vcTOXAbsorMuestra;
		}
	}

	/**
	 * Este metodo se utiliza unicamente para la visualizacion de resultados de
	 * muestras
	 * 
	 * @param xAnio
	 * @param xCve
	 * @param xCve2
	 * @param xCve3
	 * @return
	 * @throws DAOException
	 */
	public Vector FindByAllRes(String xAnio, String xCve, String xCve2,
			String xCve3) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Vector vcTOXAbsorMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";

			TVTOXAbsorMuestra vTOXAbsorMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select iCveSustancia,dResultado " + " from TOXAbsorResult "
					+ " Where iAnio = " + xAnio + " And   iCveAbsorvancia= "
					+ xCve + " And   iCveMAbsorvancia= " + xCve2
					+ " And   iCveSustancia   = " + xCve3
					+ " order by iCveAbsorvancia";
			// System.out.println("SQL ==>> " +cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXAbsorMuestra = new TVTOXAbsorMuestra();
				vTOXAbsorMuestra.setICveSustancia(rset.getInt(1));
				vTOXAbsorMuestra.setDResultado(rset.getFloat(2));
				vcTOXAbsorMuestra.addElement(vTOXAbsorMuestra);
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
			return vcTOXAbsorMuestra;
		}
	}

	/**
	 * 
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, String cAnio,
			String cCveAbsorvancia, String cCveMAbsorvancia,
			String cCveSustancia, float iConcentracion, float iPorcentaje,
			String cCarrusel, String cPosicion) throws DAOException {

		// System.out.println("Antes de grabar 2a "+cAnio+cCveAbsorvancia+
		// cCveMAbsorvancia+cCveSustancia+
		// iConcentracion+iPorcentaje+
		// cCarrusel+cPosicion);
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
			// TVTOXAbsorMuestra vTOXAbsorMuestra = (TVTOXAbsorMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXAbsorMuestra(" + "iAnio,"
					+ "iCveAbsorvancia," + "iCveMAbsorvancia,"
					+ "iCveSustancia," + "dConcentracion," + "dPorcentaje,"
					+ "iCarrusel," + "iPosicion" + ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, cAnio);
			pstmt.setString(2, cCveAbsorvancia);
			pstmt.setString(3, cCveMAbsorvancia);
			pstmt.setString(4, cCveSustancia);
			pstmt.setFloat(5, iConcentracion);
			pstmt.setFloat(6, iPorcentaje);
			pstmt.setString(7, cCarrusel);
			pstmt.setString(8, cPosicion);
			pstmt.executeUpdate();
			cClave = cCveMAbsorvancia;
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

	public Object insertRes(Connection conGral, String cAnio,
			String cCveAbsorvancia, String cCveMAbsorvancia,
			String cCveSustancia, float iResult) throws DAOException {
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
			// TVTOXAbsorMuestra vTOXAbsorMuestra = (TVTOXAbsorMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXAbsorResult(" + "iAnio,"
					+ "iCveAbsorvancia," + "iCveMAbsorvancia,"
					+ "iCveSustancia," + "dResultado" + ")values(?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, cAnio);
			pstmt.setString(2, cCveAbsorvancia);
			pstmt.setString(3, cCveMAbsorvancia);
			pstmt.setString(4, cCveSustancia);
			pstmt.setFloat(5, iResult);
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
			TVTOXAbsorMuestra vTOXAbsorMuestra = (TVTOXAbsorMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXAbsorMuestra" + " set iCveSustancia= ?, "
					+ "dConcentracion= ?, " + "dPorcentaje= ?, "
					+ "iCarrusel= ?, " + "iPosicion= ? " + "where iAnio = ? "
					+ "and iCveAbsorvancia = ?" + " and iCveMAbsorvancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXAbsorMuestra.getICveSustancia());
			pstmt.setFloat(2, vTOXAbsorMuestra.getDConcentracion());
			pstmt.setFloat(3, vTOXAbsorMuestra.getDPorcentaje());
			pstmt.setInt(4, vTOXAbsorMuestra.getICarrusel());
			pstmt.setInt(5, vTOXAbsorMuestra.getIPosicion());
			pstmt.setInt(6, vTOXAbsorMuestra.getIAnio());
			pstmt.setInt(7, vTOXAbsorMuestra.getICveAbsorvancia());
			pstmt.setInt(8, vTOXAbsorMuestra.getICveMAbsorvancia());
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
			TVTOXAbsorMuestra vTOXAbsorMuestra = (TVTOXAbsorMuestra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXAbsorMuestra" + " where iAnio = ?"
					+ " and iCveAbsorvancia = ?" + " and iCveMAbsorvancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXAbsorMuestra.getIAnio());
			pstmt.setInt(2, vTOXAbsorMuestra.getICveAbsorvancia());
			pstmt.setInt(3, vTOXAbsorMuestra.getICveMAbsorvancia());
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
				warn("delete.closeTOXAbsorMuestra", ex2);
			}
			return cClave;
		}
	}
}