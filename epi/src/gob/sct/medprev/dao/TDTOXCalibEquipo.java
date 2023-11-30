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
import com.micper.seguridad.vo.TVDinRep02;

/**
 * <p>
 * Title: Data Acces Object de TOXCalibEquipo DAO
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

public class TDTOXCalibEquipo extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXCalibEquipo() {
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
		Vector vcTOXCalibEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCalibEquipo vTOXCalibEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " iCveEquipo," + " iCveCalib," + " Fecha,"
					+ " iCveUsuCalib, " + " cObservacion, "
					+ " iCveAcCorrectiva, " + " lCorrecto "
					+ " from TOXCalibEquipo" + cvFiltro + " ";
			System.out.println("Busqueda\n" + cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCalibEquipo = new TVTOXCalibEquipo();
				vTOXCalibEquipo.setiCveEquipo(new Integer(rset.getInt(1)));
				vTOXCalibEquipo.setiCveCalib(new Integer(rset.getInt(2)));
				vTOXCalibEquipo.setFecha(rset.getDate(3));
				vTOXCalibEquipo.setiCveUsuCalib(new Integer(rset.getInt(4)));
				vTOXCalibEquipo.setcObservacion(rset.getString(5));
				vTOXCalibEquipo
						.setiCveAcCorrectiva(new Integer(rset.getInt(6)));
				vTOXCalibEquipo.setlCorrecto(new Integer(rset.getInt(7)));
				vcTOXCalibEquipo.addElement(vTOXCalibEquipo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			return vcTOXCalibEquipo;
		}
	}

	public int findLastForSequence(Object obj) {
		DbConnection dbConn = null;
		Connection conn = null;
		int iCveCalib = 0;
		TVTOXCalibEquipo VTOXCalibEquipo = (TVTOXCalibEquipo) obj;
		String lSQL = "select " + " max(iCveCalib) iCveCalib"
				+ " from  TOXCalibEquipo " + " where iCveEquipo = ? " + "";
		PreparedStatement lPStmt = null;
		ResultSet lRSet = null;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setInt(1, VTOXCalibEquipo.getiCveEquipo().intValue());

			lRSet = lPStmt.executeQuery();

			if (lRSet != null) {
				if (lRSet.next())
					iCveCalib = lRSet.getInt("iCveCalib") + 1;
				else
					iCveCalib = 1;
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
			return iCveCalib;
		}
	}

	public Object insert(Object obj) throws DAOException {
		TVTOXCalibEquipo VTOXCalibEquipo = (TVTOXCalibEquipo) obj;
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

			String lSQL = " insert into TOXCalibEquipo ( ";
			lSQL += " iCveEquipo, ";
			lSQL += " iCveCalib, ";
			lSQL += " Fecha, ";
			lSQL += " iCveUsuCalib, ";
			lSQL += " cObservacion, ";
			lSQL += " iCveAcCorrectiva, ";
			lSQL += " lCorrecto ";
			lSQL += " ) values (? ,? ,? ,?, ?, ?, ?)";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setInt(1, VTOXCalibEquipo.getiCveEquipo().intValue());
			lPStmt.setInt(2, VTOXCalibEquipo.getiCveCalib().intValue());

			if (VTOXCalibEquipo.getFecha() == null)
				lPStmt.setNull(3, Types.DATE);
			else
				lPStmt.setDate(3, VTOXCalibEquipo.getFecha());

			if (VTOXCalibEquipo.getiCveUsuCalib() == null)
				lPStmt.setInt(4, new Integer("0").intValue());
			else
				lPStmt.setInt(4, VTOXCalibEquipo.getiCveUsuCalib().intValue());

			if (VTOXCalibEquipo.getcObservacion() == null)
				lPStmt.setNull(5, Types.VARCHAR);
			else
				lPStmt.setString(5, VTOXCalibEquipo.getcObservacion());

			if (VTOXCalibEquipo.getiCveAcCorrectiva() == null)
				lPStmt.setNull(6, Types.INTEGER);
			else
				lPStmt.setInt(6, VTOXCalibEquipo.getiCveAcCorrectiva()
						.intValue());

			if (VTOXCalibEquipo.getlCorrecto() == null)
				lPStmt.setNull(7, Types.INTEGER);
			else
				lPStmt.setInt(7, VTOXCalibEquipo.getlCorrecto().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
			// System.out.println("Se hizo la inserci�n");
		} catch (Exception ex) {
			ex.printStackTrace();
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
				// fatal(VTOXCalibEquipo, "No se efectu� la inserci�n",
				// ex2);
				cRegresa = "";
			}
		}
		return cRegresa;
	}

	public boolean update(Object obj) throws DAOException {
		TVTOXCalibEquipo VTOXCalibEquipo = (TVTOXCalibEquipo) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		TFechas Fecha = new TFechas();
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXCalibEquipo       "
					+ " set Fecha = ? ,             "
					+ "     iCveUsuCalib = ? ,      "
					+ "     cObservacion = ? ,      "
					+ "     iCveAcCorrectiva = ? ,  "
					+ "     lCorrecto = ?           "
					+ " where iCveEquipo = ?        "
					+ "   and iCveCalib = ?         " + "";

			lPStmt = conn.prepareStatement(lSQL);

			if (VTOXCalibEquipo.getFecha() == null)
				lPStmt.setNull(1, Types.DATE);
			else
				lPStmt.setDate(1, VTOXCalibEquipo.getFecha());

			if (VTOXCalibEquipo.getiCveUsuCalib() == null)
				lPStmt.setNull(2, Types.INTEGER);
			else
				lPStmt.setInt(2, VTOXCalibEquipo.getiCveUsuCalib().intValue());

			if (VTOXCalibEquipo.getcObservacion() == null)
				lPStmt.setNull(3, Types.VARCHAR);
			else {
				lPStmt.setString(3, VTOXCalibEquipo.getcObservacion());
				// System.out.println("Observacion " +
				// VTOXCalibEquipo.getcObservacion());
			}

			if (VTOXCalibEquipo.getiCveAcCorrectiva() == null)
				lPStmt.setNull(4, Types.INTEGER);
			else
				lPStmt.setInt(4, VTOXCalibEquipo.getiCveAcCorrectiva()
						.intValue());

			if (VTOXCalibEquipo.getlCorrecto() == null)
				lPStmt.setNull(5, Types.INTEGER);
			else
				lPStmt.setInt(5, VTOXCalibEquipo.getlCorrecto().intValue());
			// System.out.println("lCorrecto " +
			// VTOXCalibEquipo.getlCorrecto().intValue());

			lPStmt.setInt(6, VTOXCalibEquipo.getiCveEquipo().intValue());
			lPStmt.setInt(7, VTOXCalibEquipo.getiCveCalib().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
			// System.out.println("Se hizo la actualizaci�n");

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

	/**
	 * Metodo findCalibracion
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector findCalibracion(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCalibEquipo = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXCalibEquipo vTOXCalibEquipo;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(
					" select CE.iCveEquipo, CE.iCveCalib, CE.Fecha as dtFecha, CE.iCveUsuCalib, ")
					.append("         CE.cObservacion, CE.lCorrecto, ")
					.append("         E.cNumSerie, E.cCveEquipo, ")
					.append("         U.cApPaterno || ' ' || U.cApMaterno || ' ' || U.cNombre as cNomCalib ")
					.append(" from TOXCalibEquipo CE ")
					.append(" inner join EQMEquipo E on E.iCveEquipo = CE.iCveEquipo ")
					.append(" inner join SEGUsuario U on U.iCveUsuario = CE.iCveUsuCalib ")
					.append(cvFiltro);

			System.out.println("Busqueda\n" + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCalibEquipo = new TVTOXCalibEquipo();
				vTOXCalibEquipo.setiCveEquipo(new Integer(rset
						.getInt("iCveEquipo")));
				vTOXCalibEquipo.setiCveCalib(new Integer(rset
						.getInt("iCveCalib")));
				vTOXCalibEquipo.setFecha(rset.getDate("dtFecha"));
				vTOXCalibEquipo.setiCveUsuCalib(new Integer(rset
						.getInt("iCveUsuCalib")));
				vTOXCalibEquipo.setcObservacion(rset.getString("cObservacion"));
				vTOXCalibEquipo
						.setiCveAcCorrectiva(new Integer(rset.getInt(6)));
				vTOXCalibEquipo.setlCorrecto(new Integer(rset
						.getInt("lCorrecto")));
				vTOXCalibEquipo.setCNomCalibra(rset.getString("cNomCalib"));
				vTOXCalibEquipo.setCCveEquipo(rset.getString("cCveEquipo"));
				vTOXCalibEquipo.setCNumSerie(rset.getString("cNumSerie"));
				vcTOXCalibEquipo.addElement(vTOXCalibEquipo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findCalibracion", ex);
			throw new DAOException("findCalibracion");
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
				warn("findCalibracion.close", ex2);
			}
			return vcTOXCalibEquipo;
		}
	}

	/**
	 * Metodo findDetalleCalib
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector findDetalleCalib(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCalibEquipo = new Vector();
		TVDinRep02 vInfo = new TVDinRep02();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXCalibEquipo vTOXCalibEquipo = null;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select  C.iCveParamCalib, C.dValor, ")
					.append("         CE.cObservacion, CE.Fecha as dtFecha, CE.lCorrecto, CE.iCveEquipo, CE.iCveCalib, iCveUsuCalib, ")
					.append("         P.cDscParam, P.dValorMin, P.dValorMax, ")
					.append("         E.cNumSerie, E.cCveEquipo, ")
					.append("         U.cApPaterno || ' ' || U.cApMaterno || ' ' || U.cNombre as cNomCalib ")
					.append(" from TOXCalib C")
					.append(" inner join TOXCalibEquipo CE on CE.iCveEquipo = C.iCveEquipo ")
					.append("                             and CE.iCveCalib  = C.iCveCalib ")
					.append(" inner join TOXParamCalib P on P.iCveLaboratorio = C.iCveLaboratorio ")
					.append("                           and P.iCveParamCalib = C.iCveParamCalib ")
					.append(" inner join EQMEquipo E on E.iCveEquipo = CE.iCveEquipo ")
					.append(" inner join SEGUsuario U on U.iCveUsuario = CE.iCveUsuCalib ")
					.append(cvFiltro);

			System.out.println("Busqueda\n" + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			int i = 0;
			while (rset.next()) {
				if (i == 0) {
					vTOXCalibEquipo = new TVTOXCalibEquipo();
					vTOXCalibEquipo.setiCveEquipo(new Integer(rset
							.getInt("iCveEquipo")));
					vTOXCalibEquipo.setiCveCalib(new Integer(rset
							.getInt("iCveCalib")));
					vTOXCalibEquipo.setFecha(rset.getDate("dtFecha"));
					vTOXCalibEquipo.setiCveUsuCalib(new Integer(rset
							.getInt("iCveUsuCalib")));
					vTOXCalibEquipo.setcObservacion(rset
							.getString("cObservacion"));
					vTOXCalibEquipo.setlCorrecto(new Integer(rset
							.getInt("lCorrecto")));
					vTOXCalibEquipo.setCNomCalibra(rset.getString("cNomCalib"));
					vTOXCalibEquipo.setCCveEquipo(rset.getString("cCveEquipo"));
					vTOXCalibEquipo.setCNumSerie(rset.getString("cNumSerie"));
					vTOXCalibEquipo.vParametros = new Vector();
					i++;
				}
				vInfo = new TVDinRep02();
				vInfo.put("iCveParamCalib", rset.getInt("iCveParamCalib"));
				vInfo.put("cDscParam", rset.getString("cDscParam"));
				vInfo.put("dValorMin", rset.getDouble("dValorMin"));
				vInfo.put("dValorMax", rset.getDouble("dValorMax"));
				vInfo.put("dValor", rset.getDouble("dValor"));
				if (vInfo.getDouble("dValor") >= vInfo.getDouble("dValorMin")
						&& vInfo.getDouble("dValor") <= vInfo
								.getDouble("dValorMax")) {
					vInfo.put("cResultado", "Dentro del Rango");
					vInfo.put("Estilo", "ETabla");
				} else {
					vInfo.put("Estilo", "ETablaST");
					if (vInfo.getDouble("dValor") < vInfo
							.getDouble("dValorMin"))
						vInfo.put("cResultado", "Fuera de Rango: Valor Menor.");
					else if (vInfo.getDouble("dValor") > vInfo
							.getDouble("dValorMax"))
						vInfo.put("cResultado", "Fuera de Rango: Valor Mayor.");
				} // Incorrecto
				vTOXCalibEquipo.vParametros.add(vInfo);
			}
			if (i > 0)
				vcTOXCalibEquipo.addElement(vTOXCalibEquipo);
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findCalibracion", ex);
			throw new DAOException("findCalibracion");
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
				warn("findCalibracion.close", ex2);
			}
			return vcTOXCalibEquipo;
		}
	}

}
