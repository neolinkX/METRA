package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXCorteXSust DAO
 * </p>
 * <p>
 * Description: Lotes Cuantitativos
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrej�n Adame.
 * @version 1.0
 */

public class TDTOXCorteXSust extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXCorteXSust() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector FindByAll(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCorteXSust = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCorteXSust vTOXCorteXSust;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL =

			" select "
					+ " toxcortexsust.iCveSustancia,  "
					+ " toxcortexsust.iCveCorte,   "
					+ " toxcortexsust.lActivo,    "
					+ " toxcortexsust.dtInicio,  "
					+ " toxcortexsust.dtFin,  "
					+ " toxcortexsust.iCveUsuAutoriza,  "
					+ " toxcortexsust.dCorte,  "
					+ " toxcortexsust.dCorteNeg,  "
					+ " toxcortexsust.dCortePost,  "
					+ " toxcortexsust.dMargenError, "
					+ " toxsustancia.cDscSustancia, "
					+ " (segusuario.cNombre || ' ' || segusuario.cappaterno || ' ' || segusuario.capmaterno) as cDscUsuAutoriza, "
					+ " toxcortexsust.lCuantCual, "
					+ " toxcortexsust.cControles, "
					+ " toxcortexsust.dMargConcCal, "
					+ " toxcortexsust.dMargTmpRet,  "
					+ " toxcortexsust.dMargRelacion "
					+ " from TOXCorteXSust "
					+ " left join toxsustancia on toxsustancia.icvesustancia = toxcortexsust.icvesustancia "
					+ " left join segusuario on segusuario.icveusuario = toxcortexsust.iCveUsuAutoriza "
					+ cvFiltro + " ";

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCorteXSust = new TVTOXCorteXSust();
				vTOXCorteXSust.setiCveSustancia(new Integer(rset.getInt(1)));
				vTOXCorteXSust.setiCveCorte(new Integer(rset.getInt(2)));
				vTOXCorteXSust.setlActivo(new Integer(rset.getInt(3)));
				vTOXCorteXSust.setdtInicio(rset.getDate(4));
				vTOXCorteXSust.setdtFin(rset.getDate(5));
				vTOXCorteXSust.setiCveUsuAutoriza(new Integer(rset.getInt(6)));
				vTOXCorteXSust.setdCorte(new Double(rset.getDouble(7)));
				vTOXCorteXSust.setdCorteNeg(new Double(rset.getDouble(8)));
				vTOXCorteXSust.setdCortePost(new Double(rset.getDouble(9)));
				vTOXCorteXSust.setdMargenError(new Double(rset.getDouble(10)));
				vTOXCorteXSust.setCDscSustancia(rset.getString(11));
				vTOXCorteXSust.setCDscUsuAutoriza(rset.getString(12));
				vTOXCorteXSust.setLCuantCual(rset.getInt(13));
				vTOXCorteXSust.setCControles(rset.getString(14));
				vTOXCorteXSust.setDMargConcCal(rset.getDouble(15));
				vTOXCorteXSust.setDMargTmpRet(rset.getDouble(16));
				vTOXCorteXSust.setDMargRelacion(rset.getDouble(17));
				vcTOXCorteXSust.addElement(vTOXCorteXSust);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return vcTOXCorteXSust;
		}
	}

	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmtMax = null;
		ResultSet rsetMax = null;
		PreparedStatement pstmtUpd = null;
		ResultSet rsetUpd = null;
		String cClave = "";
		int iMax = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXCorteXSust vTOXCorteXSust = (TVTOXCorteXSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "select max(iCveCorte) from TOXCorteXSust where iCveSustancia = ?";
			pstmtMax = conn.prepareStatement(cSQL);
			pstmtMax.setInt(1, vTOXCorteXSust.getiCveSustancia().intValue());
			rsetMax = pstmtMax.executeQuery();
			if (rsetMax.next()) {
				iMax = rsetMax.getInt(1);
			}
			iMax++;
			cClave = iMax + "";
			vTOXCorteXSust.setiCveCorte(new Integer(iMax));
			cSQL = " insert into TOXCorteXSust( " + " iCveSustancia, "
					+ " iCveCorte, " + " lActivo, " + " dtInicio, "
					+ " dtFin, " + " iCveUsuAutoriza, " + " dCorte, "
					+ " dCorteNeg, " + " dCortePost, "
					+ " dMargenError,lCuantCual,cControles,"
					+ " dMargConcCal, " + " dMargTmpRet,  " + " dMargRelacion "
					+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXCorteXSust.getiCveSustancia().intValue());
			pstmt.setInt(2, vTOXCorteXSust.getiCveCorte().intValue());
			pstmt.setInt(3, vTOXCorteXSust.getlActivo().intValue());
			pstmt.setDate(4, vTOXCorteXSust.getdtInicio());
			pstmt.setDate(5, vTOXCorteXSust.getdtFin());
			pstmt.setInt(6, vTOXCorteXSust.getiCveUsuAutoriza().intValue());
			pstmt.setDouble(7, vTOXCorteXSust.getdCorte().doubleValue());
			pstmt.setDouble(8, vTOXCorteXSust.getdCorteNeg().doubleValue());
			pstmt.setDouble(9, vTOXCorteXSust.getdCortePost().doubleValue());
			pstmt.setDouble(10, vTOXCorteXSust.getdMargenError().doubleValue());
			pstmt.setInt(11, vTOXCorteXSust.getLCuantCual());
			pstmt.setString(12, vTOXCorteXSust.getCControles());
			pstmt.setDouble(13, vTOXCorteXSust.getDMargConcCal());
			pstmt.setDouble(14, vTOXCorteXSust.getDMargTmpRet());
			pstmt.setDouble(15, vTOXCorteXSust.getDMargRelacion());
			pstmt.executeUpdate();

			if (vTOXCorteXSust.getlActivo().intValue() == 1
					&& vTOXCorteXSust.getdCortePost().intValue() > 0) {
				cSQL = "update TOXCorteXSust set lActivo = 0 where iCveSustancia = ? and iCveCorte <> ? and dCortePost > 0";
				pstmtUpd = conn.prepareStatement(cSQL);
				pstmtUpd.setInt(1, vTOXCorteXSust.getiCveSustancia().intValue());
				pstmtUpd.setInt(2, vTOXCorteXSust.getiCveCorte().intValue());
				pstmtUpd.executeUpdate();
			}

			if (vTOXCorteXSust.getlActivo().intValue() == 1
					&& vTOXCorteXSust.getdMargenError().intValue() > 0) {
				cSQL = "update TOXCorteXSust set lActivo = 0 where iCveSustancia = ? and iCveCorte <> ? and dMargenError > 0";
				pstmtUpd = conn.prepareStatement(cSQL);
				pstmtUpd.setInt(1, vTOXCorteXSust.getiCveSustancia().intValue());
				pstmtUpd.setInt(2, vTOXCorteXSust.getiCveCorte().intValue());
				pstmtUpd.executeUpdate();
			}

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

				if (pstmtUpd != null) {
					pstmtUpd.close();
				}
				if (rsetUpd != null) {
					rsetUpd.close();
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
		PreparedStatement pstmtUpd = null;
		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVTOXCorteXSust vTOXCorteXSust = (TVTOXCorteXSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXCorteXSust" + " set lActivo= ?, "
					+ "dtInicio= ?, " + "dtFin= ?, " + "iCveUsuAutoriza= ?, "
					+ "dCorte= ?, " + "dCorteNeg= ?, " + "dCortePost= ?, "
					+ "dMargenError= ?, " + "lCuantCual= ?, cControles=?, "
					+ " dMargConcCal = ?, " + " dMargTmpRet  = ?,  "
					+ " dMargRelacion  = ? " + "where iCveSustancia = ? "
					+ " and iCveCorte = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vTOXCorteXSust.getlActivo().intValue());
			pstmt.setDate(2, vTOXCorteXSust.getdtInicio());
			pstmt.setDate(3, vTOXCorteXSust.getdtFin());
			pstmt.setInt(4, vTOXCorteXSust.getiCveUsuAutoriza().intValue());
			pstmt.setDouble(5, vTOXCorteXSust.getdCorte().doubleValue());
			pstmt.setDouble(6, vTOXCorteXSust.getdCorteNeg().doubleValue());
			pstmt.setDouble(7, vTOXCorteXSust.getdCortePost().doubleValue());
			pstmt.setDouble(8, vTOXCorteXSust.getdMargenError().doubleValue());
			pstmt.setInt(9, vTOXCorteXSust.getLCuantCual());
			pstmt.setString(10, vTOXCorteXSust.getCControles());
			pstmt.setDouble(11, vTOXCorteXSust.getDMargConcCal());
			pstmt.setDouble(12, vTOXCorteXSust.getDMargTmpRet());
			pstmt.setDouble(13, vTOXCorteXSust.getDMargRelacion());
			pstmt.setInt(14, vTOXCorteXSust.getiCveSustancia().intValue());
			pstmt.setInt(15, vTOXCorteXSust.getiCveCorte().intValue());
			pstmt.executeUpdate();

			pstmt.close();

			if (vTOXCorteXSust.getlActivo().intValue() == 1
					&& vTOXCorteXSust.getdCortePost().intValue() > 0) {
				cSQL = "update TOXCorteXSust set lActivo = 0 where iCveSustancia = ? and iCveCorte <> ? and dCortePost > 0";
				pstmtUpd = conn.prepareStatement(cSQL);
				pstmtUpd.setInt(1, vTOXCorteXSust.getiCveSustancia().intValue());
				pstmtUpd.setInt(2, vTOXCorteXSust.getiCveCorte().intValue());
				pstmtUpd.executeUpdate();
				pstmtUpd.close();
			}

			if (vTOXCorteXSust.getlActivo().intValue() == 1
					&& vTOXCorteXSust.getdMargenError().intValue() > 0) {
				cSQL = "update TOXCorteXSust set lActivo = 0 where iCveSustancia = ? and iCveCorte <> ? and dMargenError > 0";
				pstmtUpd = conn.prepareStatement(cSQL);
				pstmtUpd.setInt(1, vTOXCorteXSust.getiCveSustancia().intValue());
				pstmtUpd.setInt(2, vTOXCorteXSust.getiCveCorte().intValue());
				pstmtUpd.executeUpdate();
				pstmtUpd.close();
			}

			cClave = "";
			if (conGral == null) {
				conn.commit();
			}
		} catch (Exception ex) {
			try {
				/*
				 * if (pstmt != null) { pstmt.close(); } if (pstmtUpd != null) {
				 * pstmtUpd.close(); } if (rsetUpd != null) { rsetUpd.close(); }
				 */

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
			TVTOXCorteXSust vTOXCorteXSust = (TVTOXCorteXSust) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " update TOXCorteXSust " + " set lActivo = 0      "
					+ " where iCveSustancia = ? " + "   and iCveCorte = ? ";

			pstmt = conn.prepareStatement(cSQL);

			pstmt.setInt(1, vTOXCorteXSust.getiCveSustancia().intValue());
			pstmt.setInt(2, vTOXCorteXSust.getiCveCorte().intValue());
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
				warn("delete.closeTOXCorteXSust", ex2);
			}
			return cClave;
		}
	}

}
