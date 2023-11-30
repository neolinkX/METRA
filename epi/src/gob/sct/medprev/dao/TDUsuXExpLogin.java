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
import gob.sct.medprev.dwr.vo.ExpBitMod;
import gob.sct.medprev.dwr.vo.GrlExpedientes;
import gob.sct.medprev.dwr.vo.GrlExpedientes;
import gob.sct.medprev.dwr.vo.GrlUsuXExpLogin;
import gob.sct.medprev.dwr.vo.GrlUsuXExpLogin;
import gob.sct.medprev.vo.*;

/**
 * 
 * @author admin
 */
public class TDUsuXExpLogin extends DAOBase {

	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public int getiDedoAValidar(int iCveUsuario) {
		int res = -1;
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "SELECT IDEDO FROM SEGUSUEXP " + "WHERE ICVEUSUARIO = "
					+ iCveUsuario;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				res = rset.getInt("IDEDO");
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
				warn("FindAll.close", ex2);
			}
		}
		return res;

	}

	public boolean guardaHuellasLogin(GrlUsuXExpLogin Login)
			throws DAOException {
		boolean res = false;

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "INSERT INTO SEGUSUEXP (ICVEUSUARIO,ICVEEXPEDIENTE,INODOCTO,IDEDO,TSCAPTURA,ICVEUSUARIOREGISTRO,LLICENCIAS,ICVEMDOTRANS) " +
					"VALUES (?,?,?,?,?,?,?,?)";

			/*System.out.println(cSQL);
			System.out.println(Login.getICVEUSUARIO());
			System.out.println(Login.getICVEEXPEDIENTE());
			System.out.println(Login.getINODOCTO());
			System.out.println(Login.getIDEDO());
			System.out.println(Login.getTSCAPTURA());
			System.out.println(Login.getICVEUSUARIOREGISTRO());
			System.out.println("--="+Login.getLLICENCIAS());*/
			
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Login.getICVEUSUARIO());
			pstmt.setInt(2, Login.getICVEEXPEDIENTE());
			pstmt.setInt(3, Login.getINODOCTO());
			pstmt.setInt(4, Login.getIDEDO());
			pstmt.setTimestamp(5, Login.getTSCAPTURA());
			pstmt.setInt(6, Login.getICVEUSUARIOREGISTRO());
			pstmt.setInt(7, Login.getLLICENCIAS());
			pstmt.setInt(8, Login.getICVEMDOTRANS());

			int rowsafected = pstmt.executeUpdate();
			res = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DAOException("insert");
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
		}
		return res;
	}

	public boolean modificaHuellasLogin(GrlUsuXExpLogin Login)
			throws DAOException {
		boolean res = false;

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "UPDATE SEGUSUEXP " + "SET INODOCTO = ? " + ", IDEDO = ? "
					+ ", TSCAPTURA = ? " + ", ICVEUSUARIOREGISTRO = ?, LLICENCIAS = ?, ICVEMDOTRANS = ? "
					+ "WHERE (" + "ICVEUSUARIO = ? "
					+ "AND ICVEEXPEDIENTE = ? )";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, Login.getINODOCTO());
			pstmt.setInt(2, Login.getIDEDO());
			pstmt.setTimestamp(3, Login.getTSCAPTURA());
			pstmt.setInt(4, Login.getICVEUSUARIOREGISTRO());
			pstmt.setInt(5, Login.getLLICENCIAS());
			pstmt.setInt(6, Login.getICVEMDOTRANS());
			pstmt.setInt(7, Login.getICVEUSUARIO());
			pstmt.setInt(8, Login.getICVEEXPEDIENTE());

			int rowsafected = pstmt.executeUpdate();
			res = true;
		} catch (Exception ex) {
			warn("insert", ex);
			throw new DAOException("insert");
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
		}
		return res;
	}

	public int buscaUltimoDocumentoContentManager(String iCvePersonal,
			String iDedo) throws DAOException {
		int res = -1;

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "SELECT INODOCTO,ICVEPERSONAL, IDEDO FROM LICFFH "
					+ " WHERE ICVEPERSONAL = " + iCvePersonal + " AND IDEDO = "
					+ iDedo + " ORDER BY INODOCTO DESC";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			boolean isfirst = true;

			while (rset.next() && isfirst) {
				res = rset.getInt("INODOCTO");
				isfirst = false;
			}
		} catch (Exception ex) {
			warn("insert", ex);
			throw new DAOException("insert");
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
				warn("FindAll.close", ex2);
			}
		}
		return res;
	}

	public boolean existeRegistroLoginUsuario(String iCveUsuario)
			throws DAOException {
		boolean res = false;

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "SELECT * FROM SEGUSUEXP WHERE ICVEUSUARIO = " + iCveUsuario;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				res = true;
			}
		} catch (Exception ex) {
			warn("insert", ex);
			throw new DAOException("insert");
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
				warn("FindAll.close", ex2);
			}
		}
		return res;
	}

	public boolean existeRegistroLoginExpediente(String iCveExpediente)
			throws DAOException {
		boolean res = false;

		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "SELECT * FROM SEGUSUEXP WHERE ICVEEXPEDIENTE = "
					+ iCveExpediente;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				res = true;
			}
		} catch (Exception ex) {
			warn("insert", ex);
			throw new DAOException("insert");
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
				warn("FindAll.close", ex2);
			}
		}
		return res;
	}

	/**
	 * Metodo Find By All
	 */
	public List<GrlUsuXExpLogin> findAllUsuXExpLogin() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		List<GrlUsuXExpLogin> listaR = new ArrayList<GrlUsuXExpLogin>();
		GrlUsuXExpLogin grlUsuarioExp;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "SELECT A.*, B.CUSUARIO AS CUSUARIO, B.CNOMBRE AS CNOMBRE, B.CAPPATERNO AS CAPPATERNO, B.CAPMATERNO AS CAPMATERNO, " +
					"C.CUSUARIO AS CUSUARIOREGISTRO "
					+ "FROM SEGUSUEXP A  "
					+ "JOIN SEGUSUARIO B ON A.ICVEUSUARIO = B.ICVEUSUARIO "
					+ "JOIN SEGUSUARIO C ON A.ICVEUSUARIOREGISTRO = C.ICVEUSUARIO";

			//System.out.println("findAllUsuXExpLogin"+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				grlUsuarioExp = new GrlUsuXExpLogin();
				grlUsuarioExp.setICVEUSUARIO(rset.getInt("ICVEUSUARIO"));
				grlUsuarioExp.setCUSUARIO(rset.getString("CUSUARIO"));
				grlUsuarioExp.setICVEEXPEDIENTE(rset.getInt("ICVEEXPEDIENTE"));
				grlUsuarioExp.setICVEUSUARIOREGISTRO(rset
						.getInt("ICVEUSUARIOREGISTRO"));
				grlUsuarioExp.setCNOMBRE(rset.getString("CNOMBRE"));
				grlUsuarioExp.setCAPPATERNO(rset.getString("CAPPATERNO"));
				grlUsuarioExp.setCAPMATERNO(rset.getString("CAPMATERNO"));
				grlUsuarioExp.setTSCAPTURA(rset.getTimestamp("TSCAPTURA"));
				grlUsuarioExp.setCCVEUSUARIOREGISTRO(rset
						.getString("CUSUARIOREGISTRO"));
				grlUsuarioExp.setIDEDO(rset.getInt("IDEDO"));
				grlUsuarioExp.setINODOCTO(rset.getInt("INODOCTO"));
				grlUsuarioExp.setLLICENCIAS(rset.getInt("LLICENCIAS"));
				grlUsuarioExp.setICVEMDOTRANS(rset.getInt("ICVEMDOTRANS"));
				listaR.add(grlUsuarioExp);
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
			return listaR;
		}
	}

	public int deleteUsuXExp(int iCveUsuario, int iCveExpediente)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmtEx = null;
		ResultSet rsetEx = null;
		int x = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			cSQL = "DELETE  " + "FROM SEGUSUEXP " + "WHERE "
					+ " ICVEUSUARIO = " + iCveUsuario
					+ " AND ICVEEXPEDIENTE = " + iCveExpediente;

			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			x = pstmt.executeUpdate();

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
				ex2.printStackTrace();
			}
			return x;
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
