package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de CtrolCalibra DAO
 * </p>
 * <p>
 * Description: DAO de TOXCtrolCalibra
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

public class TDCtrolCalibra extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDCtrolCalibra() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(Object Obj, String cWhere, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCtrolCalibra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			String cSQLJoin = "";
			TVCtrolCalibra vCtrolCalibra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQLJoin = "Select "
					+ "iCveLaboratorio,"
					+ "iCveCtrolCalibra,"
					+ "iAnio,"
					+ "cLote,"
					+ "cDscCtrolCalibra,"
					+ "cDscBreve,"
					+ "iCveSustancia,"
					+ "dVolumen,"
					+ "dConcentracion,"
					+ "iCveEmpleoCalib,"
					+ "lCuantCual,"
					+ "iViales,"
					+ "dtPreparacion,"
					+ "dtCaducidad,"
					+ "dtAutoriza,"
					+ "iCveUsuAutoriza,"
					+ "lAgotado,"
					+ "dtAgotado,"
					+ "lBaja,"
					+ "dtBaja,"
					+ "iAnioBaja,"
					+ "iCveBaja,"
					+ "iCveMarcaSust,"
					+ "cObservacion,"
					+ "cDscLaboratorio,"
					+ "cDscSustancia,"
					+ "cDscEmpleoCalib,"
					+ "cDscMarcaSust"
					+ "from db2admin.TOXCtrolCalibra  "
					+ "left join TOXLaboratorio on ( iCveLaboratorio = iCveLaboratorio ) "
					+ "left join TOXEmpleoCalib on ( iCveEmpleoCalib = iCveEmpleoCalib ) "
					+ "left join TOXMarcaSust on ( iCveMarcaSust = iCveMarcaSust ) "
					+ "left join TOXSustancia on ( iCveSustancia = iCveSustancia ) "
					+ cWhere + cOrden;

			cSQL = "select " + "iCveLaboratorio," + "iCveCtrolCalibra,"
					+ "iAnio," + "cLote," + " cDscCtrolCalibra," + "cDscBreve,"
					+ "iCveSustancia," + "dVolumen," + "dConcentracion,"
					+ "iCveEmpleoCalib," + "lCuantCual," + "iViales,"
					+ "dtPreparacion," + "dtCaducidad," + "dtAutoriza,"
					+ "iCveUsuAutoriza," + "lAgotado," + "dtAgotado,"
					+ "lBaja," + "dtBaja," + "iAnioBaja," + "iCveBaja,"
					+ "iCveMarcaSust," + "cObservacion"
					+ " from TOXCtrolCalibra " + cWhere + cOrden;

			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCtrolCalibra = new TVCtrolCalibra();
				vCtrolCalibra.setICveLaboratorio(rset.getInt(1));
				vCtrolCalibra.setICveCtrolCalibra(rset.getInt(2));
				vCtrolCalibra.setIAnio(rset.getInt(3));
				vCtrolCalibra.setCLote(rset.getString(4));
				vCtrolCalibra.setCDscBreve(rset.getString(6));
				vCtrolCalibra.setICveSustancia(rset.getInt(7));
				vCtrolCalibra.setDVolumen(rset.getFloat(8));
				vCtrolCalibra.setDConcentracion(rset.getFloat(9));
				vCtrolCalibra.setICveEmpleoCalib(rset.getInt(10));
				vCtrolCalibra.setLCuantCual(rset.getInt(11));
				vCtrolCalibra.setIViales(rset.getInt(12));
				vCtrolCalibra.setDtPreparacion(rset.getDate(13));
				vCtrolCalibra.setDtCaducidad(rset.getDate(14));
				vCtrolCalibra.setDtAutoriza(rset.getDate(15));
				vCtrolCalibra.setICveUsuAutoriza(rset.getInt(16));
				vCtrolCalibra.setLAgotado(rset.getInt(17));
				vCtrolCalibra.setDtAgotado(rset.getDate(18));
				vCtrolCalibra.setLBaja(rset.getInt(19));
				vCtrolCalibra.setDtBaja(rset.getDate(20));
				vCtrolCalibra.setIAnioBaja(rset.getInt(21));
				vCtrolCalibra.setICveBaja(rset.getInt(22));
				vCtrolCalibra.setICveMarcaSust(rset.getInt(23));
				vCtrolCalibra.setCObservacion(rset.getString(24));
				vcCtrolCalibra.addElement(vCtrolCalibra);
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
			return vcCtrolCalibra;
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
		ResultSet rset = null;
		int iCve = 0;

		String cClave = "";
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVCtrolCalibra vCtrolCalibra = (TVCtrolCalibra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "Select max(iCveCtrolCalibra)+1 from db2admin.TOXCtrolCalibra ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				iCve = rset.getInt(1);
			}

			vCtrolCalibra.setICveCtrolCalibra(iCve);

			cSQL = "insert into TOXCtrolCalibra("
					+ "iCveLaboratorio,"
					+ "iCveCtrolCalibra,"
					+ "iAnio,"
					+ "cLote,"
					+ " cDscCtrolCalibra,"
					+ "cDscBreve,"
					+ "iCveSustancia,"
					+ "dVolumen,"
					+ "dConcentracion,"
					+ "iCveEmpleoCalib,"
					+ "lCuantCual,"
					+ "iViales,"
					+ "dtPreparacion,"
					+ "dtCaducidad,"
					+ "dtAutoriza,"
					+ "iCveUsuAutoriza,"
					+ "lAgotado,"
					+ "dtAgotado,"
					+ "lBaja,"
					+ "dtBaja,"
					+ "iAnioBaja,"
					+ "iCveBaja,"
					+ "iCveMarcaSust,"
					+ "cObservacion"
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vCtrolCalibra.getICveLaboratorio());
			pstmt.setInt(2, vCtrolCalibra.getICveCtrolCalibra());
			pstmt.setInt(3, vCtrolCalibra.getIAnio());
			pstmt.setString(4, vCtrolCalibra.getCLote());
			pstmt.setString(6, vCtrolCalibra.getCDscBreve());
			pstmt.setInt(7, vCtrolCalibra.getICveSustancia());
			pstmt.setFloat(8, vCtrolCalibra.getDVolumen());
			pstmt.setFloat(9, vCtrolCalibra.getDConcentracion());
			pstmt.setInt(10, vCtrolCalibra.getICveEmpleoCalib());
			pstmt.setInt(11, vCtrolCalibra.getLCuantCual());
			pstmt.setInt(12, vCtrolCalibra.getIViales());
			pstmt.setDate(13, vCtrolCalibra.getDtPreparacion());
			pstmt.setDate(14, vCtrolCalibra.getDtCaducidad());
			pstmt.setDate(15, vCtrolCalibra.getDtAutoriza());
			pstmt.setInt(16, vCtrolCalibra.getICveUsuAutoriza());
			pstmt.setInt(17, vCtrolCalibra.getLAgotado());
			pstmt.setDate(18, vCtrolCalibra.getDtAgotado());
			pstmt.setInt(19, vCtrolCalibra.getLBaja());
			pstmt.setDate(20, vCtrolCalibra.getDtBaja());
			pstmt.setInt(21, vCtrolCalibra.getIAnioBaja());
			pstmt.setInt(22, vCtrolCalibra.getICveBaja());
			pstmt.setInt(23, vCtrolCalibra.getICveMarcaSust());
			pstmt.setString(24, vCtrolCalibra.getCObservacion());
			pstmt.executeUpdate();
			cClave = "" + vCtrolCalibra.getICveLaboratorio();
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
			TVCtrolCalibra vCtrolCalibra = (TVCtrolCalibra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update TOXCtrolCalibra" + " set iAnio= ?, " + "cLote= ?, "
					+ " cDscCtrolCalibra= ?, " + "cDscBreve= ?, "
					+ "iCveSustancia= ?, " + "dVolumen= ?, "
					+ "dConcentracion= ?, " + "iCveEmpleoCalib= ?, "
					+ "lCuantCual= ?, " + "iViales= ?, " + "dtPreparacion= ?, "
					+ "dtCaducidad= ?, " + "dtAutoriza= ?, "
					+ "iCveUsuAutoriza= ?, " + "lAgotado= ?, "
					+ "dtAgotado= ?, " + "lBaja= ?, " + "dtBaja= ?, "
					+ "iAnioBaja= ?, " + "iCveBaja= ?, " + "iCveMarcaSust= ?, "
					+ "cObservacion= ? " + "where iCveLaboratorio = ? "
					+ " and iCveCtrolCalibra = ?";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCtrolCalibra.getIAnio());
			pstmt.setString(2, vCtrolCalibra.getCLote());
			pstmt.setString(4, vCtrolCalibra.getCDscBreve());
			pstmt.setInt(5, vCtrolCalibra.getICveSustancia());
			pstmt.setFloat(6, vCtrolCalibra.getDVolumen());
			pstmt.setFloat(7, vCtrolCalibra.getDConcentracion());
			pstmt.setInt(8, vCtrolCalibra.getICveEmpleoCalib());
			pstmt.setInt(9, vCtrolCalibra.getLCuantCual());
			pstmt.setInt(10, vCtrolCalibra.getIViales());
			pstmt.setDate(11, vCtrolCalibra.getDtPreparacion());
			pstmt.setDate(12, vCtrolCalibra.getDtCaducidad());
			pstmt.setDate(13, vCtrolCalibra.getDtAutoriza());
			pstmt.setInt(14, vCtrolCalibra.getICveUsuAutoriza());
			pstmt.setInt(15, vCtrolCalibra.getLAgotado());
			pstmt.setDate(16, vCtrolCalibra.getDtAgotado());
			pstmt.setInt(17, vCtrolCalibra.getLBaja());
			pstmt.setDate(18, vCtrolCalibra.getDtBaja());
			pstmt.setInt(19, vCtrolCalibra.getIAnioBaja());
			pstmt.setInt(20, vCtrolCalibra.getICveBaja());
			pstmt.setInt(21, vCtrolCalibra.getICveMarcaSust());
			pstmt.setString(22, vCtrolCalibra.getCObservacion());
			pstmt.setInt(23, vCtrolCalibra.getICveLaboratorio());
			pstmt.setInt(24, vCtrolCalibra.getICveCtrolCalibra());
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
			TVCtrolCalibra vCtrolCalibra = (TVCtrolCalibra) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from TOXCtrolCalibra" + " where iCveLaboratorio = ?"
					+ " and iCveCtrolCalibra = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCtrolCalibra.getICveLaboratorio());
			pstmt.setInt(2, vCtrolCalibra.getICveCtrolCalibra());
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
				warn("delete.closeCtrolCalibra", ex2);
			}
			return cClave;
		}
	}
}
