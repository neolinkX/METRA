package gob.sct.medprev.dao;

import java.sql.*;
import java.util.*;

import com.micper.excepciones.*;
import com.micper.ingsw.*;
import com.micper.sql.*;
import com.micper.util.*;
import gob.sct.medprev.vo.*;
import gob.sct.medprev.msc.*;
import com.micper.seguridad.vo.TVDinRep02;

/**
 * <p>
 * Title: Data Acces Object de TOXMuestra DAO
 * </p>
 * <p>
 * Description: DAO para el manejo de datos de Muestras
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: Micros Personales S.A. de C.V.
 * </p>
 * 
 * @author Javier Mendoza
 * @version 1.0
 */

public class TDTOXMuestra extends DAOBase {
	private TParametro VParametros = new TParametro("07");
	private String dataSourceName = VParametros
			.getPropEspecifica("ConDBModulo");

	public TDTOXMuestra() {
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cFechaRecolec;
		TFechas dtFecha = new TFechas();
		Vector vcTOXMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXMuestra vTOXMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select a.iAnio,a.iCveMuestra,a.iCveUniMed, "
					+ "a.iCveEnvio,a.dtRecoleccion,a.iCveProceso, "
					+ "a.iCveMotivo,a.iCveTipoRecep,a.iCveMotRecep, cDscProceso, "
					+ "cDscTipoRecep, cDscMotRecep, a.iOrden "
					+ "from TOXMuestra a "
					+ "left join  ToxTipoRecep on ToxTipoRecep.iCveTipoRecep = a.iCveTipoRecep "
					+ "left join  ToxMotivoRecep on ToxMotivoRecep.iCveMotRecep = a.iCveMotRecep "
					+ "left join  GrlProceso on GrlProceso.iCveProceso = a.iCveProceso "
					+ cWhere;
			// System.out.println("-- Query " + cSQL);
			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMuestra = new TVTOXMuestra();
				vTOXMuestra.setIAnio(rset.getInt(1));
				vTOXMuestra.setICveMuestra(rset.getInt(2));
				vTOXMuestra.setICveUniMed(rset.getInt(3));
				vTOXMuestra.setICveEnvio(rset.getInt(4));
				vTOXMuestra.setDtRecoleccion(rset.getDate(5));
				vTOXMuestra.setICveProceso(rset.getInt(6));
				vTOXMuestra.setICveMotivo(rset.getInt(7));
				vTOXMuestra.setICveTipoRecep(rset.getInt(8));
				vTOXMuestra.setICveMotRecep(rset.getInt(9));
				vTOXMuestra.setCDscProceso(rset.getString(10));
				vTOXMuestra.setCDscTipoRecep(rset.getString(11));
				vTOXMuestra.setCDscMotRecep(rset.getString(12));
				vTOXMuestra.setIOrden(rset.getInt(13));
				cFechaRecolec = dtFecha.getFechaDDMMYYYY(
						vTOXMuestra.getDtRecoleccion(), "/");
				vTOXMuestra.setCdtRecoleccion(cFechaRecolec);
				vcTOXMuestra.addElement(vTOXMuestra);
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
			return vcTOXMuestra;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public Vector FindByAll2(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cFechaRecolec;
		TFechas dtFecha = new TFechas();
		Vector vcTOXMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			TVTOXMuestra vTOXMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL = "select a.iAnio,a.iCveMuestra,a.iCveUniMed, "
					+ "a.iCveEnvio,a.dtRecoleccion,a.iCveProceso, "
					+ "a.iCveMotivo,a.iCveTipoRecep,a.iCveMotRecep, cDscProceso, "
					+ "cDscTipoRecep, cDscMotRecep, a.iOrden, "
					+ " {FN YEAR(a.dtRecoleccion)} "
					+ "from TOXMuestra a "
					+ "left join  ToxTipoRecep on ToxTipoRecep.iCveTipoRecep = a.iCveTipoRecep "
					+ "left join  ToxMotivoRecep on ToxMotivoRecep.iCveMotRecep = a.iCveMotRecep "
					+ "left join  GrlProceso on GrlProceso.iCveProceso = a.iCveProceso "
					+ cWhere;
			// System.out.println("-- Query -- " + cSQL);
			pstmt = conn.prepareStatement(cSQL);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMuestra = new TVTOXMuestra();
				vTOXMuestra.setIAnio(rset.getInt(1));
				vTOXMuestra.setICveMuestra(rset.getInt(2));
				vTOXMuestra.setICveUniMed(rset.getInt(3));
				vTOXMuestra.setICveEnvio(rset.getInt(4));
				vTOXMuestra.setDtRecoleccion(rset.getDate(5));
				vTOXMuestra.setICveProceso(rset.getInt(6));
				vTOXMuestra.setICveMotivo(rset.getInt(7));
				vTOXMuestra.setICveTipoRecep(rset.getInt(8));
				vTOXMuestra.setICveMotRecep(rset.getInt(9));
				vTOXMuestra.setCDscProceso(rset.getString(10));
				vTOXMuestra.setCDscTipoRecep(rset.getString(11));
				vTOXMuestra.setCDscMotRecep(rset.getString(12));
				vTOXMuestra.setIOrden(rset.getInt(13));
				vTOXMuestra.setIAnioRec(rset.getInt(14));
				cFechaRecolec = dtFecha.getFechaDDMMYYYY(
						vTOXMuestra.getDtRecoleccion(), "/");
				vTOXMuestra.setCdtRecoleccion(cFechaRecolec);
				vcTOXMuestra.addElement(vTOXMuestra);
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
			return vcTOXMuestra;
		}
	}

	/**
	 * Metodo update
	 */
	public Object update(Connection conGral, String tTipoR, String tTipoM,
			int iSituacion, int iCveM, int iAnio) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String cClave = "";
		int iTipoR = 0;
		int iTipoM = 0;

		iTipoR = Integer.parseInt(tTipoR);
		iTipoM = Integer.parseInt(tTipoM);
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
			cSQL = "update TOXMuestra" + " set iCveTipoRecep = ?, "
					+ "     iCveMotRecep  = ?, " + "     iCveSituacion = ? "
					+ "where iAnio = ? and iCveMuestra = ?";
			pstmt = conn.prepareStatement(cSQL);
			pstmt.setInt(1, iTipoR);
			pstmt.setInt(2, iTipoM);
			pstmt.setInt(3, iSituacion);
			pstmt.setInt(4, iAnio);
			pstmt.setInt(5, iCveM);
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

