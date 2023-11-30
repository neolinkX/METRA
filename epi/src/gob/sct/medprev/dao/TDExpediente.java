/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.dwr.MedPredDwr;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.dwr.vo.GrlExpedientes;

/**
 * 
 * @author ivna snatnainsfonsdaonsdoasndaoisdnaosidsoikn
 */
public class TDExpediente extends DAOBase {

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");// a MEDPREV

	/**
	 * Metodo Find By CUSUARIO
	 */
	public List<GrlExpedientes> findExpediente(String ICVEEXPEDIENTE,
			String CRFC, String CNOMBRE, String CAP, String CAM)
			throws DAOException {
		String WHERE = "";
		if (!ICVEEXPEDIENTE.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE ICVEEXPEDIENTE = " + ICVEEXPEDIENTE;
			} else {
				WHERE += " OR ICVEEXPEDIENTE =" + ICVEEXPEDIENTE;
			}
		}

		// cambios oprqiurw e[uta medepojasohdfeosn
		if (!CRFC.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CRFC) LIKE LCASE('%" + CRFC + "%')";
			} else {
				WHERE += " OR LCASE(CRFC) LIKE LCASE('%" + CRFC + "%')";
			}
		}
		if (!CNOMBRE.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CNOMBRE) LIKE LCASE('%" + CNOMBRE + "%')";
			} else {
				WHERE += " OR LCASE(CNOMBRE) LIKE LCASE('%" + CNOMBRE + "%')";
			}
		}
		if (!CAP.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CAPPATERNO) LIKE LCASE('%" + CAP + "%')";
			} else {
				WHERE += " OR LCASE(CAPPATERNO) LIKE LCASE('%" + CAP + "%')";
			}
		}
		if (!CAM.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CAPMATERNO) LIKE LCASE('%" + CAM + "%')";
			} else {
				WHERE += " OR LCASE(CAPMATERNO) LIKE LCASE('%" + CAM + "%')";
			}
		}
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlExpedientes> listaTipoOpera = new ArrayList<GrlExpedientes>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			GrlExpedientes grlExpedientes;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT ICVEPERSONAL,ICVEEXPEDIENTE,CNOMBRE, CAPPATERNO, CAPMATERNO FROM PERDATOS "
					+ WHERE;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			grlExpedientes = new GrlExpedientes();
			while (rset.next()) {
				grlExpedientes = new GrlExpedientes();
				grlExpedientes.setICVEEXPEDIENTE(rset.getInt("ICVEEXPEDIENTE"));
				grlExpedientes.setICVEPERSONAL(rset.getInt("ICVEPERSONAL"));
				grlExpedientes.setCNOMBRE(rset.getString("CNOMBRE"));
				grlExpedientes.setCAPPATERNO(rset.getString("CAPPATERNO"));
				grlExpedientes.setCAPMATERNO(rset.getString("CAPMATERNO"));
				listaTipoOpera.add(grlExpedientes);
			}
		} catch (Exception ex) {
			warn("FindAll", ex);
			throw new DAOException("FindAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindAll.close", ex2);
			}
			return listaTipoOpera;
		}
	}

	public List<GrlExpedientes> findExpedienteBloqueado(String ICVEEXPEDIENTE,
			String CRFC, String CNOMBRE, String CAP, String CAM)
			throws DAOException {
		String WHERE = "";
		if (!ICVEEXPEDIENTE.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE ICVEEXPEDIENTE = " + ICVEEXPEDIENTE;
			} else {
				WHERE += " OR ICVEEXPEDIENTE =" + ICVEEXPEDIENTE;
			}
		}
		if (!CRFC.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CRFC) LIKE LCASE('%" + CRFC + "%')";
			} else {
				WHERE += " OR LCASE(CRFC) LIKE LCASE('%" + CRFC + "%')";
			}
		}
		if (!CNOMBRE.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CNOMBRE) LIKE LCASE('%" + CNOMBRE + "%')";
			} else {
				WHERE += " OR LCASE(CNOMBRE) LIKE LCASE('%" + CNOMBRE + "%')";
			}
		}
		if (!CAP.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CAPPATERNO) LIKE LCASE('%" + CAP + "%')";
			} else {
				WHERE += " OR LCASE(CAPPATERNO) LIKE LCASE('%" + CAP + "%')";
			}
		}
		if (!CAM.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CAPMATERNO) LIKE LCASE('%" + CAM + "%')";
			} else {
				WHERE += " OR LCASE(CAPMATERNO) LIKE LCASE('%" + CAM + "%')";
			}
		}
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlExpedientes> listaTipoOpera = new ArrayList<GrlExpedientes>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			GrlExpedientes grlExpedientes;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT ICVEPERSONAL,ICVEEXPEDIENTE,CNOMBRE, CAPPATERNO, CAPMATERNO FROM PERDATOS "
					+ WHERE;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			grlExpedientes = new GrlExpedientes();
			MedPredDwr OBJECT = new MedPredDwr();
			while (rset.next()) {
				grlExpedientes = new GrlExpedientes();
				grlExpedientes.setICVEEXPEDIENTE(rset.getInt("ICVEEXPEDIENTE"));
				grlExpedientes.setICVEPERSONAL(rset.getInt("ICVEPERSONAL"));
				grlExpedientes.setCNOMBRE(rset.getString("CNOMBRE"));
				grlExpedientes.setCAPPATERNO(rset.getString("CAPPATERNO"));
				grlExpedientes.setCAPMATERNO(rset.getString("CAPMATERNO"));

				if (OBJECT.validaAccesosIncorrectosBitacoraExpedientes(""
						+ grlExpedientes.getICVEEXPEDIENTE()) >= 3) {
					listaTipoOpera.add(grlExpedientes);
				}
			}
		} catch (Exception ex) {
			warn("FindAll", ex);
			throw new DAOException("FindAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindAll.close", ex2);
			}
			return listaTipoOpera;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public List<GrlExpedientes> findAllExpedientes() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlExpedientes> listaTipoOpera = new ArrayList<GrlExpedientes>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			GrlExpedientes grlExpedientes;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT ICVEPERSONAL,ICVEEXPEDIENTE,CNOMBRE, CAPPATERNO, CAPMATERNO FROM PERDATOS";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			grlExpedientes = new GrlExpedientes();

			while (rset.next()) {
				grlExpedientes = new GrlExpedientes();
				grlExpedientes.setICVEEXPEDIENTE(rset.getInt("ICVEEXPEDIENTE"));
				grlExpedientes.setICVEPERSONAL(rset.getInt("ICVEPERSONAL"));
				grlExpedientes.setCNOMBRE(rset.getString("CNOMBRE"));
				grlExpedientes.setCAPPATERNO(rset.getString("CAPPATERNO"));
				grlExpedientes.setCAPMATERNO(rset.getString("CAPMATERNO"));
				listaTipoOpera.add(grlExpedientes);
			}
		} catch (Exception ex) {
			warn("FindAll", ex);
			throw new DAOException("FindAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindAll.close", ex2);
			}
			return listaTipoOpera;
		}
	}

	public List<ExpBitMod> findExpBitMod(String persona, String usuario,
			String operacion, String fechaInicio, String fechaFin)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<ExpBitMod> listaBit = new ArrayList<ExpBitMod>();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			ExpBitMod expBitMod;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			boolean and = false;

			cSQL = "SELECT mod.*, op.CDESCRIPCION as DESCOP FROM EXPBITMOD mod join GRLTIPOOPERABIT op on op.IOPERACION = mod.IOPERACION  where ";
			if (!persona.equals("")) {
				cSQL += " mod.ICVEEXPEDIENTE = " + persona;
				and = true;
			}
			if (!usuario.equals("")) {
				if (and) {
					cSQL += " and mod.ICVEUSUREALIZA = " + usuario;
				} else {
					cSQL += " mod.ICVEUSUREALIZA = " + usuario;
					and = true;
				}
			}
			if (!operacion.equals("")) {
				if (and) {
					cSQL += " and mod.IOPERACION = " + operacion;
				} else {
					cSQL += " mod.IOPERACION = " + operacion;
					and = true;
				}
			}
			if (!fechaInicio.equals("") && !fechaFin.equals("")) {
				if (and) {
					cSQL += " and mod.DTREALIZADO BETWEEN '" + fechaInicio
							+ " 00:00:00.0' and '" + fechaFin + " 00:00:00.0' ";
				} else {
					cSQL += " mod.DTREALIZADO BETWEEN '" + fechaInicio
							+ " 00:00:00.0' and '" + fechaFin + " 00:00:00.0' ";
					and = true;
				}
			}

			// System.out.println("revisando " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(
					"yyyy-MM-dd");
			while (rset.next()) {
				expBitMod = new ExpBitMod();
				// expBitMod.setiOperacion(rset.getString("IOPERACION"));
				// expBitMod.setcDescripcion(rset.getString("CDESCRIPCION"));
				expBitMod.setDescripcion(rset.getString("CDESCRIPCION"));
				expBitMod.setDtRealizado(formatoDelTexto.format(rset
						.getDate("DTREALIZADO")));
				expBitMod.setOperacion(rset.getString("DESCOP"));
				expBitMod.setiCveExpediente(rset.getString("ICVEEXPEDIENTE"));
				expBitMod.setiCveRama(rset.getString("ICVERAMA"));
				expBitMod.setiCveServicio(rset.getString("ICVESERVICIO"));
				expBitMod.setiCveSintoma(rset.getString("ICVESINTOMA"));
				expBitMod.setiCveUsuarioRealiza(rset
						.getString("ICVEUSUREALIZA"));
				expBitMod.setiNumExamen(rset.getString("INUMEXAMEN"));
				expBitMod.setlDictamen(rset.getString("LDICTAMEN"));
				listaBit.add(expBitMod);
			}
		} catch (Exception ex) {
			warn("FindAll", ex);
			throw new DAOException("FindAll");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rsetEx != null) {
					rsetEx.close();
				}
				if (pstmtEx != null) {
					pstmtEx.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("FindAll.close", ex2);
			}
			return listaBit;
		}
	}
}
