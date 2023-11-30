package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de CTRPersonal DAO
 * </p>
 * <p>
 * Description: DAO para la tabla CTRPersonal
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Marco Antonio Hern�ndez Garc�a
 * @version 1.0
 */

public class TDCTRPersonal extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");
	public String cValSQL = "";
	private int iInserted; // Agregada por Rafael Alcocer Caldera 10/Ago/2006
	private int iUpdated; // Agregada por Rafael Alcocer Caldera 10/Ago/2006
	private int iDeleted; // Agregada por Rafael Alcocer Caldera 10/Ago/2006

	public TDCTRPersonal() {
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 07/Ago/2006 Verifica la existencia
	 * del operador a partir de iCveEmpresa, iCvePlantilla, cNombre, cApPaterno,
	 * cApMaterno y CRFC
	 * 
	 * @param iCveEmpresa
	 *            int
	 * @param iCvePlantilla
	 *            int
	 * @param cNombre
	 *            String
	 * @param cApPaterno
	 *            String
	 * @param cApMaterno
	 *            String
	 * @param cRFC
	 *            String
	 * @throws DAOException
	 * @return boolean
	 */
	public boolean existsOperador(int iCveEmpresa, int iCvePlantilla,
			String cNombre, String cApPaterno, String cApMaterno, String cRFC)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;
		boolean exists = false;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " select  count (*) " + " from    CTRPersonal  "
					+ " where   iCveEmpresa = " + iCveEmpresa
					+ " and     iCvePlantilla = " + iCvePlantilla
					+ " and     cNombre = " + "\'" + cNombre + "\' "
					+ " and     cApPaterno = " + "\'" + cApPaterno + "\' "
					+ " and     cApMaterno = " + "\'" + cApMaterno + "\' "
					+ " and     cRFC = " + "\'" + cRFC + "\'";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				count = rset.getInt(1);
			}

			if (count > 0) {
				exists = true;
			}
		} catch (Exception ex) {
			warn("existsOperador", ex);
			throw new DAOException("existsOperador");
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
				warn("existsOperador.close", ex2);
			}
		}

		return exists;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 07/Ago/2006 Verifica la existencia
	 * del operador a partir un filtro. No utilice findByAll() ya que no obtenia
	 * el resultado esperado.
	 * 
	 * @param cFiltro
	 *            String
	 * @throws DAOException
	 * @return TVCTRPersonal
	 */
	public TVCTRPersonal findOperador(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		TVCTRPersonal vCTRPersonal = null;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " select iCveEmpresa," + // 1
					"        iCvePlantilla," + // 2
					"        iNumPersonal," + // 3
					"        iCveExpediente," + // 4
					"        cNombre, " + // 5
					"        cApPaterno," + // 6
					"        cApMaterno," + // 7
					"        cRFC," + // 8
					"        cCURP," + // 9
					"        dtNacimiento, " + // 10
					"        iCvePaisNac," + // 11
					"        iCveEstadoNac," + // 12
					"        iCveMdoTrans," + // 13
					"        iCvePuesto," + // 14
					"        cLicencia, " + // 15
					"        cCalle," + // 16
					"        cNumExt," + // 17
					"        cNumInt," + // 18
					"        cColonia," + // 19
					"        iCP, " + // 20
					"        cCiudad," + // 21
					"        iCvePais," + // 22
					"        iCveEstado," + // 23
					"        iCveMunicipio," + // 24
					"        cTel," + // 25
					"        dtLicVencimiento," + // 26
					"        lActivo," + // 27
					"        lBaseEventual" + // 28
					" from CTRPersonal " + cFiltro;

			cValSQL = cSQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				vCTRPersonal = new TVCTRPersonal();
				vCTRPersonal.setICveEmpresa(rset.getInt(1));
				vCTRPersonal.setICvePlantilla(rset.getInt(2));
				vCTRPersonal.setINumPersonal(rset.getInt(3));
				vCTRPersonal.setICveExpediente(rset.getInt(4));
				vCTRPersonal.setCNombre(rset.getString(5));
				vCTRPersonal.setCApPaterno(rset.getString(6));
				vCTRPersonal.setCApMaterno(rset.getString(7));
				vCTRPersonal.setCRFC(rset.getString(8));
				vCTRPersonal.setCCURP(rset.getString(9));
				vCTRPersonal.setDtNacimiento(rset.getDate(10));
				vCTRPersonal.setICvePaisNac(rset.getInt(11));
				vCTRPersonal.setICveEstadoNac(rset.getInt(12));
				vCTRPersonal.setICveMdoTrans(rset.getInt(13));
				vCTRPersonal.setICvePuesto(rset.getInt(14));
				vCTRPersonal.setCLicencia(rset.getString(15));
				vCTRPersonal.setCCalle(rset.getString(16));
				vCTRPersonal.setCNumExt(rset.getString(17));
				vCTRPersonal.setCNumInt(rset.getString(18));
				vCTRPersonal.setCColonia(rset.getString(19));
				vCTRPersonal.setICP(rset.getInt(20));
				vCTRPersonal.setCCiudad(rset.getString(21));
				vCTRPersonal.setICvePais(rset.getInt(22));
				vCTRPersonal.setICveEstado(rset.getInt(23));
				vCTRPersonal.setICveMunicipio(rset.getInt(24));
				vCTRPersonal.setCTel(rset.getString(25));
				vCTRPersonal.setDtLicVencimiento(rset.getDate(26));
				vCTRPersonal.setlActivo(rset.getInt(27));
				vCTRPersonal.setlBaseEventual(rset.getInt(28));
				/*
				 * vCTRPersonal.setCDscEmpresa(rset.getString(26));
				 * vCTRPersonal.setCDscPaisNac(rset.getString(27));
				 * vCTRPersonal.setCDscEstadoNac(rset.getString(28));
				 * vCTRPersonal.setCDscMdotrans(rset.getString(29));
				 * vCTRPersonal.setCDscPuesto(rset.getString(30));
				 * vCTRPersonal.setCDscPais(rset.getString(31));
				 * vCTRPersonal.setCDscEstado(rset.getString(32));
				 * vCTRPersonal.setCDscMunicipio(rset.getString(33));
				 * vCTRPersonal.setDtRecepcion(rset.getDate(34));
				 */
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findOperador", ex);
			throw new DAOException("findOperador");
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
				warn("findOperador.close", ex2);
			}
		}

		return vCTRPersonal;

	}

	/**
	 * Agregado por Rafael Alcocer Caldera 07/Ago/2006 Verifica la existencia
	 * del operador a partir un filtro. No utilice findByAll() ya que no obtenia
	 * el resultado esperado.
	 * 
	 * @param cFiltro
	 *            String
	 * @throws DAOException
	 * @return TVCTRPersonal2
	 */
	public int findOperador2(String cFiltro) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int existe = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = " select count(iCveExpediente)" + " from CTRPersonal "
					+ cFiltro;

			cValSQL = cSQL;
			pstmt = conn.prepareStatement(cSQL);
			// System.out.println(cSQL);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				existe = rset.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findOperador", ex);
			throw new DAOException("findOperador");
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
				warn("findOperador.close", ex2);
			}
		}

		return existe;

	}

	/**
	 * Agregado por Rafael Alcocer Caldera 04/Ago/2006 Obtiene iCvePais de la
	 * tabla GRLPais a partir de cNombre.
	 * 
	 * @param cNombre
	 *            String nombre del pais
	 * @throws DAOException
	 * @return int
	 */
	public int findICvePaisByCNombre(String cNombre) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iCvePais = 1; // Por default MEXICO

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " select  iCvePais " + " from    GRLPais "
					+ " where   cNombre = " + "\'" + cNombre + "\' "
					+ " or      cAbreviatura = " + "\'" + cNombre + "\'";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				iCvePais = rset.getInt(1);
			}
		} catch (Exception ex) {
			warn("findICVEPaisByNombre", ex);
			throw new DAOException("findICVEPaisByNombre");
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
				warn("findICVEPaisByNombre.close", ex2);
			}
		}

		return iCvePais;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 04/Ago/2006 Obtiene iCveMdoTrans de
	 * la tabla GRLMdoTrans a partir de cDscMdoTrans
	 * 
	 * @param cDscMdoTrans
	 *            String descripcion del modo de transporte
	 * @throws DAOException
	 * @return int
	 */
	public int findICveMdoTransByCDscMdoTrans(String cDscMdoTrans)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iCveMdoTrans = 0; // Por default no existe

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " select  iCveMdoTrans " + " from    GRLMdoTrans "
					+ " where   cDscMdotrans = " + "\'" + cDscMdoTrans + "\'";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				iCveMdoTrans = rset.getInt(1);
			}
		} catch (Exception ex) {
			warn("findICveMdoTransByCDscMdoTrans", ex);
			throw new DAOException("findICveMdoTransByCDscMdoTrans");
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
				warn("findICveMdoTransByCDscMdoTrans.close", ex2);
			}
		}

		return iCveMdoTrans;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 04/Ago/2006 Obtiene iCveEstado de la
	 * tabla GRLENTIDADFED a partir de iCvePais y de cNombre
	 * 
	 * @param iCvePais
	 *            int
	 * @param cNombre
	 *            String nombre del estado
	 * @throws DAOException
	 * @return int iCveEstado
	 */
	public int findICveEstadoByCNombre(int iCvePais, String cNombre)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iCveEstado = 0; // Por default DESCONOCIDO

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " select  iCveEntidadFed " + " from    GRLEntidadFed "
					+ " where   iCvePais = " + iCvePais + " and     cNombre = "
					+ "\'" + cNombre + "\'";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				iCveEstado = rset.getInt(1);
			}
		} catch (Exception ex) {
			warn("findICveEstadoByCNombre", ex);
			throw new DAOException("findICveEstadoByCNombre");
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
				warn("findICveEstadoByCNombre.close", ex2);
			}
		}

		return iCveEstado;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 04/Ago/2006 Obtiene iCvePuesto de la
	 * tabla GRLPUESTO a partir de iCveModoTrans y cDscPuesto
	 * 
	 * @param iCveModoTrans
	 *            int
	 * @param cDscPuesto
	 *            String
	 * @throws DAOException
	 * @return int
	 */
	public int findICvePuestoByCDscPuesto(int iCveModoTrans, String cDscPuesto)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iCvePuesto = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " select  iCvePuesto " + " from    GRLPuesto "
					+ " where   iCveMdoTrans = " + iCveModoTrans
					+ " and     cDscPuesto = " + "\'" + cDscPuesto + "\'";

			// System.out.println("findICvePuestoByCDscPuesto \n"+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				iCvePuesto = rset.getInt(1);
			}
		} catch (Exception ex) {
			warn("findICvePuestoByCDscPuesto", ex);
			throw new DAOException("findICvePuestoByCDscPuesto");
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
				warn("findICvePuestoByCDscPuesto.close", ex2);
			}
		}

		return iCvePuesto;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 04/Ago/2006 Obtiene iCveMunicipio de
	 * la tabla GRLMUNICIPIO a partir de iCvePais, iCveEntidadFed y cNombre
	 * 
	 * @param iCvePais
	 *            int
	 * @param iCveEntidadFed
	 *            int
	 * @param cNombre
	 *            String
	 * @throws DAOException
	 * @return int
	 */
	public int findICveMunicipioByCNombre(int iCvePais, int iCveEntidadFed,
			String cNombre) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int iCvePuesto = 0;

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = " select  iCveMunicipio " + " from    GRLMunicipio "
					+ " where   iCvePais = " + iCvePais
					+ " and     iCveEntidadFed = " + iCveEntidadFed
					+ " and     cNombre = " + "\'" + cNombre + "\'";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				iCvePuesto = rset.getInt(1);
			}
		} catch (Exception ex) {
			warn("findICveMunicipioByCNombre", ex);
			throw new DAOException("findICveMunicipioByCNombre");
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
				warn("findICveMunicipioByCNombre.close", ex2);
			}
		}

		return iCvePuesto;
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cFiltro, String cOrden) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPersonal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPersonal vCTRPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select  "
					+ " CTRPersonal.iCveEmpresa, CTRPersonal.iCvePlantilla, CTRPersonal.iNumPersonal, CTRPersonal.iCveExpediente, CTRPersonal.cNombre, "
					+ " CTRPersonal.cApPaterno,  CTRPersonal.cApMaterno,    CTRPersonal.cRFC,         CTRPersonal.cCURP,          CTRPersonal.dtNacimiento, "
					+ " CTRPersonal.iCvePaisNac, CTRPersonal.iCveEstadoNac, CTRPersonal.iCveMdoTrans, CTRPersonal.iCvePuesto,     CTRPersonal.cLicencia, "
					+ " CTRPersonal.cCalle,      CTRPersonal.cNumExt,       CTRPersonal.cNumInt,      CTRPersonal.cColonia,       CTRPersonal.iCP, "
					+ " CTRPersonal.cCiudad,     CTRPersonal.iCvePais,      CTRPersonal.iCveEstado,   CTRPersonal.iCveMunicipio,  CTRPersonal.cTel, "
					+ " (GRLEmpresas.cNombreRS || ' ' || GRLEmpresas.cApPaterno || ' ' || GRLEmpresas.cApMaterno) as cDscEmpresa, "
					+
					// " CTRtpoentrega.cDscTpoEntrega, " +
					" GRLPais.cNombre as cDscPaisNac, "
					+ " GRLEntidadFedNac.cNombre as cDscEstadoNac, "
					+ " GRLMdoTrans.cDscMdotrans, "
					+ " GRLPuesto.cDscPuesto, "
					+ " GRLPais.cNombre as cDscPais, "
					+ " GRLEntidadFed.cNombre as cDscEstado, "
					+ " GRLMunicipio.cNombre as cDscMunicipio, "
					+ " CTRPlantilla.dtRecepcion, "
					+ " CTRPersonal.dtLicVencimiento, "
					+ " CTRPersonal.lActivo, "
					+ " CTRPersonal.lBaseEventual "
					+ " from CTRPersonal "
					+ " join GRLEmpresas ON GRLEmpresas.iCveEmpresa = CTRPersonal.iCveEmpresa "
					+ " join CTRPlantilla ON CTRPlantilla.iCveEmpresa = CTRPersonal.iCveEmpresa "
					+ "                  AND CTRPlantilla.iCvePlantilla = CTRPersonal.iCvePlantilla "
					+
					// " join CTRTpoEntrega ON CTRTpoentrega.iCveTpoEntrega = CTRPlantilla.iCveTpoEntrega "
					// +
					" join GRLPais GRLPaisNac ON GRLPaisNac.iCvePais = CTRPersonal.iCvePaisNac "
					+ " join GRLEntidadFed GRLEntidadFedNac ON GRLEntidadFedNac.iCvePais = CTRPersonal.iCvePaisNac AND "
					+ "                                        GRLEntidadFedNac.iCveEntidadFed = CTRPersonal.iCveEstadoNac "
					+ " join GRLMdoTrans ON GRLMdoTrans.iCveMdoTrans = CTRPersonal.iCveMdoTrans "
					+ " join GRLPuesto ON GRLPuesto.iCveMdoTrans = CTRPersonal.iCveMdoTrans AND "
					+ "                   GRLPuesto.iCvePuesto = CTRPersonal.iCvePuesto "
					+ " join GRLPais ON GRLPais.iCvePais = CTRPersonal.iCvePais "
					+ " join GRLEntidadFed ON GRLEntidadFed.iCvePais = CTRPersonal.iCvePais  AND "
					+ "                       GRLEntidadFed.iCveEntidadFed = CTRPersonal.iCveEstado "
					+ " join GRLMunicipio ON GRLMunicipio.iCvePais = CTRPersonal.iCvePais AND "
					+ "                      GRLMunicipio.iCveEntidadFed = CTRPersonal.iCveEstado AND "
					+ "                      GRLMunicipio.iCvemunicipio = CTRPersonal.iCveMunicipio "
					+ cFiltro + " " + cOrden;

			cValSQL = cSQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPersonal = new TVCTRPersonal();
				vCTRPersonal.setICveEmpresa(rset.getInt(1));
				vCTRPersonal.setICvePlantilla(rset.getInt(2));
				vCTRPersonal.setINumPersonal(rset.getInt(3));
				vCTRPersonal.setICveExpediente(rset.getInt(4));
				vCTRPersonal.setCNombre(rset.getString(5));
				vCTRPersonal.setCApPaterno(rset.getString(6));
				vCTRPersonal.setCApMaterno(rset.getString(7));
				vCTRPersonal.setCRFC(rset.getString(8));
				vCTRPersonal.setCCURP(rset.getString(9));
				vCTRPersonal.setDtNacimiento(rset.getDate(10));
				vCTRPersonal.setICvePaisNac(rset.getInt(11));
				vCTRPersonal.setICveEstadoNac(rset.getInt(12));
				vCTRPersonal.setICveMdoTrans(rset.getInt(13));
				vCTRPersonal.setICvePuesto(rset.getInt(14));
				vCTRPersonal.setCLicencia(rset.getString(15));
				vCTRPersonal.setCCalle(rset.getString(16));
				vCTRPersonal.setCNumExt(rset.getString(17));
				vCTRPersonal.setCNumInt(rset.getString(18));
				vCTRPersonal.setCColonia(rset.getString(19));
				vCTRPersonal.setICP(rset.getInt(20));
				vCTRPersonal.setCCiudad(rset.getString(21));
				vCTRPersonal.setICvePais(rset.getInt(22));
				vCTRPersonal.setICveEstado(rset.getInt(23));
				vCTRPersonal.setICveMunicipio(rset.getInt(24));
				vCTRPersonal.setCTel(rset.getString(25));
				vCTRPersonal.setCDscEmpresa(rset.getString(26));
				vCTRPersonal.setCDscPaisNac(rset.getString(27));
				vCTRPersonal.setCDscEstadoNac(rset.getString(28));
				vCTRPersonal.setCDscMdotrans(rset.getString(29));
				vCTRPersonal.setCDscPuesto(rset.getString(30));
				vCTRPersonal.setCDscPais(rset.getString(31));
				vCTRPersonal.setCDscEstado(rset.getString(32));
				vCTRPersonal.setCDscMunicipio(rset.getString(33));
				vCTRPersonal.setDtRecepcion(rset.getDate(34));
				vCTRPersonal.setDtLicVencimiento(rset.getDate(35));
				vCTRPersonal.setlActivo(rset.getInt(36));
				vCTRPersonal.setlBaseEventual(rset.getInt(37));
				vcCTRPersonal.addElement(vCTRPersonal);
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
			return vcCTRPersonal;
		}
	}

	/**
	 * Busqueda del Personal que tiene una misma Licencia.
	 */
	public Vector FindByLicencia(String cFiltro, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPersonal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPersonal vCTRPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select  "
					+ " CTRPersonal.iCveEmpresa, CTRPersonal.iCvePlantilla, CTRPersonal.iNumPersonal, CTRPersonal.iCveExpediente, CTRPersonal.cNombre, "
					+ " CTRPersonal.cApPaterno,  CTRPersonal.cApMaterno,    CTRPersonal.cRFC,         CTRPersonal.cCURP,          CTRPersonal.dtNacimiento, "
					+ " CTRPersonal.iCvePaisNac, CTRPersonal.iCveEstadoNac, CTRPersonal.iCveMdoTrans, CTRPersonal.iCvePuesto,     CTRPersonal.cLicencia, "
					+ " CTRPersonal.cCalle,      CTRPersonal.cNumExt,       CTRPersonal.cNumInt,      CTRPersonal.cColonia,       CTRPersonal.iCP, "
					+ " CTRPersonal.cCiudad,     CTRPersonal.iCvePais,      CTRPersonal.iCveEstado,   CTRPersonal.iCveMunicipio,  CTRPersonal.cTel, "
					+ " (GRLEmpresas.cNombreRS || ' ' || GRLEmpresas.cApPaterno || ' ' || GRLEmpresas.cApMaterno) as cDscEmpresa, "
					+ " GRLPais.cNombre as cDscPaisNac, "
					+ " GRLEntidadFedNac.cNombre as cDscEstadoNac, "
					+ " GRLMdoTrans.cDscMdotrans, "
					+ " GRLPuesto.cDscPuesto, "
					+ " GRLPais.cNombre as cDscPais, "
					+ " GRLEntidadFed.cNombre as cDscEstado, "
					+ " GRLMunicipio.cNombre as cDscMunicipio, "
					+ " CTRPlantilla.dtRecepcion, "
					+ " CTRPersonal.dtLicVencimiento "
					+ " from CTRPersonal "
					+ " join GRLEmpresas ON GRLEmpresas.iCveEmpresa = CTRPersonal.iCveEmpresa "
					+ " join CTRPlantilla ON CTRPlantilla.iCveEmpresa = CTRPersonal.iCveEmpresa "
					+ "                  AND CTRPlantilla.iCvePlantilla = CTRPersonal.iCvePlantilla "
					+ " join GRLPais GRLPaisNac ON GRLPaisNac.iCvePais = CTRPersonal.iCvePaisNac "
					+ " join GRLEntidadFed GRLEntidadFedNac ON GRLEntidadFedNac.iCvePais = CTRPersonal.iCvePaisNac AND "
					+ "                                        GRLEntidadFedNac.iCveEntidadFed = CTRPersonal.iCveEstadoNac "
					+ " join GRLMdoTrans ON GRLMdoTrans.iCveMdoTrans = CTRPersonal.iCveMdoTrans "
					+ " join GRLPuesto ON GRLPuesto.iCveMdoTrans = CTRPersonal.iCveMdoTrans AND "
					+ "                   GRLPuesto.iCvePuesto = CTRPersonal.iCvePuesto "
					+ " join GRLPais ON GRLPais.iCvePais = CTRPersonal.iCvePais "
					+ " join GRLEntidadFed ON GRLEntidadFed.iCvePais = CTRPersonal.iCvePais  AND "
					+ "                       GRLEntidadFed.iCveEntidadFed = CTRPersonal.iCveEstado "
					+ " join GRLMunicipio ON GRLMunicipio.iCvePais = CTRPersonal.iCvePais AND "
					+ "                      GRLMunicipio.iCveEntidadFed = CTRPersonal.iCveEstado AND "
					+ "                      GRLMunicipio.iCvemunicipio = CTRPersonal.iCveMunicipio "
					+ cFiltro + " " + cOrden;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPersonal = new TVCTRPersonal();
				vCTRPersonal.setICveEmpresa(rset.getInt(1));
				vCTRPersonal.setICvePlantilla(rset.getInt(2));
				vCTRPersonal.setINumPersonal(rset.getInt(3));
				vCTRPersonal.setICveExpediente(rset.getInt(4));
				vCTRPersonal.setCNombre(rset.getString(5));
				vCTRPersonal.setCApPaterno(rset.getString(6));
				vCTRPersonal.setCApMaterno(rset.getString(7));
				vCTRPersonal.setCRFC(rset.getString(8));
				vCTRPersonal.setCCURP(rset.getString(9));
				vCTRPersonal.setDtNacimiento(rset.getDate(10));
				vCTRPersonal.setICvePaisNac(rset.getInt(11));
				vCTRPersonal.setICveEstadoNac(rset.getInt(12));
				vCTRPersonal.setICveMdoTrans(rset.getInt(13));
				vCTRPersonal.setICvePuesto(rset.getInt(14));
				vCTRPersonal.setCLicencia(rset.getString(15));
				vCTRPersonal.setCCalle(rset.getString(16));
				vCTRPersonal.setCNumExt(rset.getString(17));
				vCTRPersonal.setCNumInt(rset.getString(18));
				vCTRPersonal.setCColonia(rset.getString(19));
				vCTRPersonal.setICP(rset.getInt(20));
				vCTRPersonal.setCCiudad(rset.getString(21));
				vCTRPersonal.setICvePais(rset.getInt(22));
				vCTRPersonal.setICveEstado(rset.getInt(23));
				vCTRPersonal.setICveMunicipio(rset.getInt(24));
				vCTRPersonal.setCTel(rset.getString(25));
				vCTRPersonal.setCDscEmpresa(rset.getString(26));
				vCTRPersonal.setCDscPaisNac(rset.getString(27));
				vCTRPersonal.setCDscEstadoNac(rset.getString(28));
				vCTRPersonal.setCDscMdotrans(rset.getString(29));
				vCTRPersonal.setCDscPuesto(rset.getString(30));
				vCTRPersonal.setCDscPais(rset.getString(31));
				vCTRPersonal.setCDscEstado(rset.getString(32));
				vCTRPersonal.setCDscMunicipio(rset.getString(33));
				vCTRPersonal.setDtRecepcion(rset.getDate(34));
				vCTRPersonal.setDtLicVencimiento(rset.getDate(35));
				vcCTRPersonal.addElement(vCTRPersonal);
			}
		} catch (Exception ex) {
			warn("FindByLicencia", ex);
			throw new DAOException("FindByLicencia");
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
				warn("FindByLicencia.close", ex2);
			}
			return vcCTRPersonal;
		}
	}

	/**
	 * Busqueda del Personal que tiene una misma Licencia.
	 */
	public Vector FindWhereAptitud(int iCveEmpresa, int iCvePlantilla,
			int iNumPersonal) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPersonal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPersonal vCTRPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select PERLicencia.lDictamen "
					+ " from   CTRPersonal           "
					+ " join GRLPuesto               "
					+ " on GRLPuesto.iCveMdoTrans = CTRPersonal.iCveMdoTrans         "
					+ " and GRLPuesto.iCvePuesto = CTRPersonal.iCvePuesto            "
					+ " left join PERLicencia                                        "
					+ " on PERLicencia.iCvePersonal     = CTRPersonal.iCveExpediente "
					+ " and PERLicencia.iCveMdoTrans    = CTRPersonal.iCveMdoTrans   "
					+ " and PERLicencia.iCveCategoria   = GRLPuesto.iCveCategoria    "
					+ " where CTRPersonal.iCveEmpresa   = " + iCveEmpresa
					+ " and   CTRPersonal.iCvePlantilla = " + iCvePlantilla
					+ " and CTRPersonal.iNumPersonal    = " + iNumPersonal + "";

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPersonal = new TVCTRPersonal();
				vCTRPersonal.setICP(rset.getInt(1));
				vcCTRPersonal.addElement(vCTRPersonal);
			}
		} catch (Exception ex) {
			warn("FindByLicencia", ex);
			throw new DAOException("FindByLicencia");
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
				warn("FindByLicencia.close", ex2);
			}
			return vcCTRPersonal;
		}
	}

	public Vector FindByUltimaEtapa() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPersonal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPersonal vCTRPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select GRLEmpresas.iCveEmpresa,       "
					+ "        max(CTRPersonal.iCvePlantilla), "
					+ "        CTRPersonal.iNumPersonal,       "
					+ "        CTRPersonal.cRFC,               "
					+ "        CTRPersonal.lActivo             "
					+ " from CTRPersonal                       "
					+ " join GRLEmpresas on GRLEmpresas.iCveEmpresa = CTRPersonal.iCveEmpresa "
					+ " group by GRLEmpresas.iCveEmpresa,  "
					+ "          CTRPersonal.iNumPersonal, "
					+ "          CTRPersonal.cRFC,         "
					+ "          CTRPersonal.lActivo       " + "";

			cValSQL = cSQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPersonal = new TVCTRPersonal();
				vCTRPersonal.setICveEmpresa(rset.getInt(1));
				vCTRPersonal.setICvePlantilla(rset.getInt(2));
				vCTRPersonal.setINumPersonal(rset.getInt(3));
				vCTRPersonal.setCRFC(rset.getString(4));
				vCTRPersonal.setlActivo(rset.getInt(5));
				vcCTRPersonal.addElement(vCTRPersonal);
			}
		} catch (Exception ex) {
			warn("FindByultimaEtapa", ex);
			throw new DAOException("FindByUltimaEtapa");
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
				warn("FindByUltimaEtapa.close", ex2);
			}
			return vcCTRPersonal;
		}
	}

	public Vector FindByPenultimaEtapa() throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPersonal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPersonal vCTRPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = " select GRLEmpresas.iCveEmpresa,           "
					+ "        max(CTRPersonal.iCvePlantilla) -1, "
					+ "        CTRPersonal.iNumPersonal,          "
					+ "        CTRPersonal.cRFC,                  "
					+ "        CTRPersonal.lActivo                "
					+ " from CTRPersonal                          "
					+ " join GRLEmpresas on GRLEmpresas.iCveEmpresa = CTRPersonal.iCveEmpresa "
					+ " group by GRLEmpresas.iCveEmpresa,  "
					+ "          CTRPersonal.iNumPersonal, "
					+ "          CTRPersonal.cRFC,         "
					+ "          CTRPersonal.lActivo       " + "";

			cValSQL = cSQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPersonal = new TVCTRPersonal();
				vCTRPersonal.setICveEmpresa(rset.getInt(1));
				vCTRPersonal.setICvePlantilla(rset.getInt(2));
				vCTRPersonal.setINumPersonal(rset.getInt(3));
				vCTRPersonal.setCRFC(rset.getString(4));
				vCTRPersonal.setlActivo(rset.getInt(5));
				vcCTRPersonal.addElement(vCTRPersonal);
			}
		} catch (Exception ex) {
			warn("FindByPenultimaEtapa", ex);
			throw new DAOException("FindByPenUltimaEtapa");
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
				warn("FindByPenUltimaEtapa.close", ex2);
			}
			return vcCTRPersonal;
		}
	}

	public Vector FindCountPersonal(String cCondicion) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcCTRPersonal = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVCTRPersonal vCTRPersonal;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select CTRPersonal.iCveEmpresa,   "
					+ "       CTRPersonal.iCvePlantilla, "
					+ "       count(*)                   "
					+ "from CTRPersonal                  " + cCondicion;

			cValSQL = cSQL;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vCTRPersonal = new TVCTRPersonal();
				vCTRPersonal.setICveEmpresa(rset.getInt(1));
				vCTRPersonal.setICvePlantilla(rset.getInt(2));
				vCTRPersonal.setINumPersonal(rset.getInt(3));
				vcCTRPersonal.addElement(vCTRPersonal);
			}
		} catch (Exception ex) {
			warn("FindCountPersonal", ex);
			throw new DAOException("FindCountPersonal");
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
				warn("FindCountPersonal.close", ex2);
			}
			return vcCTRPersonal;
		}
	}

	/**
	 * Metodo Insert
	 */
	public String insert(Connection conGral, Object obj) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null, pstmtMax = null;
		ResultSet rsetMax = null;
		String cClave = "";
		int iMax = 0;
		int iInsertQuery = 0; // Agregada por Rafael Alcocer Caldera 10/Ago/2006
		iInserted = 0; // Agregada por Rafael Alcocer Caldera 10/Ago/2006

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVCTRPersonal vCTRPersonal = (TVCTRPersonal) obj;

			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT MAX(iNumPersonal) FROM CTRPersonal WHERE  CTRPersonal.iCveEmpresa = ? AND CTRPersonal.iCvePlantilla = ?";
			pstmtMax = conn.prepareStatement(cSQL);
			pstmtMax.setInt(1, vCTRPersonal.getICveEmpresa());
			pstmtMax.setInt(2, vCTRPersonal.getiCvePlantilla());
			rsetMax = pstmtMax.executeQuery();
			if (rsetMax.next())
				iMax = rsetMax.getInt(1);
			iMax++;
			vCTRPersonal.setINumPersonal(iMax);
			cClave = "" + iMax;

			cSQL = "insert into CTRPersonal(" + "iCveEmpresa,"
					+ "iCvePlantilla," + "iNumPersonal," + "iCveExpediente,"
					+ "cNombre,"
					+ "cApPaterno,"
					+ "cApMaterno,"
					+ "cRFC,"
					+ "cCURP,"
					+ "dtNacimiento,"
					+ "iCvePaisNac,"
					+ "iCveEstadoNac,"
					+ "iCveMdoTrans,"
					+ "iCvePuesto,"
					+ "cLicencia,"
					+ "cCalle,"
					+ "cNumExt,"
					+ "cNumInt,"
					+ "cColonia,"
					+ "iCP,"
					+ "cCiudad,"
					+ "iCvePais,"
					+ "iCveEstado,"
					+ "iCveMunicipio,"
					+ "cTel, "
					+ "dtLicVencimiento, "
					+ "lActivo, "
					+ "lBaseEventual"
					+
					// ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					// Nuevos parametros
					// Agregados el 2 de abril 2012 por AG SA.
					",cRExaTox, "
					+ "cRExaDAlc "
					+ ")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(cSQL);
			// DEBE DE INGRESAR C�DIGO PARA CALCULAR EL CONSECUTIVO DE LA
			// TABLA
			pstmt.setInt(1, vCTRPersonal.getICveEmpresa());
			pstmt.setInt(2, vCTRPersonal.getiCvePlantilla());
			pstmt.setInt(3, vCTRPersonal.getINumPersonal());
			pstmt.setInt(4, vCTRPersonal.getICveExpediente());
			pstmt.setString(5, vCTRPersonal.getCNombre());
			pstmt.setString(6, vCTRPersonal.getCApPaterno());
			pstmt.setString(7, vCTRPersonal.getCApMaterno());
			pstmt.setString(8, vCTRPersonal.getCRFC());
			pstmt.setString(9, vCTRPersonal.getCCURP());
			pstmt.setDate(10, vCTRPersonal.getDtNacimiento());
			pstmt.setInt(11, vCTRPersonal.getICvePaisNac());
			pstmt.setInt(12, vCTRPersonal.getICveEstadoNac());
			pstmt.setInt(13, vCTRPersonal.getiCveMdoTrans());
			pstmt.setInt(14, vCTRPersonal.getICvePuesto());
			pstmt.setString(15, vCTRPersonal.getCLicencia());
			pstmt.setString(16, vCTRPersonal.getCCalle());
			pstmt.setString(17, vCTRPersonal.getCNumExt());
			pstmt.setString(18, vCTRPersonal.getCNumInt());
			pstmt.setString(19, vCTRPersonal.getCColonia());
			pstmt.setInt(20, vCTRPersonal.getICP());
			pstmt.setString(21, vCTRPersonal.getCCiudad());
			pstmt.setInt(22, vCTRPersonal.getICvePais());
			pstmt.setInt(23, vCTRPersonal.getICveEstado());
			pstmt.setInt(24, vCTRPersonal.getICveMunicipio());
			pstmt.setString(25, vCTRPersonal.getCTel());
			pstmt.setDate(26, vCTRPersonal.getDtLicVencimiento());
			pstmt.setInt(27, vCTRPersonal.getlActivo());
			pstmt.setInt(28, vCTRPersonal.getlBaseEventual());
			// Agregados el 2 de abril 2012 por AG SA.
			pstmt.setString(29, vCTRPersonal.getCRExaTox());
			pstmt.setString(30, vCTRPersonal.getCRExaDAlc());
			iInsertQuery = pstmt.executeUpdate(); // Modificada por Rafael
													// Alcocer Caldera
													// 10/Ago/2006

			cClave = "" + vCTRPersonal.getICveEmpresa();
			/*
			 * Modificado por Rafael Alcocer Caldera
			 * ************************************* if (conGral == null) {
			 * conn.commit(); }
			 */
			if (conGral == null) {
				// Si iInsertQuery == 1 significa que pstmt.executeUpdate()
				// insert� un renglon en la base de datos y al dar commit()
				// guardo el cambio, en caso contrario hago rollback()
				if (iInsertQuery == 1) {
					conn.commit();
					// Despu�s de hacer commit() asigno iInsertQuery a
					// iInserted
					// con esto al llamar a getIInserted() despu�s de ejecutar
					// este Metodo insert() aseguro que realmente se insert�
					// un rengl�n en la tabla
					iInserted = iInsertQuery;
				} else {
					conn.rollback();
				}
			}
			// *************************************
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
				if (rsetMax != null) {
					rsetMax.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (pstmtMax != null) {
					pstmtMax.close();
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
		int iUpdateQuery = 0; // Agregada por Rafael Alcocer Caldera 10/Ago/2006
		iUpdated = 0; // Agregada por Rafael Alcocer Caldera 10/Ago/2006

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVCTRPersonal vCTRPersonal = (TVCTRPersonal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "update CTRPersonal " + " set iCveExpediente= ?, "
					+ "     cNombre= ?, " + "     cApPaterno= ?, "
					+ "     cApMaterno= ?, " + "     cRFC= ?, "
					+ "     cCURP= ?, " + "     dtNacimiento= ?, "
					+ "     iCvePaisNac= ?, " + "     iCveEstadoNac= ?, "
					+ "     iCveMdoTrans= ?, " + "     iCvePuesto= ?, "
					+ "     cLicencia= ?, " + "     cCalle= ?, "
					+ "     cNumExt= ?, " + "     cNumInt= ?, "
					+ "     cColonia= ?, " + "     iCP= ?, "
					+ "     cCiudad= ?, " + "     iCvePais= ?, "
					+ "     iCveEstado= ?, "
					+ "     iCveMunicipio= ?, "
					+ "     cTel =  ?, "
					+ "     dtLicVencimiento = ? ,"
					+ "     lActivo = ? ,"
					+ "     lBaseEventual = ? "
					+
					// Nuevos parametros
					// Agregados el 2 de abril 2012 por AG SA.
					",     cRExaTox = ?, " + "      cRExaDAlc = ? "
					+ " where iCveEmpresa =  ? " + " and iCvePlantilla =  ? "
					+ " and iNumPersonal = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRPersonal.getICveExpediente());
			pstmt.setString(2, vCTRPersonal.getCNombre());
			pstmt.setString(3, vCTRPersonal.getCApPaterno());
			pstmt.setString(4, vCTRPersonal.getCApMaterno());
			pstmt.setString(5, vCTRPersonal.getCRFC());
			pstmt.setString(6, vCTRPersonal.getCCURP());
			pstmt.setDate(7, vCTRPersonal.getDtNacimiento());
			pstmt.setInt(8, vCTRPersonal.getICvePaisNac());
			pstmt.setInt(9, vCTRPersonal.getICveEstadoNac());
			pstmt.setInt(10, vCTRPersonal.getiCveMdoTrans());
			pstmt.setInt(11, vCTRPersonal.getICvePuesto());
			pstmt.setString(12, vCTRPersonal.getCLicencia());
			pstmt.setString(13, vCTRPersonal.getCCalle());
			pstmt.setString(14, vCTRPersonal.getCNumExt());
			pstmt.setString(15, vCTRPersonal.getCNumInt());
			pstmt.setString(16, vCTRPersonal.getCColonia());
			pstmt.setInt(17, vCTRPersonal.getICP());
			pstmt.setString(18, vCTRPersonal.getCCiudad());
			pstmt.setInt(19, vCTRPersonal.getICvePais());
			pstmt.setInt(20, vCTRPersonal.getICveEstado());
			pstmt.setInt(21, vCTRPersonal.getICveMunicipio());
			pstmt.setString(22, vCTRPersonal.getCTel());
			pstmt.setDate(23, vCTRPersonal.getDtLicVencimiento());
			pstmt.setInt(24, vCTRPersonal.getlActivo());
			pstmt.setInt(25, vCTRPersonal.getlBaseEventual());

			pstmt.setString(26, vCTRPersonal.getCRExaTox());
			pstmt.setString(27, vCTRPersonal.getCRExaDAlc());

			// System.out.println(""+vCTRPersonal.getCRExaTox());
			// System.out.println(""+vCTRPersonal.getCRExaDAlc());

			pstmt.setInt(28, vCTRPersonal.getICveEmpresa());
			pstmt.setInt(29, vCTRPersonal.getiCvePlantilla());
			pstmt.setInt(30, vCTRPersonal.getINumPersonal());
			iUpdateQuery = pstmt.executeUpdate(); // Modificada por Rafael
													// Alcocer Caldera
													// 10/Ago/2006

			cClave = "";
			/*
			 * Modificado por Rafael Alcocer Caldera
			 * ************************************* if (conGral == null) {
			 * conn.commit(); }
			 */
			if (conGral == null) {
				// Si iUpdateQuery == 1 significa que pstmt.executeUpdate()
				// actualiz� un rengl�n en la base de datos y al dar
				// commit()
				// guardo el cambio, en caso contrario hago rollback()
				if (iUpdateQuery == 1) {
					conn.commit();
					// Despu�s de hacer commit() asigno iUpdateQuery a
					// iUpdated
					// con esto al llamar a getIUpdated() despu�s de ejecutar
					// este Metodo update() aseguro que realmente se actualiz�
					// un rengl�n en la tabla
					iUpdated = iUpdateQuery;
				} else {
					conn.rollback();
				}
			}
			// *************************************
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
		// int iEntidades = 0; // No se usa
		int iDeleteQuery = 0; // Agregada por Rafael Alcocer Caldera 10/Ago/2006
		iDeleted = 0;

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			String cSQL = "";
			TVCTRPersonal vCTRPersonal = (TVCTRPersonal) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL = "delete from CTRPersonal" + " where iCveEmpresa = ?"
					+ " and iCvePlantilla = ?" + " and iNumPersonal = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, vCTRPersonal.getICveEmpresa());
			pstmt.setInt(2, vCTRPersonal.getiCvePlantilla());
			pstmt.setInt(3, vCTRPersonal.getINumPersonal());
			iDeleteQuery = pstmt.executeUpdate(); // Modificada por Rafael
													// Alcocer Caldera
													// 10/Ago/2006

			cClave = "";

			/*
			 * * Modificado por Rafael Alcocer Caldera
			 * ************************************* if (conGral == null) {
			 * conn.commit(); }
			 */
			if (conGral == null) {
				// Si iDeleteQuery == 1 significa que pstmt.executeUpdate()
				// actualiz� un rengl�n en la base de datos y al dar
				// commit()
				// guardo el cambio, en caso contrario hago rollback()
				if (iDeleteQuery == 1) {
					conn.commit();
					// Despu�s de hacer commit() asigno iDeleteQuery a
					// iDeleted
					// con esto al llamar a getIDeleted() despu�s de ejecutar
					// este Metodo delete() aseguro que realmente se elimin�
					// un rengl�n en la tabla
					iDeleted = iDeleteQuery;
				} else {
					conn.rollback();
				}
			}
			// *************************************
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
				warn("delete.closeCTRPersonal", ex2);
			}
			return cClave;
		}
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 10/Ago/2006
	 * 
	 * @return int
	 */
	public int getIDeleted() {
		return iDeleted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 10/Ago/2006
	 * 
	 * @param deleted
	 *            int
	 */
	public void setIDeleted(int deleted) {
		iDeleted = deleted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 10/Ago/2006
	 * 
	 * @return int
	 */
	public int getIInserted() {
		return iInserted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 10/Ago/2006
	 * 
	 * @param inserted
	 *            int
	 */
	public void setIInserted(int inserted) {
		iInserted = inserted;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 10/Ago/2006
	 * 
	 * @return int
	 */
	public int getIUpdated() {
		return iUpdated;
	}

	/**
	 * Agregado por Rafael Alcocer Caldera 10/Ago/2006
	 * 
	 * @param updated
	 *            int
	 */
	public void setIUpdated(int updated) {
		iUpdated = updated;
	}
}
