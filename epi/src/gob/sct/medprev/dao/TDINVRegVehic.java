package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de RegVehic DAO
 * </p>
 * <p>
 * Description: DAO de la entidad INVRegVehic
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Jaime Enrique Su�rez Romero
 * @version 1.0
 */

public class TDINVRegVehic extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDINVRegVehic() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcRegVehic = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVINVRegVehic vRegVehic;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select "
					+ "INVRegVehic.iAnio,"
					+ "INVRegVehic.iCveMdoTrans,"
					+ "INVRegVehic.iIDDGPMPT,"
					+ "INVRegVehic.iCveVehiculo,"
					+ "INVRegVehic.cMatricula,"
					+ "INVRegVehic.cPropietario,"
					+ "INVRegVehic.iCveEmpresa,"
					+ "INVRegVehic.iCveServPrestado,"
					+ "INVRegVehic.cOrigen,"
					+ "INVRegVehic.cDestino,"
					+ "INVRegVehic.iPerFedInvolucra,"
					+ "INVRegVehic.iPerEdoInvolucra,"
					+ "INVRegVehic.iPerPartInvolucra,"
					+ "INVRegVehic.iCvePaisOr,"
					+ "INVRegVehic.iCveEdoOr,"
					+ "INVRegVehic.iCvePaisDest,"
					+ "INVRegVehic.iCveEdoDest,"
					+ "CTRServPrestado.cDscServPrestado,"
					+ "grlpais.cnombre, "
					+ "grlentidadfed.cnombre,"
					+ "gpais.cnombre, "
					+ "gedo.cnombre,"
					+ "GRLEmpresas.cDscEmpresa, "
					+ "GRLMdoTrans.cDscMdoTrans "
					+ "from INVRegVehic "
					+ "join grlpais on INVRegVehic.iCvePaisOr = grlpais.iCvePais "
					+ "join grlentidadfed on INVRegVehic.iCvePaisOr = grlentidadfed.iCvePais "
					+ "and INVRegVehic.iCveEdoOr = grlentidadfed.iCveEntidadFed "
					+ "join grlpais gpais on INVRegVehic.iCvePaisDest = gpais.iCvePais "
					+ "join grlentidadfed gedo on INVRegVehic.iCvePaisDest = gedo.iCvePais "
					+ "and INVRegVehic.iCveEdoDest = gedo.iCveEntidadFed "
					+ "join CTRServPrestado on INVRegVehic.iCveServPrestado = CTRServPrestado.iCveServPrestado "
					+ "join GRLEmpresas on INVRegVehic.iCveEmpresa = GRLEmpresas.iCveEmpresa "
					+ "join GRLMdoTrans on GRLMdoTrans.iCveMdoTrans = INVRegVehic.iCveMdoTrans "
					+ cWhere;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vRegVehic = new TVINVRegVehic();
				vRegVehic.setIAnio(rset.getInt(1));
				vRegVehic.setICveMdoTrans(rset.getInt(2));
				vRegVehic.setIIDDGPMPT(rset.getInt(3));
				vRegVehic.setICveVehiculo(rset.getInt(4));
				vRegVehic.setCMatricula(rset.getString(5));
				vRegVehic.setCPropietario(rset.getString(6));
				vRegVehic.setICveEmpresa(rset.getInt(7));
				vRegVehic.setICveServPrestado(rset.getInt(8));
				vRegVehic.setCOrigen(rset.getString(9));
				vRegVehic.setCDestino(rset.getString(10));
				vRegVehic.setIPerFedInvolucra(rset.getInt(11));
				vRegVehic.setIPerEdoInvolucra(rset.getInt(12));
				vRegVehic.setIPerPartInvolucra(rset.getInt(13));
				vRegVehic.setICvePaisOr(rset.getInt(14));
				vRegVehic.setICveEdoOr(rset.getInt(15));
				vRegVehic.setICvePaisDest(rset.getInt(16));
				vRegVehic.setICveEdoDest(rset.getInt(17));
				vRegVehic.setCDscServPrestado(rset.getString(18));
				vRegVehic.setCDscPaisOr(rset.getString(19));
				vRegVehic.setCDscEdoOr(rset.getString(20));
				vRegVehic.setCDscPaisDest(rset.getString(21));
				vRegVehic.setCDscEdoDest(rset.getString(22));
				vRegVehic.setCDscEmpresa(rset.getString(23));
				vRegVehic.setCDscMdoTrans(rset.getString(24));
				vcRegVehic.addElement(vRegVehic);
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
			return vcRegVehic;
		}
	}

	/**
	 * Metodo Insert
	 */
	public Object insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVINVRegVehic vRegVehic = (TVINVRegVehic) obj;
		boolean lError = false;
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

			cSQL = "select iVehFedInvolucra from INVRegistro "
					+ "where iAnio = " + vRegVehic.getIAnio()
					+ " and iCveMdoTrans = " + vRegVehic.getICveMdoTrans()
					+ " and iIDDGPMPT = " + vRegVehic.getIIDDGPMPT();

			// debug(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			int iTotalVeh = 0;
			while (rset.next()) {
				iTotalVeh = rset.getInt(1);
			}
			if (rset != null) {
				rset.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			cSQL = "select max(iCveVehiculo) from INVRegVehic "
					+ "where iAnio = " + vRegVehic.getIAnio()
					+ " and iCveMdoTrans = " + vRegVehic.getICveMdoTrans()
					+ " and iIDDGPMPT = " + vRegVehic.getIIDDGPMPT();

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vRegVehic.setICveVehiculo(rset.getInt(1) + 1);
			}

			if (rset != null) {
				rset.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}

			if (vRegVehic.getICveVehiculo() <= iTotalVeh) {
				cSQL = "insert into INVRegVehic(" + "iAnio," + "iCveMdoTrans,"
						+ "iIDDGPMPT," + "iCveVehiculo," + "cMatricula,"
						+ "cPropietario," + "iCveEmpresa,"
						+ "iCveServPrestado," + "cOrigen," + "cDestino,"
						+ "iPerFedInvolucra," + "iPerEdoInvolucra,"
						+ "iPerPartInvolucra," + "iCvePaisOr," + "iCveEdoOr,"
						+ "iCvePaisDest," + "iCveEdoDest"
						+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(cSQL);
				pstmt.setInt(1, vRegVehic.getIAnio());
				pstmt.setInt(2, vRegVehic.getICveMdoTrans());
				pstmt.setInt(3, vRegVehic.getIIDDGPMPT());
				pstmt.setInt(4, vRegVehic.getICveVehiculo());
				pstmt.setString(5, vRegVehic.getCMatricula());
				pstmt.setString(6, vRegVehic.getCPropietario());
				pstmt.setInt(7, vRegVehic.getICveEmpresa());
				pstmt.setInt(8, vRegVehic.getICveServPrestado());
				pstmt.setString(9, vRegVehic.getCOrigen());
				pstmt.setString(10, vRegVehic.getCDestino());
				pstmt.setInt(11, vRegVehic.getIPerFedInvolucra());
				pstmt.setInt(12, vRegVehic.getIPerEdoInvolucra());
				pstmt.setInt(13, vRegVehic.getIPerPartInvolucra());
				pstmt.setInt(14, vRegVehic.getICvePaisOr());
				pstmt.setInt(15, vRegVehic.getICveEdoOr());
				pstmt.setInt(16, vRegVehic.getICvePaisDest());
				pstmt.setInt(17, vRegVehic.getICveEdoDest());
				pstmt.executeUpdate();
				if (conGral == null) {
					conn.commit();
				}
			} else {
				vRegVehic.setICveVehiculo(-1);
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
			lError = true;
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
			if (lError) {
				throw new DAOException("");
			}
			return vRegVehic;
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
		boolean lError = false;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVINVRegVehic vRegVehic = (TVINVRegVehic) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update INVRegVehic" + " set cMatricula= ?, "
					+ "cPropietario= ?, " + "iCveEmpresa= ?, "
					+ "iCveServPrestado= ?, " + "cOrigen= ?, "
					+ "cDestino= ?, " + "iPerFedInvolucra= ?, "
					+ "iPerEdoInvolucra= ?, " + "iPerPartInvolucra= ?, "
					+ "iCvePaisOr= ?, " + "iCveEdoOr= ?, "
					+ "iCvePaisDest= ?, " + "iCveEdoDest= ? "
					+ "where iAnio = ? " + "and iCveMdoTrans = ?"
					+ "and iIDDGPMPT = ?" + " and iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setString(1, vRegVehic.getCMatricula());
			pstmt.setString(2, vRegVehic.getCPropietario());
			pstmt.setInt(3, vRegVehic.getICveEmpresa());
			pstmt.setInt(4, vRegVehic.getICveServPrestado());
			pstmt.setString(5, vRegVehic.getCOrigen());
			pstmt.setString(6, vRegVehic.getCDestino());
			pstmt.setInt(7, vRegVehic.getIPerFedInvolucra());
			pstmt.setInt(8, vRegVehic.getIPerEdoInvolucra());
			pstmt.setInt(9, vRegVehic.getIPerPartInvolucra());
			pstmt.setInt(10, vRegVehic.getICvePaisOr());
			pstmt.setInt(11, vRegVehic.getICveEdoOr());
			pstmt.setInt(12, vRegVehic.getICvePaisDest());
			pstmt.setInt(13, vRegVehic.getICveEdoDest());
			pstmt.setInt(14, vRegVehic.getIAnio());
			pstmt.setInt(15, vRegVehic.getICveMdoTrans());
			pstmt.setInt(16, vRegVehic.getIIDDGPMPT());
			pstmt.setInt(17, vRegVehic.getICveVehiculo());
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
			lError = true;
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
			if (lError) {
				throw new DAOException("");
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
			TVINVRegVehic vRegVehic = (TVINVRegVehic) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from INVRegVehic" + " where iAnio = ?"
					+ " and iCveMdoTrans = ?" + " and iIDDGPMPT = ?"
					+ " and iCveVehiculo = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vRegVehic.getIAnio());
			pstmt.setInt(2, vRegVehic.getICveMdoTrans());
			pstmt.setInt(3, vRegVehic.getIIDDGPMPT());
			pstmt.setInt(4, vRegVehic.getICveVehiculo());
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
				warn("delete.closeRegVehic", ex2);
			}
			return cClave;
		}
	}
}