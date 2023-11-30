package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXValPresuntivo DAO
 * </p>
 * <p>
 * Description: TD de TOXVALPRESUNTIVO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Juan Manuel Vazquez
 * @version 1.0
 */

public class TDTOXValPresuntivo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXValPresuntivo() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TFechas dtFecha = new TFechas();
		String cFecha;

		Vector vcTOXValPresuntivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL, cSQLJoin = "";

			TVTOXValPresuntivo vTOXValPresuntivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "iAnio," + "iCveLaboratorio," + "iCveValPres,"
					+ "iCveCtrolCalibra," + "iCveEquipo," + "iCveSustancia,"
					+ "dtEstudio," + "dCorte," + "dCorteNeg," + "dCortePost,"
					+ "cObservacion," + "iCveUsuResp"
					+ " from TOXVALPRESUNTIVO " + cWhere + cOrden;

			cSQLJoin = "select "
					+ "V.iAnio,"
					+ "V.iCveLaboratorio,"
					+ "V.iCveValPres,"
					+ "V.iCveCtrolCalibra,"
					+ "V.iCveEquipo,"
					+ "V.iCveSustancia,"
					+ "V.dtEstudio,"
					+ "V.dCorte,"
					+ "V.dCorteNeg,"
					+ "V.dCortePost,"
					+ "V.cObservacion,"
					+ "V.iCveUsuResp,"
					+ "L.cDscUniMed,"
					+ "C.cDscCtrolCalibra,"
					+ "DE.cDscEquipo,"
					+ "S.cDscSustancia, "
					+ "DE.cModelo, "
					+ "DE.cNumSerie "
					+ "from TOXVALPRESUNTIVO V "
					+ "inner join GRLUniMed L on ( V.iCveLaboratorio = L.iCveUniMed ) "
					+ "inner join TOXCtrolCalibra C on ( V.iCveCtrolCalibra = C.iCveCtrolCalibra and V.iCveLaboratorio = C.iCveLaboratorio) "
					+ "inner join EQMEquipo DE on ( V.iCveEquipo = DE.iCveEquipo ) "
					+ "inner join TOXSustancia S on ( V.iCveSustancia = S.iCveSustancia ) "
					+ cWhere + cOrden;
			// System.out.println("----- > Q valPresuntivo : " + cSQLJoin);
			pstmt = conn.prepareStatement(cSQLJoin);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vTOXValPresuntivo = new TVTOXValPresuntivo();
				vTOXValPresuntivo.setIAnio(rset.getInt(1));
				vTOXValPresuntivo.setICveLaboratorio(rset.getInt(2));
				vTOXValPresuntivo.setICveValPres(rset.getInt(3));
				vTOXValPresuntivo.setICveCtrolCalibra(rset.getInt(4));
				vTOXValPresuntivo.setICveEquipo(rset.getInt(5));
				vTOXValPresuntivo.setICveSustancia(rset.getInt(6));
				vTOXValPresuntivo.setDtEstudio(rset.getDate(7));
				vTOXValPresuntivo.setDCorte(rset.getFloat(8));
				vTOXValPresuntivo.setDCorteNeg(rset.getFloat(9));
				vTOXValPresuntivo.setDCortePost(rset.getFloat(10));
				vTOXValPresuntivo.setCObservacion(rset.getString(11));
				vTOXValPresuntivo.setICveUsuResp(rset.getInt(12));

				if (rset.getString(13) != null) {
					vTOXValPresuntivo.setCDscUniMed(rset.getString(13));
				} else {
					vTOXValPresuntivo.setCDscUniMed("");
				}
				if (rset.getString(14) != null) {
					vTOXValPresuntivo.setCDscCtrolCalibra(rset.getString(14));
				} else {
					vTOXValPresuntivo.setCDscCtrolCalibra("");
				}
				if (rset.getString(15) != null) {
					vTOXValPresuntivo.setCDscEquipo(rset.getString(15));
				} else {
					vTOXValPresuntivo.setCDscEquipo("");
				}
				if (rset.getString(16) != null) {
					vTOXValPresuntivo.setCDscSustancia(rset.getString(16));
				} else {
					vTOXValPresuntivo.setCDscSustancia("");
				}

				vTOXValPresuntivo.setCModelo(rset.getString(17));
				vTOXValPresuntivo.setCNumSerie(rset.getString(18));

				// cDtPreparacion

				if (vTOXValPresuntivo.getDtEstudio() != null) {
					cFecha = "";
					cFecha = dtFecha.getFechaDDMMYYYY(
							vTOXValPresuntivo.getDtEstudio(), "/");
					vTOXValPresuntivo.setCDtEstudio(cFecha);
				} else {
					vTOXValPresuntivo.setCDtEstudio("");

				}

				vcTOXValPresuntivo.addElement(vTOXValPresuntivo);

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
			return vcTOXValPresuntivo;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtToxCorte = null;
		PreparedStatement pstmtA = null;
		int iCve = 0;
		// ResultSet rset = null;
		ResultSet rsetToxCorte = null;
		ResultSet rsetA = null;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXValPresuntivo vTOXValPresuntivo = (TVTOXValPresuntivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "";
			cSQL = "Select max(iCveValPres) from TOXValPresuntivo "
					+ " where iAnio = " + vTOXValPresuntivo.getIAnio()
					+ " and iCveLaboratorio = "
					+ vTOXValPresuntivo.getICveLaboratorio();
			pstmtA = conn.prepareStatement(cSQL);
			rsetA = pstmtA.executeQuery();
			if (rsetA.next()) {
				iCve = rsetA.getInt(1) + 1;
			}

			vTOXValPresuntivo.setICveValPres(iCve);
			// Busqueda de Corte Pos y Neg
			cSQL = "";
			cSQL = "Select dCorte,dCorteNeg, dCortePost from TOXCorteXSust "
					+ " where iCveSustancia = "
					+ vTOXValPresuntivo.getICveSustancia()
					+ " and lCuantCual = " + vTOXValPresuntivo.getLCuantCual()
					+ " and lActivo = 1";
			// System.out.println("Q lCuantCual: " + cSQL);
			pstmtToxCorte = conn.prepareStatement(cSQL);
			rsetToxCorte = pstmtToxCorte.executeQuery();
			if (rsetToxCorte.next()) {
				vTOXValPresuntivo.setDCorte(rsetToxCorte.getInt(1));
				vTOXValPresuntivo.setDCorteNeg(rsetToxCorte.getInt(2));
				vTOXValPresuntivo.setDCortePost(rsetToxCorte.getInt(3));
			}

			//

			cSQL = "insert into TOXVALPRESUNTIVO(" + "iAnio,"
					+ "iCveLaboratorio," + "iCveValPres," + "iCveCtrolCalibra,"
					+ "iCveEquipo," + "iCveSustancia," + "dtEstudio,"
					+ "dCorte," + "dCorteNeg," + "dCortePost,"
					+ "cObservacion," + "iCveUsuResp"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXValPresuntivo.getIAnio());
			pstmt.setInt(2, vTOXValPresuntivo.getICveLaboratorio());
			pstmt.setInt(3, vTOXValPresuntivo.getICveValPres());
			pstmt.setInt(4, vTOXValPresuntivo.getICveCtrolCalibra());
			pstmt.setInt(5, vTOXValPresuntivo.getICveEquipo());
			pstmt.setInt(6, vTOXValPresuntivo.getICveSustancia());
			pstmt.setDate(7, vTOXValPresuntivo.getDtEstudio());
			pstmt.setFloat(8, vTOXValPresuntivo.getDCorte());
			pstmt.setFloat(9, vTOXValPresuntivo.getDCorteNeg());
			pstmt.setFloat(10, vTOXValPresuntivo.getDCortePost());
			pstmt.setString(11, vTOXValPresuntivo.getCObservacion());
			pstmt.setInt(12, vTOXValPresuntivo.getICveUsuResp());
			pstmt.executeUpdate();
			cClave = "" + vTOXValPresuntivo.getICveValPres();
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

				if (pstmtA != null) {
					pstmtA.close();
				}

				if (pstmtToxCorte != null) {
					pstmtToxCorte.close();
				}

				/*
				 * if (rset != null) { rset.close(); }
				 */

				if (rsetA != null) {
					rsetA.close();
				}

				if (rsetToxCorte != null) {
					rsetToxCorte.close();
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
			TVTOXValPresuntivo vTOXValPresuntivo = (TVTOXValPresuntivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXVALPRESUNTIVO" + " set iCveCtrolCalibra= ?, "
					+ "iCveEquipo= ?, " + "iCveSustancia= ?, "
					+ "dtEstudio= ?, " + "dCorte= ?, " + "dCorteNeg= ?, "
					+ "dCortePost= ?, " + "cObservacion= ?, "
					+ "iCveUsuResp= ? " + "where iAnio = ? "
					+ "and iCveLaboratorio = ?" + " and iCveValPres = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXValPresuntivo.getICveCtrolCalibra());
			pstmt.setInt(2, vTOXValPresuntivo.getICveEquipo());
			pstmt.setInt(3, vTOXValPresuntivo.getICveSustancia());
			pstmt.setDate(4, vTOXValPresuntivo.getDtEstudio());
			pstmt.setFloat(5, vTOXValPresuntivo.getDCorte());
			pstmt.setFloat(6, vTOXValPresuntivo.getDCorteNeg());
			pstmt.setFloat(7, vTOXValPresuntivo.getDCortePost());
			pstmt.setString(8, vTOXValPresuntivo.getCObservacion());
			pstmt.setInt(9, vTOXValPresuntivo.getICveUsuResp());
			pstmt.setInt(10, vTOXValPresuntivo.getIAnio());
			pstmt.setInt(11, vTOXValPresuntivo.getICveLaboratorio());
			pstmt.setInt(12, vTOXValPresuntivo.getICveValPres());
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
			TVTOXValPresuntivo vTOXValPresuntivo = (TVTOXValPresuntivo) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "delete from TOXVALPRESUNTIVO" + " where iAnio = ?"
					+ " and iCveLaboratorio = ?" + " and iCveValPres = ?";
			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vTOXValPresuntivo.getIAnio());
			pstmt.setInt(2, vTOXValPresuntivo.getICveLaboratorio());
			pstmt.setInt(3, vTOXValPresuntivo.getICveValPres());

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
				warn("delete.closeTOXValPresuntivo", ex2);
			}
			return cClave;
		}
	}
}
