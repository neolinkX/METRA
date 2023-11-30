package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXCualtSust DAO
 * </p>
 * <p>
 * Description: DAO para la tabla TOXCualtSust
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Esteban Viveros
 * @version 1.0
 */

public class TDTOXCualtSust extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXCualtSust() {
	}

	/**
	 * Metodo Find By All
	 */
	// public Vector FindByAll(String cWhere,String cOrderBy) throws
	// DAOException {
	public Vector FindByAll(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCualtSust = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXCualtSust vTOXCualtSust;
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "a.iAnio,a.iCveLoteCualita,a.iCveExamCualita,a.iCveLaboratorio,"
					+ "a.iCveSustancia,a.iCveCorte,b.cDscSustancia, b.cPrefLoteConf "
					+ "from TOXCualtSust a join TOXSustancia b on "
					+ "(a.iCveSustancia=b.iCveSustancia) "
					+ "where a.iAnio=? and a.iCveLaboratorio=? and a.iCveExamCualita=? and "
					+ "a.iCveLoteCualita=?";
			// cWhere + cOrderBy;

			// System.out.println("**********3*********\n"+cSQL);

			pstmt = conn.prepareStatement(cSQL);

			String cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCualtSust = new TVTOXCualtSust();
				vTOXCualtSust.setIAnio(rset.getInt("iAnio"));
				vTOXCualtSust
						.setICveLoteCualita(rset.getInt("iCveLoteCualita"));
				vTOXCualtSust
						.setICveExamCualita(rset.getInt("iCveExamCualita"));
				vTOXCualtSust
						.setICveLaboratorio(rset.getInt("iCveLaboratorio"));
				vTOXCualtSust.setICveSustancia(rset.getInt("iCveSustancia"));
				vTOXCualtSust.setICveCorte(rset.getInt("iCveCorte"));
				vTOXCualtSust.setcDscSustancia(rset.getString("cDscSustancia"));
				vTOXCualtSust.setcDscSustancia(rset.getString("cPrefLoteConf"));
				vcTOXCualtSust.addElement(vTOXCualtSust);
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
			return vcTOXCualtSust;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCualtSust = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXCualtSust vTOXCualtSust;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select " + "iAnio," + "iCveLoteCualita,"
					+ "iCveExamCualita," + "iCveLaboratorio,"
					+ "iCveSustancia," + "iCveCorte"
					+ " from TOXCualtSust order by iAnio";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCualtSust = new TVTOXCualtSust();
				vTOXCualtSust.setIAnio(rset.getInt(1));
				vTOXCualtSust.setICveLoteCualita(rset.getInt(2));
				vTOXCualtSust.setICveExamCualita(rset.getInt(3));
				vTOXCualtSust.setICveLaboratorio(rset.getInt(4));
				vTOXCualtSust.setICveSustancia(rset.getInt(5));
				vTOXCualtSust.setICveCorte(rset.getInt(6));
				vcTOXCualtSust.addElement(vTOXCualtSust);
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
			return vcTOXCualtSust;
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

			TVTOXCualtSust vTOXCualtSust = (TVTOXCualtSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "insert into TOXCualtSust(" + "iAnio,"
					+ "iCveLoteCualita," + "iCveExamCualita,"
					+ "iCveLaboratorio," + "iCveSustancia," + "iCveCorte"
					+ ")values(?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vTOXCualtSust.getIAnio());
			pstmt.setInt(2, vTOXCualtSust.getICveLoteCualita());
			pstmt.setInt(3, vTOXCualtSust.getICveExamCualita());
			pstmt.setInt(4, vTOXCualtSust.getICveLaboratorio());
			pstmt.setInt(5, vTOXCualtSust.getICveSustancia());
			pstmt.setInt(6, vTOXCualtSust.getICveCorte());
			pstmt.executeUpdate();
			cClave = "" + vTOXCualtSust.getIAnio();
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
			TVTOXCualtSust vTOXCualtSust = (TVTOXCualtSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "update TOXCualtSust" + " set iCveCorte= ?, "
					+ "where iAnio = ? " + "and iCveLoteCualita = ?"
					+ "and iCveExamCualita = ?" + "and iCveLaboratorio = ?"
					+ " and iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXCualtSust.getICveCorte());
			pstmt.setInt(2, vTOXCualtSust.getIAnio());
			pstmt.setInt(3, vTOXCualtSust.getICveLoteCualita());
			pstmt.setInt(4, vTOXCualtSust.getICveExamCualita());
			pstmt.setInt(5, vTOXCualtSust.getICveLaboratorio());
			pstmt.setInt(6, vTOXCualtSust.getICveSustancia());
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
			TVTOXCualtSust vTOXCualtSust = (TVTOXCualtSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXCualtSust" + " where iAnio = ?"
					+ " and iCveLoteCualita = ?" + " and iCveExamCualita = ?"
					+ " and iCveLaboratorio = ?" + " and iCveSustancia = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXCualtSust.getIAnio());
			pstmt.setInt(2, vTOXCualtSust.getICveLoteCualita());
			pstmt.setInt(3, vTOXCualtSust.getICveExamCualita());
			pstmt.setInt(4, vTOXCualtSust.getICveLaboratorio());
			pstmt.setInt(5, vTOXCualtSust.getICveSustancia());
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
				warn("delete.closeTOXCualtSust", ex2);
			}
			return cClave;
		}
	}

	public Vector verificaSustancias(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCualtSust = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// conn.setAutoCommit(false);
			// conn.setTransactionIsolation(2);
			String cSQL = "select a.iCveSustancia iCveSust1,b.iCveSustancia iCveSust2,"
					+ "c.cDscSustancia "
					+ "from TOXCualtSust a left join( "
					+ "select iAnio,iCveLoteCualita,iCveExamCualita,iCveLaboratorio,"
					+ "iCveSustancia "
					+ "from TOXCalibCualita where lAutorizado=1 "
					+ ") b on ( "
					+ "a.iAnio=b.iAnio and a.iCveLoteCualita=b.iCveLoteCualita and "
					+ "a.iCveExamCualita=b.iCveExamCualita and "
					+ "a.iCveLaboratorio=b.iCveLaboratorio and "
					+ "a.iCveSustancia=b.iCveSustancia "
					+ ") join TOXSustancia c on ( a.iCveSustancia=c.iCveSustancia and c.lAnalizada = 1) "
					+ "where a.iAnio=? and a.iCveLaboratorio=? and a.iCveExamCualita=? and "
					+ "a.iCveLoteCualita=?";

			pstmt = conn.prepareStatement(cSQL);

			String cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			rset = pstmt.executeQuery();
			TVDinamico vTVDinamico;
			while (rset.next()) {
				vTVDinamico = new TVDinamico();
				vTVDinamico.put("iCveSust1", rset.getInt("iCveSust1"));
				vTVDinamico.put("iCveSust2", rset.getInt("iCveSust2"));
				vTVDinamico.put("cDscSustancia",
						rset.getString("cDscSustancia"));
				vcTOXCualtSust.addElement(vTVDinamico);
			}
		} catch (Exception ex) {
			warn("verificaSustancias", ex);
			throw new DAOException("verificaSustancias");
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
				warn("verificaSustancias.close", ex2);
			}
			return vcTOXCualtSust;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAllAnalisisPresuntivo(int iAnio, int iCveLaboratorio,
			int iCveLoteCualita, int iCveExamCualita) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCualtSust = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCualtSust vTOXCualtSust;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT SU.CDSCSUSTANCIA, CO.DCORTENEG, CO.DCORTE, CO.DCORTEPOST, RE.iCodigo, "
					+ "RE.DCORTENEG, RE.DCORTEPOST, "
					+ "CC.DCORTENEG, CC.DCORTE, CC.DCORTEPOST "
					+ "FROM TOXCUALTSUST CS, TOXSUSTANCIA SU, TOXCORTEXSUST CO, TOXCALIBCUALITA CC, "
					+ "TOXREACTIVO RE "
					+ "WHERE "
					+ "CS.ICVESUSTANCIA=SU.ICVESUSTANCIA "
					+ "AND CS.ICVESUSTANCIA=CO.ICVESUSTANCIA AND CS.ICVECORTE=CO.ICVECORTE "
					+ "AND CS.IANIO=CC.IANIO AND CS.ICVELOTECUALITA=CC.ICVELOTECUALITA "
					+ "AND CS.ICVEEXAMCUALITA=CC.ICVEEXAMCUALITA AND CS.ICVELABORATORIO=CC.ICVELABORATORIO "
					+ "AND CS.ICVESUSTANCIA=CC.ICVESUSTANCIA "
					+ "AND CC.ICVELABORATORIO=RE.ICVELABORATORIO AND CC.ICVEREACTIVO=RE.ICVEREACTIVO "
					+ "AND CS.IANIO=? AND CS.ICVELABORATORIO=? AND CS.ICVELOTECUALITA=? "
					+ "AND CS.ICVEEXAMCUALITA=? "
					+ "  and CC.iCvecAlibCualita =( select max(iCvecAlibCualita) "
					+ "                               from TOXCALIBCUALITA TCC "
					+ "                                      where TCC.iAnio = CS.IANIO "
					+ "                                        and TCC.iCveLaboratorio = CS.ICVELABORATORIO "
					+ "                                        and TCC.ICVELOTECUALITA = CS.ICVELOTECUALITA "
					+ "                                        and TCC.ICVEEXAMCUALITA = CS.ICVEEXAMCUALITA "
					+ "                                        and TCC.lAutorizado = 1) ";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iCveLaboratorio);
			pstmt.setInt(3, iCveLoteCualita);
			pstmt.setInt(4, iCveExamCualita);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCualtSust = new TVTOXCualtSust();
				vTOXCualtSust.setcDscSustancia(rset.getString(1));
				vTOXCualtSust.setDCorteNeg_co(rset.getFloat(2));
				vTOXCualtSust.setDCorte_co(rset.getFloat(3));
				vTOXCualtSust.setDCortePost_co(rset.getFloat(4));
				vTOXCualtSust.setCDscReactivo(rset.getString(5));
				vTOXCualtSust.setDCorteNeg_r(rset.getFloat(6));
				vTOXCualtSust.setDCortePost_r(rset.getFloat(7));
				// vTOXCualtSust.setCDscCtrolCalibra(rset.getString(8));
				vTOXCualtSust.setDCorteNeg_ca(rset.getFloat(8));
				vTOXCualtSust.setDCorte_ca(rset.getFloat(9));
				vTOXCualtSust.setDCortePost_ca(rset.getFloat(10));
				vcTOXCualtSust.addElement(vTOXCualtSust);
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
			return vcTOXCualtSust;
		}
	}

}
