package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.msc.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXLoteCuantita DAO
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

public class TDTOXLoteCuantita extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	private pg0703060100XLSBean2 bean2;

	public TDTOXLoteCuantita() {
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
		Vector vcTOXLoteCuantita = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXLoteCuantita vTOXLoteCuantita;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iAnio," + " iCveLaboratorio,"
					+ " iCveSustancia," + " iCveLoteCuantita,"
					+ " dtGeneracion," + " iCveUsuGenera," + " dtAnalisis,"
					+ " dtAutorizacion," + " dCorte," + " dCorteNeg,"
					+ " dCortePost," + " iCveEquipo," + " iCveUsuAnalista,"
					+ " iCveUsuEncarga," + " iCveCorte, " + " iCveCalib, "
					+ " iCveAcCorrectiva, " + " cObservacion, "
					+ " dtCalibracion, " + " lValidaCalib "
					+ " from TOXLoteCuantita" + cvFiltro + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXLoteCuantita = new TVTOXLoteCuantita();
				vTOXLoteCuantita.setiAnio(new Integer(rset.getInt(1)));
				vTOXLoteCuantita
						.setiCveLaboratorio(new Integer(rset.getInt(2)));
				vTOXLoteCuantita.setiCveSustancia(new Integer(rset.getInt(3)));
				vTOXLoteCuantita
						.setiCveLoteCuantita(new Integer(rset.getInt(4)));
				vTOXLoteCuantita.setdtGeneracion(rset.getDate(5));
				vTOXLoteCuantita.setiCveUsuGenera(new Integer(rset.getInt(6)));
				vTOXLoteCuantita.setdtAnalisis(rset.getDate(7));
				vTOXLoteCuantita.setdtAutorizacion(rset.getDate(8));
				vTOXLoteCuantita.setdCorte(new Double(rset.getInt(9)));
				vTOXLoteCuantita.setdCorteNeg(new Double(rset.getInt(10)));
				vTOXLoteCuantita.setdCortePost(new Double(rset.getInt(11)));
				vTOXLoteCuantita.setiCveEquipo(new Integer(rset.getInt(12)));
				vTOXLoteCuantita
						.setiCveUsuAnalista(new Integer(rset.getInt(13)));
				vTOXLoteCuantita
						.setiCveUsuEncarga(new Integer(rset.getInt(14)));
				vTOXLoteCuantita.setiCveCorte(new Integer(rset.getInt(15)));
				vTOXLoteCuantita.setiCveCalib(new Integer(rset.getInt(16)));
				vTOXLoteCuantita.setiCveAcCorrectiva(new Integer(rset
						.getInt(17)));
				vTOXLoteCuantita.setcObservacion(rset.getString(18));
				vTOXLoteCuantita.setdtCalibracion(rset.getDate(19));
				vTOXLoteCuantita.setlValidaCalib(new Integer(rset.getInt(20)));
				vcTOXLoteCuantita.addElement(vTOXLoteCuantita);
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
			return vcTOXLoteCuantita;
		}
	}

	public int findLastForSequence(Object obj) {
		DbConnection dbConn = null;
		Connection conn = null;
		int iCveLoteCuantita = 0;
		TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) obj;
		String lSQL = "select "
				+ " iCveLoteCuantita "
				+ " from  TOXLoteCuantita "
				+ " where iAnio = ? "
				+ "   and iCveLaboratorio = ? "
				+ "   and iCveSustancia = ? "
				+ " order by iAnio asc, iCveLaboratorio asc, iCveSustancia asc, iCveLoteCuantita desc ";
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			lPStmt = conn.prepareStatement(lSQL);
			lPStmt.setInt(1, VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(2, VTOXLoteCuantita.getiCveLaboratorio().intValue());
			lPStmt.setInt(3, VTOXLoteCuantita.getiCveSustancia().intValue());

			lRSet = lPStmt.executeQuery();

			if (lRSet != null) {
				if (lRSet.next())
					iCveLoteCuantita = lRSet.getInt("iCveLoteCuantita") + 1;
				else
					iCveLoteCuantita = 1;
			}
		} catch (Exception ex) {
			warn("findLastForSequence", ex);
			ex.printStackTrace();

		} finally {
			try {
				if (lRSet != null) {
					lRSet.close();
				}
				if (lPStmt != null) {
					lPStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("findLastForSequence.close", ex2);
				ex2.printStackTrace();
			}
			return iCveLoteCuantita;
		}
	}

	public Object insert(Object obj) throws DAOException {
		TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) obj;
		TFechas Fecha = new TFechas();
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement lPStmt = null;
		String cRegresa = "";
		String cClave = "";
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " insert into TOXLoteCuantita ( ";
			lSQL += " iAnio, ";
			lSQL += " iCveLaboratorio, ";
			lSQL += " iCveSustancia, ";
			lSQL += " iCveLoteCuantita, ";
			lSQL += " dtGeneracion )";
			lSQL += " values (? ,? ,? ,?, ?)";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setInt(1, VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(2, VTOXLoteCuantita.getiCveLaboratorio().intValue());
			lPStmt.setInt(3, VTOXLoteCuantita.getiCveSustancia().intValue());
			lPStmt.setInt(4, VTOXLoteCuantita.getiCveLoteCuantita().intValue());
			lPStmt.setNull(5, Types.DATE);

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectu� el RollBack: Insert", ex1);
			}
			warn("No se efectu� la Inserci�n", ex);
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
				// fatal(VTOXLoteCuantita, "No se efectu� la inserci�n",
				// ex2);
				cRegresa = "";
			}
		}
		return cRegresa;
	}

	public boolean update(Object obj) throws DAOException {
		TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		TFechas Fecha = new TFechas();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXLoteCuantita     "
					+ " set dtGeneracion = ?,      "
					+ "     iCveUsuGenera = ?,     "
					+ "     iCveEquipo = ?,        "
					+ "     iCveCorte = ?,         "
					+ "     iCveCalib = ?          "
					+ " where iAnio = ?            "
					+ "   and iCveLaboratorio = ?  "
					+ "   and iCveSustancia = ?    "
					+ "   and iCveLoteCuantita = ? " + "";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setDate(1, Fecha.TodaySQL());

			if (VTOXLoteCuantita.getiCveUsuGenera() == null)
				lPStmt.setNull(2, Types.INTEGER);
			else
				lPStmt.setInt(2, VTOXLoteCuantita.getiCveUsuGenera().intValue());

			if (VTOXLoteCuantita.getiCveEquipo() == null)
				lPStmt.setNull(3, Types.INTEGER);
			else
				lPStmt.setInt(3, VTOXLoteCuantita.getiCveEquipo().intValue());

			if (VTOXLoteCuantita.getiCveCorte() == null)
				lPStmt.setNull(4, Types.INTEGER);
			else
				lPStmt.setInt(4, VTOXLoteCuantita.getiCveCorte().intValue());

			if (VTOXLoteCuantita.getiCveCalib() == null)
				lPStmt.setNull(5, Types.INTEGER);
			else
				lPStmt.setInt(5, VTOXLoteCuantita.getiCveCalib().intValue());

			lPStmt.setInt(6, VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(7, VTOXLoteCuantita.getiCveLaboratorio().intValue());
			lPStmt.setInt(8, VTOXLoteCuantita.getiCveSustancia().intValue());
			lPStmt.setInt(9, VTOXLoteCuantita.getiCveLoteCuantita().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectu� el RollBack: Update", ex1);
			}
			warn("No se efectu� la Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Claves SAT");
		} finally {
			try {

				if (lPStmt != null)
					lPStmt.close();
				if (conn != null)
					conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectu� el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean updCalibracion(Object obj) throws DAOException {
		TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		TFechas Fecha = new TFechas();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXLoteCuantita     "
					+ " set dtCalibracion = ?,     "
					+ "     lValidaCalib = ?,      "
					+ "     iCveAcCorrectiva = ?,  "
					+ "     cObservacion = ?       "
					+ " where iAnio = ?            "
					+ "   and iCveLaboratorio = ?  "
					+ "   and iCveSustancia = ?    "
					+ "   and iCveLoteCuantita = ? " + "";

			lPStmt = conn.prepareStatement(lSQL);
			// System.out.println("sSQL = " + lSQL);
			if (VTOXLoteCuantita.getdtCalibracion() == null)
				lPStmt.setNull(1, Types.DATE);
			else
				lPStmt.setDate(1, VTOXLoteCuantita.getdtCalibracion());

			if (VTOXLoteCuantita.getlValidaCalib() == null)
				lPStmt.setNull(2, Types.INTEGER);
			else
				lPStmt.setInt(2, VTOXLoteCuantita.getlValidaCalib().intValue());

			if (VTOXLoteCuantita.getiCveAcCorrectiva() == null)
				lPStmt.setNull(3, Types.INTEGER);
			else
				lPStmt.setInt(3, VTOXLoteCuantita.getiCveAcCorrectiva()
						.intValue());

			if (VTOXLoteCuantita.getcObservacion() == null)
				lPStmt.setNull(4, Types.VARCHAR);
			else
				lPStmt.setString(4, VTOXLoteCuantita.getcObservacion()
						.toString());
			// System.out.println("A�o " +
			// VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(5, VTOXLoteCuantita.getiAnio().intValue());

			lPStmt.setInt(6, VTOXLoteCuantita.getiCveLaboratorio().intValue());
			lPStmt.setInt(7, VTOXLoteCuantita.getiCveSustancia().intValue());
			lPStmt.setInt(8, VTOXLoteCuantita.getiCveLoteCuantita().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectu� el RollBack: Update", ex1);
			}
			warn("No se efectu� la Update", ex);
			ex.printStackTrace();
			throw new DAOException("TDTOXLoteCuantita");
		} finally {
			try {

				if (lPStmt != null)
					lPStmt.close();
				if (conn != null)
					conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectu� el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean updResultado(Object obj) throws DAOException {
		TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		TFechas Fecha = new TFechas();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			Vector vSustancia = (Vector) new TDTOXSustancia().FindByAll(
					"where iCveSustancia = "
							+ VTOXLoteCuantita.getiCveSustancia().intValue(),
					"");
			String cCveSustancia = " = "
					+ VTOXLoteCuantita.getiCveSustancia().toString();
			if (vSustancia != null && vSustancia.size() > 0) {
				cCveSustancia = " in ("
						+ ((TVTOXSustancia) vSustancia.get(0))
								.getCSustUnifica() + " ) ";
			}

			String lSQL = " update TOXLoteCuantita     "
					+ " set dtAnalisis      = ?,   "
					+ "     iCveUsuAnalista = ?    "
					+ " where iAnio            = ? "
					+ "   and iCveLaboratorio  = ? "
					+ "   and iCveSustancia   " + cCveSustancia
					+ "   and iCveLoteCuantita = ? ";

			lPStmt = conn.prepareStatement(lSQL);

			if (VTOXLoteCuantita.getdtAnalisis() == null)
				lPStmt.setNull(1, Types.DATE);
			else
				lPStmt.setDate(1, VTOXLoteCuantita.getdtAnalisis());

			if (VTOXLoteCuantita.getiCveUsuAnalista() == null)
				lPStmt.setNull(2, Types.INTEGER);
			else
				lPStmt.setInt(2, VTOXLoteCuantita.getiCveUsuAnalista()
						.intValue());

			lPStmt.setInt(3, VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(4, VTOXLoteCuantita.getiCveLaboratorio().intValue());
			// lPStmt.setInt (5,
			// VTOXLoteCuantita.getiCveSustancia().intValue());
			lPStmt.setInt(5, VTOXLoteCuantita.getiCveLoteCuantita().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
			}
			ex.printStackTrace();
			throw new DAOException("Actualizaci�n del Lote Cuantitativo");
		} finally {
			try {

				if (lPStmt != null)
					lPStmt.close();
				if (conn != null)
					conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectu� el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean updAutorizacion(Object obj) throws DAOException {
		TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		TFechas Fecha = new TFechas();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXLoteCuantita     "
					+ " set dtAutorizacion = ?,    "
					+ "     iCveUsuEncarga = ?     "
					+ " where iAnio = ?            "
					+ "   and iCveLaboratorio = ?  "
					+ "   and iCveSustancia = ?    "
					+ "   and iCveLoteCuantita = ? " + "";

			lPStmt = conn.prepareStatement(lSQL);

			if (VTOXLoteCuantita.getdtAutorizacion() == null)
				lPStmt.setNull(1, Types.DATE);
			else
				lPStmt.setDate(1, VTOXLoteCuantita.getdtAutorizacion());

			if (VTOXLoteCuantita.getiCveUsuEncarga() == null)
				lPStmt.setNull(2, Types.INTEGER);
			else
				lPStmt.setInt(2, VTOXLoteCuantita.getiCveUsuEncarga()
						.intValue());

			lPStmt.setInt(3, VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(4, VTOXLoteCuantita.getiCveLaboratorio().intValue());
			lPStmt.setInt(5, VTOXLoteCuantita.getiCveSustancia().intValue());
			lPStmt.setInt(6, VTOXLoteCuantita.getiCveLoteCuantita().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
			}
			ex.printStackTrace();
			throw new DAOException("Error en DAO Claves SAT");
		} finally {
			try {

				if (lPStmt != null)
					lPStmt.close();
				if (conn != null)
					conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectu� el update", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	/**
	 * Metodo DetalleLote
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector DetalleLote(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector VResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXLoteCuantDetalle VDetalle;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(
					"select LC.iAnio, S.cPrefLoteConf, LC.iCveLoteCuantita,  ")
					// 1, 2, 3
					.append("       LC.dtGeneracion, LC.dtCalibracion, LC.lValidaCalib,")
					// 4, 5, 6
					.append("       LC.dtAnalisis, LC.dtAutorizacion, ")
					// 7, 8
					.append("       LC.iCveUsuAnalista,")
					// 9
					.append("       (select count(CA.iCveAnalisis)")
					.append("        from TOXCuantAnalisis CA ")
					.append("        where CA.iAnio            = LC.iAnio ")
					.append("          and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("          and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("          and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("          and CA.iCveAnalisis     > 0) AS Analizados,   ")
					// 10
					.append("       (select count(CA.iCveAnalisis) ")
					.append("        from TOXCuantAnalisis CA ")
					.append("        where CA.iAnio = LC.iAnio ")
					.append("          and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("          and CA.iCveSustancia    = LC.iCveSustancia   ")
					.append("          and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("          and CA.lAutorizado      = 1           ")
					.append("          and CA.iCveAnalisis     > 0) AS Autorizados,  ")
					// 11
					.append("       (select count(CA.iCveAnalisis)")
					.append("        from TOXCuantAnalisis CA ")
					.append("         where CA.iAnio = LC.iAnio ")
					.append("           and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("           and CA.iCveSustancia    = LC.iCveSustancia   ")
					.append("           and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("           and CA.lAutorizado      = 1 ")
					.append("           and CA.lResultado       = 0 ")
					.append("           and CA.iCveAnalisis     > 0) AS Negativos, ")
					// 12
					.append("       (select count(CA.iCveAnalisis)         ")
					.append("        from TOXCuantAnalisis CA ")
					.append("        where CA.iAnio            = LC.iAnio  ")
					.append("          and CA.iCveLaboratorio  = LC.iCveLaboratorio  ")
					.append("          and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("          and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("          and CA.lAutorizado      = 1 ")
					.append("          and CA.lResultado       = 1 ")
					.append("          and CA.iCveAnalisis     > 0) AS Positivos,  ")
					// 13
					.append("       UA.cNombre || ' ' || UA.cApPaterno || ' ' || UA.cApMaterno as Nombre, ")
					// 14
					.append("       LC.iCveLaboratorio, LC.iCveSustancia,  ")
					// 15, 16
					.append("       UG.cNombre ||  ' ' || UG.cApPaterno  || ' ' || UG.cApMaterno  as cUsuGenera, ")
					// 17
					.append("       UAT.cNombre || ' ' || UAT.cApPaterno || ' ' || UAT.cApMaterno as cUsuAutoriza ")
					// 18
					.append(" from TOXLoteCuantita LC  ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = LC.iCveSustancia  ")
					.append(" left  join SEGUsuario UG  on UG.iCveUsuario  = LC.iCveUsuGenera ")
					.append(" left  join SEGUsuario UA  on UA.iCveUsuario  = LC.iCveUsuanalista ")
					.append(" left  join SEGUsuario UAT on UAT.iCveUsuario = LC.iCveUsuEncarga ")
					.append(cvFiltro);
			System.out.println("cSQL \n" + cSQL.toString());
			int count;
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VDetalle = new TVTOXLoteCuantDetalle();
				VDetalle.VLote.setiAnio(new Integer(rset.getInt(1)));
				VDetalle.VLote.VSustancia = new TVSustancia();
				VDetalle.VLote.VSustancia.setCPrefLoteConf(rset.getString(2));
				VDetalle.VLote.setiCveLoteCuantita(new Integer(rset.getInt(3)));
				VDetalle.VLote.setdtGeneracion(rset.getDate(4));
				VDetalle.VLote.setdtCalibracion(rset.getDate(5));
				VDetalle.VLote.setlValidaCalib(new Integer(rset.getInt(6)));
				VDetalle.VLote.setdtAnalisis(rset.getDate(7));
				VDetalle.VLote.setdtAutorizacion(rset.getDate(8));
				VDetalle.VLote.setiCveUsuAnalista(new Integer(rset.getInt(9)));
				VDetalle.setIAnalizados(rset.getInt(10));
				VDetalle.setIAutorizados(rset.getInt(11));
				VDetalle.setINegativos(rset.getInt(12));
				VDetalle.setIPositivos(rset.getInt(13));
				VDetalle.setCUsuAnalista(rset.getString(14));
				VDetalle.VLote.setiCveLaboratorio(new Integer(rset.getInt(15)));
				VDetalle.VLote.setiCveSustancia(new Integer(rset.getInt(16)));
				VDetalle.setCUsuGenera(rset.getString(17));
				VDetalle.setCUsuAutoriza(rset.getString(18));
				VResultado.addElement(VDetalle);
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
			return VResultado;
		}
	}

	public Vector SustPost(Connection connE, Object OMuestra)
			throws DAOException {
		Connection conn = null;
		DbConnection dbConn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcLoteCuant = new Vector();
		StringBuffer cSQL;
		TVTOXLoteCuantita VLote;
		try {
			if (connE == null) {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else
				conn = connE;

			// Query
			cSQL = new StringBuffer();
			cSQL.append(
					" select CA.iAnio, CA.iCveSustancia, CA.iCveLoteCuantita, CA.iCveAnalisis, ")
					.append("        CA.lResultado, CA.lAutorizado, ")
					.append("        LC.dtAnalisis, ")
					.append("        S.cDscSustancia, S.cTitRepConf, CA.dDensidad ")
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXLoteCuantita LC on LC.iAnio            = CA.iAnio ")
					.append("                              and LC.iCveLaboratorio  = CA.iCveLaboratorio ")
					.append("                              and LC.iCveSustancia    = CA.iCveSustancia ")
					.append("                              and LC.iCveLoteCuantita = CA.iCveLoteCuantita ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = CA.iCveSustancia ")
					.append(" where CA.iAnio           = ")
					.append(((TVMuestra) OMuestra).getIAnio())
					.append("   and CA.iCveLaboratorio = ")
					.append(((TVMuestra) OMuestra).getICveLaboratorio())
					.append("   and CA.iCveAnalisis    = ")
					.append(((TVMuestra) OMuestra).getICveAnalisis())
					.append("   and CA.lCorrecto       = 1 ")
					.append("   and CA.lAutorizado     = 1 ")
					.append("   and CA.lResultado      = ")
					.append(((TVMuestra) OMuestra).getLResultado());
			// System.out.println("Muestra Conf --> \n" + cSQL.toString());
			// Ejecutar el query
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VLote = new TVTOXLoteCuantita();
				// Obtener informaci�n
				VLote.setiAnio(new Integer(rset.getInt(1)));
				VLote.setiCveSustancia(new Integer(rset.getInt(2)));
				VLote.setiCveLoteCuantita(new Integer(rset.getInt(3)));
				VLote.setdtAnalisis(rset.getDate(7));
				VLote.VSustancia = new TVSustancia();
				VLote.VSustancia.setCDscSustancia(rset.getString(8));
				VLote.VSustancia.setCTitRepConf(rset.getString(9));
				VLote.VCuantAn = new TVTOXCuantAnalisis();
				VLote.VCuantAn.setdResultado(new Double(rset.getDouble(10)));
				VLote.VCuantAn.setlResultado(new Integer(rset.getInt(5)));
				// Agregar los resultados
				vcLoteCuant.add(VLote);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("SustPost", ex);
			throw new DAOException("SustPost");
		} finally {
			try {
				if (connE == null) {
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
				}
			} catch (Exception ex2) {
				warn("SustPost.close", ex2);
			}
			return vcLoteCuant;
		}
	}

	public Vector MuestrasXLote(Connection connE, Object vObjeto)
			throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DbConnection dbConn = null;
		ResultSet rset = null;
		Vector vcLoteCuant = new Vector();
		StringBuffer cSQL;
		TVTOXLoteCuantita VLote;
		try {
			if (connE == null) {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else
				conn = connE;

			// Query
			cSQL = new StringBuffer();
			cSQL.append(
					" select S.cDscSustancia, S.cPrefLoteConf, S.iMuestrasLC, ")
					.append("        (select count(*) ")
					.append("         from TOXCuantAnalisis CA  ")
					.append("         where CA.iAnio            = LC.iAnio ")
					.append("           and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("           and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("           and CA.iCveLoteCuantita = LC.iCveLoteCuantita) as iNumMuestras ")
					.append(" from TOXLoteCuantita LC ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = LC.iCveSustancia ")
					.append(" where LC.iAnio            = ")
					.append(((TVTOXLoteCuantita) vObjeto).getiAnio())
					.append("   and LC.iCveLaboratorio  = ")
					.append(((TVTOXLoteCuantita) vObjeto).getiCveLaboratorio())
					.append("   and LC.iCveSustancia    = ")
					.append(((TVTOXLoteCuantita) vObjeto).getiCveSustancia())
					.append("   and LC.iCveLoteCuantita = ")
					.append(((TVTOXLoteCuantita) vObjeto).getiCveLoteCuantita());
			// System.out.println("Query Valida = " + cSQL.toString());
			// Ejecutar el query
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VLote = new TVTOXLoteCuantita();
				VLote.setINumMuestras(rset.getInt("iNumMuestras"));
				// Obtener informaci�n
				VLote.VSustancia = new TVSustancia();
				VLote.VSustancia.setCPrefLoteConf(rset
						.getString("cPrefLoteConf"));
				VLote.VSustancia.setIMuestrasLC(rset.getInt("iMuestrasLC"));
				if (VLote.getINumMuestras() >= VLote.VSustancia
						.getIMuestrasLC())
					VLote.setLValidacion(true);
				else
					VLote.setLValidacion(false);
				// Agregar los resultados
				vcLoteCuant.add(VLote);
			}
		} catch (Exception ex) {
			warn("MuestrasXLote", ex);
			throw new DAOException("SustPost");
		} finally {
			try {
				if (connE == null) {
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
				}
			} catch (Exception ex2) {
				warn("MuestrasXLote.close", ex2);
			}
			return vcLoteCuant;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 11/Ago/2006 Sirve para generar el
	 * reporte: Registro y Comportamiento de Controles para An�lisis
	 * Confirmatorio
	 * 
	 * @param cFiltro
	 *            String
	 * @throws DAOException
	 * @return JXLSBean
	 */
	public JXLSBean generaReporteXLS(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		JXLSBean jxlsBean = new JXLSBean("");
		bean2 = new pg0703060100XLSBean2();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String cSQL = " select LC.iAnio, LC.iCveSustancia, LC.iCveLoteCuantita, "
					+ "        LC.dtAnalisis, LC.dtCalibracion, "
					+ "        (select CA.dDensidad "
					+ "         from TOXCuantAnalisis CA "
					+ "         where CA.iAnio = LC.iAnio "
					+ "         and CA.iCveLaboratorio  = LC.iCveLaboratorio "
					+ "         and CA.iCveSustancia    = LC.iCveSustancia "
					+ "         and CA.iCveLoteCuantita = LC.iCveLoteCuantita "
					+ "         and CA.iCveAnalisis = -3) as dNegativo, "
					+ "        (select CA.dDensidad "
					+ "         from TOXCuantAnalisis CA "
					+ "         where CA.iAnio = LC.iAnio "
					+ "         and CA.iCveLaboratorio  = LC.iCveLaboratorio "
					+ "         and CA.iCveSustancia    = LC.iCveSustancia "
					+ "         and CA.iCveLoteCuantita = LC.iCveLoteCuantita "
					+ "         and CA.iCveAnalisis = -2) as dCorte, "
					+ "        (select CA.dDensidad "
					+ "         from TOXCuantAnalisis CA "
					+ "         where CA.iAnio = LC.iAnio "
					+ "         and CA.iCveLaboratorio  = LC.iCveLaboratorio "
					+ "         and CA.iCveSustancia    = LC.iCveSustancia "
					+ "         and CA.iCveLoteCuantita = LC.iCveLoteCuantita "
					+ "         and CA.iCveAnalisis = -1) as dPositivo, "
					+ "        (select CC.cLote "
					+ "         from TOXCuantAnalisis CA "
					+ "         inner join TOXCtrolCalibra CC on CC.iCveCtrolCalibra = CA.iCveCtrolCalibra "
					+ "         where CA.iAnio = LC.iAnio "
					+ "         and CA.iCveLaboratorio  = LC.iCveLaboratorio "
					+ "         and CA.iCveSustancia    = LC.iCveSustancia "
					+ "         and CA.iCveLoteCuantita = LC.iCveLoteCuantita "
					+ "         and CA.iCveAnalisis = -3) as cLote, "
					+ "         S.cDscSustancia, "
					+ "         E.cCveEquipo, E.cModelo , E.cNumSerie, "
					+ "         LC.cObservacion "
					+ " from TOXLoteCuantita LC "
					+ " inner join TOXSustancia S on S.iCveSustancia = LC.iCveSustancia "
					+ " inner join EQMEquipo E on E.iCveEquipo = LC.iCveEquipo "
					+ cFiltro;
			// " where LC.iCveSustancia = 3 " +
			// " and LC.iCveEquipo     = 2 " +
			// " order by LC.iAnio, LC.iCveLaboratorio, LC.iCveSustancia, LC.iCveLoteCuantita; "

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			int iCount = 0;

			while (rset.next()) {
				pg0703060100XLSBean bean = new pg0703060100XLSBean();

				bean.setIcount(++iCount);
				;
				bean.setDtanalisis(new java.util.Date(rset
						.getDate("dtAnalisis").getTime()));

				// cLote puede venir de la forma: 040913-A o 050311/A
				// y se require quitar todo lo que va despues de "/" o "-"
				StringBuffer codificacion = new StringBuffer(
						rset.getString("cLote"));

				if (codificacion.indexOf("/") != -1) {
					codificacion.replace(codificacion.indexOf("/"),
							codificacion.length(), "");
				} else if (codificacion.indexOf("-") != -1) {
					codificacion.replace(codificacion.indexOf("-"),
							codificacion.length(), "");
				}

				// El formato para esta parte de ser: XXXX/XXX
				// (iAnio/iCveLoteCuantita)
				int iAnio = rset.getInt("iAnio");
				int iCveLoteCuantita = rset.getInt("iCveLoteCuantita");
				StringBuffer buffer = new StringBuffer();
				buffer.insert(0, iCveLoteCuantita);

				if (String.valueOf(iCveLoteCuantita).length() == 0) {
					buffer.replace(0, buffer.length(), "000");
				} else if (String.valueOf(iCveLoteCuantita).length() == 1) {
					buffer.replace(0, buffer.length(),
							"00" + String.valueOf(iCveLoteCuantita));
				} else if (String.valueOf(iCveLoteCuantita).length() == 2) {
					buffer.replace(0, buffer.length(),
							"0" + String.valueOf(iCveLoteCuantita));
				} else if (String.valueOf(iCveLoteCuantita).length() == 3) {
					buffer.replace(0, buffer.length(),
							String.valueOf(iCveLoteCuantita));
				}

				bean.setClote("" + iAnio + "/" + buffer);
				bean.setDcorteneg(new Double(rset.getDouble("dNegativo")));
				bean.setDcorte(new Double(rset.getDouble("dCorte")));
				bean.setDcortepost(new Double(rset.getDouble("dPositivo")));
				bean.setCcodificacion(codificacion.toString());
				bean.setCobservacion(rset.getString("cObservacion"));

				bean2.setCdscsustancia(rset.getString("cDscSustancia"));
				bean2.setCcveequipo(rset.getString("cCveEquipo"));
				bean2.setCmodelo(rset.getString("cModelo"));
				bean2.setCnumserie(rset.getString("cNumSerie"));

				jxlsBean.addBean(bean);
			}
		} catch (Exception ex) {
			warn("generaReporteXLS", ex);
			throw new DAOException("generaReporteXLS");
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
				warn("generaReporteXLS.close", ex2);
			}

			return jxlsBean;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 11/Ago/2006
	 * 
	 * @return pg0703060100XLSBean2
	 */
	public pg0703060100XLSBean2 getBean2() {
		return bean2;
	}

	public Vector obtenerControles(String iCveLaboratorio, int iControles,
			int iTamLote) throws DAOException {
		TDTOXCtrolCalibra dCtrol = new TDTOXCtrolCalibra();
		TVTOXCtrolCalibra vCtrol = new TVTOXCtrolCalibra();
		TVTOXAnalisis vAnalisG = new TVTOXAnalisis();
		Vector vCCal = new Vector(), vCCalN = new Vector(), vCCalU = new Vector(), vResultado = new Vector();
		Random vRandom = new Random();
		boolean lNegativo = false;
		int iPosicion = 0;
		try {
			// Obtener el vector de los controles.
			String cFiltro = " WHERE TOXCtrolCalibra.iCveLaboratorio = "
					+ iCveLaboratorio
					+ "   AND TOXCtrolCalibra.lCual           = 1 "
					+ "   AND TOXCtrolCalibra.lAgotado        = 0 "
					+ "   AND TOXCtrolCalibra.lBaja           = 0 "
					+ "   AND TOXCtrolCalibra.iCveEmpleoCalib = "
					+ this.VParametros.getPropEspecifica("CveCtrol");
			vCCal = dCtrol.FindByAll2(cFiltro, "");
			cFiltro += " and TOXCtrolCalibra.iCveSustancia = "
					+ this.VParametros.getPropEspecifica("TOXNegativo");
			vCCalN = dCtrol.FindByAll2(cFiltro, "");

			// Calcular los controles
			for (int i = 0; i < iControles; i++) {
				iPosicion = 0;
				vAnalisG = new TVTOXAnalisis();
				// Reiniciar el Vector de los controles disponibles
				vCCalU.trimToSize();
				if (vCCalU.size() == 0)
					vCCalU = (Vector) vCCal.clone();
				if (vCCalU.size() > 1)
					iPosicion = this.aleatorio(vRandom, vCCalU.size());
				vCtrol = ((TVTOXCtrolCalibra) vCCalU.get(iPosicion));
				vAnalisG.setiCveCtrolCalibra(new Integer(String.valueOf(vCtrol
						.getICveCtrolCalibra())));
				vAnalisG.setiCveAnalisis(new Integer(String.valueOf(this
						.aleatorio(vRandom, iTamLote))));
				vCCalU.remove(iPosicion);
				// Verificar que sea un negativo
				if (!lNegativo) {
					for (int iCtrol = 0; iCtrol < vCCalN.size(); iCtrol++) {
						if (vCtrol.getICveCtrolCalibra() == ((TVTOXCtrolCalibra) vCCalN
								.get(iCtrol)).getICveCtrolCalibra()) {
							lNegativo = true;
						}
					}// Barrido para quitar el negativo
				} // Verificar el negativo
				vResultado.add(vAnalisG);
			} // Calcular los controles
				// Sustituir el control negativo
			iPosicion = 0;
			if (!lNegativo && vCCalN.size() > 0) {
				if (vCCalN.size() > 1)
					iPosicion = this.aleatorio(vRandom, vCCalN.size());
				vCtrol = ((TVTOXCtrolCalibra) vCCalN.get(iPosicion));
				iPosicion = this.aleatorio(vRandom, vResultado.size());
				vAnalisG = (TVTOXAnalisis) vResultado.get(iPosicion);
				vAnalisG.setiCveCtrolCalibra(new Integer(String.valueOf(vCtrol
						.getICveCtrolCalibra())));
				vResultado.remove(iPosicion);
				vResultado.add(iPosicion, vAnalisG);
			}// Barrido para quitar el negativo
			/*
			 * // System.out.println("Resultado --- "); for(int i=0; i <
			 * vResultado.size(); i++){ vAnalisG = new TVTOXAnalisis(); vAnalisG
			 * = (TVTOXAnalisis) vResultado.get(i); // System.out.println("" + i
			 * + "," + vAnalisG.getiCveCtrolCalibra().toString() + "," +
			 * vAnalisG.getiCveAnalisis().toString() + ","); }
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vResultado;
	}

	/**
	 * aleatorio Calcula el aleatorio a partir de una base.
	 * 
	 * @return int
	 */
	public int aleatorio(Random vRandom, int base) {
		return (int) (vRandom.nextDouble() * 1000 % base);
	}

	public boolean updCalibracionVarias(Object obj, String cSustanciasUnif)
			throws DAOException {
		TVTOXLoteCuantita VTOXLoteCuantita = (TVTOXLoteCuantita) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		TFechas Fecha = new TFechas();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXLoteCuantita     "
					+ " set lValidaCalib = ?   "
					+ " where iAnio = ?            "
					+ "   and iCveLaboratorio = ?  "
					+ "   and iCveSustancia in  " + cSustanciasUnif
					+ "   and iCveLoteCuantita = ? " + "";
			lPStmt = conn.prepareStatement(lSQL);
			// System.out.println("sSQL = " + lSQL);

			if (VTOXLoteCuantita.getlValidaCalib() == null)
				lPStmt.setNull(1, Types.INTEGER);
			else
				lPStmt.setInt(1, VTOXLoteCuantita.getlValidaCalib().intValue());

			// System.out.println("A�o " +
			// VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(2, VTOXLoteCuantita.getiAnio().intValue());
			lPStmt.setInt(3, VTOXLoteCuantita.getiCveLaboratorio().intValue());
			lPStmt.setInt(4, VTOXLoteCuantita.getiCveLoteCuantita().intValue());
			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectu� el RollBack: Update", ex1);
			}
			warn("No se efectu� la Update", ex);
			ex.printStackTrace();
			throw new DAOException("TDTOXLoteCuantita");
		} finally {
			try {
				if (lPStmt != null)
					lPStmt.close();
				if (conn != null)
					conn.close();
				dbConn.closeConnection();
			} catch (Exception ex2) {
				fatal("No se efectu� el update", ex2);
				lRegresa = false;
			}

		}
		return lRegresa;
	}

	public ArrayList findGlobal(String cCondicion) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DbConnection dbConn = null;
		ResultSet rset = null;
		ArrayList vResultado = new ArrayList();
		ArrayList aQueryU = new ArrayList();
		QueryStructure qSentencia;
		HashMap hCamposQ1 = new HashMap();
		try {
			QueryManager qQuery = new QueryManager("07", "ConDBModulo", false);
			StringBuffer cSQL = new StringBuffer(), cFrom = new StringBuffer();
			cSQL.append("   LC.iAnio, ")
					.append("         LC.iCveLoteCuantita, ")
					.append("         LC.dtGeneracion, LC.dtCalibracion, LC.lValidaCalib,  ")
					.append("         LC.dtAnalisis, LC.dtAutorizacion, ")
					.append("         S.cPrefLoteConf, S.cDscSustancia,  ")
					.append("         E.cCveEquipo, ")
					.append("         LC.iCveUsuAnalista, ")
					.append("         (select CA.dDensidad ")
					.append("          from TOXCuantAnalisis CA  ")
					.append("           where CA.iAnio            = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.iCveAnalisis     = -5) AS dCalibrador, ")
					.append("         (select CA.dDensidad ")
					.append("          from TOXCuantAnalisis CA  ")
					.append("           where CA.iAnio            = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.iCveAnalisis     = -4) AS dNegativo, ")
					.append("         (select CA.dDensidad ")
					.append("          from TOXCuantAnalisis CA  ")
					.append("           where CA.iAnio            = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.iCveAnalisis     = -3) AS dControl1, ")
					.append("         (select CA.dDensidad ")
					.append("          from TOXCuantAnalisis CA  ")
					.append("           where CA.iAnio            = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.iCveAnalisis     = -2) AS dControl2, ")
					.append("         (select CA.dDensidad ")
					.append("          from TOXCuantAnalisis CA  ")
					.append("           where CA.iAnio            = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.iCveAnalisis     = -1) AS dControl3, ")
					.append("         (select count(CA.iCveAnalisis) ")
					.append("          from TOXCuantAnalisis CA  ")
					.append("           where CA.iAnio            = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.iCveAnalisis     > 0) AS iAnalizados, ")
					.append("         (select count(CA.iCveAnalisis) ")
					.append("          from TOXCuantAnalisis CA ")
					.append("           where CA.iAnio = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.lAutorizado      = 1 ")
					.append("             and CA.iCveAnalisis     > 0) AS iAutorizados, ")
					.append("         (select count(CA.iCveAnalisis) ")
					.append("          from TOXCuantAnalisis CA  ")
					.append("           where CA.iAnio = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.lAutorizado      = 1 ")
					.append("             and CA.lResultado       = 0 ")
					.append("             and CA.iCveAnalisis     > 0) AS iNegativos, ")
					.append("         (select count(CA.iCveAnalisis) ")
					.append("          from TOXCuantAnalisis CA ")
					.append("           where CA.iAnio            = LC.iAnio ")
					.append("             and CA.iCveLaboratorio  = LC.iCveLaboratorio ")
					.append("             and CA.iCveSustancia    = LC.iCveSustancia ")
					.append("             and CA.iCveLoteCuantita = LC.icveLoteCuantita ")
					.append("             and CA.lAutorizado      = 1 ")
					.append("             and CA.lResultado       = 1 ")
					.append("             and CA.iCveAnalisis     > 0) AS iPositivos, ")
					.append("         UA.cNombre || ' ' || UA.cApPaterno || ' ' || UA.cApMaterno as cUsuAnalisa, ")
					.append("         LC.iCveLaboratorio, LC.iCveSustancia, ")
					.append("         UG.cNombre ||  ' ' || UG.cApPaterno  || ' ' || UG.cApMaterno  as cUsuGenera, ")
					.append("         UAT.cNombre || ' ' || UAT.cApPaterno || ' ' || UAT.cApMaterno as cUsuAutoriza ");

			cFrom.append(" TOXLoteCuantita LC ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = LC.iCveSustancia ")
					.append(" inner join EQMEquipo E on E.iCveEquipo = LC.iCveEquipo ")
					.append(" left  join SEGUsuario UG  on UG.iCveUsuario  = LC.iCveUsuGenera ")
					.append(" left  join SEGUsuario UA  on UA.iCveUsuario  = LC.iCveUsuanalista ")
					.append(" left  join SEGUsuario UAT on UAT.iCveUsuario = LC.iCveUsuEncarga ");
			hCamposQ1.put(cSQL.toString(), "");
			qSentencia = new QueryStructure(hCamposQ1, cFrom.toString(),
					cCondicion.toUpperCase().replaceAll("WHERE", " "),
					QueryStructure.SELECT);
			aQueryU.add(qSentencia);
			vResultado = qQuery.manageTransaction(aQueryU);
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findGlobal", ex);
			throw new DAOException("findGlobal");
		}
		return vResultado;
	}

}
