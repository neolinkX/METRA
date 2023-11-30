package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXTemperRefr DAO
 * </p>
 * <p>
 * Description: DAO de la tabla TOXTemperRefr
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Leonel Popoca G.
 * @version 1.0
 */

public class TDTOXTemperRefr extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private pg070307020Bean bean2;

	public TDTOXTemperRefr() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTemperRefr = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXTemperRefr vTOXTemperRefr;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveTurnoRef," + "iCveRefrigerador,"
					+ "dTemperatura" + " from TOXTemperRefr order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTemperRefr = new TVTOXTemperRefr();
				vTOXTemperRefr.setIAnio(rset.getInt(1));
				vTOXTemperRefr.setICveTurnoRef(rset.getInt(2));
				vTOXTemperRefr.setICveRefrigerador(rset.getInt(3));
				vTOXTemperRefr.setDTemperatura(rset.getFloat(4));
				vcTOXTemperRefr.addElement(vTOXTemperRefr);
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
			return vcTOXTemperRefr;
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
			TVTOXTemperRefr vTOXTemperRefr = (TVTOXTemperRefr) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXTemperRefr(" + "iAnio," + "iCveTurnoRef,"
					+ "iCveRefrigerador," + "dTemperatura" + ")values(?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA

			pstmt.setInt(1, vTOXTemperRefr.getIAnio());
			pstmt.setInt(2, vTOXTemperRefr.getICveTurnoRef());
			pstmt.setInt(3, vTOXTemperRefr.getICveRefrigerador());
			pstmt.setDouble(4, vTOXTemperRefr.getDTemperatura());
			pstmt.executeUpdate();
			cClave = "" + vTOXTemperRefr.getIAnio();
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
			TVTOXTemperRefr vTOXTemperRefr = (TVTOXTemperRefr) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXTemperRefr" + " set dTemperatura= ?, "
					+ "where iAnio = ? " + "and iCveTurnoRef = ?"
					+ " and iCveRefrigerador = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setFloat(1, vTOXTemperRefr.getDTemperatura());
			pstmt.setInt(2, vTOXTemperRefr.getIAnio());
			pstmt.setInt(3, vTOXTemperRefr.getICveTurnoRef());
			pstmt.setInt(4, vTOXTemperRefr.getICveRefrigerador());
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
			TVTOXTemperRefr vTOXTemperRefr = (TVTOXTemperRefr) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXTemperRefr" + " where iAnio = ?"
					+ " and iCveTurnoRef = ?" + " and iCveRefrigerador = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXTemperRefr.getIAnio());
			pstmt.setInt(2, vTOXTemperRefr.getICveTurnoRef());
			pstmt.setInt(3, vTOXTemperRefr.getICveRefrigerador());
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
				warn("delete.closeTOXTemperRefr", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cfiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTemperRefr = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXTemperRefr vTOXTemperRefr;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveTurnoRef," + "iCveRefrigerador,"
					+ "dTemperatura" + " from TOXTemperRefr " + cfiltro
					+ " order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTemperRefr = new TVTOXTemperRefr();
				vTOXTemperRefr.setIAnio(rset.getInt(1));
				vTOXTemperRefr.setICveTurnoRef(rset.getInt(2));
				vTOXTemperRefr.setICveRefrigerador(rset.getInt(3));
				vTOXTemperRefr.setDTemperatura(rset.getFloat(4));
				vcTOXTemperRefr.addElement(vTOXTemperRefr);
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
			return vcTOXTemperRefr;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 22/Sep/2006
	 * 
	 * Obtiene el reporte de Excel.
	 * 
	 * @param iAnio
	 *            int
	 * @param iMes
	 *            int
	 * @param iCveArea
	 *            int
	 * @param iCveEquipo
	 *            int
	 * @param listTurnos
	 *            List
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean generaReporte(int iAnio, int iMes, int iCveArea,
			int iCveEquipo, List listTurnos) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JXLSBean jxlsBean = new JXLSBean("");
		bean2 = new pg070307020Bean();
		bean2.setIanio(new Integer(iAnio));
		bean2.setImes(new Integer(iMes));
		TDTOXArea dTOXArea = new TDTOXArea();
		Vector vcTOXArea = dTOXArea.FindByAll(" where iCveArea = " + iCveArea);

		for (int i = 0; i < vcTOXArea.size(); i++) {
			TVTOXArea vTOXArea = (TVTOXArea) vcTOXArea.get(i);
			bean2.setCdscarea(vTOXArea.getCDscArea());
		}

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			for (int i = 0; i < listTurnos.size(); i++) {
				cSQL = "SELECT (SELECT CCVEEQUIPO " + "        FROM EQMEQUIPO "
						+ "        WHERE ICVEEQUIPO = "
						+ iCveEquipo
						+ ") AS CCVEEQUIPO, "
						+ "       (SELECT CMODELO "
						+ "        FROM EQMEQUIPO "
						+ "        WHERE ICVEEQUIPO = "
						+ iCveEquipo
						+ ") AS CMODELO, "
						+ "       (SELECT CNUMSERIE "
						+ "        FROM EQMEQUIPO "
						+ "        WHERE ICVEEQUIPO = "
						+ iCveEquipo
						+ ") AS CNUMSERIE, "
						+ "       (SELECT ICVEMARCA "
						+ "        FROM EQMEQUIPO "
						+ "        WHERE ICVEEQUIPO = "
						+ iCveEquipo
						+ ") AS ICVEMARCA, "
						+ "       CDSCBREVE, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 1 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA1, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 2 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA2, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 3 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA3, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 4 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA4, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 5 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA5, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 6 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA6, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 7 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA7, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 8 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA8, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 9 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA9, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 10 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA10, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 11 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA11, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 12 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA12, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 13 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA13, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 14 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA14, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 15 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA15, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 16 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA16, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 17 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA17, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 18 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA18, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 19 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA19, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 20 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA20, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 21 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA21, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 22 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA22, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 23 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA23, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 24 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA24, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 25 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA25, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 26 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA26, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 27 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA27, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 28 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA28, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 29 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA29, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 30 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA30, "
						+ "       (SELECT DTEMPERATURA "
						+ "        FROM TOXTEMPERREFR "
						+ "        WHERE IANIO = "
						+ iAnio
						+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
						+ "                                FROM TOXREFRIGERADOR "
						+ "                                WHERE ICVEAREA = "
						+ iCveArea
						+ "                                AND ICVEEQUIPO = "
						+ iCveEquipo
						+ ") "
						+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                            FROM TOXTURNOREF "
						+ "                            WHERE IANIO = "
						+ iAnio
						+ "                            AND IMES = "
						+ iMes
						+ "                            AND IDIA = 31 "
						+ "                            AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA31 "
						+ " FROM TOXTURNOVALIDA "
						+ " WHERE ICVEAREA = "
						+ iCveArea
						+ " AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue();

				pstmt = conn.prepareStatement(cSQL);
				rset = pstmt.executeQuery();

				while (rset.next()) {
					pg070307020Bean bean = new pg070307020Bean();
					bean.setCcveequipo(rset.getString(1));
					bean.setCmodelo(rset.getString(2));
					bean.setCnumserie(rset.getString(3));
					bean.setCdscmarca(String.valueOf(rset.getInt(4)));
					bean.setCdscbreve(rset.getString(5));
					bean.setDtemperatura1(new Double(rset.getDouble(6)));
					bean.setDtemperatura2(new Double(rset.getDouble(7)));
					bean.setDtemperatura3(new Double(rset.getDouble(8)));
					bean.setDtemperatura4(new Double(rset.getDouble(9)));
					bean.setDtemperatura5(new Double(rset.getDouble(10)));
					bean.setDtemperatura6(new Double(rset.getDouble(11)));
					bean.setDtemperatura7(new Double(rset.getDouble(12)));
					bean.setDtemperatura8(new Double(rset.getDouble(13)));
					bean.setDtemperatura9(new Double(rset.getDouble(14)));
					bean.setDtemperatura10(new Double(rset.getDouble(15)));
					bean.setDtemperatura11(new Double(rset.getDouble(16)));
					bean.setDtemperatura12(new Double(rset.getDouble(17)));
					bean.setDtemperatura13(new Double(rset.getDouble(18)));
					bean.setDtemperatura14(new Double(rset.getDouble(19)));
					bean.setDtemperatura15(new Double(rset.getDouble(20)));
					bean.setDtemperatura16(new Double(rset.getDouble(21)));
					bean.setDtemperatura17(new Double(rset.getDouble(22)));
					bean.setDtemperatura18(new Double(rset.getDouble(23)));
					bean.setDtemperatura19(new Double(rset.getDouble(24)));
					bean.setDtemperatura20(new Double(rset.getDouble(25)));
					bean.setDtemperatura21(new Double(rset.getDouble(26)));
					bean.setDtemperatura22(new Double(rset.getDouble(27)));
					bean.setDtemperatura23(new Double(rset.getDouble(28)));
					bean.setDtemperatura24(new Double(rset.getDouble(29)));
					bean.setDtemperatura25(new Double(rset.getDouble(30)));
					bean.setDtemperatura26(new Double(rset.getDouble(31)));
					bean.setDtemperatura27(new Double(rset.getDouble(32)));
					bean.setDtemperatura28(new Double(rset.getDouble(33)));
					bean.setDtemperatura29(new Double(rset.getDouble(34)));
					bean.setDtemperatura30(new Double(rset.getDouble(35)));
					bean.setDtemperatura31(new Double(rset.getDouble(36)));

					bean2.setIcvemarca(new Integer(rset.getInt(4)));
					bean2.setCmodelo(rset.getString(2));
					bean2.setCnumserie(rset.getString(3));

					jxlsBean.addBean(bean);
				}
			}
		} catch (Exception ex) {
			warn("generaReporte", ex);
			throw new DAOException("generaReporte");
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
				warn("generaReporte.close", ex2);
			}

			return jxlsBean;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 22/Sep/2006
	 * 
	 * Obtiene las temperaturas de los equipos para que sean mostradas en la
	 * pantalla.
	 * 
	 * @param iAnio
	 *            int
	 * @param iMes
	 *            int
	 * @param iCveArea
	 *            int
	 * @param listTurnos
	 *            List
	 * @param listEquipos
	 *            List
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean findTemperaturas(int iAnio, int iMes, int iCveArea,
			List listTurnos, List listEquipos) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JXLSBean jxlsBean = new JXLSBean("");

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			for (int k = 0; k < listEquipos.size(); k++) {
				for (int i = 0; i < listTurnos.size(); i++) {
					cSQL = "SELECT (SELECT CCVEEQUIPO "
							+ "        FROM EQMEQUIPO "
							+ "        WHERE ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") AS CCVEEQUIPO, "
							+ "       (SELECT CMODELO "
							+ "        FROM EQMEQUIPO "
							+ "        WHERE ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") AS CMODELO, "
							+ "       (SELECT CNUMSERIE "
							+ "        FROM EQMEQUIPO "
							+ "        WHERE ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") AS CNUMSERIE, "
							+ "       (SELECT ICVEMARCA "
							+ "        FROM EQMEQUIPO "
							+ "        WHERE ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") AS ICVEMARCA, "
							+ "       CDSCBREVE, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 1 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA1, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 2 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA2, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 3 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA3, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 4 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA4, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 5 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA5, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 6 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA6, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 7 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA7, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 8 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA8, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 9 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA9, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 10 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA10, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 11 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA11, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 12 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA12, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 13 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA13, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 14 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA14, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 15 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA15, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 16 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA16, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 17 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA17, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 18 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA18, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 19 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA19, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 20 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA20, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 21 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA21, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 22 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA22, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 23 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA23, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 24 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA24, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 25 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA25, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 26 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA26, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 27 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA27, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 28 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA28, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 29 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA29, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 30 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA30, "
							+ "       (SELECT DTEMPERATURA "
							+ "        FROM TOXTEMPERREFR "
							+ "        WHERE IANIO = "
							+ iAnio
							+ "        AND ICVEREFRIGERADOR = (SELECT ICVEREFRIGERADOR "
							+ "                                FROM TOXREFRIGERADOR "
							+ "                                WHERE ICVEAREA = "
							+ iCveArea
							+ "                                AND ICVEEQUIPO = "
							+ ((Integer) listEquipos.get(k)).intValue()
							+ ") "
							+ "        AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                            FROM TOXTURNOREF "
							+ "                            WHERE IANIO = "
							+ iAnio
							+ "                            AND IMES = "
							+ iMes
							+ "                            AND IDIA = 31 "
							+ "                            AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA31 "
							+ " FROM TOXTURNOVALIDA "
							+ " WHERE ICVEAREA = "
							+ iCveArea
							+ " AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue();

					pstmt = conn.prepareStatement(cSQL);
					rset = pstmt.executeQuery();

					while (rset.next()) {
						pg070307020Bean bean = new pg070307020Bean();
						bean.setCcveequipo(rset.getString(1));
						bean.setCmodelo(rset.getString(2));
						bean.setCnumserie(rset.getString(3));
						bean.setCdscmarca(String.valueOf(rset.getInt(4)));
						bean.setCdscbreve(rset.getString(5));
						bean.setDtemperatura1(new Double(rset.getDouble(6)));
						bean.setDtemperatura2(new Double(rset.getDouble(7)));
						bean.setDtemperatura3(new Double(rset.getDouble(8)));
						bean.setDtemperatura4(new Double(rset.getDouble(9)));
						bean.setDtemperatura5(new Double(rset.getDouble(10)));
						bean.setDtemperatura6(new Double(rset.getDouble(11)));
						bean.setDtemperatura7(new Double(rset.getDouble(12)));
						bean.setDtemperatura8(new Double(rset.getDouble(13)));
						bean.setDtemperatura9(new Double(rset.getDouble(14)));
						bean.setDtemperatura10(new Double(rset.getDouble(15)));
						bean.setDtemperatura11(new Double(rset.getDouble(16)));
						bean.setDtemperatura12(new Double(rset.getDouble(17)));
						bean.setDtemperatura13(new Double(rset.getDouble(18)));
						bean.setDtemperatura14(new Double(rset.getDouble(19)));
						bean.setDtemperatura15(new Double(rset.getDouble(20)));
						bean.setDtemperatura16(new Double(rset.getDouble(21)));
						bean.setDtemperatura17(new Double(rset.getDouble(22)));
						bean.setDtemperatura18(new Double(rset.getDouble(23)));
						bean.setDtemperatura19(new Double(rset.getDouble(24)));
						bean.setDtemperatura20(new Double(rset.getDouble(25)));
						bean.setDtemperatura21(new Double(rset.getDouble(26)));
						bean.setDtemperatura22(new Double(rset.getDouble(27)));
						bean.setDtemperatura23(new Double(rset.getDouble(28)));
						bean.setDtemperatura24(new Double(rset.getDouble(29)));
						bean.setDtemperatura25(new Double(rset.getDouble(30)));
						bean.setDtemperatura26(new Double(rset.getDouble(31)));
						bean.setDtemperatura27(new Double(rset.getDouble(32)));
						bean.setDtemperatura28(new Double(rset.getDouble(33)));
						bean.setDtemperatura29(new Double(rset.getDouble(34)));
						bean.setDtemperatura30(new Double(rset.getDouble(35)));
						bean.setDtemperatura31(new Double(rset.getDouble(36)));

						jxlsBean.addBean(bean);
					}
				} // fin for i
			} // fin for k
		} catch (Exception ex) {
			warn("generaReporte", ex);
			throw new DAOException("generaReporte");
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
				warn("generaReporte.close", ex2);
			}

			return jxlsBean;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 20/Sep/2006
	 * 
	 * Obtiene los iCveTurnoValida de la tabla TOXTurnoValida a partir de la
	 * clave del Area (iCveArea).
	 * 
	 * @param iCveArea
	 *            int
	 * @throws DAOException
	 * @return List
	 */
	public List findTurnos(int iCveArea) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List list = new ArrayList();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " SELECT iCveTurnoValida " + " FROM TOXTURNOVALIDA "
					+ " WHERE ICVEAREA = " + iCveArea;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Integer(rset.getInt(1)));
			}
		} catch (Exception ex) {
			warn("findTurnos", ex);
			throw new DAOException("findTurnos");
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
				warn("findTurnos.close", ex2);
			}

			return list;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 20/Sep/2006
	 * 
	 * Obtiene los iCveEquipo de la tabla EQMEQUIPO de todos los Refrigeradores
	 * y Congeladores.
	 * 
	 * @throws DAOException
	 * @return List
	 */
	public List findEquipos() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List list = new ArrayList();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " SELECT ICVEEQUIPO FROM EQMEQUIPO "
					+ " WHERE CDSCEQUIPO LIKE '%REFRIGERADOR%' "
					+ "                  OR CDSCEQUIPO LIKE '%CONGELADOR%' "
					+ " ORDER BY CCVEEQUIPO";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Integer(rset.getInt(1)));
			}
		} catch (Exception ex) {
			warn("findTurnos", ex);
			throw new DAOException("findTurnos");
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
				warn("findTurnos.close", ex2);
			}

			return list;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 21/Sep/2006
	 * 
	 * @return pg070307020Bean
	 */
	public pg070307020Bean getBean2() {
		return bean2;
	}
}
