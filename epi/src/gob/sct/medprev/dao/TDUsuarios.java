/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.sct.medprev.dao;

import com.micper.ingsw.TParametro;
import com.micper.sql.DAOBase;
import com.micper.sql.DbConnection;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import com.micper.util.logging.*;
import gob.sct.medprev.dwr.MedPredDwr;
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.dwr.vo.GrlExpedientes;
import gob.sct.medprev.dwr.vo.GrlExpedientes;
import gob.sct.medprev.dwr.vo.GrlUsuarios;
import gob.sct.medprev.vo.*;

/**
 * 
 * @author admin
 */
public class TDUsuarios extends DAOBase {

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");// MEDPREV
	private String dataSourceName2 = VParametros.getPropEspecifica("ConDB");// ADMSEG

	/**
	 * Metodo Find By All
	 */
	public List<GrlUsuarios> findAllUsuarios() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlUsuarios> listaTipoOpera = new ArrayList<GrlUsuarios>();
		try {
			dbConn = new DbConnection(dataSourceName2);
			conn = dbConn.getConnection();
			String cSQL = "";
			GrlUsuarios grlUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT ICVEUSUARIO,CUSUARIO,CNOMBRE,CAPPATERNO, CAPMATERNO FROM SEGUSUARIO";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			grlUsuario = new GrlUsuarios();
			grlUsuario.setICVEUSUARIO(0);
			grlUsuario.setCUSUARIO("SELECCIONE...");
			grlUsuario.setCNOMBRE(" ");
			grlUsuario.setCAPPATERNO(" ");
			grlUsuario.setCAPMATERNO(" ");
			listaTipoOpera.add(grlUsuario);
			while (rset.next()) {
				grlUsuario = new GrlUsuarios();
				grlUsuario.setICVEUSUARIO(rset.getInt("ICVEUSUARIO"));
				grlUsuario.setCUSUARIO(rset.getString("CUSUARIO"));
				grlUsuario.setCNOMBRE(rset.getString("CNOMBRE"));
				grlUsuario.setCAPPATERNO(rset.getString("CAPPATERNO"));
				grlUsuario.setCAPMATERNO(rset.getString("CAPMATERNO"));
				listaTipoOpera.add(grlUsuario);
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

	public List<GrlUsuarios> findUsuarioBloqueado(String CUSUARIO,
			String ICVEUSUARIO, String CNOMBRE, String CAP, String CAM)
			throws DAOException {
		String WHERE = "";
		if (!CUSUARIO.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CUSUARIO) LIKE LCASE('%" + CUSUARIO
						+ "%')";
			} else {
				WHERE += " OR LCASE(CUSUARIO) LIKE LCASE('%" + CUSUARIO + "%')";
			}
		}

		// cambios
		if (!ICVEUSUARIO.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE ICVEUSUARIO = " + ICVEUSUARIO;
			} else {
				WHERE += " OR ICVEUSUARIO = " + ICVEUSUARIO;
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

			// /en el cambaoisdhjsoidhfsodh
		}
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlUsuarios> listaTipoOpera = new ArrayList<GrlUsuarios>();
		try {
			dbConn = new DbConnection(dataSourceName2);
			conn = dbConn.getConnection();
			String cSQL = "";
			GrlUsuarios grlUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT ICVEUSUARIO,CUSUARIO,CNOMBRE,CAPPATERNO, CAPMATERNO FROM SEGUSUARIO "
					+ WHERE;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			grlUsuario = new GrlUsuarios();

			MedPredDwr OBJECT = new MedPredDwr();

			while (rset.next()) {
				grlUsuario = new GrlUsuarios();
				grlUsuario.setICVEUSUARIO(rset.getInt("ICVEUSUARIO"));
				grlUsuario.setCUSUARIO(rset.getString("CUSUARIO"));
				grlUsuario.setCNOMBRE(rset.getString("CNOMBRE"));
				grlUsuario.setCAPPATERNO(rset.getString("CAPPATERNO"));
				grlUsuario.setCAPMATERNO(rset.getString("CAPMATERNO"));

				if (OBJECT.validaAccesosIncorrectosBitacora(""
						+ grlUsuario.getICVEUSUARIO()) >= 3) {
					listaTipoOpera.add(grlUsuario);
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
	 * Metodo Find By CUSUARIO
	 */
	public List<GrlUsuarios> findUsuario(String CUSUARIO, String ICVEUSUARIO,
			String CNOMBRE, String CAP, String CAM) throws DAOException {
		String WHERE = "";
		if (!CUSUARIO.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE LCASE(CUSUARIO) LIKE LCASE('%" + CUSUARIO
						+ "%')";
			} else {
				WHERE += " OR LCASE(CUSUARIO) LIKE LCASE('%" + CUSUARIO + "%')";
			}
		}
		if (!ICVEUSUARIO.equals("")) {
			if (WHERE.equals("")) {
				WHERE = " WHERE ICVEUSUARIO = " + ICVEUSUARIO;
			} else {
				WHERE += " OR ICVEUSUARIO = " + ICVEUSUARIO;
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
				WHERE += " OR LCASE(CAPMATERNO) LIKE ('%" + CAM + "%')";
			}
		}
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlUsuarios> listaTipoOpera = new ArrayList<GrlUsuarios>();
		try {
			dbConn = new DbConnection(dataSourceName2);
			conn = dbConn.getConnection();
			String cSQL = "";
			GrlUsuarios grlUsuario;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT ICVEUSUARIO,CUSUARIO,CNOMBRE,CAPPATERNO, CAPMATERNO FROM SEGUSUARIO "
					+ WHERE;
			System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			grlUsuario = new GrlUsuarios();
			while (rset.next()) {
				grlUsuario = new GrlUsuarios();
				grlUsuario.setICVEUSUARIO(rset.getInt("ICVEUSUARIO"));
				grlUsuario.setCUSUARIO(rset.getString("CUSUARIO"));
				grlUsuario.setCNOMBRE(rset.getString("CNOMBRE"));
				grlUsuario.setCAPPATERNO(rset.getString("CAPPATERNO"));
				grlUsuario.setCAPMATERNO(rset.getString("CAPMATERNO"));
				listaTipoOpera.add(grlUsuario);
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
