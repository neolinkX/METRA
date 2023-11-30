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
 * Title: Data Acces Object de TOXExamResult DAO
 * </p>
 * <p>
 * Description: Resultado del Exámen Toxicológico
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author LCI. Oscar Castrejón Adame.
 * @version 1.0
 */

public class TDTOXExamResult extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXExamResult() {
	}

	/**
	 * Metodo Find By All
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracción de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector FindByAll(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamResult = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXExamResult vTOXExamResult;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " TOXExamResult.iAnio,"
					+ " TOXExamResult.iCveLaboratorio,"
					+ " TOXExamResult.iCveLoteCualita,"
					+ " TOXExamResult.iCveExamCualita,"
					+ " TOXExamResult.iCveAnalisis,"
					+ " TOXExamResult.iCveSustancia,"
					+ " TOXExamResult.dResultado,"
					+ " TOXExamResult.dDilucion,"
					+ " TOXExamResult.iAsignado, " + " S.cDscSustancia "
					+ " from TOXExamResult" + cvFiltro + " ";
			System.out.println("Busqueda para Analisis Cuantitativo = " + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamResult = new TVTOXExamResult();
				vTOXExamResult.setiAnio(new Integer(rset.getInt(1)));
				vTOXExamResult.setiCveLaboratorio(new Integer(rset.getInt(2)));
				vTOXExamResult.setiCveLoteCualita(new Integer(rset.getInt(3)));
				vTOXExamResult.setiCveExamCualita(new Integer(rset.getInt(4)));
				vTOXExamResult.setiCveAnalisis(new Integer(rset.getInt(5)));
				vTOXExamResult.setiCveSustancia(new Integer(rset.getInt(6)));
				vTOXExamResult.setdResultado(new Double(rset.getDouble(7)));
				vTOXExamResult.setdDilucion(new Double(rset.getDouble(8)));
				vTOXExamResult.setlAsignado(new Integer(rset.getInt(9)));
				vTOXExamResult
						.setcDscSustancia(rset.getString("cDscSustancia"));
				vcTOXExamResult.addElement(vTOXExamResult);
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
			return vcTOXExamResult;
		}
	}

	/**
	 * Metodo FindByAll
	 * 
	 * @param hmFiltro
	 *            HashMap con los filtros por aplicar en la extracción de los
	 *            datos
	 * @return Un Vector con los campos requeridos, en caso de no encontrar
	 *         registros regresa un Vector vacio.
	 * @throws DAOException
	 */
	public Vector FindByAll(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamResult = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXExamResult vTOXExamResult;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String cSQL = "select "
					+ "iAnio,iCveLaboratorio,iCveLoteCualita,iCveExamCualita,"
					+ "iCveAnalisis,iCveSustancia,dResultado,lPositivo,dDilucion,iAsignado,lDudoso,lAutorizado "
					+ "from TOXExamResult "
					+ "where iAnio=? and iCveLaboratorio=? and iCveLoteCualita=? and "
					+
					// "iCveExamCualita=? and lAutorizado=0 ";
					"iCveExamCualita=?  ";

			// System.out.println("***********\n"+cSQL);

			pstmt = conn.prepareStatement(cSQL);

			String cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamResult = new TVTOXExamResult();
				vTOXExamResult.setiAnio(new Integer(rset.getInt("iAnio")));
				vTOXExamResult.setiCveLaboratorio(new Integer(rset
						.getInt("iCveLaboratorio")));
				vTOXExamResult.setiCveLoteCualita(new Integer(rset
						.getInt("iCveLoteCualita")));
				vTOXExamResult.setiCveExamCualita(new Integer(rset
						.getInt("iCveExamCualita")));
				vTOXExamResult.setiCveAnalisis(new Integer(rset
						.getInt("iCveAnalisis")));
				vTOXExamResult.setiCveSustancia(new Integer(rset
						.getInt("iCveSustancia")));
				vTOXExamResult.setdResultado(new Double(rset
						.getDouble("dResultado")));
				vTOXExamResult.setLPositivo(new Integer(rset
						.getInt("lPositivo")));
				vTOXExamResult.setdDilucion(new Double(rset
						.getDouble("dDilucion")));
				vTOXExamResult.setlAsignado(new Integer(rset
						.getInt("iAsignado")));
				vTOXExamResult.setLDudoso(new Integer(rset.getInt("lDudoso")));
				vTOXExamResult.setlAutorizado(new Integer(rset
						.getInt("lAutorizado")));
				vcTOXExamResult.addElement(vTOXExamResult);
			}
		} catch (Exception ex) {
			warn("getAnalisisPorSustancia", ex);
			throw new DAOException("getAnalisisPorSustancia");
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
				warn("getAnalisisPorSustancia.close", ex2);
			}
			return vcTOXExamResult;
		}
	}

	/**
	 * Metodo FindByAll para la Evaluacion y autorizacion del analisis
	 * Presuntivo
	 * 
	 * @param hmFiltro
	 *            HashMap con los filtros por aplicar en la extracción de los
	 *            datos
	 * @return Un Vector con los campos requeridos, en caso de no encontrar
	 *         registros regresa un Vector vacio.
	 * @throws DAOException
	 */
	public Vector FindByAllEAAP(HashMap hmFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		PreparedStatement pstmtCC = null;
		ResultSet rsetCC = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamResult = new Vector();
		Vector vcTOXANPRECC = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			TVTOXExamResult vTOXExamResult;
			TVTOXANPRECC vTOXANPRECC;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Obteniendo valores cercanos al corte para cada sustancia
			String cSQLCC = "SELECT ICVESUSTANCIA, DCORTE, DMARGENERROR FROM TOXANPRECC WHERE LACTIVO = 1";
			pstmtCC = conn.prepareStatement(cSQLCC);
			rsetCC = pstmtCC.executeQuery();
			while (rsetCC.next()) {
				// System.out.println("iCveSustancia = "
				// +rsetCC.getInt("iCveSustancia"));
				// System.out.println("lActivo = "+rsetCC.getInt("DCORTE"));
				// System.out.println("DMARGENERROR = "+rsetCC.getDouble("DMARGENERROR"));
				vTOXANPRECC = new TVTOXANPRECC();
				vTOXANPRECC.setiCveSustancia(rsetCC.getInt("iCveSustancia"));
				vTOXANPRECC.setdCorte(rsetCC.getDouble("dCorte"));
				vTOXANPRECC.setdMargenError(rsetCC.getDouble("dMargenError"));
				vcTOXANPRECC.addElement(vTOXANPRECC);
			}

			String Actualiza = "UPDATE TOXEXAMRESULT SET LDUDOSO = 0 WHERE IANIO = ? AND ICVELABORATORIO = ? AND ICVELOTECUALITA = ? "
					+ " AND ICVEEXAMCUALITA = ?";

			lPStmt = conn.prepareStatement(Actualiza);

			String cTmp2 = (String) hmFiltro.get("iAnio");
			lPStmt.setInt(1, cTmp2 != null ? Integer.parseInt(cTmp2) : 0);
			cTmp2 = (String) hmFiltro.get("iCveLaboratorio");
			lPStmt.setInt(2, cTmp2 != null ? Integer.parseInt(cTmp2) : 0);
			cTmp2 = (String) hmFiltro.get("iCveLoteCualita");
			lPStmt.setInt(3, cTmp2 != null ? Integer.parseInt(cTmp2) : 0);
			cTmp2 = (String) hmFiltro.get("iCveExamCualita");
			lPStmt.setInt(4, cTmp2 != null ? Integer.parseInt(cTmp2) : 0);
			lPStmt.executeUpdate();

			// Validando informacion para determinar si es cercana al corte o no
			for (int i = 0; i < vcTOXANPRECC.size(); i++) {
				vTOXANPRECC = (TVTOXANPRECC) vcTOXANPRECC.get(i);
				// System.out.println(vTOXANPRECC.getiCveSustancia());
				// System.out.println(vTOXANPRECC.getdMargenCC());
				// System.out.println(vTOXANPRECC.getdCorte());
				Actualiza = "UPDATE TOXEXAMRESULT SET LDUDOSO = 1 WHERE IANIO = ? AND ICVELABORATORIO = ? AND ICVELOTECUALITA = ? "
						+ " AND ICVEEXAMCUALITA = ? AND ICVESUSTANCIA = "
						+ vTOXANPRECC.getiCveSustancia().toString()
						+ " AND DRESULTADO >= "
						+ vTOXANPRECC.getdMargenCC().toString()
						+ " AND DRESULTADO < "
						+ vTOXANPRECC.getdCorte().toString() + "";
				lPStmt = conn.prepareStatement(Actualiza);
				String cTmp3 = (String) hmFiltro.get("iAnio");
				lPStmt.setInt(1, cTmp3 != null ? Integer.parseInt(cTmp3) : 0);
				cTmp3 = (String) hmFiltro.get("iCveLaboratorio");
				lPStmt.setInt(2, cTmp3 != null ? Integer.parseInt(cTmp3) : 0);
				cTmp3 = (String) hmFiltro.get("iCveLoteCualita");
				lPStmt.setInt(3, cTmp3 != null ? Integer.parseInt(cTmp3) : 0);
				cTmp3 = (String) hmFiltro.get("iCveExamCualita");
				lPStmt.setInt(4, cTmp3 != null ? Integer.parseInt(cTmp3) : 0);
				lPStmt.executeUpdate();
				// System.out.println("Se actualizaron cercanas al corte");
			}

			String cSQL = "select "
					+ "iAnio,iCveLaboratorio,iCveLoteCualita,iCveExamCualita,"
					+ "iCveAnalisis,iCveSustancia,dResultado,lPositivo,dDilucion,iAsignado,lDudoso,lAutorizado "
					+ "from TOXExamResult "
					+ "where iAnio=? and iCveLaboratorio=? and iCveLoteCualita=? and "
					+
					// "iCveExamCualita=? and lAutorizado=0 ";
					"iCveExamCualita=?  ";

			// System.out.println("***********\n"+cSQL);
			pstmt = conn.prepareStatement(cSQL);

			String cTmp = (String) hmFiltro.get("iAnio");
			pstmt.setInt(1, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLaboratorio");
			pstmt.setInt(2, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveLoteCualita");
			pstmt.setInt(3, cTmp != null ? Integer.parseInt(cTmp) : 0);
			cTmp = (String) hmFiltro.get("iCveExamCualita");
			pstmt.setInt(4, cTmp != null ? Integer.parseInt(cTmp) : 0);

			// Enviando informacion al Vector
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamResult = new TVTOXExamResult();
				vTOXExamResult.setiAnio(new Integer(rset.getInt("iAnio")));
				vTOXExamResult.setiCveLaboratorio(new Integer(rset
						.getInt("iCveLaboratorio")));
				vTOXExamResult.setiCveLoteCualita(new Integer(rset
						.getInt("iCveLoteCualita")));
				vTOXExamResult.setiCveExamCualita(new Integer(rset
						.getInt("iCveExamCualita")));
				vTOXExamResult.setiCveAnalisis(new Integer(rset
						.getInt("iCveAnalisis")));
				vTOXExamResult.setiCveSustancia(new Integer(rset
						.getInt("iCveSustancia")));
				vTOXExamResult.setdResultado(new Double(rset
						.getDouble("dResultado")));
				vTOXExamResult.setLPositivo(new Integer(rset
						.getInt("lPositivo")));
				vTOXExamResult.setdDilucion(new Double(rset
						.getDouble("dDilucion")));
				vTOXExamResult.setlAsignado(new Integer(rset
						.getInt("iAsignado")));
				vTOXExamResult.setLDudoso(new Integer(rset.getInt("lDudoso")));
				vTOXExamResult.setlAutorizado(new Integer(rset
						.getInt("lAutorizado")));
				vcTOXExamResult.addElement(vTOXExamResult);
			}

		} catch (Exception ex) {
			warn("getAnalisisPorSustancia", ex);
			throw new DAOException("getAnalisisPorSustancia");
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
				warn("getAnalisisPorSustancia.close", ex2);
			}
			return vcTOXExamResult;
		}
	}

	public Vector FindByLoteCualita(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamResult = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXExamResult vTOXExamResult;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select max(TOXExamResult.iCveLoteCualita) iCveLoteCualita "
					+ cvFiltro + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamResult = new TVTOXExamResult();
				vTOXExamResult.setiCveLoteCualita(new Integer(rset.getInt(1)));
				vcTOXExamResult.addElement(vTOXExamResult);
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
			return vcTOXExamResult;
		}
	}

	public boolean update(Object obj) throws DAOException {
		TVTOXExamResult VTOXExamResult = (TVTOXExamResult) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXExamResult      "
					+ " set dDilucion = ?,        "
					+ "     iAsignado = ?         "
					+ " where iAnio = ?           "
					+ "   and iCveLaboratorio = ? "
					+ "   and iCveLoteCualita = ? "
					+ "   and iCveExamCualita = ? "
					+ "   and iCveAnalisis = ?    "
					+ "   and iCveSustancia = ?   " + "";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setDouble(1, VTOXExamResult.getdDilucion().doubleValue());
			lPStmt.setInt(2, VTOXExamResult.getlAsignado().intValue());
			lPStmt.setInt(3, VTOXExamResult.getiAnio().intValue());
			lPStmt.setInt(4, VTOXExamResult.getiCveLaboratorio().intValue());
			lPStmt.setInt(5, VTOXExamResult.getiCveLoteCualita().intValue());
			lPStmt.setInt(6, VTOXExamResult.getiCveExamCualita().intValue());
			lPStmt.setInt(7, VTOXExamResult.getiCveAnalisis().intValue());
			lPStmt.setInt(8, VTOXExamResult.getiCveSustancia().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectuó el RollBack: Update", ex1);
			}
			warn("No se efectuó la Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Claves SAT");
		} finally {
			try {

				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectuó el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean insert(TVTOXExamResult VTOXExamResult) throws DAOException {
		PreparedStatement lPStmt = null;
		boolean lRegresa = false;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " insert into TOXExamResult ("
					+ "iAnio,"
					+ "iCveLaboratorio,"
					+ "iCveLoteCualita,"
					+ "iCveExamCualita,"
					+ "iCveAnalisis,"
					+ "iCveSustancia,"
					+ "lPositivo,"
					+ "dResultado,dDilucion,iAsignado,lDudoso)values(?,?,?,?,?,?,?,?,?,0,?)";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setInt(1, VTOXExamResult.getiAnio().intValue());
			lPStmt.setInt(2, VTOXExamResult.getiCveLaboratorio().intValue());
			lPStmt.setInt(3, VTOXExamResult.getiCveLoteCualita().intValue());
			lPStmt.setInt(4, VTOXExamResult.getiCveExamCualita().intValue());
			lPStmt.setInt(5, VTOXExamResult.getiCveAnalisis().intValue());
			lPStmt.setInt(6, VTOXExamResult.getiCveSustancia().intValue());
			lPStmt.setInt(7, VTOXExamResult.getLPositivo().intValue());
			lPStmt.setDouble(8, VTOXExamResult.getdResultado().doubleValue());
			lPStmt.setDouble(9, VTOXExamResult.getdDilucion().doubleValue());
			lPStmt.setInt(10, VTOXExamResult.getLDudoso().intValue());

			lPStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectuó el RollBack: Update", ex1);
			}
			// warn("No se efectuó la Update",ex);
		} finally {
			try {
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectuó el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean update(TVTOXExamResult VTOXExamResult, String cCondicion)
			throws DAOException {
		PreparedStatement lPStmt = null;
		boolean lRegresa = false;
		StringBuffer lSQL = new StringBuffer();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			lSQL.append(" update TOXExamResult E ")
					.append("    set E.iAsignado = ?, ")
					.append("        E.dDilucion = ? ")
					.append("  where E.iAnio           = ? ")
					.append("    and E.iCveLaboratorio = ? ")
					.append("    and E.iCveLoteCualita = ? ")
					.append("    and E.iCveExamCualita = ? ")
					.append("    and E.iCveAnalisis    = ? ");
			if (cCondicion.length() > 0)
				lSQL.append(cCondicion);
			else
				lSQL.append("    and E.iCveSustancia   = ? ");
			lPStmt = conn.prepareStatement(lSQL.toString());
			lPStmt.setInt(1, VTOXExamResult.getlAsignado().intValue());
			lPStmt.setDouble(2, VTOXExamResult.getdDilucion().doubleValue());

			lPStmt.setInt(3, VTOXExamResult.getiAnio().intValue());
			lPStmt.setInt(4, VTOXExamResult.getiCveLaboratorio().intValue());
			lPStmt.setInt(5, VTOXExamResult.getiCveLoteCualita().intValue());
			lPStmt.setInt(6, VTOXExamResult.getiCveExamCualita().intValue());
			lPStmt.setInt(7, VTOXExamResult.getiCveAnalisis().intValue());
			if (cCondicion.length() <= 0)
				lPStmt.setInt(8, VTOXExamResult.getiCveSustancia().intValue());

			lPStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectuó el RollBack: Update", ex1);
			}
			// warn("No se efectuó la Update",ex);
		} finally {
			try {
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectuó el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean ActivarMuestras(String lSQL) throws DAOException {
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			lPStmt = conn.prepareStatement(lSQL);

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
			}
			throw new DAOException("Error TDTOXExamResult");
		} finally {
			try {

				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectuó el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean updateAnalisisExamResutl(Vector vAnalisis, Vector vExamen)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = "update TOXAnalisis "
					+ "set lAutorizado=?,dtAutorizacion=?,lPresuntoPost=?,iCveUsuAutoriza=?,lResultado=?,iSustPost=?,iSustConf=?,iCveExamCualita=?"
					+ "where iAnio=? and iCveLaboratorio=? and iCveAnalisis=?";
			lPStmt = conn.prepareStatement(lSQL);
			for (int i = 0; i < vAnalisis.size(); i++) {
				TVTOXAnalisis tvAnalisis = (TVTOXAnalisis) vAnalisis.get(i);
				lPStmt.setInt(1, tvAnalisis.getlAutorizado().intValue());
				lPStmt.setDate(2, tvAnalisis.getdtAutorizacion());
				lPStmt.setInt(3, tvAnalisis.getlPresuntoPost().intValue());
				lPStmt.setInt(4, tvAnalisis.getiCveUsuAutoriza().intValue());
				lPStmt.setInt(5, tvAnalisis.getlResultado().intValue());
				lPStmt.setInt(6, tvAnalisis.getISustPost().intValue());
				lPStmt.setInt(7, tvAnalisis.getISustConf().intValue());
				lPStmt.setInt(8, tvAnalisis.getiCveExamCualita().intValue());
				lPStmt.setInt(9, tvAnalisis.getiAnio().intValue());
				lPStmt.setInt(10, tvAnalisis.getiCveLaboratorio().intValue());
				lPStmt.setInt(11, tvAnalisis.getiCveAnalisis().intValue());
				lPStmt.executeUpdate();
			}
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectuó el RollBack: Update", ex1);
			}
			warn("No se efectuó el Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Claves SAT");
		} finally {
			try {
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectuó el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean updateLoteCualita(Vector vExamen) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = "update ToxExamenCualita "
					+ "set lReanalisis=?,dtAutorizado=?,lAutorizado=?,iCveUsuAutoriza=? "
					+ "where iAnio=? and iCveLaboratorio=? and iCveLoteCualita=? and iCveExamCualita=? ";
			lPStmt = conn.prepareStatement(lSQL);
			// Actualizar ToxExamenCualita
			for (int i = 0; i < vExamen.size(); i++) {
				TVTOXExamenCualita tvExamen = (TVTOXExamenCualita) vExamen
						.get(i);
				lPStmt.setInt(1, tvExamen.getLReanalisis());
				lPStmt.setDate(2, tvExamen.getDtAutorizado());
				lPStmt.setInt(3, tvExamen.getLAutorizado());
				lPStmt.setInt(4, tvExamen.getICveUsuAutoriza());
				lPStmt.setInt(5, tvExamen.getIAnio());
				lPStmt.setInt(6, tvExamen.getICveLaboratorio());
				lPStmt.setInt(7, tvExamen.getICveLoteCualita());
				lPStmt.setInt(8, tvExamen.getICveExamCualita());
				lPStmt.executeUpdate();
			}
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectuó el RollBack: Update", ex1);
			}
			warn("No se efectuó el Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO LoteCualita");
		} finally {
			try {
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectuó el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	/**
	 * Metodo Insert
	 */
	public int insert(Connection conGral, Vector vcRegistros)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iContador = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String lSQL = "insert into TOXExamResult("
					+ "iAnio,iCveLaboratorio,iCveLoteCualita,iCveExamCualita,"
					+ "iCveAnalisis,iCveSustancia,dResultado,lPositivo,dDilucion,"
					+ "iAsignado) " + "values (?,?,?,?,?,?,?,?,1,0)";
			pstmt = conn.prepareStatement(lSQL);
			for (Enumeration eRegistros = vcRegistros.elements(); eRegistros
					.hasMoreElements();) {
				TVTOXExamResult VTOXExamResult = (TVTOXExamResult) eRegistros
						.nextElement();
				pstmt.setInt(1, VTOXExamResult.getiAnio().intValue());
				pstmt.setInt(2, VTOXExamResult.getiCveLaboratorio().intValue());
				pstmt.setInt(3, VTOXExamResult.getiCveLoteCualita().intValue());
				pstmt.setInt(4, VTOXExamResult.getiCveExamCualita().intValue());
				pstmt.setInt(5, VTOXExamResult.getiCveAnalisis().intValue());
				pstmt.setInt(6, VTOXExamResult.getiCveSustancia().intValue());
				pstmt.setDouble(7, VTOXExamResult.getdResultado().doubleValue());
				pstmt.setInt(8, VTOXExamResult.getLPositivo().intValue());
				iContador += pstmt.executeUpdate();
			}
			if (conGral == null) {
				conn.commit();
			}
			conn.setAutoCommit(true);
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
		}
		return iContador;
	}

	/**
	 * Metodo Find By All Análisis Presuntivo
	 * 
	 * @param int iAnio, int iCveLaboratorio, int iCveLoteCualita, int
	 *        iCveExamCualita
	 * @return Value Object del Resultado del Análisis Presuntivo
	 * @throws DAOException
	 */
	public Vector FindByAllAnalisisPresuntivo(int iAnio, int iCveLaboratorio,
			int iCveLoteCualita, int iCveExamCualita) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXExamResult = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXExamResult vTOXExamResult;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT A.ICVEANALISIS, S.CDSCSUSTANCIA, E.DRESULTADO, E.LPOSITIVO, C.CDSCCTROLCALIBRA, E.ICVESUSTANCIA "
					+ "FROM TOXEXAMRESULT E, TOXANALISIS A, TOXSUSTANCIA S, TOXCTROLCALIBRA C "
					+ "WHERE E.IANIO=A.IANIO AND E.ICVELABORATORIO=A.ICVELABORATORIO "
					+ "AND E.ICVEANALISIS=A.ICVEANALISIS "
					+ "AND E.ICVESUSTANCIA=S.ICVESUSTANCIA "
					+ "AND A.ICVELABORATORIO=C.ICVELABORATORIO AND A.ICVECTROLCALIBRA=C.ICVECTROLCALIBRA "
					+ "AND A.LAUTORIZADO = 0 AND E.IANIO=? AND E.ICVELABORATORIO=? "
					+ "AND E.ICVELOTECUALITA=? AND E.ICVEEXAMCUALITA=? "
					+ "ORDER BY E.ICVEANALISIS ";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iCveLaboratorio);
			pstmt.setInt(3, iCveLoteCualita);
			pstmt.setInt(4, iCveExamCualita);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXExamResult = new TVTOXExamResult();
				vTOXExamResult.setiCveAnalisis(new Integer(rset.getInt(1)));
				vTOXExamResult.setcDscSustancia(rset.getString(2));
				vTOXExamResult.setdResultado(new Double(rset.getDouble(3)));
				vTOXExamResult.setLPositivo(new Integer(rset.getInt(4)));
				vTOXExamResult.setCDscCtrolCalibra(rset.getString(5));
				vTOXExamResult.setiCveSustancia(new Integer(rset.getInt(6)));
				vcTOXExamResult.addElement(vTOXExamResult);
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
			return vcTOXExamResult;
		}
	}

	/**
	 * Metodo Find By All Análisis Presuntivo
	 * 
	 * @param int iAnio, int iCveLaboratorio, int iCveLoteCualita, int
	 *        iCveExamCualita
	 * @return Value Object del Resultado del Análisis Presuntivo
	 * @throws DAOException
	 */
	public int[] getSustanciasAnalisis(int iAnio, int iCveLaboratorio,
			int iCveLoteCualita, int iCveExamCualita) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int aSust[] = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT ICVESUSTANCIA FROM TOXCUALTSUST WHERE IANIO=? AND ICVELABORATORIO=? "
					+ "AND ICVELOTECUALITA=? AND ICVEEXAMCUALITA=? ";

			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iAnio);
			pstmt.setInt(2, iCveLaboratorio);
			pstmt.setInt(3, iCveLoteCualita);
			pstmt.setInt(4, iCveExamCualita);

			rset = pstmt.executeQuery();
			int i = 0;
			while (rset.next()) {
				aSust[i] = rset.getInt(1);
				i++;
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
			return aSust;
		}
	}

	public int updateDilucion(Connection conGral, Vector vcRegistros)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int iContador = 0;
		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String lSQL = "update TOXExamResult set dDilucion=? "
					+ "where iAnio=? and iCveLaboratorio=? and iCveLoteCualita=? and "
					+ "iCveExamCualita=? and iCveAnalisis=? and iCveSustancia=?";
			pstmt = conn.prepareStatement(lSQL);
			for (Enumeration eRegistros = vcRegistros.elements(); eRegistros
					.hasMoreElements();) {
				TVTOXExamResult VTOXExamResult = (TVTOXExamResult) eRegistros
						.nextElement();
				pstmt.setDouble(1, VTOXExamResult.getdDilucion().doubleValue());
				pstmt.setInt(2, VTOXExamResult.getiAnio().intValue());
				pstmt.setInt(3, VTOXExamResult.getiCveLaboratorio().intValue());
				pstmt.setInt(4, VTOXExamResult.getiCveLoteCualita().intValue());
				pstmt.setInt(5, VTOXExamResult.getiCveExamCualita().intValue());
				pstmt.setInt(6, VTOXExamResult.getiCveAnalisis().intValue());
				pstmt.setInt(7, VTOXExamResult.getiCveSustancia().intValue());
				iContador += pstmt.executeUpdate();
			}
			if (conGral == null) {
				conn.commit();
			}
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				if (conGral == null) {
					conn.rollback();
				}
			} catch (Exception ex1) {
				warn("updateDilucion", ex1);
			}
			warn("updateDilucion", ex);
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
				warn("updateDilucion.close", ex2);
			}
		}
		return iContador;
	}

	public boolean updateEnviaReanalisis(Vector vExamResult, Vector vAnalisis)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			// Actualizacion de TOXExamResult de todos, seleccionado o no.
			String lSQL = "update TOXExamResult " + "set lAutorizado=? "
					+ "where iAnio=? " + "and iCveLaboratorio=? "
					+ "and iCveLoteCualita=? " + "and iCveExamCualita=? "
					+ "and iCveAnalisis=? " + "and iCveSustancia=? " + "";
			lPStmt = conn.prepareStatement(lSQL);
			for (int i = 0; i < vExamResult.size(); i++) {
				TVTOXExamResult tvvExamResult = (TVTOXExamResult) vExamResult
						.get(i);
				lPStmt.setInt(1, tvvExamResult.getlAutorizado().intValue());
				lPStmt.setInt(2, tvvExamResult.getiAnio().intValue());
				lPStmt.setInt(3, tvvExamResult.getiCveLaboratorio().intValue());
				lPStmt.setInt(4, tvvExamResult.getiCveLoteCualita().intValue());
				lPStmt.setInt(5, tvvExamResult.getiCveExamCualita().intValue());
				lPStmt.setInt(6, tvvExamResult.getiCveAnalisis().intValue());
				lPStmt.setInt(7, tvvExamResult.getiCveSustancia().intValue());
				lPStmt.executeUpdate();
			}

			// Actualizacion de TOXAnalisis para los Positivos.
			lSQL = "update TOXAnalisis "
					+ "set TOXAnalisis.lPresuntoPost=1, "
					+ "TOXAnalisis.iSustPost = TOXAnalisis.iSustPost + "
					+ "                        (select count(lAutorizado) "
					+ "                         from TOXExamResult "
					+ "                         where TOXExamResult.iAnio = TOXAnalisis.iAnio "
					+ "                         and TOXExamResult.iCveLaboratorio = TOXAnalisis.iCveLaboratorio "
					+ "                         and TOXExamResult.iCveLoteCualita = ? "
					+ "                         and TOXExamResult.iCveExamCualita = ? "
					+ "                         and TOXExamResult.iCveAnalisis = TOXAnalisis.iCveAnalisis) "
					+ "where TOXAnalisis.iAnio=? "
					+ "and TOXAnalisis.iCveLaboratorio=? "
					+ "and TOXAnalisis.iCveAnalisis=? " + "";
			lPStmt = conn.prepareStatement(lSQL);
			for (int i = 0; i < vAnalisis.size(); i++) {
				TVTOXAnalisis tvAnalisis = (TVTOXAnalisis) vAnalisis.get(i);
				lPStmt.setInt(1, tvAnalisis.getiCveLoteCualita().intValue());
				lPStmt.setInt(2, tvAnalisis.getiCveExamCualita().intValue());
				lPStmt.setInt(3, tvAnalisis.getiAnio().intValue());
				lPStmt.setInt(4, tvAnalisis.getiCveLaboratorio().intValue());
				lPStmt.setInt(5, tvAnalisis.getiCveLaboratorio().intValue());
				lPStmt.executeUpdate();
			}

			conn.setAutoCommit(true);

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectuó el RollBack: Update", ex1);
			}
			warn("No se efectuó el Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Claves SAT");
		} finally {
			try {
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectuó el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}
}
