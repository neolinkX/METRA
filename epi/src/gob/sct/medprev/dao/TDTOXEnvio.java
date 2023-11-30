package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;

/**
 * <p>
 * Title: Data Acces Object de TOXMarcaSust DAO
 * </p>
 * <p>
 * Description: DAO de la entidad TOXMarcaSust
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

public class TDTOXEnvio extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXEnvio() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXEnvio = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

			// System.out.println("Fecha por Default ==>> "+defaultFecha);

			String iFechaEnvio = "";
			TFechas dtFecha = new TFechas();

			TVTOXEnvio vTOXEnvio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "Select	a.iCveEnvio,      "
					+ "          a.iCveUniMed, "
					+ "          a.iAnio,  "
					+ "          iCveLaboratorio,"
					+ // Esta definicion se cambiara posteriormente por el campo
						// real
					"          a.iCveUsuEnvia, "
					+ "          a.dtEnvio,      "
					+ "          a.iCveTipoEnvio,"
					+ "          a.dtRecepcion,  "
					+ "          a.iCveUsuRec,   "
					+ "          a.cObsEnvio,    "
					+ "          a.cObsRecep,    "
					+ "          cDscTipoEnvio,  "
					+ "          E.cNombre || ' ' ||E.cApPaterno||' '||E.cApMaterno cNombre, "
					+ "          R.cNombre || ' ' ||R.cApPaterno||' '||R.cApMaterno cRECIBE, "
					+ "          (select count(*) "
					+ "           from TOXMuestra M "
					+ "            where M.iAnio = a.iAnio "
					+ "              and M.iCveUniMed = a.iCveUniMed "
					+ "              and M.iCveEnvio  = a.iCveEnvio) as iNumMuestras "
					+ "From	TOXEnvio a      "
					+ "left join  GrlTipoEnvio on GrlTipoEnvio.iCveTipoEnvio = a.iCveTipoEnvio "
					+ "left join  SegUsuario E on E.iCveUsuario = a.iCveUsuEnvia "
					+ "left join  SegUsuario R on R.iCveUsuario = a.iCveUsuRec "
					+ cWhere + " Order by  a.iCveEnvio,a.iCveUniMed,a.iAnio ";
			// // System.out.println("CSQL-->> "+cSQL);
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXEnvio = new TVTOXEnvio();
				vTOXEnvio.setICveEnvio(rset.getInt(1));
				vTOXEnvio.setICveUniMed(rset.getInt(2));
				vTOXEnvio.setIAnio(rset.getInt(3));
				vTOXEnvio.setICveLaboratorio(rset.getInt(4));
				vTOXEnvio.setICveUsuEnvia(rset.getInt(5));
				vTOXEnvio.setdtEnvio(rset.getDate(6));
				if (vTOXEnvio.getdtEnvio() == null) {
					vTOXEnvio.setdtEnvio(defaultFecha);
				}
				vTOXEnvio.setICveTipoEnvio(rset.getInt(7));
				vTOXEnvio.setdtRecepcion(rset.getDate(8));
				vTOXEnvio.setICveUsuRec(rset.getInt(9));
				vTOXEnvio.setCObsEnvio(rset.getString(10));
				if (vTOXEnvio.getCObsEnvio() == null) {
					vTOXEnvio.setCObsEnvio("");
				}
				vTOXEnvio.setCObsRecep(rset.getString(11));
				if (vTOXEnvio.getCObsRecep() == null) {
					vTOXEnvio.setCObsRecep("");
				}
				vTOXEnvio.setCDscTipoEnvio(rset.getString(12));
				vTOXEnvio.setCNombre(rset.getString(13));
				vTOXEnvio.setCNombreRec(rset.getString(14));
				iFechaEnvio = dtFecha.getFechaDDMMYYYY(vTOXEnvio.getdtEnvio(),
						"/");
				vTOXEnvio.setCdtEnvio(iFechaEnvio);
				vTOXEnvio.setITotalEnviadas(rset.getInt("iNumMuestras"));
				vcTOXEnvio.addElement(vTOXEnvio);

				// System.out.println("Fecha Envio modificada==>> "+vTOXEnvio.getCdtEnvio());
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
			return vcTOXEnvio;
		}
	}

	/**
	 * Metodo Find By All 2 Tablas: TOXEnvio, GRLUniMed
	 */
	public Vector FindByAll2(String cCondicion, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXEnvio = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

			String iFechaEnvio = "";
			TFechas dtFecha = new TFechas();

			TVTOXEnvio vTOXEnvio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = "select E.iAnio, E.iCveUniMed, E.iCveEnvio, GRLUniMed.cDscUniMed, E.dtEnvio, "
					+ "       E.iCveTipoEnvio, GRLTipoEnvio.cDscTipoEnvio, E.iCveUsuEnvia,  "
					+ "       UEnv.cNombre, UEnv.cApPaterno, UEnv.cApMaterno, E.cObsEnvio,  "
					+ "       E.dtRecepcion, "
					+ "       URec.cNombre, URec.cApPaterno, URec.cApMaterno, E.cObsRecep  "
					+ " from TOXEnvio  E "
					+ " left join GRLUniMed on E.iCveUniMed = GRLUniMed.iCveUniMed  "
					+ " left join GRLTipoEnvio    on GRLTipoEnvio.iCveTipoEnvio  = E.iCveTipoEnvio "
					+ " left join SEGUsuario UEnv on UEnv.iCveUsuario            = E.iCveUsuEnvia "
					+ " left join SEGUsuario URec on URec.iCveUsuario            = E.iCveUsuRec "
					+ cCondicion + cOrden;
			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXEnvio = new TVTOXEnvio();
				vTOXEnvio.setIAnio(rset.getInt(1));
				vTOXEnvio.setICveUniMed(rset.getInt(2));
				vTOXEnvio.setICveEnvio(rset.getInt(3));
				vTOXEnvio.setCDscUniMed(rset.getString(4));
				vTOXEnvio.setdtEnvio(rset.getDate(5));
				if (vTOXEnvio.getdtEnvio() == null) {
					vTOXEnvio.setdtEnvio(defaultFecha);
				}
				vTOXEnvio.setICveTipoEnvio(rset.getInt(6));
				vTOXEnvio.setCDscTipoEnvio(rset.getString(7));
				if (vTOXEnvio.getCDscTipoEnvio() == null) {
					vTOXEnvio.setCDscTipoEnvio("");
				}
				vTOXEnvio.setICveUsuEnvia(rset.getInt(8));
				vTOXEnvio.setCNombre(rset.getString(9));
				if (vTOXEnvio.getCNombre() == null) {
					vTOXEnvio.setCNombre("");
				}
				vTOXEnvio.setCApPaterno(rset.getString(10));
				if (vTOXEnvio.getCApPaterno() == null) {
					vTOXEnvio.setCApPaterno("");
				}
				vTOXEnvio.setCApMaterno(rset.getString(11));
				if (vTOXEnvio.getCApMaterno() == null) {
					vTOXEnvio.setCApMaterno("");
				}
				vTOXEnvio.setCObsEnvio(rset.getString(12));
				if (vTOXEnvio.getCObsEnvio() == null) {
					vTOXEnvio.setCObsEnvio("");
				}

				if (vTOXEnvio.getdtEnvio() != null)
					iFechaEnvio = dtFecha.getFechaDDMMYYYY(
							vTOXEnvio.getdtEnvio(), "/");
				vTOXEnvio.setCdtEnvio(iFechaEnvio);
				vTOXEnvio.setdtRecepcion(rset.getDate(13));
				vTOXEnvio.setCNombreRec(rset.getString(14));
				vTOXEnvio.setCApPaternoRec(rset.getString(15));
				vTOXEnvio.setCApMaternoRec(rset.getString(16));
				vTOXEnvio.setCObsRecep(rset.getString(17));

				vcTOXEnvio.addElement(vTOXEnvio);
			}
		} catch (Exception ex) {
			warn("FindByAll2", ex);
			throw new DAOException("FindByAll2");
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
				warn("FindByAll2.close", ex2);
			}
			return vcTOXEnvio;
		}
	}

	/**
	 * Metodo Find By All 2 Tablas: TOXEnvio, GRLUniMed
	 */
	public Vector FindByAll3(String cCondicion, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXEnvio = new Vector();

		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			java.util.Date today = new java.util.Date();
			java.sql.Date defFecha = new java.sql.Date(today.getTime());
			java.sql.Date defaultFecha = new java.sql.Date(today.getTime());

			String iFechaEnvio = "";
			TFechas dtFecha = new TFechas();

			TVTOXEnvio vTOXEnvio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;

			cSQL = " select iAnio, iCveEnvio, TOXEnvio.iCveUniMed, GRLUniMed.cDscUniMed, dtEnvio,   "
					+ " TOXEnvio.iCveTipoEnvio, GRLTipoEnvio.cDscTipoEnvio, TOXEnvio.iCveUsuEnvia,   "
					+ " SEGUsuario.cNombre, SEGUsuario.cApPaterno, SEGUsuario.cApMaterno, cObsEnvio, "
					+ " (SELECT count(a.iCveMuestra) FROM TOXMuestra a "
					+ "   WHERE TOXEnvio.iAnio       = a.iAnio "
					+ "     AND TOXEnvio.iCveUniMed = a.iCveUniMed "
					+ "     AND TOXEnvio.iCveEnvio  = a.iCveEnvio   "
					+ "   GROUP BY a.iAnio, a.iCveUniMed, a.iCveEnvio) as iTotalEnviadas, "
					+

					" (SELECT count(a.iCveMuestra) FROM TOXMuestra a "
					+ "   WHERE TOXEnvio.iAnio       = a.iAnio "
					+ "     AND TOXEnvio.iCveUniMed = a.iCveUniMed "
					+ "     AND TOXEnvio.iCveEnvio  = a.iCveEnvio   "
					+ "     AND a.iCveSituacion = "
					+ VParametros.getPropEspecifica("TOXRechazo").toString()
					+ "   GROUP BY a.iAnio, a.iCveUniMed, a.iCveEnvio) as iTotalRechazadas, "
					+

					" (SELECT count(a.iCveMuestra) FROM TOXMuestra a "
					+ "   WHERE TOXEnvio.iAnio       = a.iAnio "
					+ "     AND TOXEnvio.iCveUniMed = a.iCveUniMed "
					+ "     AND TOXEnvio.iCveEnvio  = a.iCveEnvio   "
					+ "     AND a.iCveSituacion <> "
					+ VParametros.getPropEspecifica("TOXRechazo").toString()
					+ "   GROUP BY a.iAnio, a.iCveUniMed, a.iCveEnvio) as iTotalRecibidas,GRLUniMed2.cDscUniMed, dtRecepcion, "
					+ " (select count(M.iCveMuestra) from TOXMuestra M "
					+ "     inner join TOXAnalisis A on A.iAnio = M.iAnio "
					+ "                             and A.iCveMuestra = M.iCveMuestra "
					+ "  where M.iAnio         = TOXEnvio.iAnio "
					+ "    and M.iCveUniMed    = TOXEnvio.iCveUniMed "
					+ "    and M.iCveEnvio     = TOXEnvio.iCveEnvio "
					+ "    and M.iCveSituacion = "
					+ VParametros.getPropEspecifica("TOXEnEstudio").toString()
					+ "    and ( A.LAutorizado =  0 or (A.lPresuntoPost = 1 and A.iSustConf < A.iSustPost) ) ) as Pendientes, "
					+ " (select count(M.iCveMuestra) from TOXMuestra M "
					+ "     inner join TOXAnalisis A on A.iAnio = M.iAnio "
					+ "           and A.iCveMuestra = M.iCveMuestra "
					+ " where M.iAnio         = TOXEnvio.iAnio "
					+ "   and M.iCveUniMed    = TOXEnvio.iCveUniMed "
					+ "   and M.iCveEnvio     = TOXEnvio.iCveEnvio "
					+ "   and M.iCveSituacion = "
					+ VParametros.getPropEspecifica("TOXEnEstudio").toString()
					+ "   and A.LAutorizado =  1  "
					+ "   and A.LResultado  =  1  "
					+ "   and A.iSustConf = A.iSustPost) as Positivos, "
					+ " R.cNombre || ' ' || R.cApPaterno || ' ' || R.cApMaterno RespUniMed "
					+ " from TOXEnvio   "
					+ " left join GRLUniMed on TOXEnvio.iCveUniMed = GRLUniMed.iCveUniMed "
					+ " left join GRLTipoEnvio on TOXEnvio.iCveTipoEnvio =  GRLTipoEnvio.iCveTipoEnvio   "
					+ " left join SEGUsuario on TOXEnvio.iCveUsuRec = SEGUsuario.iCveUsuario "
					+ " left join GRLUniMed GRLUniMed2 on TOXEnvio.iCveLaboratorio = GRLUniMed2.iCveUniMed "
					+ " left join SEGUsuario R on R.iCveUsuario = GRLUniMed.iCveUsuRespUM "
					+ cCondicion + cOrden;

			// System.out.println("cSQL = "+cSQL);

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXEnvio = new TVTOXEnvio();
				vTOXEnvio.setIAnio(rset.getInt(1));
				vTOXEnvio.setICveEnvio(rset.getInt(2));
				vTOXEnvio.setICveUniMed(rset.getInt(3));
				vTOXEnvio.setCDscUniMed(rset.getString(4));
				vTOXEnvio.setdtEnvio(rset.getDate(5));
				if (vTOXEnvio.getdtEnvio() == null) {
					vTOXEnvio.setdtEnvio(defaultFecha);
				}
				vTOXEnvio.setICveTipoEnvio(rset.getInt(6));
				vTOXEnvio.setCDscTipoEnvio(rset.getString(7));
				if (vTOXEnvio.getCDscTipoEnvio() == null) {
					vTOXEnvio.setCDscTipoEnvio("");
				}
				vTOXEnvio.setICveUsuEnvia(rset.getInt(8));
				vTOXEnvio.setCNombre(rset.getString(9));
				if (vTOXEnvio.getCNombre() == null) {
					vTOXEnvio.setCNombre("");
				}
				vTOXEnvio.setCApPaterno(rset.getString(10));
				if (vTOXEnvio.getCApPaterno() == null) {
					vTOXEnvio.setCApPaterno("");
				}
				vTOXEnvio.setCApMaterno(rset.getString(11));
				if (vTOXEnvio.getCApMaterno() == null) {
					vTOXEnvio.setCApMaterno("");
				}
				vTOXEnvio.setCObsEnvio(rset.getString(12));
				if (vTOXEnvio.getCObsEnvio() == null) {
					vTOXEnvio.setCObsEnvio("");
				}
				vTOXEnvio.setITotalEnviadas(rset.getInt(13));
				vTOXEnvio.setITotalRechazadas(rset.getInt(14));
				vTOXEnvio.setITotalRecibidas(rset.getInt(15));
				vTOXEnvio.setCDscLaboratorio(rset.getString(16));
				vTOXEnvio.setdtRecepcion(rset.getDate(17));
				vTOXEnvio.setITotalPendientes(rset.getInt(18));
				vTOXEnvio.setITotalPositivos(rset.getInt(19));

				if (vTOXEnvio.getdtEnvio() != null)
					iFechaEnvio = dtFecha.getFechaDDMMYYYY(
							vTOXEnvio.getdtEnvio(), "/");
				vTOXEnvio.setCdtEnvio(iFechaEnvio);
				vTOXEnvio.setCNombreRec(rset.getString(20));

				vcTOXEnvio.addElement(vTOXEnvio);
			}
		} catch (Exception ex) {
			warn("FindByAll3", ex);
			ex.printStackTrace();
			throw new DAOException("FindByAll3");
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
				warn("FindByAll3.close", ex2);
			}
			return vcTOXEnvio;
		}
	}

	/**
	 * Metodo update // Pendiente de finalizar desarrollo, este se Utilizara
	 * para actualizar la fecha de recepcion El usuario que recibe y las
	 * observaciones de la recepcion
	 */
	public Object update(Connection conGral, Object obj) throws DAOException {
		// System.out.println("Si entre a update!!! ");
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";

		java.util.Date today = new java.util.Date();
		java.sql.Date fechaRecep = new java.sql.Date(today.getTime());

		// System.out.println("Fecha Recepcion ==>> "+fechaRecep);

		try {
			if (conGral != null) {
				conn = conGral;
			} else {
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			}
			StringBuffer cSQL = new StringBuffer();
			TVTOXEnvio vTOXEnvio = (TVTOXEnvio) obj;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append("update TOXEnvio ")
					.append("set   cObsRecep = CONCAT(?, ' ' || CHAR ({FN CURTIME() }, USA) ) ,")
					.append("      dtRecepcion = ?,")
					.append("      iCveUsuRec  = ?")
					.append("where iAnio      = ? ")
					.append("  and iCveUniMed = ? ")
					.append("  and iCveEnvio  = ? ");
			pstmt = conn.prepareStatement(cSQL.toString());
			pstmt.setString(1, vTOXEnvio.getCObsRecep());
			pstmt.setDate(2, fechaRecep);
			pstmt.setInt(3, vTOXEnvio.getICveUsuRec());
			pstmt.setInt(4, vTOXEnvio.getIAnio());
			pstmt.setInt(5, vTOXEnvio.getICveUniMed());
			pstmt.setInt(6, vTOXEnvio.getICveEnvio());
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

	public Vector RepTarjPost(Object LoteCuantita) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXEnvio = new Vector();
		StringBuffer cSQL = new StringBuffer();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			TVTOXEnvio vTOXEnvio;
			int count;
			// B�squeda de Env�os involucrados en el lote Cuantitativo:
			// DISTINCT UniMed, Envio, Resultado
			cSQL.append(
					" select distinct M.iAnio, M.iCveUniMed, M.iCveEnvio, A.lResultado ")
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXAnalisis A on A.iAnio           = CA.iAnio ")
					.append("                         and A.iCveLaboratorio = CA.iCveLaboratorio ")
					.append("                         and A.iCveAnalisis    = CA.iCveAnalisis ")
					.append(" inner join TOXMuestra M on M.iAnio = A.iAnio ")
					.append("                        and M.iCveMuestra = A.iCveMuestra ")
					.append(" where CA.iAnio            = ")
					.append(((TVTOXLoteCuantita) LoteCuantita).getiAnio()
							.toString())
					.append("   and CA.iCveLaboratorio  = ")
					.append(((TVTOXLoteCuantita) LoteCuantita)
							.getiCveLaboratorio().toString())
					.append("   and CA.iCveSustancia    = ")
					.append(((TVTOXLoteCuantita) LoteCuantita)
							.getiCveSustancia().toString())
					.append("   and CA.iCveLoteCuantita = ")
					.append(((TVTOXLoteCuantita) LoteCuantita)
							.getiCveLoteCuantita().toString())
					.append("   and CA.lAutorizado      = 1 ")
					.append("   and A.iSustPost         = A.iSustConf ")
					.append(" group by M.iAnio, M.iCveUniMed, M.iCveEnvio, A.lResultado ");
			System.out.println("Muestra Conf --> \n" + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				// Para cada uno de los elementos se realiza la b�squeda del
				// detalle de los env�os
				StringBuffer cFiltro = new StringBuffer();
				cFiltro.append(" where TOXEnvio.iAnio      = ")
						.append(rset.getInt(1))
						.append("   and TOXEnvio.iCveUniMed = ")
						.append(rset.getInt(2))
						.append("   and TOXEnvio.iCveEnvio  = ")
						.append(rset.getInt(3));
				// Informaci�n del Env�o
				vTOXEnvio = new TVTOXEnvio();
				vTOXEnvio = (TVTOXEnvio) (this.FindByAll3(cFiltro.toString(),
						"")).get(0);
				vTOXEnvio.setLResultado(rset.getInt(4));
				// Traer el vector con las muestras
				Vector vMuestra = (new TDTOXMuestra()).MuestrasConf2(conn,
						LoteCuantita, vTOXEnvio);
				vTOXEnvio.vMuestra = vMuestra;
				// Agregar objeto del env�o al vector.
				vcTOXEnvio.add(vTOXEnvio);
			}
		} catch (Exception ex) {
			warn("RepTarjPost", ex);
			ex.printStackTrace();
			throw new DAOException("RepTarjPost");
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
				warn("RepTarjPost.close", ex2);
			}
			return vcTOXEnvio;
		}
	}

	public Vector findEnvioOrden(String cCondicion, String cOrden)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXEnvio = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXEnvio vTOXEnvio;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(" select E.iAnio, E.iCveUniMed, E.iCveEnvio, ")
					.append("       (select count(*) ")
					.append("        from TOXMuestra M ")
					.append("        where M.iAnio      = E.iAnio ")
					.append("          and M.iCveUniMed = E.iCveUniMed ")
					.append("          and M.iCveEnvio  = E.iCveEnvio ) as iNumMuestras, ")
					.append("       (select count(*) ")
					.append("        from TOXMuestra M ")
					.append("        where M.iAnio      = E.iAnio ")
					.append("          and M.iCveUniMed = E.iCveUniMed ")
					.append("          and M.iCveEnvio  = E.iCveEnvio ")
					.append("          and M.iOrden     = 0) as iOrdenAlmacenado, ")
					.append("       (select MAX(iOrden) ")
					.append("        from TOXMuestra M ")
					.append("        where M.iAnio      = E.iAnio ")
					.append("          and M.iCveUniMed = E.iCveUniMed ")
					.append("          and M.iCveEnvio  = E.iCveEnvio ) as iUltimo,")
					.append("        U.cDscUniMed")
					.append(" from TOXEnvio E ")
					.append(" inner join GRLUniMed U on U.iCveUniMed = E.iCveUniMed ")
					.append(cCondicion).append(cOrden);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXEnvio = new TVTOXEnvio();
				vTOXEnvio.setIAnio(rset.getInt("iAnio"));
				vTOXEnvio.setICveUniMed(rset.getInt("iCveUniMed"));
				vTOXEnvio.setICveEnvio(rset.getInt("iCveEnvio"));
				vTOXEnvio.setITotalEnviadas(rset.getInt("iNumMuestras"));
				vTOXEnvio.setITotalRecibidas(rset.getInt("iOrdenAlmacenado"));
				vTOXEnvio.setIMaxOrden(rset.getInt("iUltimo"));
				vTOXEnvio.setCDscUniMed(rset.getString("cDscUniMed"));
				vcTOXEnvio.addElement(vTOXEnvio);
			}
		} catch (Exception ex) {
			warn("findEnvioOrden", ex);
			throw new DAOException("findEnvioOrden");
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
				warn("findEnvioOrden.close", ex2);
			}
			return vcTOXEnvio;
		}
	}

}
