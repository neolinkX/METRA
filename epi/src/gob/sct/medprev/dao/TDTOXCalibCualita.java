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
 * Title: Data Acces Object de TOXCalibCualita DAO
 * </p>
 * <p>
 * Description: DAO para el manejo de los datos deCalib Cualita
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

public class TDTOXCalibCualita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXCalibCualita() {
	}

	/**
	 * 
	 * @param conGral
	 * @param obj
	 * @return
	 * @throws DAOException
	 */

	public Vector FindByAllc(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCalib = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCtrolCalibra vTOXCalib;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select iCveCtrolCalibra, cDscCtrolCalibra "
					+ " From ToxCtrolCalibra " + cWhere
					+ " order by iCveCtrolCalibra";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCalib = new TVCtrolCalibra();
				vTOXCalib.setICveCtrolCalibra(rset.getInt(1));
				vTOXCalib.setCDscBreve(rset.getString(2));
				vcTOXCalib.addElement(vTOXCalib);
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
			return vcTOXCalib;
		}
	}

	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCalibCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCalibCualita vTOXCalibCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select a.iAnio,a.iCveLaboratorio,a.iCveExamCualita,"
					+ "a.iCveLoteCualita,a.iCveSustancia,a.iCveCalibCualita,"
					+ "a.iCveReactivo,a.iCveCtrolCalibra,a.dtCalibracion,"
					+ "a.iCveUsuRealiza,a.dCorte,a.dCorteNeg,a.dCortePost,"
					+ "a.iCveAcCorrectiva,a.cObservacion,a.lAutorizado,"
					+ "a.iCveUsuAutoriza, cDscReactivo ,cDscCtrolCalibra,"
					+ "cDscAcCorrectiva, cDscSustancia, a.lActualizado, ToxReactivo.iCodigo, "
					+ "ToxCtrolCalibra.cLote "
					+ "From TOXCalibCualita a "
					+ "left join  ToxReactivo on ToxReactivo.iCveReactivo = a.iCveReactivo "
					+ "left join  ToxCtrolCalibra on ToxCtrolCalibra.iCveCtrolCalibra = a.iCveCtrolCalibra "
					+ "left join  ToxAcCorrectiva on ToxAcCorrectiva.iCveAcCorrectiva = a.iCveAcCorrectiva "
					+ "left join  ToxSustancia    on ToxSustancia.iCveSustancia = a.iCveSustancia "
					+ cWhere + " order by a.iCveSustancia";

			// System.out.println("QUERY==>> " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCalibCualita = new TVTOXCalibCualita();
				vTOXCalibCualita.setIAnio(rset.getInt(1));
				vTOXCalibCualita.setICveLaboratorio(rset.getInt(2));
				vTOXCalibCualita.setICveExamCualita(rset.getInt(3));
				vTOXCalibCualita.setICveLoteCualita(rset.getInt(4));
				vTOXCalibCualita.setICveSustancia(rset.getInt(5));
				vTOXCalibCualita.setICveCalibCualita(rset.getInt(6));
				vTOXCalibCualita.setICveReactivo(rset.getInt(7));
				vTOXCalibCualita.setICveCtrolCalibra(rset.getInt(8));
				vTOXCalibCualita.setDtCalibracion(rset.getDate(9));
				vTOXCalibCualita.setICveUsuRealiza(rset.getInt(10));
				vTOXCalibCualita.setDCorte(rset.getFloat(11));
				vTOXCalibCualita.setDCorteNeg(rset.getFloat(12));
				vTOXCalibCualita.setDCortePost(rset.getFloat(13));
				vTOXCalibCualita.setICveAcCorrectiva(rset.getInt(14));
				vTOXCalibCualita.setCObservacion(rset.getString(15));
				vTOXCalibCualita.setLAutorizado(rset.getInt(16));
				vTOXCalibCualita.setICveUsuAutoriza(rset.getInt(17));
				vTOXCalibCualita.setCDscReactivo(rset.getString(18));
				vTOXCalibCualita.setCDscCtrolCalibra(rset.getString(19));
				vTOXCalibCualita.setCDscAcCorrectiva(rset.getString(20));
				vTOXCalibCualita.setCDscSustancia(rset.getString(21));
				vTOXCalibCualita.setLActualizado(rset.getInt(22));
				vTOXCalibCualita.setICodigo(rset.getString(23));
				vTOXCalibCualita.setCLote(rset.getString(24));
				vcTOXCalibCualita.addElement(vTOXCalibCualita);
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
			return vcTOXCalibCualita;
		}
	}

	public Vector FindByAllPresuntivo(int iAnio, int iCveLaboratorio,
			int iCveLoteCualita, int iCveExamCualita) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCalibCualita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCalibCualita vTOXCalibCualita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT S.CDSCSUSTANCIA, CS.DCORTE, CS.DCORTENEG, CS.DCORTEPOST,"
					+ " R.CDSCREACTIVO, R.DCORTENEG, R.DCORTEPOST,"
					+ " CC.CDSCCTROLCALIBRA, CA.DCORTENEG, CA.DCORTE, CA.DCORTEPOST"
					+ " FROM TOXCALIBCUALITA CA, TOXCORTE_CUAL CO, TOXCORTEXSUST CS, TOXSUSTANCIA S,"
					+ " TOXREACTIVO R, TOXCTROLCALIBRA CC"
					+ " WHERE CO.IANIO=CA.IANIO AND CO.ICVELABORATORIO=CA.ICVELABORATORIO"
					+ " AND CO.ICVELOTECUALITA=CA.ICVELOTECUALITA"
					+ " AND CO.ICVESUSTANCIA=CA.ICVESUSTANCIA"
					+ " AND CO.ICVEEXAMCUALITA=CA.ICVEEXAMCUALITA AND CS.LACTIVO=0"
					+ " AND CO.ICVECORTE=CS.ICVECORTE"
					+ " AND S.ICVESUSTANCIA=CS.ICVESUSTANCIA"
					+ " AND CA.ICVESUSTANCIA=S.ICVESUSTANCIA"
					+ " AND CA.ICVELABORATORIO=R.ICVELABORATORIO AND CA.ICVEREACTIVO=R.ICVEREACTIVO"
					+ " AND CA.ICVELABORATORIO=CC.ICVELABORATORIO AND CA.ICVECTROLCALIBRA=CC.ICVECTROLCALIBRA"
					+ " AND CA.IANIO=? AND CA.ICVELABORATORIO=? AND CA.ICVELOTECUALITA=? AND CA.ICVEEXAMCUALITA=?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iCveLaboratorio);
			pstmt.setInt(3, iCveLoteCualita);
			pstmt.setInt(4, iCveExamCualita);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCalibCualita = new TVTOXCalibCualita();

				vTOXCalibCualita.setCDscSustancia(rset.getString(1));
				vTOXCalibCualita.setDCorte(rset.getFloat(2));
				vTOXCalibCualita.setDCorteNeg(rset.getFloat(3));
				vTOXCalibCualita.setDCortePost(rset.getFloat(4));

				vTOXCalibCualita.setCDscReactivo(rset.getString(5));
				vTOXCalibCualita.setDCorteNeg_r(rset.getFloat(6));
				vTOXCalibCualita.setDCortePost_r(rset.getFloat(7));

				vTOXCalibCualita.setCDscCtrolCalibra(rset.getString(8));
				vTOXCalibCualita.setDCorteNeg_ca(rset.getFloat(9));
				vTOXCalibCualita.setDCorte_ca(rset.getFloat(10));
				vTOXCalibCualita.setDCortePost_ca(rset.getFloat(11));
				vcTOXCalibCualita.addElement(vTOXCalibCualita);
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
			return vcTOXCalibCualita;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, String cAnio, String cLab,
			String cLote, String cExam, String cSust, String cReac,
			String cCali, float cRNega, float cRCort, float cRPost, int result,
			String uId, String cAcco, String cObse) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

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
			cSQL = "insert into TOXCalibCualita(" + "iAnio,"
					+ "iCveLaboratorio," + "iCveExamCualita,"
					+ "iCveLoteCualita," + "iCveSustancia,"
					+ "iCveCalibCualita," + "iCveReactivo,"
					+ "iCveCtrolCalibra," + "dtCalibracion,"
					+ "iCveUsuRealiza," + "dCorte," + "dCorteNeg,"
					+ "dCortePost," + "iCveAcCorrectiva," + "cObservacion,"
					+ "lAutorizado," + "iCveUsuAutoriza"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);

			String cSQLMax = "select max(iCveCalibcualita)+1 from TOXCalibCualita "
					+ "Where iAnio = "
					+ cAnio
					+ " And iCveLaboratorio = "
					+ cLab
					+ " And iCveExamCualita = "
					+ cExam
					+ " And iCveLoteCualita = "
					+ cLote
					+ " And iCveSustancia = " + cSust;
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();

			// ///////////////////////////////////

			pstmt.setString(1, cAnio);
			pstmt.setString(2, cLab);
			pstmt.setString(3, cExam);
			pstmt.setString(4, cLote);
			pstmt.setString(5, cSust);
			pstmt.setInt(6, iMax);
			pstmt.setString(7, cReac);
			pstmt.setString(8, cCali);
			pstmt.setDate(9, defaultFecha);
			pstmt.setString(10, uId);
			pstmt.setFloat(11, cRCort);
			pstmt.setFloat(12, cRNega);
			pstmt.setFloat(13, cRPost);
			pstmt.setString(14, cAcco);
			pstmt.setString(15, cObse);
			pstmt.setInt(16, result);
			pstmt.setString(17, uId);
			pstmt.executeUpdate();
			cClave = cAnio;
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

	public Object insertTOXCualtSust(Connection conGral, String cAnio,
			String cLab, String cLote, String cExam, String cSust)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		java.util.Date today = new java.util.Date();
		java.sql.Date defFecha = new java.sql.Date(today.getTime());
		java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

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
			cSQL = "insert into TOXCualtSust(" + "iAnio," + "iCveLaboratorio,"
					+ "iCveExamCualita," + "iCveLoteCualita,"
					+ "iCveSustancia," + "iCveCorte" + ")values(?,?,?,?,?,?)";
			int iCveCorte = 0;
			pstmt = conn.prepareStatement(cSQL);
			String cSQLMax = "select CS.iCveCorte from TOXCortexSust CS "
					+ "where CS.iCveSustancia =  " + cSust
					+ "  and CS.lActivo       = 1 "
					+ "  and CS.lCuantCual    = 0 ";
			pstmtMax = conn.prepareStatement(cSQLMax);
			rsetMax = pstmtMax.executeQuery();
			while (rsetMax.next()) {
				iCveCorte = rsetMax.getInt(1);
			}
			rsetMax.close();
			pstmtMax.close();
			pstmt.setString(1, cAnio);
			pstmt.setString(2, cLab);
			pstmt.setString(3, cExam);
			pstmt.setString(4, cLote);
			pstmt.setString(5, cSust);
			pstmt.setInt(6, iCveCorte);
			pstmt.executeUpdate();
			cClave = cAnio;
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
	public Object update(Connection conGral, String cAnio, String cLab,
			String cExam, String cLote, String cSust, int iCveCalibCualita,
			int iActivo) throws DAOException {
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

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXCalibCualita" + " set lActualizado = ?"
					+ " where iAnio = ?" + " And   iCveLaboratorio =?"
					+ " And   iCveExamCualita =?" + " And   iCveLoteCualita =?"
					+ " And   iCveSustancia   =?" + " And   iCveCalibCualita=?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iActivo);
			pstmt.setString(2, cAnio);
			pstmt.setString(3, cLab);
			pstmt.setString(4, cExam);
			pstmt.setString(5, cLote);
			pstmt.setString(6, cSust);
			pstmt.setInt(7, iCveCalibCualita);
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
	 * Metodo Find CteReac Este metodo se utiliza unicamente para obtener los
	 * datos para realizar el calcculo de calibraciones
	 */
	public Vector findCteReac(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXReactivo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXReactivo vTOXReactivo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select " + "dCorteNeg," + "dCortePost"
					+ " from TOXReactivo " + cWhere;
			// System.out.println("SQL ==>> "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXReactivo = new TVTOXReactivo();
				vTOXReactivo.setDCorteNeg(rset.getFloat(1));
				vTOXReactivo.setDCortePost(rset.getFloat(2));
				vcTOXReactivo.addElement(vTOXReactivo);
			}
		} catch (Exception ex) {
			warn("FindCteReac", ex);
			throw new DAOException("FindCteReac");
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
				warn("FindCteReac.close", ex2);
			}
			return vcTOXReactivo;
		}
	}

	public TVTOXCalibCualita find(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVTOXCalibCualita vTOXCalibCualita = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select iAnio,iCveLaboratorio,iCveExamCualita,iCveLoteCualita,"
					+ "iCveSustancia,iCveCalibCualita,iCveReactivo,iCveCtrolCalibra,"
					+ "dtCalibracion,iCveUsuRealiza,dCorte,dCorteNeg,dCortePost,"
					+ "iCveAcCorrectiva,cObservacion,lAutorizado,iCveUsuAutoriza "
					+ "from TOXCalibCualita "
					+ "where iAnio=? and iCveLaboratorio=? and iCveLoteCualita=? and "
					+ "iCveExamCualita=? and iCveSustancia=? and lAutorizado=1";

			pstmt = conn.prepareStatement(cSQL);

			Integer iTmp = (Integer) hmFiltro.get("iAnio");
			pstmt.setInt(1, iTmp != null ? iTmp.intValue() : 0);
			iTmp = (Integer) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, iTmp != null ? iTmp.intValue() : 0);
			iTmp = (Integer) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(3, iTmp != null ? iTmp.intValue() : 0);
			iTmp = (Integer) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(4, iTmp != null ? iTmp.intValue() : 0);
			iTmp = (Integer) hmFiltro.get("iCveSustancia");
			pstmt.setInt(5, iTmp != null ? iTmp.intValue() : 0);

			rset = pstmt.executeQuery();
			if (rset.next()) {
				vTOXCalibCualita = new TVTOXCalibCualita();
				vTOXCalibCualita.setIAnio(rset.getInt(1));
				vTOXCalibCualita.setICveLaboratorio(rset.getInt(2));
				vTOXCalibCualita.setICveExamCualita(rset.getInt(3));
				vTOXCalibCualita.setICveLoteCualita(rset.getInt(4));
				vTOXCalibCualita.setICveSustancia(rset.getInt(5));
				vTOXCalibCualita.setICveCalibCualita(rset.getInt(6));
				vTOXCalibCualita.setICveReactivo(rset.getInt(7));
				vTOXCalibCualita.setICveCtrolCalibra(rset.getInt(8));
				vTOXCalibCualita.setDtCalibracion(rset.getDate(9));
				vTOXCalibCualita.setICveUsuRealiza(rset.getInt(10));
				vTOXCalibCualita.setDCorte(rset.getFloat(11));
				vTOXCalibCualita.setDCorteNeg(rset.getFloat(12));
				vTOXCalibCualita.setDCortePost(rset.getFloat(13));
				vTOXCalibCualita.setICveAcCorrectiva(rset.getInt(14));
				vTOXCalibCualita.setCObservacion(rset.getString(15));
				vTOXCalibCualita.setLAutorizado(rset.getInt(16));
				vTOXCalibCualita.setICveUsuAutoriza(rset.getInt(17));
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
			return vTOXCalibCualita;
		}
	}
}