	/**
	 * Met�do que regresa la informaci�n de las muestras confirmadas, se
	 * filtra la informaci�n por lote confirmatorio y env�o de la unidad
	 * m�dica.
	 * 
	 * @param connE
	 *            Conexi�n a la base de datos.
	 * @param OLote
	 *            Objeto TVTOXLoteCuantita con la informaci�n del lote que se
	 *            est� analizando
	 * @param OEnvio
	 *            Objeto TVTOXEnvio con la informaci�n del Env�o a
	 *            consultar.
	 * @return Regresa el vector de objetos TVMuestra con la informaci�n del
	 *         query.
	 * @throws DAOException
	 */
	public Vector MuestrasConf(Connection connE, Object OLote, Object OEnvio)
			throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXMuestra = new Vector();
		StringBuffer cSQL;
		TVMuestra VMuestra;
		try {
			if (connE == null) {
				DbConnection dbConn = null;
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else
				conn = connE;

			// Query
			cSQL = new StringBuffer();
			cSQL.append(
					" select CA.iAnio, CA.iCveLaboratorio, CA.iCveSustancia, ")
					.append("        CA.iCveLoteCuantita, CA.iCveAnalisis, ")
					.append("        A.lResultado, ")
					.append("        M.iCveMuestra, P.cDscProceso ")
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXAnalisis A on A.iAnio           = CA.iAnio ")
					.append("                         and A.iCveLaboratorio = CA.iCveLaboratorio ")
					.append("                         and A.iCveAnalisis    = CA.iCveAnalisis ")
					.append(" inner join TOXMuestra M on M.iAnio = A.iAnio ")
					.append("                        and M.iCveMuestra = A.iCveMuestra ")
					.append(" inner join GRLProceso P on P.iCveProceso = M.iCveProceso ")
					.append(" where CA.iAnio            = ")
					.append(((TVTOXLoteCuantita) OLote).getiAnio().toString())
					.append("   and CA.iCveLaboratorio  = ")
					.append(((TVTOXLoteCuantita) OLote).getiCveLaboratorio()
							.toString())
					.append("   and CA.iCveSustancia    = ")
					.append(((TVTOXLoteCuantita) OLote).getiCveSustancia()
							.toString())
					.append("   and CA.iCveLoteCuantita = ")
					.append(((TVTOXLoteCuantita) OLote).getiCveLoteCuantita()
							.toString())
					.append("   and CA.lAutorizado      = 1 ")
					.append("   and CA.lCorrecto        = 1 ")
					.append("   and A.iSustPost         <= A.iSustConf ")
					.append("   and A.lResultado        = ")
					.append(((TVTOXEnvio) OEnvio).getLResultado())
					.append("   and M.iCveUniMed        = ")
					.append(((TVTOXEnvio) OEnvio).getICveUniMed())
					.append("   and M.iCveEnvio         = ")
					.append(((TVTOXEnvio) OEnvio).getICveEnvio())
					.append(" order by CA.iCveAnalisis ");
			// Ejecutar el query
			System.out.println("Muestra Conf --> \n" + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VMuestra = new TVMuestra();
				// Obtener informaci�n
				VMuestra.setIAnio(rset.getInt(1));
				VMuestra.setICveLaboratorio(rset.getInt(2));
				VMuestra.setICveAnalisis(rset.getInt(5));
				VMuestra.setLResultado(rset.getInt(6));
				VMuestra.setICveMuestra(rset.getInt(7));
				VMuestra.setCDscProceso(rset.getString(8));
				// Consultar los resultados de las muestras positivas
				if (VMuestra.getLResultado() == 1) {
					Vector VResultado = new TDTOXLoteCuantita().SustPost(conn,
							VMuestra);
					VMuestra.vResultado = VResultado;
				}
				// Agregar las muestras
				vcTOXMuestra.add(VMuestra);
			}

		} catch (Exception ex) {
			warn("MuestrasConf", ex);
			ex.printStackTrace();
			throw new DAOException("MuestrasConf");
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
				warn("MuestrasConf.close", ex2);
			}
			return vcTOXMuestra;
		}
	}

	/**
	 * Metodo LiberaMuestra
	 */
	public boolean LiberaMuestra(String cAnio, String cCveMuestra)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		String cClave = "";
		boolean bUpdate = false;
		int iUpdate = 0;
		try {

			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " UPDATE TOXMuestra SET "
					+ " iCveTipoRecep = null , " + " iCveMotRecep = null , "
					+ " iCveSituacion = 1 " + " WHERE iAnio = " + cAnio
					+ " AND iCveMuestra = " + cCveMuestra
					+ " AND lIntegraLote = 0 ";

			stmt = conn.createStatement();

			iUpdate = stmt.executeUpdate(cSQL);

			if (iUpdate != 0) {
				bUpdate = true;
			}
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception ex1) {
				ex.printStackTrace();
			}
			warn("update", ex);
			throw new DAOException("");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return bUpdate;
		}
	}

	/**
	 * Metodo LiberaMuestra
	 */
	public boolean BorrarMuestra(String cAnio, String cCveMuestra)
			throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		Statement stmt = null;
		String cClave = "";
		boolean bUpdate = false;
		int iUpdate = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = " DELETE FROM TOXMuestra " + " WHERE iAnio = "
					+ cAnio + " AND iCveMuestra = " + cCveMuestra
					+ " AND lIntegraLote = 0 ";

			stmt = conn.createStatement();
			iUpdate = stmt.executeUpdate(cSQL);

			if (iUpdate != 0) {
				bUpdate = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex1) {
				ex.printStackTrace();
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				dbConn.closeConnection();
			} catch (Exception ex2) {
			}
			return bUpdate;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public HashMap ObtenerOrden(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String cFechaRecolec;
		TFechas dtFecha = new TFechas();
		HashMap hMuestra = new HashMap();
		Vector vcTOXMuestra = new Vector();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXMuestra vTOXMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			int count;
			cSQL.append(" select E.iAnio, E.iCveMuestra, E.iOrden ")
					.append(" from TOXMuestra E ").append(cWhere);

			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMuestra = new TVTOXMuestra();
				vTOXMuestra.setIAnio(rset.getInt("iAnio"));
				vTOXMuestra.setICveMuestra(rset.getInt("iCveMuestra"));
				vTOXMuestra.setIOrden(rset.getInt("iOrden"));
				hMuestra.put(String.valueOf(vTOXMuestra.getICveMuestra()),
						vTOXMuestra);
			}
		} catch (Exception ex) {
			warn("ObtenerOrden", ex);
			ex.printStackTrace();
			throw new DAOException("ObtenerOrden");
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
				warn("ObtenerOrden.close", ex2);
			}
			return hMuestra;
		}
	}

	/**
	 * Metodo Find By All
	 */
	public ArrayList guardarOrdenXFiltro(String cWhere) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList aMuestra = new ArrayList();
		int iOrden = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			StringBuffer cSQL = new StringBuffer();
			TVTOXMuestra vTOXMuestra;
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);
			cSQL.append(" select E.iAnio, E.iCveMuestra, E.iOrden ")
					.append(" from TOXMuestra E ").append(cWhere);
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				vTOXMuestra = new TVTOXMuestra();
				vTOXMuestra.setIAnio(rset.getInt("iAnio"));
				vTOXMuestra.setICveMuestra(rset.getInt("iCveMuestra"));
				vTOXMuestra.setIOrden(++iOrden);
				aMuestra.add(vTOXMuestra);
			}
		} catch (Exception ex) {
			warn("guardarOrdenXFiltro", ex);
			ex.printStackTrace();
			throw new DAOException("guardarOrdenXFiltro");
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
				warn("guardarOrdenXFiltro.close", ex2);
			}
			return aMuestra;
		}
	}

	public boolean guardarOrden(Object oMuestra) throws DAOException {
		boolean lResultado = false;
		HashMap hCamposQ1 = new HashMap();
		String cCondicion;
		ArrayList aQueryU = new ArrayList(), aMuestra = (ArrayList) oMuestra;

		QueryManager qQuery = new QueryManager("07", "ConDBModulo");
		QueryStructure[] qSentencias = new QueryStructure[aMuestra.size()];
		try {
			// Generar sentencias para actualizaciones
			TVTOXMuestra vMuestra;
			for (int i = 0; i < aMuestra.size(); i++) {
				vMuestra = (TVTOXMuestra) aMuestra.get(i);
				hCamposQ1.clear();
				hCamposQ1.put("M.iOrden", String.valueOf(vMuestra.getIOrden()));
				cCondicion = "   M.iAnio       = " + vMuestra.getIAnio()
						+ "  and M.iCveMuestra = " + vMuestra.getICveMuestra();
				qSentencias[i] = new QueryStructure(hCamposQ1,
						" TOXMuestra M ", cCondicion, QueryStructure.UPDATE);
				aQueryU.add(qSentencias[i]);
			} // Sentencias generadas

			/*
			 * // Debug for(int j=0; j < aQueryU.size(); j++){ //
			 * System.out.println("Query " + j + ":" +
			 * qQuery.generateQuery((QueryStructure)aQueryU.get(j))); }
			 */
			// Ejecutar las sentencias de Actualizaci�n e inserci�n.
			qQuery.manageTransaction(aQueryU);
			lResultado = true;
		} // try
		catch (Exception ex) {
			ex.printStackTrace();
			lResultado = false;
		} // catch
		finally {
			return lResultado;
		} // finally
	} // Metodo

	public Vector findInfoGeneral(String cCondicion) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DbConnection dbConn = null;
		ResultSet rset = null;
		Vector vcTOXMuestra = new Vector();
		StringBuffer cSQL = new StringBuffer(), cSQLAP = new StringBuffer();
		TVMuestra VMuestra;
		HashMap hConsulta = new HashMap();
		QueryStructure qSentencia;
		QueryManager qQuery = new QueryManager("07", "ConDBModulo", false);
		ArrayList aSentencia = new ArrayList();
		String cCondAP = new String();
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			// Query
			cSQL.append(" select M.iAnio, M.iCveMuestra, ")
					// 1, 2
					.append("        UM.cDscUniMed, MM.cDscModulo, ")
					// 3, 4
					.append("        GP.cDscProceso, GM.cDscMotivo, ")
					// 5, 6
					.append("        M.dtRecoleccion, ")
					// 7
					.append("        UR.cApPaterno || ' ' || UR.cApMaterno || ' ' || UR.cNombre as cUsuRecolec, ")
					// 8
					.append("        M.lTemperac, M.lAlteracion, M.lBajoObserva, ")
					// 9, 10, 11
					.append("        MT.cDscMdoTrans, GE.cDscEmpresa, ")
					// 12, 13,
					.append("        M.iCvePersonal, M.iEdad, ")
					// 14, 15
					.append("        M.dtCaptura, ")
					// 16
					.append("        UC.cApPaterno || ' ' || UC.cApMaterno || ' ' || UC.cNombre as cUsuCaptura, ")
					// 17
					.append("        S.cDscSituacion, TR.cDscTipoRecep, MR.cDscMotRecep, ")
					// 18, 19, 20
					.append("        A.iCveAnalisis, A.lResultado, A.lPresuntoPost, ")
					// 21, 22, 23
					.append("        A.iSustPost, A.iSustConf, A.iCveLaboratorio ")
					// 24, 25, 26
					.append(" from TOXMuestra M ")
					.append(" inner join GRLUniMed UM on UM.iCveUniMed = M.iCveUniMed ")
					.append(" inner join GRLModulo MM on MM.iCveUniMed = M.iCveUniMed ")
					.append("                        and MM.iCveModulo = M.iCveModulo ")
					.append(" inner join GRLProceso GP on GP.iCveProceso = M.iCveProceso ")
					.append(" inner join GRLMotivo  GM on GM.iCveMotivo  = M.iCveMotivo ")
					.append(" inner join GRLEmpresas GE on GE.iCveEmpresa = M.iCveEmpresa ")
					.append(" inner join GRLMdoTrans MT on MT.iCveMdoTrans = M.iCveMdoTrans ")
					.append(" inner join SEGUsuario UR on UR.iCveUsuario = M.iCveUsuRecolec ")
					.append(" inner join SEGUsuario UC on UC.iCveUsuario = M.iCveUsuCaptura ")
					.append(" inner join TOXSituacion S on S.iCveSituacion = M.iCveSituacion ")
					.append(" inner join TOXTipoRecep TR on TR.iCveTipoRecep = M.iCveTipoRecep ")
					.append(" inner join TOXMotivoRecep  MR on MR.iCveTipoRecep = M.iCveTipoRecep ")
					.append("                              and MR.iCveMotRecep = M.iCveMotRecep ")
					.append(" left join TOXAnalisis A on A.iAnio = M.iAnio ")
					.append("                        and A.iCveMuestra     = M.iCveMuestra ")
					.append(cCondicion);

			// Informaci�n del an�lisis presuntivo
			hConsulta
					.put(" EC.dtProcesado, ER.iCveAnalisis, ER.iCveSustancia, ER.dResultado, ER.lPositivo, ER.lDudoso, ER.lAutorizado ",
							" ");
			cSQLAP.append("  TOXAnalisis A ")
					.append(" inner join TOXExamenCualita EC on EC.iAnio = A.iAnio ")
					.append("                               and EC.iCveLaboratorio = A.iCveLaboratorio ")
					.append("                               and EC.iCveLoteCualita = A.iCveLoteCualita  ")
					.append("                               and EC.iCveExamCualita = A.iCveExamCualita  ")
					.append(" inner join  TOXExamResult ER on ER.iAnio = A.iAnio ")
					.append("                             and ER.iCveLaboratorio = A.iCveLaboratorio  ")
					.append("                             and ER.iCveLoteCualita = A.iCveLoteCualita  ")
					.append("                             and ER.iCveExamCualita = A.iCveExamCualita  ")
					.append("                             and ER.iCveAnalisis    = A.iCveAnalisis  ");

			System.out.println("Busqueda Info Gral. = " + cSQL.toString());

			// Ejecutar el query
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VMuestra = new TVMuestra();
				// Informaci�n de la Muestra

				VMuestra.setIAnio(rset.getInt(1));
				VMuestra.setICveMuestra(rset.getInt(2));
				VMuestra.setCDscUM(rset.getString(3));
				VMuestra.setCDscModulo(rset.getString(4));
				VMuestra.setCDscProceso(rset.getString(5));
				VMuestra.setCDscMotivo(rset.getString(6));
				VMuestra.setDtRecoleccion(rset.getDate(7));
				VMuestra.setCDscUsuRecolec(rset.getString(8));
				VMuestra.setLTemperaC(rset.getInt(9));
				VMuestra.setLAlteracion(rset.getInt(10));
				VMuestra.setLBajoObserva(rset.getInt(11));
				VMuestra.setCDscMdoTrans(rset.getString(12));
				VMuestra.setCDscEmpresa(rset.getString(13));
				VMuestra.setICvePersonal(rset.getInt(14));
				VMuestra.setIEdad(rset.getInt(15));
				VMuestra.setDtCaptura(rset.getDate(16));
				VMuestra.setCDscUsuCaptura(rset.getString(17));
				VMuestra.setCDscSituacion(rset.getString(18));
				VMuestra.setCDscTipoRecep(rset.getString(19));
				VMuestra.setCDscMotRecep(rset.getString(20));
				// Informaci�n del An�lisis
				VMuestra.setICveAnalisis(rset.getInt(21));
				if (VMuestra.getICveAnalisis() > 0) {
					VMuestra.setLResultado(rset.getInt(22));
					VMuestra.setLPresuntoPost(rset.getInt(23));
					VMuestra.setISustPost(rset.getInt(24));
					VMuestra.setISustConf(rset.getInt(25));
					VMuestra.setICveLaboratorio(rset.getInt(26));
					// Guardar resultado
					VMuestra.setCResultado(VMuestra.getISustConf() >= VMuestra
							.getISustPost() ? VMuestra.getLResultado() == 1 ? "Positiva"
							: "Negativa"
							: "Pendiente");
					// Obtener informaci�n del An�lisis Presuntivo
					cCondAP = "  A.iAnio = " + VMuestra.getIAnio()
							+ "   and A.iCveLaboratorio = "
							+ VMuestra.getICveLaboratorio()
							+ "   and A.iCveAnalisis    = "
							+ VMuestra.getICveAnalisis();
					aSentencia.clear();
					qSentencia = new QueryStructure(hConsulta,
							cSQLAP.toString(), cCondAP, QueryStructure.SELECT);
					aSentencia.add(qSentencia);
					// Ejecutar las sentencias

					ArrayList vResultado = qQuery.manageTransaction(aSentencia);
					VMuestra.vResultPresuntivo = new ArrayList();
					if (vResultado.size() > 0)
						VMuestra.vResultPresuntivo = (ArrayList) vResultado
								.get(0);
					// Consultar los resultados de las muestras a las que fue
					// presunto positivo
					if (VMuestra.getLPresuntoPost() == 1) {
						Vector VResultado = new TDTOXLoteCuantita().SustPost(
								conn, VMuestra);
						VMuestra.vResultado = VResultado;
					} // La muestra fue presunta positiva
				} // Se analiz�
					// Agregar las muestras
				vcTOXMuestra.add(VMuestra);
			} // Barrer el el result set

			// System.out.println("Registros * " + vcTOXMuestra.size() );
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findInfoGeneral", ex);
			throw new DAOException("findInfoGeneral");
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
				warn("findInfoGeneral.close", ex2);
			}
			return vcTOXMuestra;
		}
	}

	public ArrayList findInformeAnalisis(String cCondicion) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DbConnection dbConn = null;
		ResultSet rset = null;
		ArrayList vResultado = new ArrayList();
		try {
			ArrayList aQueryU = new ArrayList();
			QueryManager qQuery = new QueryManager("07", "ConDBModulo", false);
			QueryStructure qSentencia;
			HashMap hCamposQ1 = new HashMap(), hCamposQ2 = new HashMap();

			// Definir la B�squeda por sitio de recolecci�n.
			// @Motivo define los motivos que se utilizan para agrupar la
			// informaci�n del reporte
			// (5,26,27) = Revaloraci�n = TOXInfAn_MotRevalora
			// (7) = Operativo = TOXInfAn_MotOperativo
			// (29) = Seguimiento = TOXInfAn_MotSeguimiento
			// (20) = Accidente = TOXInfAn_MotAccidente
			// @Modo Define la clave del modo de transporte a presentar
			// 1 = Aereo = TOXInfAn_Aereo
			// 2 = Autotransporte = TOXInfAn_Autotrans
			// 3 = Maritimo = TOXInfAn_Maritimo
			// 4 = Ferroviario = TOXInfAn_Ferroviario
			// @Periodo define el rango de fecha que utiliza el reporte
			// @CDI define la clave de la unidad = TOXInfAn_CDI
			// @CENMA define la clave de la unidad = TOXInfAn_CENMA
			// @TipoRecep define la clave del motivo de recepci�n de las
			// muestras = TOXInfAn_TipoRecep

			// Claves de las Sustancias
			String cCveAnf = "1", cCveCann = "2", cCveCoc = "3", cCveOpio = "4", cCvePCP = "5", cCveMeta = "7";

			String cMuestra = "  P.iCveProceso, "
					+ "        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo"
					+ "            and M.iCveUniMed    = @CDI "
					+ "            and M.iCveModulo    <> @CENMA "
					+ "            and M.dtRecoleccion between @Periodo  "
					+ "            and M.iCveTipoRecep = @TipoRecep) as CDI, "
					+ "        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo "
					+ "            and M.iCveUniMed    = @CDI "
					+ "            and M.iCveModulo    = @CENMA "
					+ "            and M.dtRecoleccion between @Periodo   "
					+ "            and M.iCveTipoRecep = @TipoRecep) as CENMA, "
					+ "        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo "
					+ "            and M.iCveUniMed    > @CDI "
					+ "            and M.dtRecoleccion between @Periodo   "
					+ "            and M.iCveTipoRecep = @TipoRecep) as UMF, "
					+ "        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo "
					+ "            and M.iCveMdoTrans  = @Modo1 "
					+ "            and M.dtRecoleccion between @Periodo  "
					+ "            and M.iCveTipoRecep = @TipoRecep) as AEREO, "
					+ "        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo "
					+ "            and M.iCveMdoTrans  = @Modo2 "
					+ "            and M.dtRecoleccion between @Periodo   "
					+ "            and M.iCveTipoRecep = 1) as AUTOTRANSPORTE, "
					+ "        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo "
					+ "            and M.iCveMdoTrans  = @Modo3 "
					+ "            and M.dtRecoleccion between @Periodo  "
					+ "            and M.iCveTipoRecep = @TipoRecep) as FERROVIARIO, "
					+ "        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo "
					+ "            and M.iCveMdoTrans  = @Modo4 "
					+ "            and M.dtRecoleccion between @Periodo   "
					+ "            and M.iCveTipoRecep = @TipoRecep) as MARITIMO,    "
					+

					// /Se agrego la siguiente columna en el Query
					// -------------------------------------------------
					"        (select count(1) "
					+ "          from TOXMuestra M  "
					+ "          where M.iCveProceso   = P.iCveProceso "
					+ "            and M.iCveMotivo    @Motivo "
					+ "            and M.iCveMdoTrans  not in ( @Modo1 , @Modo2 , @Modo3 , @Modo4 ) "
					+ "            and M.dtRecoleccion between @Periodo   "
					+ "            and M.iCveTipoRecep = @TipoRecep) as OTROS,    "
					+
					// /-----------------------------------------------------------------------------------------------

					"        (select count(1)  "
					+ "          from TOXMuestra M   "
					+ "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
					+ "                                  and A.iCveMuestra = M.iCveMuestra  "
					+ "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
					+ "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
					+ "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
					+ "                                        and LC.iCveExamCualita = 1  "
					+ "          where M.iCveProceso = P.iCveProceso  "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtProcesado between @Periodo) as MUESTRA, "
					+ "        (select count(1)  "
					+ "          from TOXMuestra M   "
					+ "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
					+ "                                  and A.iCveMuestra = M.iCveMuestra  "
					+ "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
					+ "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
					+ "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
					+ "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
					+ "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
					+ "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
					+ "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
					+ "                         and ER.iCveSustancia   = "
					+ cCveAnf
					+ "          where M.iCveProceso = P.iCveProceso  "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtProcesado between @Periodo) as ANALISIS_ANF, "
					+ "        (select count(1)  "
					+ "          from TOXMuestra M   "
					+ "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
					+ "                                  and A.iCveMuestra = M.iCveMuestra  "
					+ "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
					+ "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
					+ "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
					+ "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
					+ "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
					+ "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
					+ "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
					+ "                         and ER.iCveSustancia   = "
					+ cCveCann
					+ "          where M.iCveProceso = P.iCveProceso  "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtProcesado between @Periodo) as ANALISIS_CANN, "
					+ "        (select count(1)  "
					+ "          from TOXMuestra M   "
					+ "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
					+ "                                  and A.iCveMuestra = M.iCveMuestra  "
					+ "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
					+ "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
					+ "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
					+ "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
					+ "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
					+ "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
					+ "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
					+ "                         and ER.iCveSustancia   = "
					+ cCveCoc
					+ "          where M.iCveProceso = P.iCveProceso  "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtProcesado between @Periodo) as ANALISIS_COC, "
					+ "        (select count(1)  "
					+ "          from TOXMuestra M   "
					+ "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
					+ "                                  and A.iCveMuestra = M.iCveMuestra  "
					+ "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
					+ "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
					+ "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
					+ "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
					+ "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
					+ "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
					+ "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
					+ "                         and ER.iCveSustancia   = "
					+ cCveOpio
					+ "          where M.iCveProceso = P.iCveProceso  "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtProcesado between @Periodo) as ANALISIS_OPIO, "
					+ "        (select count(1)  "
					+ "          from TOXMuestra M   "
					+ "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
					+ "                                  and A.iCveMuestra = M.iCveMuestra  "
					+ "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
					+ "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
					+ "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
					+ "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
					+ "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
					+ "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
					+ "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
					+ "                         and ER.iCveSustancia   = "
					+ cCvePCP
					+ "          where M.iCveProceso = P.iCveProceso  "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtProcesado between @Periodo) as ANALISIS_PCP, "
					+ "        (select count(1)  "
					+ "          from TOXMuestra M   "
					+ "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
					+ "                                  and A.iCveMuestra = M.iCveMuestra  "
					+ "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
					+ "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
					+ "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
					+ "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
					+ "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
					+ "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
					+ "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
					+ "                         and ER.iCveSustancia   = "
					+ cCveMeta
					+ "          where M.iCveProceso = P.iCveProceso  "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtProcesado between @Periodo) as ANALISIS_META, "
					+ "        (select count(1) "
					+ "           from TOXMuestra M  "
					+ "            inner join TOXAnalisis A on A.iAnio       = M.iAnio        "
					+ "                                    and A.iCveMuestra = M.iCveMuestra  "
					+ "            inner join TOXCuantAnalisis CA on CA.iAnio = A.iAnio       "
					+ "                                          and CA.iCveAnalisis = A.iCveAnalisis         "
					+ "           inner join TOXLoteCuantita LC  on LC.iAnio            = CA.iAnio            "
					+ "                                         and LC.iCveLaboratorio  = CA.iCveLaboratorio  "
					+ "                                         and LC.iCveLoteCuantita = CA.iCveLoteCuantita "
					+ "                                         and LC.iCveSustancia    = "
					+ cCveAnf
					+ "           where M.iCveProceso = P.iCveProceso "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtAnalisis between @Periodo) as ANALISIS_CONF_ANF, "
					+ "        (select count(1) "
					+ "           from TOXMuestra M  "
					+ "            inner join TOXAnalisis A on A.iAnio       = M.iAnio        "
					+ "                                    and A.iCveMuestra = M.iCveMuestra  "
					+ "            inner join TOXCuantAnalisis CA on CA.iAnio = A.iAnio       "
					+ "                                          and CA.iCveAnalisis = A.iCveAnalisis         "
					+ "           inner join TOXLoteCuantita LC  on LC.iAnio            = CA.iAnio            "
					+ "                                         and LC.iCveLaboratorio  = CA.iCveLaboratorio  "
					+ "                                         and LC.iCveLoteCuantita = CA.iCveLoteCuantita "
					+ "                                         and LC.iCveSustancia    = 2                   "
					+ "           where M.iCveProceso = P.iCveProceso "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtAnalisis between @Periodo) as ANALISIS_CONF_CANN, "
					+ "        (select count(1) "
					+ "           from TOXMuestra M  "
					+ "            inner join TOXAnalisis A on A.iAnio       = M.iAnio        "
					+ "                                    and A.iCveMuestra = M.iCveMuestra  "
					+ "            inner join TOXCuantAnalisis CA on CA.iAnio = A.iAnio       "
					+ "                                          and CA.iCveAnalisis = A.iCveAnalisis         "
					+ "           inner join TOXLoteCuantita LC  on LC.iAnio            = CA.iAnio            "
					+ "                                         and LC.iCveLaboratorio  = CA.iCveLaboratorio  "
					+ "                                         and LC.iCveLoteCuantita = CA.iCveLoteCuantita "
					+ "                                         and LC.iCveSustancia    = 3                   "
					+ "           where M.iCveProceso = P.iCveProceso "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtAnalisis between @Periodo) as ANALISIS_CONF_COC, "
					+ "        (select count(1) "
					+ "           from TOXMuestra M  "
					+ "            inner join TOXAnalisis A on A.iAnio       = M.iAnio        "
					+ "                                    and A.iCveMuestra = M.iCveMuestra  "
					+ "            inner join TOXCuantAnalisis CA on CA.iAnio = A.iAnio       "
					+ "                                          and CA.iCveAnalisis = A.iCveAnalisis         "
					+ "           inner join TOXLoteCuantita LC  on LC.iAnio            = CA.iAnio            "
					+ "                                         and LC.iCveLaboratorio  = CA.iCveLaboratorio  "
					+ "                                         and LC.iCveLoteCuantita = CA.iCveLoteCuantita "
					+ "                                         and LC.iCveSustancia    = 4                   "
					+ "           where M.iCveProceso = P.iCveProceso "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtAnalisis between @Periodo) as ANALISIS_CONF_OPIO, "
					+ "        (select count(1) "
					+ "           from TOXMuestra M  "
					+ "            inner join TOXAnalisis A on A.iAnio       = M.iAnio        "
					+ "                                    and A.iCveMuestra = M.iCveMuestra  "
					+ "            inner join TOXCuantAnalisis CA on CA.iAnio = A.iAnio       "
					+ "                                          and CA.iCveAnalisis = A.iCveAnalisis         "
					+ "           inner join TOXLoteCuantita LC  on LC.iAnio            = CA.iAnio            "
					+ "                                         and LC.iCveLaboratorio  = CA.iCveLaboratorio  "
					+ "                                         and LC.iCveLoteCuantita = CA.iCveLoteCuantita "
					+ "                                         and LC.iCveSustancia    = 5                   "
					+ "           where M.iCveProceso = P.iCveProceso "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtAnalisis between @Periodo) as ANALISIS_CONF_PCP, "
					+ "        (select count(1) "
					+ "           from TOXMuestra M  "
					+ "            inner join TOXAnalisis A on A.iAnio       = M.iAnio        "
					+ "                                    and A.iCveMuestra = M.iCveMuestra  "
					+ "            inner join TOXCuantAnalisis CA on CA.iAnio = A.iAnio       "
					+ "                                          and CA.iCveAnalisis = A.iCveAnalisis         "
					+ "           inner join TOXLoteCuantita LC  on LC.iAnio            = CA.iAnio            "
					+ "                                         and LC.iCveLaboratorio  = CA.iCveLaboratorio  "
					+ "                                         and LC.iCveLoteCuantita = CA.iCveLoteCuantita "
					+ "                                         and LC.iCveSustancia    = 7                  "
					+ "           where M.iCveProceso = P.iCveProceso "
					+ "            and M.iCveMotivo  @Motivo  "
					+ "            and LC.dtAnalisis between @Periodo) as ANALISIS_CONF_META ";
			/*
			 * "        (select count(1)  " + "          from TOXMuestra M   " +
			 * "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
			 * +
			 * "                                  and A.iCveMuestra = M.iCveMuestra  "
			 * +
			 * "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
			 * +
			 * "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
			 * +
			 * "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
			 * +
			 * "                                        and LC.iCveExamCualita = 1  "
			 * + "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
			 * +
			 * "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
			 * +
			 * "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
			 * +
			 * "                         and ER.iCveExamCualita = LC.iCveExamCualita"
			 * +
			 * "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
			 * + "                         and ER.iCveSustancia   = " + cCveAnf
			 * + "          where M.iCveProceso = P.iCveProceso  " +
			 * "            and M.iCveMotivo  @Motivo  " +
			 * "            and LC.dtProcesado between @Periodo) as MUESTRA_ANF, "
			 * + "        (select count(1)  " + "          from TOXMuestra M   "
			 * +
			 * "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
			 * +
			 * "                                  and A.iCveMuestra = M.iCveMuestra  "
			 * +
			 * "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
			 * +
			 * "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
			 * +
			 * "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
			 * +
			 * "                                        and LC.iCveExamCualita = 1  "
			 * + "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
			 * +
			 * "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
			 * +
			 * "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
			 * +
			 * "                         and ER.iCveExamCualita = LC.iCveExamCualita"
			 * +
			 * "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
			 * + "                         and ER.iCveSustancia   = " + cCveCann
			 * + "          where M.iCveProceso = P.iCveProceso  " +
			 * "            and M.iCveMotivo  @Motivo  " +
			 * "            and LC.dtProcesado between @Periodo) as MUESTRA_CANN, "
			 * + "        (select count(1)  " + "          from TOXMuestra M   "
			 * +
			 * "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
			 * +
			 * "                                  and A.iCveMuestra = M.iCveMuestra  "
			 * +
			 * "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
			 * +
			 * "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
			 * +
			 * "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
			 * +
			 * "                                        and LC.iCveExamCualita = 1  "
			 * + "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
			 * +
			 * "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
			 * +
			 * "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
			 * +
			 * "                         and ER.iCveExamCualita = LC.iCveExamCualita"
			 * +
			 * "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
			 * + "                         and ER.iCveSustancia   = " + cCveCoc
			 * + "          where M.iCveProceso = P.iCveProceso  " +
			 * "            and M.iCveMotivo  @Motivo  " +
			 * "            and LC.dtProcesado between @Periodo) as MUESTRA_COC, "
			 * + "        (select count(1)  " + "          from TOXMuestra M   "
			 * +
			 * "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
			 * +
			 * "                                  and A.iCveMuestra = M.iCveMuestra  "
			 * +
			 * "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
			 * +
			 * "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
			 * +
			 * "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
			 * +
			 * "                                        and LC.iCveExamCualita = 1  "
			 * + "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
			 * +
			 * "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
			 * +
			 * "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
			 * +
			 * "                         and ER.iCveExamCualita = LC.iCveExamCualita"
			 * +
			 * "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
			 * + "                         and ER.iCveSustancia   = " + cCveOpio
			 * + "          where M.iCveProceso = P.iCveProceso  " +
			 * "            and M.iCveMotivo  @Motivo  " +
			 * "            and LC.dtProcesado between @Periodo) as MUESTRA_OPIO,  "
			 * + "        (select count(1)  " + "          from TOXMuestra M   "
			 * +
			 * "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
			 * +
			 * "                                  and A.iCveMuestra = M.iCveMuestra  "
			 * +
			 * "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
			 * +
			 * "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
			 * +
			 * "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
			 * +
			 * "                                        and LC.iCveExamCualita = 1  "
			 * + "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
			 * +
			 * "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
			 * +
			 * "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
			 * +
			 * "                         and ER.iCveExamCualita = LC.iCveExamCualita"
			 * +
			 * "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
			 * + "                         and ER.iCveSustancia   = " + cCvePCP
			 * + "          where M.iCveProceso = P.iCveProceso  " +
			 * "            and M.iCveMotivo  @Motivo  " +
			 * "            and LC.dtProcesado between @Periodo) as MUESTRA_PCP, "
			 * + "        (select count(1)  " + "          from TOXMuestra M   "
			 * +
			 * "          inner join TOXAnalisis A on A.iAnio       = M.iAnio   "
			 * +
			 * "                                  and A.iCveMuestra = M.iCveMuestra  "
			 * +
			 * "          inner join TOXExamenCualita LC on LC.iAnio = A.iAnio  "
			 * +
			 * "                                        and LC.iCveLaboratorio = A.iCveLaboratorio  "
			 * +
			 * "                                        and LC.iCveLoteCualita = A.iCveLoteCualita  "
			 * +
			 * "                                        and LC.iCveExamCualita = 1  "
			 * + "          inner join TOXExamResult ER on ER.iAnio = A.iAnio "
			 * +
			 * "                         and ER.iCveLaboratorio = A.iCveLaboratorio "
			 * +
			 * "                         and ER.iCveLoteCualita = A.iCveLoteCualita "
			 * +
			 * "                         and ER.iCveExamCualita = LC.iCveExamCualita"
			 * +
			 * "                         and ER.iCveAnalisis    = A.iCveAnalisis    "
			 * + "                         and ER.iCveSustancia   = " + cCveMeta
			 * + "          where M.iCveProceso = P.iCveProceso  " +
			 * "            and M.iCveMotivo  @Motivo  " +
			 * "            and LC.dtProcesado between @Periodo) as MUESTRA_META "
			 */

			/*
			 * "  from GRLProceso P  " + "  where P.iCveProceso = @Proceso ";
			 */
			String cSentencia = "";
			// Sustituir las constantes seg�n propiedades
			cMuestra = cMuestra
					.replaceAll("@CDI",
							this.VParametros.getPropEspecifica("TOXInfAn_CDI"))
					.replaceAll(
							"@CENMA",
							this.VParametros
									.getPropEspecifica("TOXInfAn_CENMA"))
					.replaceAll(
							"@TipoRecep",
							this.VParametros
									.getPropEspecifica("TOXInfAn_TipoRecep"))
					.replaceAll(
							"@Modo1",
							this.VParametros
									.getPropEspecifica("TOXInfAn_Aereo"))
					.replaceAll(
							"@Modo2",
							this.VParametros
									.getPropEspecifica("TOXInfAn_Autotrans"))
					.replaceAll(
							"@Modo3",
							this.VParametros
									.getPropEspecifica("TOXInfAn_Maritimo"))
					.replaceAll(
							"@Modo4",
							this.VParametros
									.getPropEspecifica("TOXInfAn_Ferroviario"))
					.replaceAll("@Periodo", cCondicion);
			// Generar las consultas para cada proceso.
			for (int i = 1; i <= 4; i++) {
				hCamposQ1.clear();
				hCamposQ2.clear();
				switch (i) {
				case 1: // Examen Psicof�sico Integral
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" not in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotRevalora"));// EXAMEN
					hCamposQ1.put(cSentencia, "");
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotRevalora"));// REVALORACION
					hCamposQ2.put(cSentencia, "");
					break;
				case 2: // Examen M�dico en Operaci�n
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" not in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotOperativo"));// MODULO
					hCamposQ1.put(cSentencia, "");
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotOperativo"));// OOPERATIVO
					hCamposQ2.put(cSentencia, "");
					break;
				case 3: // Examen Toxicol�gico
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotSeguimiento"));
					hCamposQ1.put(cSentencia, "");
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" not in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotSeguimiento"));
					hCamposQ2.put(cSentencia, "");
					break;
				case 4: // Investigaci�n de Accidentes
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotAccidente"));
					hCamposQ1.put(cSentencia, "");
					cSentencia = cMuestra
							.replaceAll(
									"@Motivo",
									" not in "
											+ this.VParametros
													.getPropEspecifica("TOXInfAn_MotAccidente"));
					hCamposQ2.put(cSentencia, "");
					break;
				}
				qSentencia = new QueryStructure(hCamposQ1, "GRLProceso P ",
						" P.iCveProceso = " + i, QueryStructure.SELECT);
				aQueryU.add(qSentencia);
				qSentencia = new QueryStructure(hCamposQ2, "GRLProceso P ",
						" P.iCveProceso = " + i, QueryStructure.SELECT);
				aQueryU.add(qSentencia);
				/*
				 * if (i == 2) { // System.out.println(
				 * "===================================================="); //
				 * System.out.println("IVAN SELECT " + cSentencia); //
				 * System.out
				 * .println("===================================================="
				 * ); }
				 */
			}

			// System.out.println(cMuestra);
			// System.out.println();

			// Ejecutar las sentencias
			vResultado = qQuery.manageTransaction(aQueryU);
		} catch (Exception ex) {
			ex.printStackTrace();
			warn("findInfoGeneral", ex);
			throw new DAOException("findInformeAnalisis");
		}
		return vResultado;
	}

	/**
	 * Metodo Find By All
	 * 
	 * @author: AG SA
	 * @param: cCvePersona - Clave del Personal en Caracter. Incluye Join con
	 *         las Direcciones
	 */
	public int FindByAllM(String cCvePersona) throws DAOException {
		DbConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int existe = 0;
		int count = 0;
		try {
			dbConn = new DbConnection(dataSourceName);
			conn = dbConn.getConnection();
			String cSQL = "";
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(2);

			cSQL = "SELECT COUNT(ICVEPERSONAL) FROM TOXMUESTRA "
					+ " WHERE ICVEPERSONAL = " + cCvePersona;

			pstmt = conn.prepareStatement(cSQL);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				count = rset.getInt(1);
			}

			if (count > 0) {
				// Existe registros
				existe = 1;
				// System.out.println(existe);
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
			return existe;
		}
	}

	/**
	 * Met�do que regresa la informaci�n de las muestras confirmadas, se
	 * filtra la informaci�n por lote confirmatorio y env�o de la unidad
	 * m�dica.
	 * 
	 * @param connE
	 *            Conexi�n a la base de datos.
	 * @param OLote
	 *            Objeto TVTOXLoteCuantita con la informaci�n del lote que se
	 *            est� analizando
	 * @param OEnvio
	 *            Objeto TVTOXEnvio con la informaci�n del Env�o a
	 *            consultar.
	 * @return Regresa el vector de objetos TVMuestra con la informaci�n del
	 *         query.
	 * @throws DAOException
	 */
	public Vector MuestrasConf2(Connection connE, Object OLote, Object OEnvio)
			throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Vector vcTOXMuestra = new Vector();
		StringBuffer cSQL;
		TVMuestra VMuestra;
		try {
			if (connE == null) {
				DbConnection dbConn = null;
				dbConn = new DbConnection(dataSourceName);
				conn = dbConn.getConnection();
			} else
				conn = connE;

			// Query
			cSQL = new StringBuffer();
			cSQL.append(
					" select CA.iAnio, CA.iCveLaboratorio, CA.iCveSustancia, ")
					.append("        CA.iCveLoteCuantita, CA.iCveAnalisis, ")
					.append("        A.lResultado, ")
					.append("        M.iCveMuestra, P.cDscProceso ")
					.append(" from TOXCuantAnalisis CA ")
					.append(" inner join TOXAnalisis A on A.iAnio           = CA.iAnio ")
					.append("                         and A.iCveLaboratorio = CA.iCveLaboratorio ")
					.append("                         and A.iCveAnalisis    = CA.iCveAnalisis ")
					.append(" inner join TOXMuestra M on M.iAnio = A.iAnio ")
					.append("                        and M.iCveMuestra = A.iCveMuestra ")
					.append(" inner join GRLProceso P on P.iCveProceso = M.iCveProceso ")
					.append(" where CA.iAnio            = ")
					.append(((TVTOXLoteCuantita) OLote).getiAnio().toString())
					.append("   and CA.iCveLaboratorio  = ")
					.append(((TVTOXLoteCuantita) OLote).getiCveLaboratorio()
							.toString())
					.append("   and CA.iCveSustancia    = ")
					.append(((TVTOXLoteCuantita) OLote).getiCveSustancia()
							.toString())
					.append("   and CA.iCveLoteCuantita = ")
					.append(((TVTOXLoteCuantita) OLote).getiCveLoteCuantita()
							.toString())
					.append("   and CA.lAutorizado      = 1 ")
					.append("   and CA.lCorrecto        = 1 ")
					.append("   and A.iSustPost         <= A.iSustConf ")
					.append("   and A.lResultado        = ")
					.append(((TVTOXEnvio) OEnvio).getLResultado())
					// .append("   and M.iCveUniMed        = ").append(((TVTOXEnvio)
					// OEnvio).getICveUniMed())
					// .append("   and M.iCveEnvio         = ").append(((TVTOXEnvio)
					// OEnvio).getICveEnvio())
					.append(" order by CA.iCveAnalisis ");
			// Ejecutar el query
			System.out.println("Muestra Conf --> \n" + cSQL.toString());
			pstmt = conn.prepareStatement(cSQL.toString());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				VMuestra = new TVMuestra();
				// Obtener informaci�n
				VMuestra.setIAnio(rset.getInt(1));
				VMuestra.setICveLaboratorio(rset.getInt(2));
				VMuestra.setICveAnalisis(rset.getInt(5));
				VMuestra.setLResultado(rset.getInt(6));
				VMuestra.setICveMuestra(rset.getInt(7));
				VMuestra.setCDscProceso(rset.getString(8));
				// Consultar los resultados de las muestras positivas
				if (VMuestra.getLResultado() == 1) {
					Vector VResultado = new TDTOXLoteCuantita().SustPost(conn,
							VMuestra);
					VMuestra.vResultado = VResultado;
				}
				// Agregar las muestras
				vcTOXMuestra.add(VMuestra);
			}

		} catch (Exception ex) {
			warn("MuestrasConf", ex);
			ex.printStackTrace();
			throw new DAOException("MuestrasConf");
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
				warn("MuestrasConf.close", ex2);
			}
			return vcTOXMuestra;
		}
	}

}
