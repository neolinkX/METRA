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
import java.text.DecimalFormat;
import com.micper.util.TNumeros;

/**
 * <p>
 * Title: Data Acces Object de TOXCuantAnalisis DAO
 * </p>
 * <p>
 * Description: Resultado del Ex�men Toxicol�gico
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

public class TDTOXCuantAnalisis extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXCuantAnalisis() {
	}

	/**
	 * 
	 * @return
	 * @throws DAOException
	 */
	/**
	 * Método Find By All
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
		Vector vcTOXCuantAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCuantAnalisis vTOXCuantAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select " + " TOXCuantAnalisis.iAnio,"
					+ " TOXCuantAnalisis.iCveSustancia,"
					+ " TOXCuantAnalisis.iCveLoteCuantita,"
					+ " TOXCuantAnalisis.iCveAnalisis,"
					+ " TOXCuantAnalisis.iCveLaboratorio,"
					+ " TOXCuantAnalisis.iCveCtrolCalibra,"
					+ " TOXCuantAnalisis.lControl,"
					+ " TOXCuantAnalisis.iPosicion,"
					+ " TOXCuantAnalisis.iDilusion,"
					+ " TOXCuantAnalisis.lResultado,"
					+ " TOXCuantAnalisis.dDensidad,"
					+ " TOXCuantAnalisis.dResultadoDil,"
					+ " TOXCuantAnalisis.lAutorizado,"
					+ " TOXCuantAnalisis.dConcentracion, "
					+ " TOXCuantAnalisis.cObservacion,   "
					+ " TOXCuantAnalisis.dTmpRetenc,     "
					+ " TOXCuantAnalisis.dIon01,         "
					+ " TOXCuantAnalisis.dIon02,         "
					+ " TOXCuantAnalisis.dIon03,         "
					+ " TOXCuantAnalisis.dTmpRetencD,    "
					+ " TOXCuantAnalisis.dIon04,         "
					+ " TOXCuantAnalisis.dIon05,         "
					+ " TOXCuantAnalisis.lCorrecto,      "
					+ " TOXCuantAnalisis.lRegistrado     "
					+ " from TOXCuantAnalisis " + cvFiltro + " ";

			// System.out.println("TOXCuantAnalisis  findByAll");
			// System.out.println(cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCuantAnalisis = new TVTOXCuantAnalisis();
				vTOXCuantAnalisis.setiAnio(new Integer(rset.getInt(1)));
				vTOXCuantAnalisis.setiCveSustancia(new Integer(rset.getInt(2)));
				vTOXCuantAnalisis.setiCveLoteCuantita(new Integer(rset
						.getInt(3)));
				vTOXCuantAnalisis.setiCveAnalisis(new Integer(rset.getInt(4)));
				vTOXCuantAnalisis
						.setiCveLaboratorio(new Integer(rset.getInt(5)));
				vTOXCuantAnalisis.setiCveCtrolCalibra(new Integer(rset
						.getInt(6)));
				vTOXCuantAnalisis.setlControl(new Integer(rset.getInt(7)));
				vTOXCuantAnalisis.setiPosicion(new Integer(rset.getInt(8)));
				vTOXCuantAnalisis.setiDilusion(new Integer(rset.getInt(9)));
				vTOXCuantAnalisis.setlResultado(new Integer(rset.getInt(10)));
				vTOXCuantAnalisis.setdResultado(new Double(rset.getDouble(11)));
				vTOXCuantAnalisis.setdResultadoDil(new Double(rset
						.getDouble(12)));
				vTOXCuantAnalisis.setlAutorizado(new Integer(rset.getInt(13)));
				vTOXCuantAnalisis.setdConcentracion(new Double(rset
						.getDouble(14)));
				vTOXCuantAnalisis.setcObservacion(rset.getString(15));
				vTOXCuantAnalisis.setDTmpRetenc(rset.getDouble(16));
				vTOXCuantAnalisis.setDIon01(rset.getDouble(17));
				vTOXCuantAnalisis.setDIon02(rset.getDouble(18));
				vTOXCuantAnalisis.setDIon03(rset.getDouble(19));
				vTOXCuantAnalisis.setDTmpRetencD(rset.getDouble(20));
				vTOXCuantAnalisis.setDIon04(rset.getDouble(21));
				vTOXCuantAnalisis.setDIon05(rset.getDouble(22));
				vTOXCuantAnalisis.setLCorrecto(new Integer(rset.getInt(23)));
				vTOXCuantAnalisis.setLRegistrado(new Integer(rset.getInt(24)));
				vcTOXCuantAnalisis.addElement(vTOXCuantAnalisis);
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
			return vcTOXCuantAnalisis;
		}
	}

	public Vector CountByAll(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCuantAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCuantAnalisis vTOXCuantAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select count(*) " + " from TOXCuantAnalisis " + cvFiltro
					+ " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCuantAnalisis = new TVTOXCuantAnalisis();
				vTOXCuantAnalisis.setiAnio(new Integer(rset.getInt(1)));
				vcTOXCuantAnalisis.addElement(vTOXCuantAnalisis);
			}
		} catch (Exception ex) {
			warn("countByAll", ex);
			throw new DAOException("CountByAll");
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
			return vcTOXCuantAnalisis;
		}
	}

	public Object insert(Object obj) throws DAOException {
		TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
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

			String lSQL = " insert into TOXCuantAnalisis ( ";
			lSQL += " iAnio, ";
			lSQL += " iCveSustancia, ";
			lSQL += " iCveLoteCuantita, ";
			lSQL += " iCveAnalisis, ";
			lSQL += " iCveLaboratorio, ";
			lSQL += " iDilusion ) ";
			lSQL += " values (? ,? ,? ,?, ?, ?)";

			lPStmt = conn.prepareStatement(lSQL);

			/*
			 * System.out.println("Insert = " +lSQL);
			 * 
			 * // System.out.println( VTOXCuantAnalisis.getiAnio().intValue());
			 * // System.out.println(
			 * VTOXCuantAnalisis.getiCveSustancia().intValue()); //
			 * System.out.println(
			 * VTOXCuantAnalisis.getiCveLoteCuantita().intValue()); //
			 * System.out.println(
			 * VTOXCuantAnalisis.getiCveAnalisis().intValue()); //
			 * System.out.println(
			 * VTOXCuantAnalisis.getiCveLaboratorio().intValue()); //
			 * System.out.println( VTOXCuantAnalisis.getiDilusion().intValue());
			 */

			lPStmt.setInt(1, VTOXCuantAnalisis.getiAnio().intValue());
			lPStmt.setInt(2, VTOXCuantAnalisis.getiCveSustancia().intValue());
			lPStmt.setInt(3, VTOXCuantAnalisis.getiCveLoteCuantita().intValue());
			lPStmt.setInt(4, VTOXCuantAnalisis.getiCveAnalisis().intValue());
			lPStmt.setInt(5, VTOXCuantAnalisis.getiCveLaboratorio().intValue());
			lPStmt.setInt(6, VTOXCuantAnalisis.getiDilusion().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			// ex.printStackTrace();
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
				// fatal(VTOXCuantAnalisis, "No se efectu� la inserci�n", ex2);
				cRegresa = "";
			}
		}
		return cRegresa;
	}

	public Object insert1(Object obj) throws DAOException {
		TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
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

			String lSQL = " insert into TOXCuantAnalisis ( ";
			lSQL += " iAnio, ";
			lSQL += " iCveSustancia, ";
			lSQL += " iCveLoteCuantita, ";
			lSQL += " iCveAnalisis, ";
			lSQL += " iCveLaboratorio, ";
			lSQL += " lControl, ";
			lSQL += " iPosicion, ";
			lSQL += " iDilusion, ";
			lSQL += " dConcentracion ";
			lSQL += " ) values (? ,? ,?, ?, ?, ?, ?, ?, ?)";

			// System.out.println("insert1");

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setInt(1, VTOXCuantAnalisis.getiAnio().intValue());
			lPStmt.setInt(2, VTOXCuantAnalisis.getiCveSustancia().intValue());
			lPStmt.setInt(3, VTOXCuantAnalisis.getiCveLoteCuantita().intValue());
			lPStmt.setInt(4, VTOXCuantAnalisis.getiCveAnalisis().intValue());
			lPStmt.setInt(5, VTOXCuantAnalisis.getiCveLaboratorio().intValue());
			lPStmt.setInt(6, VTOXCuantAnalisis.getlControl().intValue());
			lPStmt.setInt(7, VTOXCuantAnalisis.getiPosicion().intValue());
			lPStmt.setInt(8, VTOXCuantAnalisis.getiDilusion().intValue());
			lPStmt.setDouble(9, VTOXCuantAnalisis.getdConcentracion()
					.doubleValue());

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
				// fatal(VTOXCuantAnalisis, "No se efectu� la inserci�n", ex2);
				cRegresa = "";
			}
		}
		return cRegresa;
	}

	public boolean delete(Object obj) throws DAOException {
		TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
		PreparedStatement lPStmt = null;
		DbConnection dbConn = null;
		Connection conn = null;
		boolean lRegresa = true;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " delete from TOXCuantAnalisis "
					+ " where iAnio            = ?  "
					+ "   and iCveLaboratorio  = ?  "
					+ "   and iCveSustancia    = ?  "
					+ "   and iCveLoteCuantita = ?  "
					+ "   and iCveAnalisis     = ?  " + "";

			lPStmt = conn.prepareStatement(lSQL);

			lPStmt.setInt(1, VTOXCuantAnalisis.getiAnio().intValue());
			lPStmt.setInt(2, VTOXCuantAnalisis.getiCveLaboratorio().intValue());
			lPStmt.setInt(3, VTOXCuantAnalisis.getiCveSustancia().intValue());
			lPStmt.setInt(4, VTOXCuantAnalisis.getiCveLoteCuantita().intValue());
			lPStmt.setInt(5, VTOXCuantAnalisis.getiCveAnalisis().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
				warn("No se efectu� el RollBack: borrado", ex1);
			}
			warn("No se efectu� el borrado ", ex);
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
				fatal("No se efectu� el borrado ", ex2);
				lRegresa = false;
			}
		}
		return lRegresa;
	}

	public boolean update(Object obj) throws DAOException {
		TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXCuantAnalisis    "
					+ " set iPosicion = ?          "
					+ " where iAnio = ?            "
					+ "   and iCveLaboratorio = ? "
					+ "   and iCveSustancia = ?    "
					+ "   and iCveLoteCuantita = ? "
					+ "   and iCveAnalisis = ?    " + "";

			lPStmt = conn.prepareStatement(lSQL);
			// System.out.println("update");

			lPStmt.setInt(1, VTOXCuantAnalisis.getiPosicion().intValue());
			lPStmt.setInt(2, VTOXCuantAnalisis.getiAnio().intValue());
			lPStmt.setInt(3, VTOXCuantAnalisis.getiCveLaboratorio().intValue());
			lPStmt.setInt(4, VTOXCuantAnalisis.getiCveSustancia().intValue());
			lPStmt.setInt(5, VTOXCuantAnalisis.getiCveLoteCuantita().intValue());
			lPStmt.setInt(6, VTOXCuantAnalisis.getiCveAnalisis().intValue());

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

	/**
	 * Busqueda del corte del lote cuantitativo.
	 * 
	 * @param cvFiltro
	 * @return
	 * @throws DAOException
	 */
	public Vector FindCorte(String cvFiltro) throws DAOException {
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
			int count;
			cSQL = " select a.iCveCorte, b.dCorteNeg, b.dCorte, b.dCortePost "
					+ " from TOXLoteCuantita a " + " join TOXCorteXSust b   "
					+ "   on  (b.iCveSustancia = a.iCveSustancia "
					+ "   and  b.iCveCorte     = a.iCveCorte) " + cvFiltro
					+ " ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCorteXSust = new TVTOXCorteXSust();
				vTOXCorteXSust.setiCveCorte(new Integer(rset.getInt(1)));
				vTOXCorteXSust.setdCorteNeg(new Double(rset.getDouble(2)));
				vTOXCorteXSust.setdCorte(new Double(rset.getDouble(3)));
				vTOXCorteXSust.setdCortePost(new Double(rset.getDouble(4)));
				vcTOXCorteXSust.addElement(vTOXCorteXSust);
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
			return vcTOXCorteXSust;
		}
	}

	public boolean updCalibracion(Object obj) throws DAOException {
		TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			String lSQL = " update TOXCuantAnalisis    "
					+ " set dResultadoDil = ?,     "
					+ "     dDensidad = ?,         "
					+ "     iCveCtrolCalibra = ?,   "
					+ "     dTmpRetenc = ?,        "
					+ "     dIon01 = ?,            "
					+ "     dIon02 = ?,            "
					+ "     dIon03 = ?,            "
					+ "     dTmpRetencD = ?,       "
					+ "     dIon04 = ?,            "
					+ "     dIon05 = ?,            "
					+ "     lRegistrado = ?        "
					+ " where iAnio = ?            "
					+ "   and iCveLaboratorio = ? "
					+ "   and iCveSustancia = ?    "
					+ "   and iCveLoteCuantita = ? "
					+ "   and iCveAnalisis = ?    " + "";

			// System.out.println("* * * * * * * * * * * * * * * * * * * Actualizando informacion * * * * * * * * * * * * * * * * * * * *");
			// System.out.println(lSQL);

			lPStmt = conn.prepareStatement(lSQL);

			if (VTOXCuantAnalisis.getdResultadoDil() == null)
				lPStmt.setNull(1, Types.DOUBLE);
			else
				lPStmt.setDouble(1, VTOXCuantAnalisis.getdResultadoDil()
						.doubleValue());

			if (VTOXCuantAnalisis.getdResultado() == null)
				lPStmt.setNull(2, Types.DOUBLE);
			else
				lPStmt.setDouble(2, VTOXCuantAnalisis.getdResultado()
						.doubleValue());

			if (VTOXCuantAnalisis.getiCveCtrolCalibra() == null)
				lPStmt.setNull(3, Types.INTEGER);
			else
				lPStmt.setInt(3, VTOXCuantAnalisis.getiCveCtrolCalibra()
						.intValue());

			lPStmt.setDouble(4, VTOXCuantAnalisis.getDTmpRetenc());
			lPStmt.setDouble(5, VTOXCuantAnalisis.getDIon01());
			lPStmt.setDouble(6, VTOXCuantAnalisis.getDIon02());
			lPStmt.setDouble(7, VTOXCuantAnalisis.getDIon03());
			lPStmt.setDouble(8, VTOXCuantAnalisis.getDTmpRetencD());
			lPStmt.setDouble(9, VTOXCuantAnalisis.getDIon04());
			lPStmt.setDouble(10, VTOXCuantAnalisis.getDIon05());

			if (VTOXCuantAnalisis.getLRegistrado() == null)
				lPStmt.setNull(11, Types.INTEGER);
			else
				lPStmt.setInt(11, VTOXCuantAnalisis.getLRegistrado().intValue());

			lPStmt.setInt(12, VTOXCuantAnalisis.getiAnio().intValue());
			lPStmt.setInt(13, VTOXCuantAnalisis.getiCveLaboratorio().intValue());
			lPStmt.setInt(14, VTOXCuantAnalisis.getiCveSustancia().intValue());
			lPStmt.setInt(15, VTOXCuantAnalisis.getiCveLoteCuantita()
					.intValue());
			lPStmt.setInt(16, VTOXCuantAnalisis.getiCveAnalisis().intValue());

			// System.out.println(lPStmt);
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
			throw new DAOException("Error en DAO Analisis Cuantitativo");
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
		TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXCuantAnalisis    "
					+ " set dResultadoDil = ?," + // 1
					"     dDensidad     = ?," + // 2
					"     lResultado    = ?," + // 3
					"     dTmpRetenc    = ?," + // 4
					"     dIon01        = ?," + // 5
					"     dIon02        = ?," + // 6
					"     dIon03        = ?," + // 7
					"     dTmpRetencD   = ?," + // 8
					"     dIon04        = ?," + // 9
					"     dIon05        = ?," + // 10
					"     lRegistrado   = ?," + // 11
					"     lCorrecto     = ? " + // 12
					" where iAnio            = ? " + // 13
					"   and iCveLaboratorio  = ? " + // 14
					"   and iCveSustancia    = ? " + // 15
					"   and iCveLoteCuantita = ? " + // 16
					"   and iCveAnalisis     = ? " + // 17
					"";

			StringBuffer cSQL = new StringBuffer();
			cSQL.append("update TOXCuantAnalisis set dResultadoDil = ")
					.append(VTOXCuantAnalisis.getdResultadoDil().doubleValue())
					.append(" dDensidad = ")
					.append(VTOXCuantAnalisis.getdResultado().doubleValue())
					.append(" lResultado = ")
					.append(VTOXCuantAnalisis.getlResultado().intValue())
					.append(" dTmpRetenc = ")
					.append(VTOXCuantAnalisis.getDTmpRetenc())
					.append(" dIon01 = ").append(VTOXCuantAnalisis.getDIon01())
					.append(" dIon02 = ").append(VTOXCuantAnalisis.getDIon02())
					.append(" dIon03 = ").append(VTOXCuantAnalisis.getDIon03())
					.append(" dTmpRetencD = ")
					.append(VTOXCuantAnalisis.getDTmpRetencD())
					.append(" dIon04 = ").append(VTOXCuantAnalisis.getDIon04())
					.append(" dIon05 = ").append(VTOXCuantAnalisis.getDIon05())
					.append(" lRegistrado = ")
					.append(VTOXCuantAnalisis.getLRegistrado())
					.append(" lCorrecto = ")
					.append(VTOXCuantAnalisis.getLCorrecto())
					.append(" where iAnio = ")
					.append(VTOXCuantAnalisis.getiAnio())
					.append("   and iCveSustancia = ")
					.append(VTOXCuantAnalisis.getiCveSustancia())
					.append("   and iCveLoteCuantita = ")
					.append(VTOXCuantAnalisis.getiCveLoteCuantita())
					.append("   and iCveAnalisis     = ")
					.append(VTOXCuantAnalisis.getiCveAnalisis());
			// System.out.println("Actualizacion = " + cSQL);

			lPStmt = conn.prepareStatement(lSQL);

			if (VTOXCuantAnalisis.getdResultadoDil() == null)
				lPStmt.setNull(1, Types.DOUBLE);
			else
				lPStmt.setDouble(1, VTOXCuantAnalisis.getdResultadoDil()
						.doubleValue());

			if (VTOXCuantAnalisis.getdResultado() == null)
				lPStmt.setNull(2, Types.DOUBLE);
			else
				lPStmt.setDouble(2, VTOXCuantAnalisis.getdResultado()
						.doubleValue());
			if (VTOXCuantAnalisis.getlResultado() == null)
				lPStmt.setNull(3, Types.INTEGER);
			else
				lPStmt.setInt(3, VTOXCuantAnalisis.getlResultado().intValue());
			lPStmt.setDouble(4, VTOXCuantAnalisis.getDTmpRetenc());
			lPStmt.setDouble(5, VTOXCuantAnalisis.getDIon01());
			lPStmt.setDouble(6, VTOXCuantAnalisis.getDIon02());
			lPStmt.setDouble(7, VTOXCuantAnalisis.getDIon03());
			lPStmt.setDouble(8, VTOXCuantAnalisis.getDTmpRetencD());
			lPStmt.setDouble(9, VTOXCuantAnalisis.getDIon04());
			lPStmt.setDouble(10, VTOXCuantAnalisis.getDIon05());
			lPStmt.setInt(11, VTOXCuantAnalisis.getLRegistrado().intValue());

			if (VTOXCuantAnalisis.getLCorrecto() == null)
				lPStmt.setNull(12, Types.INTEGER);
			else
				lPStmt.setInt(12, VTOXCuantAnalisis.getLCorrecto().intValue());

			lPStmt.setInt(13, VTOXCuantAnalisis.getiAnio().intValue());
			lPStmt.setInt(14, VTOXCuantAnalisis.getiCveLaboratorio().intValue());
			lPStmt.setInt(15, VTOXCuantAnalisis.getiCveSustancia().intValue());
			lPStmt.setInt(16, VTOXCuantAnalisis.getiCveLoteCuantita()
					.intValue());
			lPStmt.setInt(17, VTOXCuantAnalisis.getiCveAnalisis().intValue());

			int Contador = lPStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
				// warn("No se efectu� el RollBack: Update", ex1);
			}
			// warn("No se efectu� la Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Analisis Cuantitativo");
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
		TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = " update TOXCuantAnalisis    "
					+ " set iDilusion = ? ,        "
					+ "     lAutorizado = ? ,      "
					+ "     cObservacion = ?       "
					+ " where iAnio = ?            "
					+ "   and iCveSustancia = ?    "
					+ "   and iCveLoteCuantita = ? "
					+ "   and iCveAnalisis = ?     "
					+ "   and iCveLaboratorio = ?  " + "";

			lPStmt = conn.prepareStatement(lSQL);

			if (VTOXCuantAnalisis.getiDilusion() == null)
				lPStmt.setNull(1, Types.INTEGER);
			else
				lPStmt.setInt(1, VTOXCuantAnalisis.getiDilusion().intValue());

			if (VTOXCuantAnalisis.getlAutorizado() == null)
				lPStmt.setNull(2, Types.INTEGER);
			else
				lPStmt.setInt(2, VTOXCuantAnalisis.getlAutorizado().intValue());

			if (VTOXCuantAnalisis.getcObservacion() == null)
				lPStmt.setNull(3, Types.VARCHAR);
			else
				lPStmt.setString(3, VTOXCuantAnalisis.getcObservacion()
						.toString());

			lPStmt.setInt(4, VTOXCuantAnalisis.getiAnio().intValue());
			lPStmt.setInt(5, VTOXCuantAnalisis.getiCveSustancia().intValue());
			lPStmt.setInt(6, VTOXCuantAnalisis.getiCveLoteCuantita().intValue());
			lPStmt.setInt(7, VTOXCuantAnalisis.getiCveAnalisis().intValue());
			lPStmt.setInt(8, VTOXCuantAnalisis.getiCveLaboratorio().intValue());

			if (VTOXCuantAnalisis.getlAutorizado().intValue() == 1) {
				TDTOXAnalisis dTOXAnalisis = new TDTOXAnalisis();
				TVTOXAnalisis vTOXAnalisis = new TVTOXAnalisis();
				Vector vAnalisis = new Vector();
				int iSustConf = 0;
				int lResultado = 0;
				String cFiltro = " WHERE iAnio = "
						+ VTOXCuantAnalisis.getiAnio()
						+ "   AND iCveLaboratorio = "
						+ VTOXCuantAnalisis.getiCveLaboratorio()
						+ "   AND iCveAnalisis    = "
						+ VTOXCuantAnalisis.getiCveAnalisis();
				vAnalisis = dTOXAnalisis.FindByAll(cFiltro);
				if (vAnalisis.size() > 0) {
					vTOXAnalisis = (TVTOXAnalisis) vAnalisis.get(0);
					iSustConf = vTOXAnalisis.getISustConf().intValue() + 1;
					lResultado = vTOXAnalisis.getlResultado().intValue();
					if (lResultado != 1)
						lResultado = VTOXCuantAnalisis.getlResultado()
								.intValue();
					vTOXAnalisis.setlResultado(new Integer(lResultado));
					vTOXAnalisis.setISustConf(new Integer(iSustConf));
					dTOXAnalisis.updatelResultado(null, vTOXAnalisis);

				}
			}

			int Contador = lPStmt.executeUpdate();

			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
			}
			warn("No se efectu� la Update", ex);
			ex.printStackTrace();
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

	public Vector FindReporte(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector VResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXCuantAnalisis vTOXCuantAnalisis;
			TVTOXLoteCuantita vLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append("select CA.iPosicion, CA.icveAnalisis, CA.lControl, ")
					// 1,2,3
					.append("       CA.iDilusion, CA.dConcentracion, CA.dDensidad, CA.dResultadoDil,")
					// 4,5,6,7
					.append("       LC.iAnio, LC.iCveLaboratorio, LC.iCveSustancia,")
					// 8,9,10
					.append("       LC.iCveLoteCuantita, LC.dtGeneracion, ")
					// 11,12
					.append("       S.cTitRepConf, S.cPrefLoteConf, ")
					// 13,14
					.append("       E.cDscEquipo, E.cNumSerie, E.cModelo,   ")
					// 15,16,17
					.append("       CA.iCveCtrolCalibra, CA.lResultado, CA.lAutorizado, ")
					// 18, 19, 20
					.append("       CA.cObservacion, ")
					// 21
					.append("       CC.cDscCalib, CC.dPorcentaje, ")
					// 22, 23
					.append("       A.iSustPost, LC.dtAnalisis, LC.dtCalibracion, LC.dtAutorizacion, ")
					// 24, 25, 26, 27
					.append("       CA.lCorrecto, E.cCveEquipo ")
					// 28, 29
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXLoteCuantita LC   on LC.iAnio            = CA.iAnio ")
					.append("                                and LC.iCveLaboratorio  = CA.iCveLaboratorio ")
					.append("                                and LC.iCveSustancia    = CA.iCveSustancia ")
					.append("                                and LC.iCveLoteCuantita = CA.iCveLoteCuantita ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = LC.iCveSustancia ")
					.append(" inner join EQMEquipo    E on E.iCveEquipo    = LC.iCveEquipo ")
					.append(" left  join TOXAnalisis  A on A.iAnio = CA.iAnio ")
					.append("                          and A.iCveLaboratorio  = CA.iCveLaboratorio ")
					.append("                          and A.iCveAnalisis     = CA.iCveAnalisis ")
					.append(" left  join TOXCalibCuantita CC on CC.iCveLaboratorio = LC.iCveLaboratorio ")
					.append("                               and CC.iCveCalib       = LC.iCveCalib ")
					// se agrego el siguiente campo por el problema de
					// porcentajes
					.append("                               and CC.iCveSustancia       = CA.iCveSustancia ")

					.append("                               and CC.iPosicion       = CA.iCveAnalisis  ")
					.append(cvFiltro)
					.append(" order by CA.iAnio, CA.iCveLaboratorio, CA.iCveSustancia, CA.iCveLoteCuantita, CA.iPosicion ");
			// System.out.println("Busqueda reporte = " + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			int i = 0;
			while (rset.next()) {
				// Informaci�n del Lote
				if (i == 0) {
					vLote = new TVTOXLoteCuantita();
					vLote.VSustancia = new TVSustancia();
					vLote.VEquipo = new TVEQMEquipo();
					vLote.setiAnio(new Integer(rset.getInt(8)));
					vLote.setdtGeneracion(rset.getDate(12));
					vLote.setdtAnalisis(rset.getDate(25));
					vLote.setdtCalibracion(rset.getDate(26));
					vLote.setdtAutorizacion(rset.getDate(27));
					vLote.VSustancia.setCTitRepConf(rset.getString(13));
					vLote.VSustancia.setCPrefLoteConf(rset.getString(14));
					vLote.setiCveLoteCuantita(new Integer(rset.getInt(11)));
					vLote.VEquipo.setCDscEquipo(rset.getString(15));
					vLote.VEquipo.setCNumSerie(rset.getString(16));
					vLote.VEquipo.setCModelo(rset.getString(17));
					vLote.VEquipo.setCCveEquipo(rset.getString(29));
					VResultado.addElement(vLote);
					i = 1;
				}
				// Informaci�n del An�lisis
				vTOXCuantAnalisis = new TVTOXCuantAnalisis();
				vTOXCuantAnalisis.setiAnio(new Integer(rset.getInt(8)));
				vTOXCuantAnalisis
						.setiCveSustancia(new Integer(rset.getInt(10)));
				vTOXCuantAnalisis.setiCveLoteCuantita(new Integer(rset
						.getInt(11)));
				vTOXCuantAnalisis.setiCveAnalisis(new Integer(rset.getInt(2)));
				vTOXCuantAnalisis
						.setiCveLaboratorio(new Integer(rset.getInt(9)));
				vTOXCuantAnalisis.setiCveCtrolCalibra(new Integer(rset
						.getInt(18)));
				vTOXCuantAnalisis.setlControl(new Integer(rset.getInt(3)));
				vTOXCuantAnalisis.setiPosicion(new Integer(rset.getInt(1)));
				vTOXCuantAnalisis.setiDilusion(new Integer(rset.getInt(4)));
				vTOXCuantAnalisis.setlResultado(new Integer(rset.getInt(19)));
				vTOXCuantAnalisis.setdResultado(new Double(rset.getDouble(6)));
				vTOXCuantAnalisis
						.setdResultadoDil(new Double(rset.getDouble(7)));
				vTOXCuantAnalisis.setlAutorizado(new Integer(rset.getInt(20)));
				vTOXCuantAnalisis.setdConcentracion(new Double(rset
						.getDouble(5)));
				vTOXCuantAnalisis.setcObservacion(rset.getString(21));
				vTOXCuantAnalisis.setCDscCalib(rset.getString(22));
				vTOXCuantAnalisis.setPorcentaje(new Double(rset.getDouble(23)));
				vTOXCuantAnalisis
						.setiCveSustancia(new Integer(rset.getInt(24)));
				vTOXCuantAnalisis.setLCorrecto(new Integer(rset.getInt(28)));
				VResultado.addElement(vTOXCuantAnalisis);
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

	public Vector FindCalibra(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCuantAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXCuantAnalisis vTOXCuantAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select C.cDscCalib, C.dPorcentaje, C.lControl, ")
					// 1,2,3
					.append("        A.iCveCtrolCalibra, (CS.dCorte * C.dPorcentaje) AS Concentracion, ")
					// 4,5
					.append("        L.lValidaCalib, L.iCveLaboratorio, L.iCveSustancia, ")
					// 6, 7, 8
					.append("        CS.dCorte, A.iCveAnalisis, CS.dMargenError, ")
					// 9, 10, 11
					.append("        A.iAnio, A.iCveLoteCuantita, A.iDilusion, A.dDensidad,  L.dtCalibracion, ")
					// 12, 13, 14, 15, 16
					.append("        A.dTmpRetenc, A.dIon01, A.dIon02, A.dIon03, ")
					// 17, 18, 19, 20
					.append("        A.dTmpRetencD, A.dIon04, A.dIon05, A.lCorrecto, A.lRegistrado, S.cSustUnifica ")
					// 21, 22, 23, 24, 25, 26
					.append(" from TOXCalibCuantita C ")
					.append(" inner join TOXLoteCuantita L on L.iCveLaboratorio = C.iCveLaboratorio  ")
					.append("                             and L.iCveCalib       = C.iCveCalib ")
					// Se agrego la siguiente linea por el problema con los % de
					// los calibradores y controles para canabis
					// AG.A.S. 14 de Abril 2010
					.append("                             and L.iCveSustancia       = C.iCveSustancia ")
					.append(" inner join TOXCuantAnalisis A on A.iAnio            = L.iAnio ")
					.append("                              and A.iCveLaboratorio  = L.iCveLaboratorio ")
					.append("                              and A.iCveSustancia    = L.iCveSustancia ")
					.append("                              and A.iCveLoteCuantita = L.iCveLoteCuantita ")
					.append("                              and A.icveAnalisis     = C.iPosicion ")
					.append(" inner join TOXCorteXSust CS  on CS.iCveSustancia = L.iCveSustancia ")
					.append("                             and CS.iCveCorte     = L.iCveCorte ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = L.iCveSustancia ")
					.append(cvFiltro).append(" order by C.iPosicion ");
			// System.out.println("**************************************************************************");
			// System.out.println("Controles = " + cSQL);
			// System.out.println("**************************************************************************");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCuantAnalisis = new TVTOXCuantAnalisis();
				vTOXCuantAnalisis.setCDscCalib(rset.getString(1));
				vTOXCuantAnalisis.setPorcentaje(new Double(rset.getDouble(2)));
				vTOXCuantAnalisis.setlControl(new Integer(rset.getInt(3)));
				vTOXCuantAnalisis.setiCveCtrolCalibra(new Integer(rset
						.getInt(4)));
				vTOXCuantAnalisis.setdConcentracion(new Double(rset
						.getDouble(5)));
				if (rset.getDate(16) != null)
					vTOXCuantAnalisis
							.setlAutorizado(new Integer(rset.getInt(6)));
				else
					vTOXCuantAnalisis.setlAutorizado(null);
				vTOXCuantAnalisis.setiAnio(new Integer(rset.getInt(12)));
				vTOXCuantAnalisis
						.setiCveLaboratorio(new Integer(rset.getInt(7)));
				vTOXCuantAnalisis.setiCveSustancia(new Integer(rset.getInt(8)));
				vTOXCuantAnalisis.setiCveAnalisis(new Integer(rset.getInt(10)));
				vTOXCuantAnalisis
						.setDMargenError(new Double(rset.getDouble(11)));
				vTOXCuantAnalisis.setiCveLoteCuantita(new Integer(rset
						.getInt(13)));
				vTOXCuantAnalisis.setiDilusion(new Integer(rset.getInt(14)));
				vTOXCuantAnalisis.setdResultado(new Double(rset.getDouble(15)));
				vTOXCuantAnalisis.setDTmpRetenc(rset.getDouble(17));
				vTOXCuantAnalisis.setDIon01(rset.getDouble(18));
				vTOXCuantAnalisis.setDIon02(rset.getDouble(19));
				vTOXCuantAnalisis.setDIon03(rset.getDouble(20));
				vTOXCuantAnalisis.setDTmpRetencD(rset.getDouble(21));
				vTOXCuantAnalisis.setDIon04(rset.getDouble(22));
				vTOXCuantAnalisis.setDIon05(rset.getDouble(23));
				vTOXCuantAnalisis.setLCorrecto(new Integer(rset.getInt(24)));
				vTOXCuantAnalisis.setLRegistrado(new Integer(rset.getInt(25)));
				vTOXCuantAnalisis.setcObservacion(rset.getString(26));
				vcTOXCuantAnalisis.addElement(vTOXCuantAnalisis);
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
			return vcTOXCuantAnalisis;
		}
	}

	public Vector FindValCalibra(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXLoteCalibra VLoteCalibra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select C.cDscCalib, C.dPorcentaje, C.lControl, ")
					.append("        A.iCveCtrolCalibra, (CS.dCorte * C.dPorcentaje) AS Concentracion,  ")
					.append("        L.lValidaCalib, L.iCveLaboratorio, L.iCveSustancia,  ")
					.append("        CS.dCorte, A.iCveAnalisis, CS.dMargenError,  ")
					.append("        A.iAnio, A.iCveLoteCuantita, A.iDilusion, A.dDensidad,  ")
					.append("        CC.cLote, ")
					.append("        (A.dConcentracion - (A.dConcentracion * (CS.dMargenError / 100))) AS LimInf,")
					.append("        A.dConcentracion, ")
					.append("        (A.dConcentracion + (A.dConcentracion * (CS.dMargenError / 100))) AS LimSup, ")
					.append("        A.dTmpRetenc, A.dIon01, A.dIon02, A.dIon03, A.dTmpRetencD, A.dIon04, A.dIon05, ")
					.append("        A.iCveAnalisis, A.dDensidad  ")
					.append("  from TOXCalibCuantita C ")
					.append("  inner join TOXLoteCuantita L on L.iCveLaboratorio = C.iCveLaboratorio  ")
					.append("                              and L.iCveCalib       = C.iCveCalib ")
					.append("  inner join TOXCuantAnalisis A on A.iAnio            = L.iAnio ")
					.append("                               and A.iCveLaboratorio  = L.iCveLaboratorio ")
					.append("                               and A.iCveSustancia    = L.iCveSustancia ")
					.append("                               and A.iCveLoteCuantita = L.iCveLoteCuantita ")
					.append("                               and A.icveAnalisis     = C.iPosicion ")
					.append("  inner join TOXCorteXSust CS  on CS.iCveSustancia = L.iCveSustancia ")
					.append("                              and CS.iCveCorte     = L.iCveCorte ")
					.append("  inner join TOXCtrolCalibra CC on CC.iCveLaboratorio    = A.iCveLaboratorio ")
					.append("                               and CC.iCveCtrolCalibra = A.iCveCtrolCalibra ")
					.append(cvFiltro).append(" order by C.iPosicion ");
			// System.out.println("Query= " + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VLoteCalibra = new TVTOXLoteCalibra();
				VLoteCalibra.VAnalisis.setiCveAnalisis(new Integer(rset
						.getInt(10)));
				VLoteCalibra.VAnalisis.setdConcentracion(new Double(rset
						.getDouble(5)));
				VLoteCalibra.VAnalisis.setdResultado(new Double(rset
						.getDouble(15)));

				VLoteCalibra.VAnalisis.setCDscCalib(rset.getString(1));
				VLoteCalibra.VAnalisis.setPorcentaje(new Double(rset
						.getDouble(2)));
				VLoteCalibra.VAnalisis.setdResultadoDil(new Double(rset
						.getDouble(9))); // Corte para la Sustancia
				VLoteCalibra.VAnalisis.setDMargenError(new Double(rset
						.getDouble(11)));

				VLoteCalibra.VCtrol.setCLote(rset.getString(16));
				VLoteCalibra.VCtrol.setDConcentracion(rset.getFloat(18));
				VLoteCalibra.VAnalisis.setlControl(new Integer(rset.getInt(3)));
				VLoteCalibra.VLote.setlValidaCalib(new Integer(rset.getInt(6)));
				VLoteCalibra.VAnalisis.setiAnio(new Integer(rset.getInt(12)));
				VLoteCalibra.VLote.setiAnio(new Integer(rset.getInt(12)));
				// System.out.println("Ano " + VLoteCalibra.VLote.getiAnio());
				VLoteCalibra.VAnalisis.setiCveLaboratorio(new Integer(rset
						.getInt(7)));
				VLoteCalibra.VLote.setiCveLaboratorio(new Integer(rset
						.getInt(7)));
				VLoteCalibra.VAnalisis.setiCveSustancia(new Integer(rset
						.getInt(8)));
				VLoteCalibra.VLote
						.setiCveSustancia(new Integer(rset.getInt(8)));
				VLoteCalibra.VAnalisis.setiCveLoteCuantita(new Integer(rset
						.getInt(13)));
				VLoteCalibra.VLote.setiCveLoteCuantita(new Integer(rset
						.getInt(13)));
				VLoteCalibra.VAnalisis.setiCveAnalisis(new Integer(rset
						.getInt(27)));

				VLoteCalibra.setDLimInferior(rset.getDouble(17));
				VLoteCalibra.setDLimSuperior(rset.getDouble(19));

				VLoteCalibra.VAnalisis.setDTmpRetenc(rset.getDouble(20));
				VLoteCalibra.VAnalisis.setDIon01(rset.getDouble(21));
				VLoteCalibra.VAnalisis.setDIon02(rset.getDouble(22));
				VLoteCalibra.VAnalisis.setDIon03(rset.getDouble(23));
				VLoteCalibra.VAnalisis.setDTmpRetencD(rset.getDouble(24));
				VLoteCalibra.VAnalisis.setDIon04(rset.getDouble(25));
				VLoteCalibra.VAnalisis.setDIon05(rset.getDouble(26));
				vResultado.addElement(VLoteCalibra);
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
			return vResultado;
		}
	}

	public Vector FindcLote(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXcLote = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXcLote vTOXcLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "SELECT TOXCA.iCveAnalisis,TOXCC.cLote "
					+ "FROM TOXCuantAnalisis TOXCA,TOXCtrolCalibra TOXCC  "
					+ cvFiltro + " ";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXcLote = new TVTOXcLote();
				vTOXcLote.setiCveAnalisis(new Integer(rset.getInt(1)));
				vTOXcLote.setcLote(rset.getString(2));
				vcTOXcLote.addElement(vTOXcLote);
			}
		} catch (Exception ex) {
			warn("FindcLote", ex);
			throw new DAOException("FindcLote");
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
				warn("FindcLote.close", ex2);
			}
			return vcTOXcLote;
		}
	}

	/**
	 * Consulta del resultado del an�lisis confirmatorio.
	 * 
	 * @param cvFiltro
	 *            informaci�n del lote que se consulta.
	 * @return Vector de objetos TVTOXAnalsisCuant que contiene la informaci�n
	 *         de cada uno de los an�lisis del lote.
	 * @throws DAOException
	 */
	public Vector FindResult(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCuantAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXAnalisisCuant vAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select CA.iAnio, ")
					// 1
					.append("        CA.iCveAnalisis, M.iCveMuestra, ")
					// 2, 3
					.append("        U.cDscUniMed, MD.cDscModulo, M.iCveEnvio, ")
					// 4, 5, 6
					.append("        CA.lAutorizado, CA.dDensidad, CA.lResultado, ")
					// 7, 8, 9
					.append("        A.iSustPost, A.iSustConf, A.iCveLoteCualita, ")
					// 10, 11, 12
					.append("        CA.iCveLaboratorio, CA.iCveSustancia, CA.cObservacion ")
					// 13, 14, 15
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXAnalisis A on A.iAnio           = CA.iAnio ")
					.append("                         and A.iCveLaboratorio = CA.iCveLaboratorio ")
					.append("                         and A.iCveAnalisis    = CA.iCveAnalisis ")
					.append(" inner join TOXMuestra  M on M.iAnio        = A.iAnio ")
					.append("                         and M.iCveMuestra  = A.iCveMuestra ")
					.append(" inner join GRLUniMed   U on U.iCveUniMed   = M.iCveUniMed ")
					.append(" inner join GRLModulo   MD on MD.iCveUniMed   = M.iCveUniMed ")
					.append("                          and MD.iCveModulo   = M.iCveModulo ")
					.append(cvFiltro);
			// System.out.println("Informaci�n del Lote Confirmatorio");
			// System.out.println(cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vAnalisis = new TVTOXAnalisisCuant();
				vAnalisis.VCAnal.setiAnio(new Integer(rset.getInt(1)));
				vAnalisis.VMuestra.setIAnio(rset.getInt(1));
				vAnalisis.VCAnal.setiCveAnalisis(new Integer(rset.getInt(2)));
				vAnalisis.VMuestra.setICveAnalisis(rset.getInt(2));
				vAnalisis.VMuestra.setICveMuestra(rset.getInt(3));
				vAnalisis.VMuestra.setCDscUM(rset.getString(4)); // Unidad
				vAnalisis.VMuestra.setCDscModulo(rset.getString(5)); // M�dulo
				vAnalisis.VMuestra.setICveEnvio(rset.getInt(6));
				vAnalisis.VCAnal.setlAutorizado(new Integer(rset.getInt(7)));
				vAnalisis.VCAnal.setdResultado(new Double(rset.getDouble(8)));
				vAnalisis.VCAnal.setlResultado(new Integer(rset.getInt(9)));
				vAnalisis.VAnalisis.setISustPost(new Integer(rset.getInt(10)));
				vAnalisis.VAnalisis.setISustConf(new Integer(rset.getInt(11)));
				vAnalisis.VAnalisis.setiCveLoteCualita(new Integer(rset
						.getInt(12)));
				vAnalisis.VCAnal
						.setiCveLaboratorio(new Integer(rset.getInt(13)));
				vAnalisis.VCAnal.setiCveSustancia(new Integer(rset.getInt(14)));
				vAnalisis.VCAnal.setcObservacion(rset.getString(15));
				vcTOXCuantAnalisis.addElement(vAnalisis);

			}
		} catch (Exception ex) {
			warn("FindResult", ex);
			throw new DAOException("FindResult");
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
				warn("FindResult.close", ex2);
			}
			return vcTOXCuantAnalisis;
		}
	}

	/**
	 * Método utilizado para obtener los valores del Calibrador del lote, con
	 * los cuales se realizar� la validaci�n de la calibraci�n y las muestras.
	 * 
	 * @param cvFiltro
	 * @return
	 * @throws DAOException
	 */
	public Vector FindCalibrador(String cvFiltro) throws DAOException {
		DecimalFormat Formato = new DecimalFormat("#.00");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vResultado = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXLoteCalibra VLoteCalibra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(
					" select CA.iAnio, CA.iCveLaboratorio, CA.iCveSustancia, CA.iCveLoteCuantita, ")
					.append("       (CS.dCorte - (CS.dCorte * CS.dMargConcCal/100 )) AS ConcLimInf, ")
					.append("        CA.dDensidad, ")
					.append("       (CS.dCorte + (CS.dCorte * CS.dMargConcCal/100 )) AS ConcLimSup, ")
					.append("       (CA.dTmpRetenc - (CA.dTmpRetenc * CS.dMargTmpRet/100 )) AS TmpRetLimInf, ")
					.append("        CA.dTmpRetenc, ")
					.append("       (CA.dTmpRetenc + (CA.dTmpRetenc * CS.dMargTmpRet/100 )) AS TmpRetLimSup, ")
					.append("        CA.dIon01, ")
					.append("       (CA.dIon02 - (CA.dIon02 * CS.dMargRelacIon/100 )) AS Ion2Inf, ")
					.append("        CA.dIon02, ")
					.append("       (CA.dIon02 + (CA.dIon02 * CS.dMargRelacIon/100 )) AS Ion2Inf, ")
					.append("       (CA.dIon03 - (CA.dIon03 * CS.dMargRelacIon/100 )) AS Ion3Inf, ")
					.append("        CA.dIon03, ")
					.append("       (CA.dIon03 + (CA.dIon03 * CS.dMargRelacIon/100 )) AS Ion3Inf, ")
					.append("       (CA.dTmpRetencD - (CA.dTmpRetencD * CS.dMargTmpRet/100 )) AS TmpRetDLimInf, ")
					.append("        CA.dTmpRetencD, ")
					.append("       (CA.dTmpRetencD + (CA.dTmpRetencD * CS.dMargTmpRet/100 )) AS TmpRetDLimSup, ")
					.append("        CA.dIon04, ")
					.append("       (CA.dIon05 - (CA.dIon05 * CS.dMargRelacIon/100 )) AS Ion5Inf, ")
					.append("        CA.dIon05, ")
					.append("       (CA.dIon05 + (CA.dIon05 * CS.dMargRelacIon/100 )) AS Ion5Inf, ")
					.append("        CS.dCorte, CA.lRegistrado, CA.lCorrecto, S.lValConcentracion ")
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXLoteCuantita LC on LC.iAnio            = CA.iAnio ")
					.append("                              and LC.iCveLaboratorio  = CA.iCveLaboratorio ")
					.append("                              and LC.iCveSustancia    = CA.iCveSustancia  ")
					.append("                              and LC.iCveLoteCuantita = CA.iCveLoteCuantita  ")
					.append(" inner join TOXCorteXSust CS on CS.iCveSustancia = LC.iCveSustancia ")
					.append("                            and CS.iCveCorte     = LC.iCveCorte ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = LC.iCveSustancia ")
					.append(cvFiltro);
			// System.out.println("Query Calibrador = " + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VLoteCalibra = new TVTOXLoteCalibra();
				VLoteCalibra.VAnalisis.setiAnio(new Integer(rset.getInt(1)));
				VLoteCalibra.VAnalisis.setiCveLaboratorio(new Integer(rset
						.getInt(2)));
				VLoteCalibra.VLote
						.setiCveSustancia(new Integer(rset.getInt(3)));
				VLoteCalibra.VAnalisis.setiCveLoteCuantita(new Integer(rset
						.getInt(4)));
				VLoteCalibra.setDLimInferior(Double.valueOf(
						Formato.format(rset.getDouble(5))).doubleValue());
				VLoteCalibra.VAnalisis.setdResultado(new Double(rset
						.getDouble(6)));
				VLoteCalibra.setDLimSuperior(Double.valueOf(
						Formato.format(rset.getDouble(7))).doubleValue());
				VLoteCalibra.setDTmpRetencInf(Double.valueOf(Formato
						.format(rset.getDouble(8))));
				VLoteCalibra.VAnalisis.setDTmpRetenc(rset.getDouble(9));
				VLoteCalibra.setDTmpRetencSup(Double.valueOf(Formato
						.format(rset.getDouble(10))));
				VLoteCalibra.setDIon01(new Double(rset.getDouble(11)));
				VLoteCalibra.setDIon02Inf(Double.valueOf(Formato.format(rset
						.getDouble(12))));
				VLoteCalibra.VAnalisis.setDIon02(rset.getDouble(13));
				VLoteCalibra.setDIon02Sup(Double.valueOf(Formato.format(rset
						.getDouble(14))));
				VLoteCalibra.setDIon03Inf(Double.valueOf(Formato.format(rset
						.getDouble(15))));
				VLoteCalibra.VAnalisis.setDIon03(rset.getDouble(16));
				VLoteCalibra.setDIon03Sup(Double.valueOf(Formato.format(rset
						.getDouble(17))));
				VLoteCalibra.setDTmpRetencDInf(Double.valueOf(Formato
						.format(rset.getDouble(18))));
				VLoteCalibra.VAnalisis.setDTmpRetencD(rset.getDouble(19));
				VLoteCalibra.setDTmpRetencDSup(Double.valueOf(Formato
						.format(rset.getDouble(20))));
				VLoteCalibra.setDIon04(Double.valueOf(Formato.format(rset
						.getDouble(21))));
				VLoteCalibra.setDIon05Inf(Double.valueOf(Formato.format(rset
						.getDouble(22))));
				VLoteCalibra.VAnalisis.setDIon05(rset.getDouble(23));
				VLoteCalibra.setDIon05Sup(Double.valueOf(Formato.format(rset
						.getDouble(24))));
				VLoteCalibra.VLote.setdCorte(Double.valueOf(Formato.format(rset
						.getDouble(25))));
				VLoteCalibra.VAnalisis.setLRegistrado(new Integer(rset
						.getInt(26)));
				VLoteCalibra.VAnalisis
						.setLCorrecto(new Integer(rset.getInt(27)));
				VLoteCalibra.setLValConcentracion(rset.getInt(28));
				vResultado.addElement(VLoteCalibra);
			}
		} catch (Exception ex) {
			warn("FindCalibrador", ex);
			throw new DAOException("FindCalibrador");
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
				warn("FindCalibrador.close", ex2);
			}
			return vResultado;
		}
	}

	public int ValidaMuestra(TVTOXLoteCalibra Calibrador,
			TVTOXCuantAnalisis Muestra) throws DAOException {
		int lValida = 1;
		boolean lValidaMuestra = true;
		try {
			// System.out.println("*********  Muestra = " +
			// Muestra.getiCveAnalisis() );
			// Validaci�n del Est�ndar Interno.
			// Tiempo de retenci�n
			// System.out.println("Tiempo de Retenci�n Deuteriado  = " +
			// Muestra.getDTmpRetencD() + " < " +
			// Calibrador.getDTmpRetencDInf().doubleValue() +
			// Muestra.getDTmpRetencD() + ">" +
			// Calibrador.getDTmpRetencDSup().doubleValue());
			if (Muestra.getDTmpRetencD() < Calibrador.getDTmpRetencDInf()
					.doubleValue()
					|| Muestra.getDTmpRetencD() > Calibrador
							.getDTmpRetencDSup().doubleValue())
				lValida = 0;
			// System.out.println("Ion 05 Deuteriado  = " + Muestra.getDIon05()
			// + " < " + Calibrador.getDIon05Inf().doubleValue() +
			// Muestra.getDIon05() + ">" +
			// Calibrador.getDIon05Sup().doubleValue());

			if (Muestra.getDIon05() < Calibrador.getDIon05Inf().doubleValue()
					|| Muestra.getDIon05() > Calibrador.getDIon05Sup()
							.doubleValue())
				lValida = 0;

			// System.out.println("Validaci�n " + Muestra.getiCveAnalisis() +
			// "del deuteriado=" + lValida );
			// Validaci�n de la muestra
			if (Muestra.getlControl().equals(new Integer(0)))
				if (Muestra.getdResultado().compareTo(
						Calibrador.VLote.getdCorte()) >= 0)
					lValidaMuestra = true;
				else {
					lValidaMuestra = false;
					// Incorrecta por concentraci�n
					if (Muestra.getiDilusion().intValue() > 2
							&& Calibrador.getLValConcentracion() == 1) {
						lValida = 0;
						// System.out.println("Validaci�n de la muestra cuando es negativa ="
						// + lValida );
					}
				}
			else {// No es el calibrador u otra muestra
				if (Muestra.getdConcentracion().doubleValue() == 0
						&& Muestra.getdResultado().doubleValue() == 0)
					lValidaMuestra = false;
				else
					lValidaMuestra = true;
			} // Calibradores
			// System.out.println("Concentraciones = " + Muestra.getdResultado()
			// + " - Corte " + Calibrador.VLote.getdCorte());
			// System.out.println("Se va a validar la muestra? " +
			// lValidaMuestra);
			if (lValidaMuestra) {
				if (Muestra.getdConcentracion().doubleValue() > 0
						&& (Muestra.getDTmpRetenc() < Calibrador
								.getDTmpRetencInf().doubleValue() || Muestra
								.getDTmpRetenc() > Calibrador
								.getDTmpRetencSup().doubleValue()))
					lValida = 0;
				if (Muestra.getDIon02() < Calibrador.getDIon02Inf()
						.doubleValue()
						|| Muestra.getDIon02() > Calibrador.getDIon02Sup()
								.doubleValue())
					lValida = 0;
				if (Muestra.getDIon03() < Calibrador.getDIon03Inf()
						.doubleValue()
						|| Muestra.getDIon03() > Calibrador.getDIon03Sup()
								.doubleValue())
					lValida = 0;
				// System.out.println("Validaci�n de la muestra cuando se tiene que validar ="
				// + lValida );
			}

			// System.out.println("Validaci�n Final =" + lValida );

		} catch (Exception ex) {
			warn("ValidaMuestra", ex);
			throw new DAOException("ValidaMuestra");
		} finally {
			return lValida;
		}
	}

	public boolean updCorrecto(Object obj) throws DAOException {
		TVTOXCuantAnalisis VTOXCuantAnalisis;
		Vector vRegistros = (Vector) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			for (int i = 0; i < vRegistros.size(); i++) {
				VTOXCuantAnalisis = (TVTOXCuantAnalisis) vRegistros.get(i);
				String lSQL = " update TOXCuantAnalisis    "
						+ " set lCorrecto = ? " + // 1
						" where iAnio = ?            " + // 2
						"   and iCveLaboratorio  = ? " + // 3
						"   and iCveSustancia    = ? " + // 4
						"   and iCveLoteCuantita = ? " + // 5
						"   and iCveAnalisis     = ? " + // 6
						" ";

				lPStmt = conn.prepareStatement(lSQL);

				if (VTOXCuantAnalisis.getLCorrecto() == null)
					lPStmt.setNull(1, Types.DOUBLE);
				else
					lPStmt.setInt(1, VTOXCuantAnalisis.getLCorrecto()
							.intValue());
				lPStmt.setInt(2, VTOXCuantAnalisis.getiAnio().intValue());
				lPStmt.setInt(3, VTOXCuantAnalisis.getiCveLaboratorio()
						.intValue());
				lPStmt.setInt(4, VTOXCuantAnalisis.getiCveSustancia()
						.intValue());
				lPStmt.setInt(5, VTOXCuantAnalisis.getiCveLoteCuantita()
						.intValue());
				lPStmt.setInt(6, VTOXCuantAnalisis.getiCveAnalisis().intValue());

				int Contador = lPStmt.executeUpdate();
			}
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				// warn("No se efectu� el RollBack: Update", ex1);
			}
			// warn("No se efectu� la Update", ex);
			ex.printStackTrace();
			throw new DAOException("Error en DAO Analisis Cuantitativo");
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

	public int EvaluaResultado(TVTOXLoteCalibra Calibrador,
			TVTOXCuantAnalisis Muestra) throws DAOException {
		int lResultado = 1;
		try {
			if (Muestra.getlControl().equals(new Integer(0)))
				if (Muestra.getdResultado().compareTo(
						Calibrador.VLote.getdCorte()) < 0)
					lResultado = 0;
				else
					lResultado = 1;
			else
				lResultado = 0;
		} catch (Exception ex) {
			warn("EvaluaResultado", ex);
			throw new DAOException("EvaluaResultado");
		} finally {
			return lResultado;
		}
	}

	public Vector SustPresConf(Connection connE, Object OMuestra,
			int lConfirmadas, int lAnalisMuestra) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vAnalisis = new Vector();
		StringBuffer cSQL;
		TVTOXCuantAnalisis VAnalisis;
		DbConnection dbConn = null;
		try {
			if (connE == null) {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else
				conn = connE;

			// ********************************Actualizando Informaci�n
			// **************************************************
			// String sustancias =
			// "select icvesustancia from toxcuantanalisis where icveanalisis = "+((TVTOXCuantAnalisis)
			// OMuestra).getiCveAnalisis().toString()+" and ianio = "+((TVTOXCuantAnalisis)
			// OMuestra).getiAnio().toString()+" group by icvesustancia";

			String sustancias = "select toxc.icvesustancia, toxc.icveanalisis from toxcuantanalisis as toxc, toxanalisis tox "
					+ " where toxc.icveanalisis = tox.icveanalisis and ";
			if (lAnalisMuestra == 1)
				sustancias += " tox.icvemuestra = "
						+ ((TVTOXCuantAnalisis) OMuestra).getiCveAnalisis()
								.toString();
			if (lAnalisMuestra == 0)
				sustancias += " tox.icveanalisis = "
						+ ((TVTOXCuantAnalisis) OMuestra).getiCveAnalisis()
								.toString();
			sustancias += " and toxc.ianio = "
					+ ((TVTOXCuantAnalisis) OMuestra).getiAnio().toString()
					+ " group by toxc.icvesustancia, toxc.icveanalisis ";

			pstmt = conn.prepareStatement(sustancias);
			rset = pstmt.executeQuery();
			Vector Sustancias = new Vector();
			int icveanalisis = 0;
			Vector lotemax = new Vector();
			while (rset.next()) {
				Sustancias.addElement(rset.getInt(1));
				icveanalisis = rset.getInt(2);
			}
			/*
			 * System.out.println(sustancias); //
			 * System.out.println(Sustancias.size()); //
			 * System.out.println("esperamos 5 seg..."); Thread.sleep(1000);
			 */
			String maxlotexanal = "";
			String resultado = "";
			String update = "";
			int countS = 0;
			int countL = 0;
			// int countS = 0;
			if (Sustancias.size() > 0) {
				for (int j = 0; j < Sustancias.size(); j++) {
					maxlotexanal = "select max(icvelotecuantita) "
							+ " from toxcuantanalisis where icveanalisis = "
							+ icveanalisis
							+ " and ianio = "
							+ ((TVTOXCuantAnalisis) OMuestra).getiAnio()
									.toString() + " and icvesustancia = "
							+ Sustancias.elementAt(j).toString();

					// System.out.print(maxlotexanal);
					pstmt = conn.prepareStatement(maxlotexanal);
					rset = pstmt.executeQuery();
					while (rset.next()) {
						lotemax.addElement(rset.getInt(1));
					}
				}
				/*
				 * System.out.println(lotemax.size()); //
				 * System.out.println("esperamos 5 seg..."); Thread.sleep(1000);
				 */

				for (int k = 0; k < Sustancias.size(); k++) {
					resultado = "select icvesustancia, lresultado, lcorrecto, lautorizado "
							+ " from toxcuantanalisis where icveanalisis = "
							+ icveanalisis
							+ " and ianio = "
							+ ((TVTOXCuantAnalisis) OMuestra).getiAnio()
									.toString()
							+ " and icvesustancia = "
							+ Sustancias.elementAt(k)
							+ " and icvelotecuantita = " + lotemax.elementAt(k);
					// System.out.println(resultado);
					pstmt = conn.prepareStatement(resultado);
					rset = pstmt.executeQuery();
					while (rset.next()) {
						if (rset.getInt(3) == 1 && rset.getInt(4) == 1) {
							String Sentencia = "update toxexamresult set lpositivo = "
									+ rset.getInt(2)
									+ ", lautorizado = "
									+ rset.getInt(4)
									+ "  where icvesustancia = "
									+ Sustancias.elementAt(k)
									+ " and icveanalisis = "
									+ icveanalisis
									+ " and ianio = "
									+ ((TVTOXCuantAnalisis) OMuestra)
											.getiAnio().toString();
							this.update(Sentencia);
						}

						// Actualiza valor de sustancias confiramtorias
						/*
						 * if(rset.getInt(3) == 1 && rset.getInt(4) == 1){
						 * countS++; if(countS == Sustancias.size()){
						 * System.out.print(icveanalisis
						 * +" Analisis concluido sustancias = "+countS); } }
						 */
					}

				}
				/*
				 * System.out.println(lotemax.size()); //
				 * System.out.println("esperamos 5 seg..."); Thread.sleep(1000);
				 */

			}
			// **********************************************************************************

			// Query
			cSQL = new StringBuffer();
			cSQL.append(
					" select CA.iAnio, CA.iCveLaboratorio, CA.iCveSustancia, CA.iCveLoteCuantita, CA.iCveAnalisis, ")
					.append("        CA.lResultado, CA.lAutorizado, ")
					.append("        S.cDscSustancia, S.cTitRepConf ")
					.append(" from TOXAnalisis EA ")
					.append(" inner join TOXExamResult ER on ER.iAnio = EA.iAnio ")
					.append("                            and ER.iCveLaboratorio = EA.iCveLaboratorio ")
					.append("                            and ER.iCveLoteCualita = EA.iCveLoteCualita ")
					.append("                            and ER.iCveAnalisis    = EA.iCveAnalisis ")
					.append("                            and ER.iCveExamCualita = EA.iCveExamCualita ")
					.append(" inner join TOXCuantAnalisis CA on CA.iAnio = EA.iAnio ")
					.append("                               and CA.iCveLaboratorio = EA.iCveLaboratorio ")
					.append("                               and CA.iCveSustancia   = ER.iCveSustancia ")
					.append("                               and CA.iCveAnalisis    = EA.iCveAnalisis ")
					.append(" inner join TOXLoteCuantita LC on LC.iAnio = CA.iAnio ")
					.append("                              and LC.iCveLaboratorio = CA.iCveLaboratorio ")
					.append("                              and LC.iCveSustancia   = CA.iCveSustancia ")
					.append("                              and LC.iCveLoteCuantita = CA.iCveLoteCuantita ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = CA.iCveSustancia ")
					.append(" where EA.iAnio           = ")
					.append(((TVTOXCuantAnalisis) OMuestra).getiAnio()
							.toString());
			if (lAnalisMuestra == 0)
				cSQL.append("   and EA.iCveLaboratorio = ")
						.append(((TVTOXCuantAnalisis) OMuestra)
								.getiCveLaboratorio().toString())
						.append("   and EA.iCveAnalisis    = ")
						.append(((TVTOXCuantAnalisis) OMuestra)
								.getiCveAnalisis().toString());
			if (lAnalisMuestra == 1)
				cSQL.append("   and EA.iCveMuestra    = ").append(
						((TVTOXCuantAnalisis) OMuestra).getiCveAnalisis()
								.toString());
			cSQL.append("   and ER.lPositivo       = 1 ");
			if (lConfirmadas == 0)
				cSQL.append(
						"   and ( (LC.dtCalibracion is not null and LC.lValidaCalib = 1 and LC.dtAutorizacion is null)  ")
						.append("          or LC.dtCalibracion is null ")
						.append("          or LC.dtAutorizacion is not null and CA.lAutorizado = 1 and CA.lCorrecto = 1) ");
			if (lConfirmadas == 1)
				cSQL.append(" and CA.lAutorizado = 1 and CA.lCorrecto = 1");
			// Se agrego la siguiente linea para ordenar por sustancia para no
			// replicar la impresion de la droga positiva
			// AG SA 11 de agosto 2010
			// cSQL.append(" and CA.icvelotecuantita = 48") .append(
			// ((TVTOXCuantAnalisis) OMuestra).getiCveLoteCuantita());
			cSQL.append(" order by CA.iCveSustancia, CA.iCveLoteCuantita,  CA.iAnio, CA.iCveLaboratorio,  CA.iCveAnalisis, CA.lResultado, CA.lAutorizado, S.cDscSustancia, S.cTitRepConf  asc");
			// Ejecutar el query
			pstmt = conn.prepareStatement(cSQL.toString());
			// System.out.println("***************************************");
			// System.out.println(cSQL.toString());
			// System.out.println("***************************************");
			rset = pstmt.executeQuery();
			int icvesustanciap = 0;
			int contador = 0;
			while (rset.next()) {
				VAnalisis = new TVTOXCuantAnalisis();
				// Obtener informaci�n
				VAnalisis.setiAnio(new Integer(rset.getInt(1)));
				VAnalisis.setiCveLaboratorio(new Integer(rset.getInt(2)));
				VAnalisis.setiCveSustancia(new Integer(rset.getInt(3)));
				VAnalisis.setiCveLoteCuantita(new Integer(rset.getInt(4)));
				VAnalisis.setiCveAnalisis(new Integer(rset.getInt(5)));
				VAnalisis.setlResultado(new Integer(rset.getInt(6)));
				VAnalisis.setlAutorizado(new Integer(rset.getInt(7)));
				VAnalisis.setCDscCalib(rset.getString(8));
				// Agregar los resultados
				// Se agrego la siguiente validadcion AG SA 11 de agosto
				// 2010
				if (icvesustanciap != rset.getInt(3)) {
					vAnalisis.add(VAnalisis);
				}
				icvesustanciap = rset.getInt(3);
				contador++;
			}
			// System.out.println("contador = "+contador);

		} catch (Exception ex) {
			warn("SustPresConf", ex);
			throw new DAOException("SustPresConf");
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
				warn("SustPresConf.close", ex2);
			}
			return vAnalisis;
		}
	}

	public String getOtrasSust(TVTOXCuantAnalisis VCAnal) {
		StringBuffer cTexto = new StringBuffer();
		TVTOXCuantAnalisis VAnalisis;
		Vector vSust;
		int lConfirmadas = 0, lAnalisis = 0;
		try {
			vSust = this.SustPresConf(null, VCAnal, lConfirmadas, lAnalisis);
			for (int i = 0; i < vSust.size(); i++) {
				// System.out.println("Entro en el for");
				VAnalisis = new TVTOXCuantAnalisis();
				VAnalisis = (TVTOXCuantAnalisis) vSust.get(i);
				// System.out.println("Analisis = " +
				// VAnalisis.getiCveAnalisis()+" Clave Sustancia = "+
				// VCAnal.getiCveSustancia().equals(VAnalisis.getiCveSustancia())+
				// " getCDscCalib = "+VAnalisis.getCDscCalib()
				// +" lAutorizado = "+ VAnalisis.getlAutorizado().intValue()==0
				// +" lResultado = "+VAnalisis.getlResultado().intValue()+" iCveLoteCuantita = "+VAnalisis.getiCveLoteCuantita());
				if (!VCAnal.getiCveSustancia().equals(
						VAnalisis.getiCveSustancia())) {
					if (i > 0)
						cTexto.append("<br>");
					cTexto.append(VAnalisis.getCDscCalib()).append(" - ");
					if (VAnalisis.getlAutorizado().intValue() == 0)
						cTexto.append(" PENDIENTE ");
					else {
						cTexto.append(VAnalisis.getlResultado().intValue() == 0 ? " NEGATIVO "
								: " POSITIVO ");
						cTexto.append(VAnalisis.getiAnio()).append("/")
								.append(VAnalisis.getiCveLoteCuantita());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto.toString();
	}

	public Vector SustPresConf2(Connection connE, Object OMuestra,
			int lConfirmadas, int lAnalisMuestra) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vAnalisis = new Vector();
		StringBuffer cSQL;
		TVTOXCuantAnalisis VAnalisis;
		DbConnection dbConn = null;
		try {
			if (connE == null) {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else
				conn = connE;

			// ********************************Actualizando Informaci�n
			// **************************************************
			// String sustancias =
			// "select icvesustancia from toxcuantanalisis where icveanalisis = "+((TVTOXCuantAnalisis)
			// OMuestra).getiCveAnalisis().toString()+" and ianio = "+((TVTOXCuantAnalisis)
			// OMuestra).getiAnio().toString()+" group by icvesustancia";

			String sustancias = "select toxc.icvesustancia, toxc.icveanalisis from toxcuantanalisis as toxc, toxanalisis tox "
					+ " where toxc.icveanalisis = tox.icveanalisis and "
					+ " tox.icveanalisis = "
					+ ((TVTOXCuantAnalisis) OMuestra).getiCveAnalisis()
							.toString()
					+ " and toxc.ianio = "
					+ ((TVTOXCuantAnalisis) OMuestra).getiAnio().toString()
					+ " group by toxc.icvesustancia, toxc.icveanalisis ";

			pstmt = conn.prepareStatement(sustancias);
			rset = pstmt.executeQuery();
			Vector Sustancias = new Vector();
			int icveanalisis = 0;
			Vector lotemax = new Vector();
			while (rset.next()) {
				Sustancias.addElement(rset.getInt(1));
				icveanalisis = rset.getInt(2);
			}
			/*
			 * System.out.println(sustancias); //
			 * System.out.println(Sustancias.size()); //
			 * System.out.println("esperamos 5 seg..."); Thread.sleep(1000);
			 */
			String maxlotexanal = "";
			String resultado = "";
			String update = "";
			int countS = 0;
			int countL = 0;
			// int countS = 0;
			if (Sustancias.size() > 0) {
				for (int j = 0; j < Sustancias.size(); j++) {
					maxlotexanal = "select max(icvelotecuantita) "
							+ " from toxcuantanalisis where icveanalisis = "
							+ icveanalisis
							+ " and ianio = "
							+ ((TVTOXCuantAnalisis) OMuestra).getiAnio()
									.toString() + " and icvesustancia = "
							+ Sustancias.elementAt(j).toString();

					// System.out.print(maxlotexanal);
					pstmt = conn.prepareStatement(maxlotexanal);
					rset = pstmt.executeQuery();
					while (rset.next()) {
						lotemax.addElement(rset.getInt(1));
					}
				}
				/*
				 * // System.out.println(lotemax.size()); //
				 * System.out.println("esperamos 5 seg..."); Thread.sleep(1000);
				 */

				for (int k = 0; k < Sustancias.size(); k++) {
					resultado = "select icvesustancia, lresultado, lcorrecto, lautorizado "
							+ " from toxcuantanalisis where icveanalisis = "
							+ icveanalisis
							+ " and ianio = "
							+ ((TVTOXCuantAnalisis) OMuestra).getiAnio()
									.toString()
							+ " and icvesustancia = "
							+ Sustancias.elementAt(k)
							+ " and icvelotecuantita = " + lotemax.elementAt(k);
					// // System.out.println(resultado);
					pstmt = conn.prepareStatement(resultado);
					rset = pstmt.executeQuery();
					while (rset.next()) {
						if (rset.getInt(3) == 1 && rset.getInt(4) == 1) {
							String Sentencia = "update toxexamresult set lpositivo = "
									+ rset.getInt(2)
									+ ", lautorizado = "
									+ rset.getInt(4)
									+ "  where icvesustancia = "
									+ Sustancias.elementAt(k)
									+ " and icveanalisis = "
									+ icveanalisis
									+ " and ianio = "
									+ ((TVTOXCuantAnalisis) OMuestra)
											.getiAnio().toString();
							this.update(Sentencia);
						}
					}
					// }fin if
					/*
					 * System.out.println(lotemax.size()); //
					 * System.out.println("esperamos 5 seg...");
					 * Thread.sleep(1000);
					 */

					// }fin for sustancias
					// **********************************************************************************

					// Query
					cSQL = new StringBuffer();
					cSQL.append(
							" select CA.iAnio, CA.iCveLaboratorio, CA.iCveSustancia, CA.iCveLoteCuantita, CA.iCveAnalisis, ")
							.append("        CA.lResultado, CA.lAutorizado, ")
							.append("        S.cDscSustancia, S.cTitRepConf ")
							.append(" from TOXAnalisis EA ")
							.append(" inner join TOXExamResult ER on ER.iAnio = EA.iAnio ")
							.append("                            and ER.iCveLaboratorio = EA.iCveLaboratorio ")
							.append("                            and ER.iCveLoteCualita = EA.iCveLoteCualita ")
							.append("                            and ER.iCveAnalisis    = EA.iCveAnalisis ")
							.append("                            and ER.iCveExamCualita = EA.iCveExamCualita ")
							.append(" inner join TOXCuantAnalisis CA on CA.iAnio = EA.iAnio ")
							.append("                               and CA.iCveLaboratorio = EA.iCveLaboratorio ")
							.append("                               and CA.iCveSustancia   = ER.iCveSustancia ")
							.append("                               and CA.iCveAnalisis    = EA.iCveAnalisis ")
							.append(" inner join TOXLoteCuantita LC on LC.iAnio = CA.iAnio ")
							.append("                              and LC.iCveLaboratorio = CA.iCveLaboratorio ")
							.append("                              and LC.iCveSustancia   = CA.iCveSustancia ")
							.append("                              and LC.iCveLoteCuantita = CA.iCveLoteCuantita ")
							.append(" inner join TOXSustancia S on S.iCveSustancia = CA.iCveSustancia ")
							.append(" where EA.iAnio           = ")
							.append(((TVTOXCuantAnalisis) OMuestra).getiAnio()
									.toString());
					if (lAnalisMuestra == 0)
						cSQL.append("   and EA.iCveLaboratorio = ")
								.append(((TVTOXCuantAnalisis) OMuestra)
										.getiCveLaboratorio().toString())
								.append("   and EA.iCveAnalisis    = ")
								.append(((TVTOXCuantAnalisis) OMuestra)
										.getiCveAnalisis().toString());
					if (lAnalisMuestra == 1)
						cSQL.append("   and EA.iCveMuestra    = ").append(
								((TVTOXCuantAnalisis) OMuestra)
										.getiCveAnalisis().toString());
					cSQL.append("   and ER.lPositivo       = 1 ");
					if (lConfirmadas == 0)
						cSQL.append(
								"   and ( (LC.dtCalibracion is not null and LC.lValidaCalib = 1 and LC.dtAutorizacion is null)  ")
								.append("          or LC.dtCalibracion is null ")
								.append("          or LC.dtAutorizacion is not null and CA.lAutorizado = 1 and CA.lCorrecto = 1) ");
					if (lConfirmadas == 1)
						cSQL.append(" and CA.lAutorizado = 1 and CA.lCorrecto = 1");
					// Se agrego la siguiente linea para ordenar por sustancia
					// para no replicar la impresion de la droga positiva
					// AG SA 11 de agosto 2010
					cSQL.append(" and CA.icvelotecuantita = ").append(
							lotemax.elementAt(k));
					cSQL.append(" and CA.iCveSustancia = ").append(
							Sustancias.elementAt(k));
					cSQL.append(" order by CA.iCveSustancia, CA.iCveLoteCuantita,  CA.iAnio, CA.iCveLaboratorio,  CA.iCveAnalisis, CA.lResultado, CA.lAutorizado, S.cDscSustancia, S.cTitRepConf  asc");
					// Ejecutar el query
					pstmt = conn.prepareStatement(cSQL.toString());
					// System.out.println("***************************************");
					// System.out.println(cSQL.toString());
					// System.out.println("***************************************");
					rset = pstmt.executeQuery();
					int icvesustanciap = 0;
					int contador = 0;
					while (rset.next()) {
						VAnalisis = new TVTOXCuantAnalisis();
						// Obtener informaci�n
						VAnalisis.setiAnio(new Integer(rset.getInt(1)));
						VAnalisis
								.setiCveLaboratorio(new Integer(rset.getInt(2)));
						VAnalisis.setiCveSustancia(new Integer(rset.getInt(3)));
						VAnalisis.setiCveLoteCuantita(new Integer(rset
								.getInt(4)));
						VAnalisis.setiCveAnalisis(new Integer(rset.getInt(5)));
						VAnalisis.setlResultado(new Integer(rset.getInt(6)));
						VAnalisis.setlAutorizado(new Integer(rset.getInt(7)));
						VAnalisis.setCDscCalib(rset.getString(8));
						// Agregar los resultados
						// Se agrego la siguiente validadcion AG SA 11 de
						// agosto 2010
						if (icvesustanciap != rset.getInt(3)) {
							vAnalisis.add(VAnalisis);
						}
						icvesustanciap = rset.getInt(3);
						contador++;
					}
					// System.out.println("contador = "+contador);
				} // fin for sustancias
			} // fin if sustancias
		}// fin del try
		catch (Exception ex) {
			warn("SustPresConf", ex);
			throw new DAOException("SustPresConf");
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
				warn("SustPresConf.close", ex2);
			}
			return vAnalisis;
		}
	}

	public String getOtrasSust2(TVTOXCuantAnalisis VCAnal) {
		StringBuffer cTexto = new StringBuffer();
		TVTOXCuantAnalisis VAnalisis;
		Vector vSust;
		int lConfirmadas = 0, lAnalisis = 0;
		try {
			vSust = this.SustPresConf2(null, VCAnal, lConfirmadas, lAnalisis);
			for (int i = 0; i < vSust.size(); i++) {
				// System.out.println("Entro en el for");
				VAnalisis = new TVTOXCuantAnalisis();
				VAnalisis = (TVTOXCuantAnalisis) vSust.get(i);
				// System.out.println("Analisis = " +
				// VAnalisis.getiCveAnalisis()+" Clave Sustancia = "+
				// VCAnal.getiCveSustancia().equals(VAnalisis.getiCveSustancia())+
				// " getCDscCalib = "+VAnalisis.getCDscCalib()
				// +" lAutorizado = "+ VAnalisis.getlAutorizado().intValue()==0
				// +" lResultado = "+VAnalisis.getlResultado().intValue()+" iCveLoteCuantita = "+VAnalisis.getiCveLoteCuantita());
				if (!VCAnal.getiCveSustancia().equals(
						VAnalisis.getiCveSustancia())) {
					if (i > 0)
						cTexto.append("<br>");
					cTexto.append(VAnalisis.getCDscCalib()).append(" - ");
					if (VAnalisis.getlAutorizado().intValue() == 0)
						cTexto.append(" PENDIENTE ");
					else {
						cTexto.append(VAnalisis.getlResultado().intValue() == 0 ? " NEGATIVO "
								: " POSITIVO ");
						cTexto.append(VAnalisis.getiAnio()).append("/")
								.append(VAnalisis.getiCveLoteCuantita());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto.toString();
	}

	public String getSustPost(TVTOXCuantAnalisis VCAnal) {
		StringBuffer cTexto = new StringBuffer();
		TVTOXCuantAnalisis VAnalisis;
		Vector vSust;
		int lConfirmadas = 1, lMuestra = 1;
		try {
			vSust = this.SustPresConf(null, VCAnal, lConfirmadas, lMuestra);
			for (int i = 0; i < vSust.size(); i++) {
				VAnalisis = new TVTOXCuantAnalisis();
				VAnalisis = (TVTOXCuantAnalisis) vSust.get(i);
				if (!VCAnal.getiCveSustancia().equals(
						VAnalisis.getiCveSustancia())) {
					if (i > 0)
						cTexto.append("<br>");
					cTexto.append(VAnalisis.getCDscCalib());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto.toString();
	}

	public HashMap findCarga(Object vInfoLote, int iTipoBusqueda, int iPosNombre)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap hsAnalisis = new HashMap();
		TNumeros Numero = new TNumeros();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXcLote vTOXcLote;
			TVTOXCuantAnalisis vCuantA = new TVTOXCuantAnalisis();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(" select CA.*,")
					.append("        S.cPrefLoteConf ")
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = CA.iCveSustancia ")
					.append(" where CA.iAnio            = ")
					.append(((TVTOXLoteCuantita) vInfoLote).getiAnio())
					.append("   and CA.iCveLaboratorio  = ")
					.append(((TVTOXLoteCuantita) vInfoLote)
							.getiCveLaboratorio())
					.append("   and CA.iCveSustancia    = ")
					.append(((TVTOXLoteCuantita) vInfoLote).getiCveSustancia())
					.append("   and CA.iCveLoteCuantita = ")
					.append(((TVTOXLoteCuantita) vInfoLote)
							.getiCveLoteCuantita());
			// Dependiendo del Tipo de Busqueda se agregan las condiciones para
			// el filtrado
			switch (iTipoBusqueda) {
			// Calibradores
			case 1:
				cSQL.append(" and CA.iCveAnalisis     < 0");
				break;
			// Muestras
			case 2:
				cSQL.append(" and CA.iCveAnalisis     > 0");
				break;
			// Todos
			case 3:
				break;
			}
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			// Obtener informaci�n de los Analisis
			while (rset.next()) {
				vCuantA = new TVTOXCuantAnalisis();
				vCuantA.setiAnio(new Integer(rset.getInt(1)));
				vCuantA.setiCveSustancia(new Integer(rset.getInt(2)));
				vCuantA.setiCveLoteCuantita(new Integer(rset.getInt(3)));
				vCuantA.setiCveAnalisis(new Integer(rset.getInt(4)));
				vCuantA.setiCveLaboratorio(new Integer(rset.getInt(5)));
				vCuantA.setiCveCtrolCalibra(new Integer(rset.getInt(6)));
				vCuantA.setlControl(new Integer(rset.getInt(7)));
				vCuantA.setiPosicion(new Integer(rset.getInt(8)));
				vCuantA.setiDilusion(new Integer(rset.getInt(9)));
				vCuantA.setlResultado(new Integer(rset.getInt(10)));
				vCuantA.setdResultado(new Double(rset.getDouble(11)));
				vCuantA.setdResultadoDil(new Double(rset.getDouble(12)));
				vCuantA.setlAutorizado(new Integer(rset.getInt(13)));
				vCuantA.setdConcentracion(new Double(rset.getDouble(14)));
				vCuantA.setcObservacion(rset.getString(15));
				vCuantA.setDTmpRetenc(rset.getDouble(16));
				vCuantA.setDIon01(rset.getDouble(17));
				vCuantA.setDIon02(rset.getDouble(18));
				vCuantA.setDIon03(rset.getDouble(19));
				vCuantA.setDTmpRetencD(rset.getDouble(20));
				vCuantA.setDIon04(rset.getDouble(21));
				vCuantA.setDIon05(rset.getDouble(22));
				vCuantA.setLCorrecto(new Integer(rset.getInt(23)));
				vCuantA.setLRegistrado(new Integer(rset.getInt(24)));
				String cIdMuestra = "";
				if (iTipoBusqueda == 1)
					cIdMuestra = rset.getString(25).substring(0, iPosNombre)
							+ Numero.getNumeroSinSeparador(
									vCuantA.getiCveLoteCuantita(), 3)
							+ vCuantA.getiCveAnalisis();
				else
					cIdMuestra = rset.getString(25).substring(0, iPosNombre)
							+ Numero.getNumeroSinSeparador(
									vCuantA.getiCveAnalisis(), 5);
				// cIdMuestra = vCuantA.getCAnalisis();
				// // System.out.println("Identificador de la muestra " +
				// cIdMuestra);
				hsAnalisis.put(cIdMuestra, vCuantA);
			}
		} catch (Exception ex) {
			warn("findCarga", ex);
			ex.printStackTrace();
			throw new DAOException("findCarga");
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
				warn("findCarga.close", ex2);
			}
			return hsAnalisis;
		}
	}

	public boolean EvaluaDosSustDep(Object vInfoLote, String cSustD)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null, lPStmt = null;
		ResultSet rset = null;
		ArrayList aAnalisis = new ArrayList();
		boolean lResultado = false;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXcLote vTOXcLote;
			TVTOXCuantAnalisis vCuantA = new TVTOXCuantAnalisis();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(
					" select CA.dDensidad as CADensidad, CS.dCorte as CACorte, CS.dCorteNeg as CACorteNeg, ")
					.append("        CA.lCorrecto as CACorrecto,")
					.append("        CAD.dDensidad as CADDensidad, CSD.dCorte as CADCorte, CSD.dCorteNeg as CADCorteNeg, ")
					.append("        CAD.lCorrecto as CADCorrecto, ")
					.append("        CAD.iAnio, CAD.iCveLaboratorio, CAD.iCveSustancia,  ")
					.append("        CAD.iCveLoteCuantita, CAD.iCveAnalisis, ")
					.append("        CA.iCveSustancia as CAiCveSustancia, CA.lResultado, CAD.lResultado as CADResultado  ")
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXCuantAnalisis CAD on CAD.iAnio = CA.iAnio ")
					.append("                                and CAD.iCveLaboratorio = CA.iCveLaboratorio ")
					.append("                                and CAD.iCveSustancia   = ")
					.append(cSustD)
					.append("                                and CAD.iCveLoteCuantita = CA.iCveLoteCuantita ")
					.append("                                and CAD.iCveAnalisis    = CA.iCveAnalisis ")
					.append(" inner join TOXLoteCuantita LC on LC.iAnio            = CA.iAnio ")
					.append("                              and LC.iCveLaboratorio  = CA.iCveLaboratorio ")
					.append("                              and LC.iCveSustancia    = CA.iCveSustancia ")
					.append("                              and LC.iCveLoteCuantita = CA.iCveLoteCuantita ")
					.append(" inner join TOXCorteXSust CS on CS.iCveSustancia = LC.iCveSustancia ")
					.append("                            and CS.iCveCorte     = LC.iCveCorte ")
					.append(" inner join TOXLoteCuantita LCD on LCD.iAnio            = CAD.iAnio ")
					.append("                               and LCD.iCveLaboratorio  = CAD.iCveLaboratorio ")
					.append("                               and LCD.iCveSustancia    = CAD.iCveSustancia ")
					.append("                               and LCD.iCveLoteCuantita = CAD.iCveLoteCuantita ")
					.append(" inner join TOXCorteXSust CSD on CSD.iCveSustancia = LCD.iCveSustancia ")
					.append("                             and CSD.iCveCorte     = LCD.iCveCorte ")
					.append(" where CA.iAnio            = ")
					.append(((TVTOXLoteCuantita) vInfoLote).getiAnio())
					.append("   and CA.iCveLaboratorio  = ")
					.append(((TVTOXLoteCuantita) vInfoLote)
							.getiCveLaboratorio())
					.append("   and CA.iCveSustancia    = ")
					.append(((TVTOXLoteCuantita) vInfoLote).getiCveSustancia())
					.append("   and CA.iCveLoteCuantita = ")
					.append(((TVTOXLoteCuantita) vInfoLote)
							.getiCveLoteCuantita())
					.append("   and CA.iCveAnalisis > 0 ");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			// Obtener informaci�n de los Analisis
			// System.out.println("cSQL \n" + cSQL);
			while (rset.next()) {
				vCuantA = new TVTOXCuantAnalisis();
				vCuantA.setiAnio(new Integer(rset.getInt("iAnio")));
				vCuantA.setiCveLaboratorio(new Integer(rset
						.getInt("iCveLaboratorio")));
				vCuantA.setiCveSustancia(new Integer(rset
						.getInt("iCveSustancia")));
				vCuantA.setiCveLoteCuantita(new Integer(rset
						.getInt("iCveLoteCuantita")));
				vCuantA.setiCveAnalisis(new Integer(rset.getInt("iCveAnalisis")));

				vCuantA.setlResultado(new Integer(rset.getInt("CADResultado")));
				vCuantA.setLCorrecto((new Integer(rset.getInt("CADCorrecto"))));

				if (rset.getDouble("CADensidad") >= rset.getDouble("CACorte")
						&& rset.getDouble("CADDensidad") >= rset
								.getDouble("CADCorteNeg"))
					vCuantA.setlResultado(Integer.valueOf("1"));
				else
					vCuantA.setlResultado(Integer.valueOf("0"));
				// Para dejar abierta la evaluaci�n de sustancias como
				// metanfetamina se comentar� el IF
				// if(rset.getInt("CACorrecto") == 0)
				// System.out.println("nuevo setting metanfetamina");
				vCuantA.setLCorrecto(Integer.valueOf("0"));
				aAnalisis.add(vCuantA);

				if (rset.getInt("CADCorrecto") == 0
						|| rset.getInt("CACorrecto") == 0) {
					vCuantA = new TVTOXCuantAnalisis();
					vCuantA.setiAnio(new Integer(rset.getInt("iAnio")));
					vCuantA.setiCveLaboratorio(new Integer(rset
							.getInt("iCveLaboratorio")));
					vCuantA.setiCveSustancia(new Integer(rset
							.getInt("CAiCveSustancia")));
					vCuantA.setiCveLoteCuantita(new Integer(rset
							.getInt("iCveLoteCuantita")));
					vCuantA.setiCveAnalisis(new Integer(rset
							.getInt("iCveAnalisis")));
					vCuantA.setlResultado(new Integer(rset.getInt("lResultado")));
					vCuantA.setLCorrecto(Integer.valueOf("0"));
					aAnalisis.add(vCuantA);
				}
			} // Obtener los resultados
			for (int i = 0; i < aAnalisis.size(); i++) {
				vCuantA = new TVTOXCuantAnalisis();
				vCuantA = (TVTOXCuantAnalisis) aAnalisis.get(i);
				String lSQL = " update TOXCuantAnalisis    "
						+ " set lResultado = ?, " + // 1
						"     lCorrecto  = ?  " + // 2
						" where iAnio = ?            " + // 3
						"   and iCveLaboratorio  = ? " + // 4
						"   and iCveSustancia    = ? " + // 5
						"   and iCveLoteCuantita = ? " + // 6
						"   and iCveAnalisis     = ? " + // 7
						" ";
				lPStmt = conn.prepareStatement(lSQL);
				if (vCuantA.getlResultado() != null)
					lPStmt.setInt(1, vCuantA.getlResultado().intValue());
				if (vCuantA.getLCorrecto() == null)
					lPStmt.setNull(2, Types.INTEGER);
				else
					lPStmt.setInt(2, vCuantA.getLCorrecto().intValue());
				lPStmt.setInt(3, vCuantA.getiAnio().intValue());
				lPStmt.setInt(4, vCuantA.getiCveLaboratorio().intValue());
				lPStmt.setInt(5, vCuantA.getiCveSustancia().intValue());
				lPStmt.setInt(6, vCuantA.getiCveLoteCuantita().intValue());
				lPStmt.setInt(7, vCuantA.getiCveAnalisis().intValue());

				// System.out.println("actualizando " +
				// vCuantA.getiCveSustancia().intValue() + " - " +
				// vCuantA.getiCveAnalisis().intValue() + "-" +
				// vCuantA.getLCorrecto().intValue() + " Resultado " +
				// vCuantA.getlResultado().intValue());
				int Contador = lPStmt.executeUpdate();
				if (Contador > 0)
					lResultado = true;
			}
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			warn("EvaluaDosSustDep", ex);
			ex.printStackTrace();
			throw new DAOException("EvaluaDosSustDep");
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (lPStmt != null)
					lPStmt.close();
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
				warn("EvaluaDosSustDep.close", ex2);
			}
			return lResultado;
		}
	}

	/**
	 * Método Find By All
	 * 
	 * @param cvFiltro
	 *            Valor del Filtro a Aplicar en la Extracci�n de los Datos
	 * @return Value Object de los Resultados de los Examenes.
	 * @throws DAOException
	 */
	public Vector FindAllSustancia(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCuantAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXCuantAnalisis vTOXCuantAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select "
					+ " TOXCuantAnalisis.iAnio,"
					+ " TOXCuantAnalisis.iCveSustancia,"
					+ " TOXCuantAnalisis.iCveLoteCuantita,"
					+ " TOXCuantAnalisis.iCveAnalisis,"
					+ " TOXCuantAnalisis.iCveLaboratorio,"
					+ " TOXCuantAnalisis.iCveCtrolCalibra,"
					+ " TOXCuantAnalisis.lControl,"
					+ " TOXCuantAnalisis.iPosicion,"
					+ " TOXCuantAnalisis.iDilusion,"
					+ " TOXCuantAnalisis.lResultado,"
					+ " TOXCuantAnalisis.dDensidad,"
					+ " TOXCuantAnalisis.dResultadoDil,"
					+ " TOXCuantAnalisis.lAutorizado,"
					+ " TOXCuantAnalisis.dConcentracion, "
					+ " TOXCuantAnalisis.cObservacion,   "
					+ " TOXCuantAnalisis.dTmpRetenc,     "
					+ " TOXCuantAnalisis.dIon01,         "
					+ " TOXCuantAnalisis.dIon02,         "
					+ " TOXCuantAnalisis.dIon03,         "
					+ " TOXCuantAnalisis.dTmpRetencD,    "
					+ " TOXCuantAnalisis.dIon04,         "
					+ " TOXCuantAnalisis.dIon05,         "
					+ " TOXCuantAnalisis.lCorrecto,      "
					+ " TOXCuantAnalisis.lRegistrado,    "
					+ " S.cDscSustancia "
					+ " from TOXCuantAnalisis "
					+ " inner join TOXSustancia S on S.iCveSustancia = TOXCuantAnalisis.iCveSustancia "
					+ cvFiltro + " ";

			// System.out.println("******************************* primer pasada **************************************");
			// System.out.println(cSQL);
			// System.out.println("*********************************************************************");
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			/*
			 * Regla para ser Positivo a MetanfetaminasPara ser positivo a
			 * Metanfetaminas debe contener la muestra una (DDensidad >= a 200 y
			 * ser correcta para Anfetaminas) y una (DDensidad >=500 de
			 * Metanfetaminas)
			 */
			int Ano = 0;
			int Sustancia = 0;
			int Lote = 0;
			int Analisis = 0;
			int Laboratorio = 0;
			int Correcto = 2;
			double Concentracion = 0.0;
			String Sentencia = "";

			while (rset.next()) {

				if (Ano != 0) {
					if (Ano == rset.getInt(1) && Sustancia == 1
							&& rset.getInt(2) == 7 && Lote == rset.getInt(3)
							&& Analisis == rset.getInt(4)
							&& Laboratorio == rset.getInt(5)) {
						// System.out.print("Corresponde al mismo analisis");

						// Validacion de Regla Metanfentaminas

						if (Concentracion >= 200 && Correcto == 1
								&& rset.getDouble(11) >= 500) {
							// System.out.println("| Ano = "+ rset.getInt(1)
							// +" | Sustancia = "+ rset.getInt(2)
							// +" | Analisis = "+ rset.getInt(4)
							// +" | Analisis = "+ rset.getInt(4)
							// +" | Concentracion = "+ rset.getInt(11)
							// +" | 1 = Pos - "+
							// rset.getInt(10)
							// +" - Debe ser Positivo  | 1 = Corr  -"+
							// rset.getInt(23)+" - Debe ser Correcto");
							// Actualizar Informaci�n Metanfetaminas de acuerdo
							// a la regla.
							Sentencia = " UPDATE TOXCuantAnalisis SET LCORRECTO = 1, LRESULTADO = 1 "
									+ " WHERE ICVEANALISIS = "
									+ rset.getInt(4)
									+ " AND "
									+ " ICVESUSTANCIA = 7 AND "
									+ " iCveLaboratorio = "
									+ rset.getInt(5)
									+ " AND "
									+ " iCveLoteCuantita = "
									+ rset.getInt(3)
									+ " AND "
									+ " IANIO = "
									+ rset.getInt(1) + " ";
							this.update(Sentencia);

						}

						if (Concentracion >= 200 && Correcto == 1
								&& rset.getDouble(11) <= 500) {
							// System.out.println("| Ano = "+ rset.getInt(1)
							// +" | Sustancia = "+ rset.getInt(2)
							// +" | Analisis = "+ rset.getInt(4)
							// +" | Concentracion = "+ rset.getInt(11)
							// +" | 1 = Pos - "+
							// rset.getInt(10)
							// +" - Debe ser Negativo  | 1 = Corr  -"+
							// rset.getInt(23)+" - Debe ser Correcto");
							// Actualizar Informaci�n Metanfetaminas de acuerdo
							// a la regla.
							Sentencia = " UPDATE TOXCuantAnalisis SET LCORRECTO = 1, LRESULTADO = 0 "
									+ " WHERE ICVEANALISIS = "
									+ rset.getInt(4)
									+ " AND "
									+ " ICVESUSTANCIA = 7 AND "
									+ " iCveLaboratorio = "
									+ rset.getInt(5)
									+ " AND "
									+ " iCveLoteCuantita = "
									+ rset.getInt(3)
									+ " AND "
									+ " IANIO = "
									+ rset.getInt(1) + " ";
							this.update(Sentencia);
						}

						if (Concentracion <= 200 && Correcto == 1
								&& rset.getDouble(11) >= 500) {
							// System.out.println("| Ano = "+ rset.getInt(1)
							// +" | Sustancia = "+ rset.getInt(2)
							// +" | Analisis = "+ rset.getInt(4)
							// +" | Concentracion = "+ rset.getInt(11)
							// +" | 1 = Pos - "+
							// rset.getInt(10)
							// +" - Debe ser Negativo  | 1 = Corr  -"+
							// rset.getInt(23)+" - Debe ser Correcto");
							// Actualizar Informaci�n Metanfetaminas de acuerdo
							// a la regla.
							Sentencia = " UPDATE TOXCuantAnalisis SET LCORRECTO = 1, LRESULTADO = 0 "
									+ " WHERE ICVEANALISIS = "
									+ rset.getInt(4)
									+ " AND "
									+ " ICVESUSTANCIA = 7 AND "
									+ " iCveLaboratorio = "
									+ rset.getInt(5)
									+ " AND "
									+ " iCveLoteCuantita = "
									+ rset.getInt(3)
									+ " AND "
									+ " IANIO = "
									+ rset.getInt(1) + " ";
							this.update(Sentencia);
						}

						if (Concentracion <= 200 && Correcto == 1
								&& rset.getDouble(11) <= 500) {
							// System.out.println("| Ano = "+ rset.getInt(1)
							// +" | Sustancia = "+ rset.getInt(2)
							// +" | Analisis = "+ rset.getInt(4)
							// +" | Concentracion = "+ rset.getInt(11)
							// +" | 1 = Pos - "+
							// rset.getInt(10)
							// +" - Debe ser Negativo  | 1 = Corr  -"+
							// rset.getInt(23)+" - Debe ser Correcto");
							// Actualizar Informaci�n Metanfetaminas de acuerdo
							// a la regla.
							Sentencia = " UPDATE TOXCuantAnalisis SET LCORRECTO = 1, LRESULTADO = 0 "
									+ " WHERE ICVEANALISIS = "
									+ rset.getInt(4)
									+ " AND "
									+ " ICVESUSTANCIA = 7 AND "
									+ " iCveLaboratorio = "
									+ rset.getInt(5)
									+ " AND "
									+ " iCveLoteCuantita = "
									+ rset.getInt(3)
									+ " AND "
									+ " IANIO = "
									+ rset.getInt(1) + " ";
							this.update(Sentencia);
						}

						// Si la concentracion de anfetas no es correcta no
						// hacer ninguna operaci�n
						/*
						 * if(Correcto == 0){ // System.out.println("| Ano = "+
						 * rset.getInt(1) +" | Sustancia = "+ rset.getInt(2)
						 * +" | Analisis = "+ rset.getInt(4)
						 * +" | Concentracion = "+ rset.getInt(11)
						 * +" | 1 = Pos - "+ rset.getInt(10)
						 * +" -  | 1 = Corr  -"+ rset.getInt(23)+" - "); }
						 */
					}
					/*
					 * else{ // System.out.println("| Ano = "+ rset.getInt(1)
					 * +" | Sustancia = "+ rset.getInt(2) +" | Analisis = "+
					 * rset.getInt(4) +" | Concentracion = "+ rset.getInt(11)
					 * +" | 1 = Pos - "+ rset.getInt(10) +" - | 1 = -"+
					 * rset.getInt(23)+" - "); }
					 */
				}
				/*
				 * else{ // System.out.println("Primer iteraci�n"); //
				 * System.out.println("| Ano = "+ rset.getInt(1)
				 * +" | Sustancia = "+ rset.getInt(2) +" | Analisis = "+
				 * rset.getInt(4) +" | Concentracion = "+ rset.getInt(11)
				 * +" | 1 = Pos - "+ rset.getInt(10) +" - | 1 = -"+
				 * rset.getInt(23)+" - "); }
				 */
				Ano = rset.getInt(1);
				Sustancia = rset.getInt(2);
				Lote = rset.getInt(3);
				Analisis = rset.getInt(4);
				Laboratorio = rset.getInt(5);
				Correcto = rset.getInt(23);
				Concentracion = rset.getDouble(11);
			}

			// System.out.println("*************************** segunda pasada ******************************************");
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCuantAnalisis = new TVTOXCuantAnalisis();
				vTOXCuantAnalisis.setiAnio(new Integer(rset.getInt(1)));
				vTOXCuantAnalisis.setiCveSustancia(new Integer(rset.getInt(2)));
				vTOXCuantAnalisis.setiCveLoteCuantita(new Integer(rset
						.getInt(3)));
				vTOXCuantAnalisis.setiCveAnalisis(new Integer(rset.getInt(4)));
				vTOXCuantAnalisis
						.setiCveLaboratorio(new Integer(rset.getInt(5)));
				vTOXCuantAnalisis.setiCveCtrolCalibra(new Integer(rset
						.getInt(6)));
				vTOXCuantAnalisis.setlControl(new Integer(rset.getInt(7)));
				vTOXCuantAnalisis.setiPosicion(new Integer(rset.getInt(8)));
				vTOXCuantAnalisis.setiDilusion(new Integer(rset.getInt(9)));
				vTOXCuantAnalisis.setlResultado(new Integer(rset.getInt(10)));
				vTOXCuantAnalisis.setdResultado(new Double(rset.getDouble(11)));
				vTOXCuantAnalisis.setdResultadoDil(new Double(rset
						.getDouble(12)));
				vTOXCuantAnalisis.setlAutorizado(new Integer(rset.getInt(13)));
				vTOXCuantAnalisis.setdConcentracion(new Double(rset
						.getDouble(14)));
				vTOXCuantAnalisis.setcObservacion(rset.getString(15));
				vTOXCuantAnalisis.setDTmpRetenc(rset.getDouble(16));
				vTOXCuantAnalisis.setDIon01(rset.getDouble(17));
				vTOXCuantAnalisis.setDIon02(rset.getDouble(18));
				vTOXCuantAnalisis.setDIon03(rset.getDouble(19));
				vTOXCuantAnalisis.setDTmpRetencD(rset.getDouble(20));
				vTOXCuantAnalisis.setDIon04(rset.getDouble(21));
				vTOXCuantAnalisis.setDIon05(rset.getDouble(22));
				vTOXCuantAnalisis.setLCorrecto(new Integer(rset.getInt(23)));
				vTOXCuantAnalisis.setLRegistrado(new Integer(rset.getInt(24)));
				vTOXCuantAnalisis.setCDscCalib(rset.getString("cDscSustancia"));
				vcTOXCuantAnalisis.addElement(vTOXCuantAnalisis);
			}
			// System.out.println("*********************************************************************");

		} catch (Exception ex) {
			warn("FindAllSustancia", ex);
			throw new DAOException("FindAllSustancia");
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
				warn("FindAllSustancia.close", ex2);
			}
			return vcTOXCuantAnalisis;
		}
	}

	public boolean findCtrolCalibra(int iAnio, int iCveLaboratorio,
			int iCveSustancia, int iCveLoteCuantita) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXcLote = new Vector();
		boolean lIntegrado = false;
		String cIntegra = String.valueOf(iCveSustancia);
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXcLote vTOXcLote;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			vcTOXcLote = new TDTOXSustancia().FindByAll(
					"where iCveSustancia = " + iCveSustancia, "");
			if (vcTOXcLote.size() > 0) {
				cIntegra = ((TVTOXSustancia) vcTOXcLote.get(0))
						.getCSustUnifica();
			}
			cSQL = " select count(1) as iFaltantes  "
					+ "  from TOXCuantAnalisis CA      "
					+ "  where CA.iAnio            =   "
					+ iAnio
					+ "    and CA.iCveLaboratorio  =   "
					+ iCveLaboratorio
					+ "    and CA.iCveSustancia    in ("
					+ cIntegra
					+ ")"
					+ "    and CA.iCveLoteCuantita =   "
					+ iCveLoteCuantita
					+ "    and CA.iCveAnalisis     in ("
					+ this.VParametros.getPropEspecifica("TOXPosCalibCtrol")
							.toString() + ")"
					+ "    and CA.iCveCtrolCalibra is null ";
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				if (rset.getInt(1) == 0)
					lIntegrado = true;
			}
		} catch (Exception ex) {
			warn("FindcLote", ex);
			throw new DAOException("FindcLote");
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
				warn("FindcLote.close", ex2);
			}
			return lIntegrado;
		}
	}

	public boolean update(String Sentencia) throws DAOException {
		// TVTOXCuantAnalisis VTOXCuantAnalisis = (TVTOXCuantAnalisis) obj;
		PreparedStatement lPStmt = null;
		boolean lRegresa = true;
		try {
			// abreConexion();
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			String lSQL = Sentencia;

			/*
			 * System.out.println(Sentencia); //
			 * System.out.println("esperamos 1 seg..."); Thread.sleep(1000);
			 */

			lPStmt = conn.prepareStatement(lSQL);

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
			throw new DAOException("Error en DAO");
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

	public String getAutorizada(TVTOXCuantAnalisis VCAnal) {
		StringBuffer cTexto = new StringBuffer();
		TVTOXLoteCuantita VLoteC;
		Vector vSust;
		int lConfirmadas = 1, lMuestra = 1;
		try {
			vSust = this.AutorizaConf(null, VCAnal, lConfirmadas, lMuestra);
			// System.out.println(vSust.size());
			for (int i = 0; i < vSust.size(); i++) {
				VLoteC = new TVTOXLoteCuantita();
				VLoteC = (TVTOXLoteCuantita) vSust.get(i);
				if (i > 0)
					cTexto.append("<br>");
				cTexto.append(VLoteC.getcDscSustancia());
				cTexto.append("  ");
				cTexto.append(VLoteC.getdtAutorizacion());
				// System.out.println(cTexto.toString());
			}
			// System.out.println("Terminado " +cTexto.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cTexto.toString();
	}

	public Vector AutorizaConf(Connection connE, Object OMuestra,
			int lConfirmadas, int lAnalisMuestra) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vLoteC = new Vector();
		StringBuffer cSQL;
		TVTOXLoteCuantita VLoteC;
		DbConnection dbConn = null;
		try {
			if (connE == null) {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else
				conn = connE;

			// Query
			cSQL = new StringBuffer();
			cSQL.append(" SELECT  ")
					.append("     TS.CDSCSUSTANCIA, TL.DTAUTORIZACION ")
					.append(" FROM ")
					.append(" 	TOXLOTECUANTITA AS TL, ")
					.append("	TOXCUANTANALISIS AS TC, ")
					.append("	TOXANALISIS AS TA, ")
					.append("	TOXSUSTANCIA AS TS ")
					.append(" WHERE ")
					.append("	TA.ICVEMUESTRA =  ")
					.append(((TVTOXCuantAnalisis) OMuestra).getiCveAnalisis()
							.toString())
					.append("	AND TA.IANIO = ")
					.append(((TVTOXCuantAnalisis) OMuestra).getiAnio()
							.toString())
					.append("	AND TA.ICVEANALISIS = TC.ICVEANALISIS	AND ")
					.append("	TC.ICVELOTECUANTITA = TL.ICVELOTECUANTITA AND ")
					.append("	TC.ICVESUSTANCIA = TL.ICVESUSTANCIA AND ")
					.append("	TA.IANIO = TL.IANIO	AND ")
					.append("	TC.ICVESUSTANCIA = TS.ICVESUSTANCIA	AND ")
					.append("	TC.LAUTORIZADO = 1 AND ")
					.append("	TC.LCORRECTO = 1 AND ")
					.append("	TC.LRESULTADO = 1 ");
			// Ejecutar el query
			// System.out.println(cSQL);
			pstmt = conn.prepareStatement(cSQL.toString());

			rset = pstmt.executeQuery();
			int icvesustanciap = 0;
			int contador = 0;
			while (rset.next()) {
				VLoteC = new TVTOXLoteCuantita();
				// Obtener informaci�n
				VLoteC.setcDscSustancia(rset.getString(1));
				VLoteC.setdtAutorizacion(rset.getDate(2));
				vLoteC.add(VLoteC);
			}
			// System.out.println("contador = "+contador);

		} catch (Exception ex) {
			warn("SustPresConf", ex);
			throw new DAOException("SustPresConf");
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
				warn("SustPresConf.close", ex2);
			}
			return vLoteC;
		}
	}

	public Vector FindCalibra2(String cvFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXCuantAnalisis = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXCuantAnalisis vTOXCuantAnalisis;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select C.cDscCalib, C.dPorcentaje, C.lControl, ")
					// 1,2,3
					.append("        A.iCveCtrolCalibra, (CS.dCorte * C.dPorcentaje) AS Concentracion, ")
					// 4,5
					.append("        L.lValidaCalib, L.iCveLaboratorio, L.iCveSustancia, ")
					// 6, 7, 8
					.append("        CS.dCorte, A.iCveAnalisis, CS.dMargenError, ")
					// 9, 10, 11
					.append("        A.iAnio, A.iCveLoteCuantita, A.iDilusion, A.dDensidad,  L.dtCalibracion, ")
					// 12, 13, 14, 15, 16
					.append("        A.dTmpRetenc, A.dIon01, A.dIon02, A.dIon03, ")
					// 17, 18, 19, 20
					.append("        A.dTmpRetencD, A.dIon04, A.dIon05, A.lCorrecto, A.lRegistrado, S.cSustUnifica ")
					// 21, 22, 23, 24, 25, 26
					.append(" from TOXCalibCuantita C ")
					.append(" inner join TOXLoteCuantita L on L.iCveLaboratorio = C.iCveLaboratorio  ")
					.append("                             and L.iCveCalib       = C.iCveCalib ")
					// Se agrego la siguiente linea por el problema con los % de
					// los calibradores y controles para canabis
					// AG.A.S. 14 de Abril 2010
					.append("                             and L.iCveSustancia       = C.iCveSustancia ")
					.append(" inner join TOXCuantAnalisis A on A.iAnio            = L.iAnio ")
					.append("                              and A.iCveLaboratorio  = L.iCveLaboratorio ")
					.append("                              and A.iCveSustancia    = L.iCveSustancia ")
					.append("                              and A.iCveLoteCuantita = L.iCveLoteCuantita ")
					.append("                              and A.icveAnalisis     = C.iPosicion ")
					.append(" inner join TOXCorteXSust CS  on CS.iCveSustancia = L.iCveSustancia ")
					// .append("                             and CS.iCveCorte     = L.iCveCorte ")
					.append(" inner join TOXSustancia S on S.iCveSustancia = L.iCveSustancia ")
					.append(cvFiltro)
					.append(" and CS.LACTIVO = 1 and CS.LCUANTCUAL = 1 ")
					.append(" order by C.iPosicion ");
			// System.out.println("**************************************************************************");
			// System.out.println("Controles = " + cSQL);
			// System.out.println("**************************************************************************");
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXCuantAnalisis = new TVTOXCuantAnalisis();
				vTOXCuantAnalisis.setCDscCalib(rset.getString(1));
				vTOXCuantAnalisis.setPorcentaje(new Double(rset.getDouble(2)));
				vTOXCuantAnalisis.setlControl(new Integer(rset.getInt(3)));
				vTOXCuantAnalisis.setiCveCtrolCalibra(new Integer(rset
						.getInt(4)));
				vTOXCuantAnalisis.setdConcentracion(new Double(rset
						.getDouble(5)));
				if (rset.getDate(16) != null)
					vTOXCuantAnalisis
							.setlAutorizado(new Integer(rset.getInt(6)));
				else
					vTOXCuantAnalisis.setlAutorizado(null);
				vTOXCuantAnalisis.setiAnio(new Integer(rset.getInt(12)));
				vTOXCuantAnalisis
						.setiCveLaboratorio(new Integer(rset.getInt(7)));
				vTOXCuantAnalisis.setiCveSustancia(new Integer(rset.getInt(8)));
				vTOXCuantAnalisis.setiCveAnalisis(new Integer(rset.getInt(10)));
				vTOXCuantAnalisis
						.setDMargenError(new Double(rset.getDouble(11)));
				vTOXCuantAnalisis.setiCveLoteCuantita(new Integer(rset
						.getInt(13)));
				vTOXCuantAnalisis.setiDilusion(new Integer(rset.getInt(14)));
				vTOXCuantAnalisis.setdResultado(new Double(rset.getDouble(15)));
				vTOXCuantAnalisis.setDTmpRetenc(rset.getDouble(17));
				vTOXCuantAnalisis.setDIon01(rset.getDouble(18));
				vTOXCuantAnalisis.setDIon02(rset.getDouble(19));
				vTOXCuantAnalisis.setDIon03(rset.getDouble(20));
				vTOXCuantAnalisis.setDTmpRetencD(rset.getDouble(21));
				vTOXCuantAnalisis.setDIon04(rset.getDouble(22));
				vTOXCuantAnalisis.setDIon05(rset.getDouble(23));
				vTOXCuantAnalisis.setLCorrecto(new Integer(rset.getInt(24)));
				vTOXCuantAnalisis.setLRegistrado(new Integer(rset.getInt(25)));
				vTOXCuantAnalisis.setcObservacion(rset.getString(26));
				vcTOXCuantAnalisis.addElement(vTOXCuantAnalisis);
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
			return vcTOXCuantAnalisis;
		}
	}

}
