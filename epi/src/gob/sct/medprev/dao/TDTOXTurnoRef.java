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
 * Title: Data Acces Object de TOXTurnoRef DAO
 * </p>
 * <p>
 * Description: DAO de la tabla TOXTurnoRef
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

public class TDTOXTurnoRef extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private pg070307020Bean bean2; // Agregado Rafael Alcocer Caldera
									// 27/Sep/2006

	public TDTOXTurnoRef() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXTurnoRef = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXTurnoRef vTOXTurnoRef;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveTurnoRef," + "iCveTurnoValida,"
					+ "iCveUsuRespon," + "iMes," + "iDia," + "dTempAmbiente,"
					+ "dHumedad " + " from TOXTurnoRef order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTurnoRef = new TVTOXTurnoRef();
				vTOXTurnoRef.setIAnio(rset.getInt(1));
				vTOXTurnoRef.setICveTurnoRef(rset.getInt(2));
				vTOXTurnoRef.setICveTurnoValida(rset.getInt(3));
				vTOXTurnoRef.setICveUsuRespon(rset.getInt(4));
				vTOXTurnoRef.setIMes(rset.getInt(5));
				vTOXTurnoRef.setIDia(rset.getInt(6));
				vTOXTurnoRef.setDTempAmbiente(rset.getFloat(7));
				vTOXTurnoRef.setDHumedad(rset.getFloat(8));
				vcTOXTurnoRef.addElement(vTOXTurnoRef);
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
			return vcTOXTurnoRef;
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
		Vector vcTOXTurnoRef = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXTurnoRef vTOXTurnoRef;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveTurnoRef," + "iCveTurnoValida,"
					+ "iCveUsuRespon," + "iMes," + "iDia," + "dTempAmbiente, "
					+ "dHumedad " + " from TOXTurnoRef " + cWhere
					+ " order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXTurnoRef = new TVTOXTurnoRef();
				vTOXTurnoRef.setIAnio(rset.getInt(1));
				vTOXTurnoRef.setICveTurnoRef(rset.getInt(2));
				vTOXTurnoRef.setICveTurnoValida(rset.getInt(3));
				vTOXTurnoRef.setICveUsuRespon(rset.getInt(4));
				vTOXTurnoRef.setIMes(rset.getInt(5));
				vTOXTurnoRef.setIDia(rset.getInt(6));
				vTOXTurnoRef.setDTempAmbiente(rset.getFloat(7));
				vTOXTurnoRef.setDHumedad(rset.getFloat(8));
				vcTOXTurnoRef.addElement(vTOXTurnoRef);
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
			return vcTOXTurnoRef;
		}
	}

	/**
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
			TVTOXTurnoRef vTOXTurnoRef = (TVTOXTurnoRef) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "insert into TOXTurnoRef(" + "iAnio," + "iCveTurnoRef,"
					+ "iCveTurnoValida," + "iCveUsuRespon," + "iMes," + "iDia,"
					+ "dTempAmbiente," + "dHumedad "
					+ ")values(?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			String cSQLMax = "select max(iCveTurnoRef) from TOXTurnoRef";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			if (iMax == 0) {
				iMax = 1;
			} else {
				iMax += 1;
			}
			vTOXTurnoRef.setICveTurnoRef(iMax);
			// *****************************************************************
			pstmt.setInt(1, vTOXTurnoRef.getIAnio());
			pstmt.setInt(2, vTOXTurnoRef.getICveTurnoRef());
			pstmt.setInt(3, vTOXTurnoRef.getICveTurnoValida());
			pstmt.setInt(4, vTOXTurnoRef.getICveUsuRespon());
			pstmt.setInt(5, vTOXTurnoRef.getIMes());
			pstmt.setInt(6, vTOXTurnoRef.getIDia());
			pstmt.setFloat(7, vTOXTurnoRef.getDTempAmbiente());
			pstmt.setFloat(8, vTOXTurnoRef.getDHumedad());
			pstmt.executeUpdate();
			cClave = "" + vTOXTurnoRef.getIAnio();
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
				if (pstmtMax != null) {
					pstmtMax.close();
				}
				if (rsetMax != null) {
					rsetMax.close();
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
			TVTOXTurnoRef vTOXTurnoRef = (TVTOXTurnoRef) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXTurnoRef" + " set iCveTurnoValida= ?, "
					+ "iCveUsuRespon= ?, " + "iMes= ?, " + "iDia= ?, "
					+ "dTempAmbiente = ?, " + "dHumedad = ? "
					+ "where iAnio = ? " + " and iCveTurnoRef = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXTurnoRef.getICveTurnoValida());
			pstmt.setInt(2, vTOXTurnoRef.getICveUsuRespon());
			pstmt.setInt(3, vTOXTurnoRef.getIMes());
			pstmt.setInt(4, vTOXTurnoRef.getIDia());
			pstmt.setFloat(5, vTOXTurnoRef.getDTempAmbiente());
			pstmt.setFloat(6, vTOXTurnoRef.getDHumedad());
			pstmt.setInt(7, vTOXTurnoRef.getIAnio());
			pstmt.setInt(8, vTOXTurnoRef.getICveTurnoRef());
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
			TVTOXTurnoRef vTOXTurnoRef = (TVTOXTurnoRef) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXTurnoRef" + " where iAnio = ?"
					+ " and iCveTurnoRef = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXTurnoRef.getIAnio());
			pstmt.setInt(2, vTOXTurnoRef.getICveTurnoRef());
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
				warn("delete.closeTOXTurnoRef", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 27/Sep/2006
	 * 
	 * Obtiene el reporte de Excel para la Temperatura Ambiente.
	 * 
	 * @param iAnio
	 *            int
	 * @param iMes
	 *            int
	 * @param iCveArea
	 *            int
	 * @param listTurnos
	 *            List
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean generaReporteTempAmbiente(int iAnio, int iMes,
			int iCveArea, List listTurnos) throws DAOException {
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
				cSQL = " SELECT (SELECT CDSCAREA " + "         FROM TOXAREA "
						+ "         WHERE ICVEAREA = "
						+ iCveArea
						+ ") AS AREA, "
						+ "         CDSCBREVE, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 1
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 1
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA1, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 2
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 2
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA2, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 3
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 3
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA3, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 4
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 4
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA4, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 5
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 5
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA5, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 6
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 6
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA6, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 7
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 7
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA7, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 8
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 8
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA8, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 9
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 9
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA9, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 10
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 10
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA10, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 11
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 11
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA11, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 12
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 12
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA12, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 13
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 13
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA13, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 14
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 14
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA14, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 15
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 15
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA15, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 16
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 16
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA16, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 17
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 17
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA17, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 18
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 18
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA18, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 19
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 19
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA19, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 20
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 20
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA20, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 21
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 21
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA21, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 22
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 22
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA22, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 23
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 23
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA23, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 24
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 24
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA24, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 25
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 25
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA25, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 26
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 26
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA26, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 27
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 27
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA27, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 28
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 28
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA28, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 29
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 29
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA29, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 30
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 30
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA30, "
						+ "        (SELECT DTEMPAMBIENTE "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 31
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 31
						+ "                             AND ICVETURNOVALIDA = "
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
					bean.setCdscarea(rset.getString(1));
					bean.setCdscbreve(rset.getString(2));
					bean.setDtemperatura1(new Double(rset.getDouble(3)));
					bean.setDtemperatura2(new Double(rset.getDouble(4)));
					bean.setDtemperatura3(new Double(rset.getDouble(5)));
					bean.setDtemperatura4(new Double(rset.getDouble(6)));
					bean.setDtemperatura5(new Double(rset.getDouble(7)));
					bean.setDtemperatura6(new Double(rset.getDouble(8)));
					bean.setDtemperatura7(new Double(rset.getDouble(9)));
					bean.setDtemperatura8(new Double(rset.getDouble(10)));
					bean.setDtemperatura9(new Double(rset.getDouble(11)));
					bean.setDtemperatura10(new Double(rset.getDouble(12)));
					bean.setDtemperatura11(new Double(rset.getDouble(13)));
					bean.setDtemperatura12(new Double(rset.getDouble(14)));
					bean.setDtemperatura13(new Double(rset.getDouble(15)));
					bean.setDtemperatura14(new Double(rset.getDouble(16)));
					bean.setDtemperatura15(new Double(rset.getDouble(17)));
					bean.setDtemperatura16(new Double(rset.getDouble(18)));
					bean.setDtemperatura17(new Double(rset.getDouble(19)));
					bean.setDtemperatura18(new Double(rset.getDouble(20)));
					bean.setDtemperatura19(new Double(rset.getDouble(21)));
					bean.setDtemperatura20(new Double(rset.getDouble(22)));
					bean.setDtemperatura21(new Double(rset.getDouble(23)));
					bean.setDtemperatura22(new Double(rset.getDouble(24)));
					bean.setDtemperatura23(new Double(rset.getDouble(25)));
					bean.setDtemperatura24(new Double(rset.getDouble(26)));
					bean.setDtemperatura25(new Double(rset.getDouble(27)));
					bean.setDtemperatura26(new Double(rset.getDouble(28)));
					bean.setDtemperatura27(new Double(rset.getDouble(29)));
					bean.setDtemperatura28(new Double(rset.getDouble(30)));
					bean.setDtemperatura29(new Double(rset.getDouble(31)));
					bean.setDtemperatura30(new Double(rset.getDouble(32)));
					bean.setDtemperatura31(new Double(rset.getDouble(33)));

					bean2.setCdscarea(bean.getCdscArea());

					jxlsBean.addBean(bean);
				}
			}
		} catch (Exception ex) {
			warn("generaReporteTempAmbiente", ex);
			throw new DAOException("generaReporteTempAmbiente");
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
				warn("generaReporteTempAmbiente.close", ex2);
			}

			return jxlsBean;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 27/Sep/2006
	 * 
	 * Obtiene las temperaturas ambiente para que sean mostradas en la pantalla.
	 * 
	 * @param iAnio
	 *            int
	 * @param iMes
	 *            int
	 * @param listAreas
	 *            List
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean findTemperaturasAmbiente(int iAnio, int iMes, List listAreas)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JXLSBean jxlsBean = new JXLSBean("");
		bean2 = new pg070307020Bean();
		bean2.setIanio(new Integer(iAnio));
		bean2.setImes(new Integer(iMes));

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			for (int k = 0; k < listAreas.size(); k++) {
				// Obtengo los turnos para cada area
				List listTurnos = findTurnos(((Integer) listAreas.get(k))
						.intValue());

				for (int i = 0; i < listTurnos.size(); i++) {
					cSQL = " SELECT (SELECT CDSCAREA "
							+ "         FROM TOXAREA "
							+ "         WHERE ICVEAREA = "
							+ ((Integer) listAreas.get(k)).intValue()
							+ ") AS AREA, "
							+ "         CDSCBREVE, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 1
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 1
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA1, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 2
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 2
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA2, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 3
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 3
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA3, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 4
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 4
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA4, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 5
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 5
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA5, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 6
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 6
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA6, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 7
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 7
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA7, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 8
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 8
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA8, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 9
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 9
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA9, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 10
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 10
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA10, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 11
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 11
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA11, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 12
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 12
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA12, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 13
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 13
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA13, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 14
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 14
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA14, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 15
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 15
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA15, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 16
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 16
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA16, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 17
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 17
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA17, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 18
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 18
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA18, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 19
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 19
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA19, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 20
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 20
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA20, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 21
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 21
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA21, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 22
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 22
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA22, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 23
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 23
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA23, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 24
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 24
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA24, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 25
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 25
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA25, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 26
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 26
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA26, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 27
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 27
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA27, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 28
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 28
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA28, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 29
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 29
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA29, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 30
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 30
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA30, "
							+ "        (SELECT DTEMPAMBIENTE "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 31
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 31
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA31 "
							+ " FROM TOXTURNOVALIDA "
							+ " WHERE ICVEAREA = "
							+ ((Integer) listAreas.get(k)).intValue()
							+ " AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue();

					pstmt = conn.prepareStatement(cSQL);
					rset = pstmt.executeQuery();

					while (rset.next()) {
						pg070307020Bean bean = new pg070307020Bean();
						bean.setCdscarea(rset.getString(1));
						bean.setCdscbreve(rset.getString(2));
						bean.setDtemperatura1(new Double(rset.getDouble(3)));
						bean.setDtemperatura2(new Double(rset.getDouble(4)));
						bean.setDtemperatura3(new Double(rset.getDouble(5)));
						bean.setDtemperatura4(new Double(rset.getDouble(6)));
						bean.setDtemperatura5(new Double(rset.getDouble(7)));
						bean.setDtemperatura6(new Double(rset.getDouble(8)));
						bean.setDtemperatura7(new Double(rset.getDouble(9)));
						bean.setDtemperatura8(new Double(rset.getDouble(10)));
						bean.setDtemperatura9(new Double(rset.getDouble(11)));
						bean.setDtemperatura10(new Double(rset.getDouble(12)));
						bean.setDtemperatura11(new Double(rset.getDouble(13)));
						bean.setDtemperatura12(new Double(rset.getDouble(14)));
						bean.setDtemperatura13(new Double(rset.getDouble(15)));
						bean.setDtemperatura14(new Double(rset.getDouble(16)));
						bean.setDtemperatura15(new Double(rset.getDouble(17)));
						bean.setDtemperatura16(new Double(rset.getDouble(18)));
						bean.setDtemperatura17(new Double(rset.getDouble(19)));
						bean.setDtemperatura18(new Double(rset.getDouble(20)));
						bean.setDtemperatura19(new Double(rset.getDouble(21)));
						bean.setDtemperatura20(new Double(rset.getDouble(22)));
						bean.setDtemperatura21(new Double(rset.getDouble(23)));
						bean.setDtemperatura22(new Double(rset.getDouble(24)));
						bean.setDtemperatura23(new Double(rset.getDouble(25)));
						bean.setDtemperatura24(new Double(rset.getDouble(26)));
						bean.setDtemperatura25(new Double(rset.getDouble(27)));
						bean.setDtemperatura26(new Double(rset.getDouble(28)));
						bean.setDtemperatura27(new Double(rset.getDouble(29)));
						bean.setDtemperatura28(new Double(rset.getDouble(30)));
						bean.setDtemperatura29(new Double(rset.getDouble(31)));
						bean.setDtemperatura30(new Double(rset.getDouble(32)));
						bean.setDtemperatura31(new Double(rset.getDouble(33)));

						bean2.setCdscarea(bean.getCdscArea());

						jxlsBean.addBean(bean);
					}
				}
			}
		} catch (Exception ex) {
			warn("generaReporteTempAmbiente", ex);
			throw new DAOException("generaReporteTempAmbiente");
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
				warn("generaReporteTempAmbiente.close", ex2);
			}

			return jxlsBean;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 27/Sep/2006
	 * 
	 * Obtiene el reporte de Excel para la Humedad.
	 * 
	 * @param iAnio
	 *            int
	 * @param iMes
	 *            int
	 * @param iCveArea
	 *            int
	 * @param listTurnos
	 *            List
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean generaReporteHumedad(int iAnio, int iMes, int iCveArea,
			List listTurnos) throws DAOException {
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
				cSQL = " SELECT (SELECT CDSCAREA " + "         FROM TOXAREA "
						+ "         WHERE ICVEAREA = "
						+ iCveArea
						+ ") AS AREA, "
						+ "         CDSCBREVE, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 1
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 1
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA1, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 2
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 2
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA2, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 3
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 3
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA3, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 4
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 4
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA4, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 5
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 5
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA5, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 6
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 6
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA6, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 7
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 7
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA7, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 8
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 8
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA8, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 9
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 9
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA9, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 10
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 10
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA10, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 11
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 11
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA11, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 12
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 12
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA12, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 13
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 13
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA13, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 14
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 14
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA14, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 15
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 15
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA15, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 16
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 16
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA16, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 17
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 17
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA17, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 18
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 18
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA18, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 19
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 19
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA19, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 20
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 20
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA20, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 21
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 21
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA21, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 22
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 22
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA22, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 23
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 23
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA23, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 24
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 24
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA24, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 25
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 25
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA25, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 26
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 26
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA26, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 27
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 27
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA27, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 28
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 28
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA28, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 29
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 29
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA29, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 30
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 30
						+ "                             AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ ")) AS DIA30, "
						+ "        (SELECT DHUMEDAD "
						+ "         FROM TOXTURNOREF "
						+ "         WHERE IANIO = "
						+ iAnio
						+ "         AND IMES = "
						+ iMes
						+ "         AND IDIA = "
						+ 31
						+ "         AND ICVETURNOVALIDA = "
						+ ((Integer) listTurnos.get(i)).intValue()
						+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
						+ "                             FROM TOXTURNOREF "
						+ "                             WHERE IANIO = "
						+ iAnio
						+ "                             AND IMES = "
						+ iMes
						+ "                             AND IDIA = "
						+ 31
						+ "                             AND ICVETURNOVALIDA = "
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
					bean.setCdscarea(rset.getString(1));
					bean.setCdscbreve(rset.getString(2));
					bean.setDtemperatura1(new Double(rset.getDouble(3)));
					bean.setDtemperatura2(new Double(rset.getDouble(4)));
					bean.setDtemperatura3(new Double(rset.getDouble(5)));
					bean.setDtemperatura4(new Double(rset.getDouble(6)));
					bean.setDtemperatura5(new Double(rset.getDouble(7)));
					bean.setDtemperatura6(new Double(rset.getDouble(8)));
					bean.setDtemperatura7(new Double(rset.getDouble(9)));
					bean.setDtemperatura8(new Double(rset.getDouble(10)));
					bean.setDtemperatura9(new Double(rset.getDouble(11)));
					bean.setDtemperatura10(new Double(rset.getDouble(12)));
					bean.setDtemperatura11(new Double(rset.getDouble(13)));
					bean.setDtemperatura12(new Double(rset.getDouble(14)));
					bean.setDtemperatura13(new Double(rset.getDouble(15)));
					bean.setDtemperatura14(new Double(rset.getDouble(16)));
					bean.setDtemperatura15(new Double(rset.getDouble(17)));
					bean.setDtemperatura16(new Double(rset.getDouble(18)));
					bean.setDtemperatura17(new Double(rset.getDouble(19)));
					bean.setDtemperatura18(new Double(rset.getDouble(20)));
					bean.setDtemperatura19(new Double(rset.getDouble(21)));
					bean.setDtemperatura20(new Double(rset.getDouble(22)));
					bean.setDtemperatura21(new Double(rset.getDouble(23)));
					bean.setDtemperatura22(new Double(rset.getDouble(24)));
					bean.setDtemperatura23(new Double(rset.getDouble(25)));
					bean.setDtemperatura24(new Double(rset.getDouble(26)));
					bean.setDtemperatura25(new Double(rset.getDouble(27)));
					bean.setDtemperatura26(new Double(rset.getDouble(28)));
					bean.setDtemperatura27(new Double(rset.getDouble(29)));
					bean.setDtemperatura28(new Double(rset.getDouble(30)));
					bean.setDtemperatura29(new Double(rset.getDouble(31)));
					bean.setDtemperatura30(new Double(rset.getDouble(32)));
					bean.setDtemperatura31(new Double(rset.getDouble(33)));

					bean2.setCdscarea(bean.getCdscArea());

					jxlsBean.addBean(bean);
				}
			}
		} catch (Exception ex) {
			warn("generaReporteHumedad", ex);
			throw new DAOException("generaReporteHumedad");
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
				warn("generaReporteHumedad.close", ex2);
			}

			return jxlsBean;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 27/Sep/2006
	 * 
	 * Obtiene la Humedad de todas las areas para que sea mostrada en la
	 * pantalla.
	 * 
	 * @param iAnio
	 *            int
	 * @param iMes
	 *            int
	 * @param listAreas
	 *            List
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean findHumedad(int iAnio, int iMes, List listAreas)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JXLSBean jxlsBean = new JXLSBean("");
		bean2 = new pg070307020Bean();
		bean2.setIanio(new Integer(iAnio));
		bean2.setImes(new Integer(iMes));

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			for (int k = 0; k < listAreas.size(); k++) {
				// Obtengo los turnos para cada area
				List listTurnos = findTurnos(((Integer) listAreas.get(k))
						.intValue());

				for (int i = 0; i < listTurnos.size(); i++) {
					cSQL = " SELECT (SELECT CDSCAREA "
							+ "         FROM TOXAREA "
							+ "         WHERE ICVEAREA = "
							+ ((Integer) listAreas.get(k)).intValue()
							+ ") AS AREA, "
							+ "         CDSCBREVE, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 1
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 1
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA1, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 2
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 2
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA2, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 3
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 3
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA3, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 4
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 4
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA4, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 5
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 5
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA5, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 6
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 6
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA6, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 7
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 7
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA7, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 8
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 8
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA8, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 9
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 9
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA9, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 10
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 10
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA10, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 11
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 11
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA11, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 12
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 12
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA12, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 13
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 13
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA13, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 14
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 14
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA14, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 15
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 15
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA15, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 16
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 16
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA16, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 17
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 17
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA17, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 18
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 18
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA18, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 19
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 19
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA19, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 20
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 20
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA20, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 21
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 21
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA21, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 22
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 22
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA22, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 23
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 23
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA23, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 24
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 24
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA24, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 25
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 25
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA25, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 26
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 26
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA26, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 27
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 27
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA27, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 28
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 28
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA28, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 29
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 29
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA29, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 30
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 30
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA30, "
							+ "        (SELECT DHUMEDAD "
							+ "         FROM TOXTURNOREF "
							+ "         WHERE IANIO = "
							+ iAnio
							+ "         AND IMES = "
							+ iMes
							+ "         AND IDIA = "
							+ 31
							+ "         AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ "         AND ICVETURNOREF = (SELECT ICVETURNOREF "
							+ "                             FROM TOXTURNOREF "
							+ "                             WHERE IANIO = "
							+ iAnio
							+ "                             AND IMES = "
							+ iMes
							+ "                             AND IDIA = "
							+ 31
							+ "                             AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue()
							+ ")) AS DIA31 "
							+ " FROM TOXTURNOVALIDA "
							+ " WHERE ICVEAREA = "
							+ ((Integer) listAreas.get(k)).intValue()
							+ " AND ICVETURNOVALIDA = "
							+ ((Integer) listTurnos.get(i)).intValue();

					pstmt = conn.prepareStatement(cSQL);
					rset = pstmt.executeQuery();

					while (rset.next()) {
						pg070307020Bean bean = new pg070307020Bean();
						bean.setCdscarea(rset.getString(1));
						bean.setCdscbreve(rset.getString(2));
						bean.setDtemperatura1(new Double(rset.getDouble(3)));
						bean.setDtemperatura2(new Double(rset.getDouble(4)));
						bean.setDtemperatura3(new Double(rset.getDouble(5)));
						bean.setDtemperatura4(new Double(rset.getDouble(6)));
						bean.setDtemperatura5(new Double(rset.getDouble(7)));
						bean.setDtemperatura6(new Double(rset.getDouble(8)));
						bean.setDtemperatura7(new Double(rset.getDouble(9)));
						bean.setDtemperatura8(new Double(rset.getDouble(10)));
						bean.setDtemperatura9(new Double(rset.getDouble(11)));
						bean.setDtemperatura10(new Double(rset.getDouble(12)));
						bean.setDtemperatura11(new Double(rset.getDouble(13)));
						bean.setDtemperatura12(new Double(rset.getDouble(14)));
						bean.setDtemperatura13(new Double(rset.getDouble(15)));
						bean.setDtemperatura14(new Double(rset.getDouble(16)));
						bean.setDtemperatura15(new Double(rset.getDouble(17)));
						bean.setDtemperatura16(new Double(rset.getDouble(18)));
						bean.setDtemperatura17(new Double(rset.getDouble(19)));
						bean.setDtemperatura18(new Double(rset.getDouble(20)));
						bean.setDtemperatura19(new Double(rset.getDouble(21)));
						bean.setDtemperatura20(new Double(rset.getDouble(22)));
						bean.setDtemperatura21(new Double(rset.getDouble(23)));
						bean.setDtemperatura22(new Double(rset.getDouble(24)));
						bean.setDtemperatura23(new Double(rset.getDouble(25)));
						bean.setDtemperatura24(new Double(rset.getDouble(26)));
						bean.setDtemperatura25(new Double(rset.getDouble(27)));
						bean.setDtemperatura26(new Double(rset.getDouble(28)));
						bean.setDtemperatura27(new Double(rset.getDouble(29)));
						bean.setDtemperatura28(new Double(rset.getDouble(30)));
						bean.setDtemperatura29(new Double(rset.getDouble(31)));
						bean.setDtemperatura30(new Double(rset.getDouble(32)));
						bean.setDtemperatura31(new Double(rset.getDouble(33)));

						bean2.setCdscarea(bean.getCdscArea());

						jxlsBean.addBean(bean);
					}
				}
			}
		} catch (Exception ex) {
			warn("generaReporteTempAmbiente", ex);
			throw new DAOException("generaReporteTempAmbiente");
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
				warn("generaReporteTempAmbiente.close", ex2);
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
	 * Agregado por Rafael Alcocer Caldera 27/Sep/2006
	 * 
	 * Obtiene las descripciones de todas las areas.
	 * 
	 * @return List
	 */
	public List findAreas() {
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

			cSQL = " SELECT iCveArea " + " FROM TOXAREA ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(new Integer(rset.getInt(1)));
			}
		} catch (Exception ex) {
			warn("findAreas", ex);
			throw new DAOException("findAreas");
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
				warn("findAreas.close", ex2);
			}

			return list;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 27/Sep/2006
	 * 
	 * @return pg070307020Bean
	 */
	public pg070307020Bean getBean2() {
		return bean2;
	}
}
